
	var svg = d3.select("svg"),
	    margin = 100,
	    diameter = +svg.attr("width"),  //直径等于<svg>的属性width值
	    //<svg>中添加<g>标签
	    g = svg.append("g").attr("transform", "translate(" + diameter / 2 + "," + (diameter / 2 -180) + ")");

	var color = d3.scaleLinear()
	    .domain([-1, 5])
	    .range(["hsl(0,30%,100%)", "hsl(188,50%,60%)"])
	    .interpolate(d3.interpolateHcl); //在两个HCL颜色间插值

	var pack = d3.pack()
	    .size([diameter - margin, diameter - margin])  //布局尺寸，宽*高
	    .padding(2);  //各圆形间边距

	d3.json("/allKeywords/showSvg", function(error, root) {
	  if (error) throw error;

	  root = d3.hierarchy(root)
	      .sum(function(d) { return d.size; })
	      .sort(function(a, b) { return b.value - a.value; });

	  var focus = root,
	      nodes = pack(root).descendants(), //计算节点数组，递减排列
	      view;

	  var circle = g.selectAll("circle")  //添加足够数量的圆形元素
	    .data(nodes)
	    .enter().append("circle")
	      .attr("class", function(d) { return d.parent ? d.children ? "node" : "node node--leaf" : "node node--root"; })
	      .style("fill", function(d) { return color(d.depth); })
	      .on("mouseover", function(d) { if ((focus !== d)&& d.children) zoom(d), d3.event.stopPropagation(); })
	      .on("click",function(d){
	      	if(!d.children)
	      		document.getElementById("keyword-right").innerHTML=d.data.name;
	      		document.getElementById("paper-left").innerHTML=d.data.name;
				var paramName = d.data.name;
				d3.json("/allKeywords/showSimilarKeywords?name=" + encodeURIComponent(paramName), function (error, root) {
                    var keywordsList = $("#keyword-right-group").empty();
					for(var i=0; i<5; i++){
						$("<a href=\"#\" class=\"list-group-item\">" + root[i].name + "</a>").appendTo(keywordsList);
					}
                });
			    d3.json("/allKeywords/showRelatedPapers?name=" + encodeURIComponent(paramName), function (error, root) {
                    var papersList = $("#paper-left-group").empty();
                    for(var i=0; i<5; i++){
                        $("<a href=\"#\" class=\"list-group-item\">" + root[i].title + "</a>").appendTo(papersList);
                    }
			    });
	      });


	  var text = g.selectAll("text")  //添加节点文字元素
	    .data(nodes)
	    .enter().append("text")
	      .attr("class", "label")
	      .style("fill-opacity", function(d) { return d.parent === root ? 1 : 0; })
	      .style("display", function(d) { return d.parent === root ? "inline" : "none"; })
	      .text(function(d) { return d.data.name; });


	  var node = g.selectAll("circle,text");

	  svg
	      .style("background", color(-1));
	      //.on("click", function(d) { zoom(root); });  //返回动画



	  zoomTo([root.x, root.y, root.r * 3.1 + margin ]);  //页面加载完成初始位置

	  function zoom(d) {
	    var focus0 = focus;
	    focus = d;

	    var transition = d3.transition()  //创建一个过渡对象
	        .duration(d3.event.altKey ? 7500 : 2500)  //按alt点击移动时间为前一个，否则后一个
	        //将属性zoom按照后面的函数过渡
	        .tween("zoom", function(d) {
	        	//在两个点之间平滑的缩放平移，从动画开始位置到停止位置
	          var i = d3.interpolateZoom(view, [focus.x, focus.y, focus.r * 3.1 + margin]);
	          return function(t) { zoomTo(i(t)); };
	        });

	    transition.selectAll("text")
	      .filter(function(d) { return d.parent === focus || this.style.display === "inline"; })
	        .style("fill-opacity", function(d) { return  (d.parent === focus) ? 1 : 0 ; })
	        .on("start", function(d) {
	        	//if(!d) d.parent.style.display="inline;"
	        	if (d.parent === focus)	this.style.display = "inline";

	        })
	        .on("end", function(d) {
	        	if (d.parent !== focus) this.style.display = "none";
	        	//if(!d) this.parent.style.display="inline";
	        });
	        //.on("click",function(d){document.getElementById("keyword-right").innerHTML=d.data.name });

	  }

	  //当前view缩放到下一个view的转换
	  function zoomTo(v) {
	    var k = diameter / v[2]; view = v;
	    node.attr("transform", function(d) { return "translate(" + (d.x - v[0]) * k + "," + (d.y - v[1]) * k + ")"; });
	    circle.attr("r", function(d) { return d.r * k; });
	  }
	});

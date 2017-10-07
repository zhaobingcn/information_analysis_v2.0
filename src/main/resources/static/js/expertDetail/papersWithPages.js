/**
 * Created by hexu on 2016/12/7.
 */
var thePageSize = 8;
var theCurrentPage = 1;
var authorName = $("#authorsName").text();
var authorInstitution = $("#authorsInstitution").val();
var authorPapersCount = Number($("#authorsPapersCount").val());
var allPagesCount = parseInt(authorPapersCount / thePageSize);
var mod = authorPapersCount % thePageSize;
if(mod > 0){
    allPagesCount = allPagesCount + 1;
}

document.getElementById("page-button-one").onclick = function () {
    if(theCurrentPage > 1){
        loadPreviousPage(thePageSize, theCurrentPage, authorName, authorInstitution);
    }
    setButtonValue();
}

document.getElementById("page-button-two").onclick = function () {
    if(theCurrentPage < allPagesCount){
        loadNextPage(thePageSize, theCurrentPage, authorName, authorInstitution);
    }
    setButtonValue();
}

function setButtonValue() {
    if(theCurrentPage > 1){
        document.getElementById("page-button-one").innerHTML = "上一页";
    } else{
        document.getElementById("page-button-one").innerHTML = "首页";
    }
    if(theCurrentPage < allPagesCount){
        document.getElementById("page-button-two").innerHTML = "下一页";
    }else{
        document.getElementById("page-button-two").innerHTML = "尾页";
    }
}

function loadPreviousPage(pageSize, currentPage, name, institution) {
    var skip = (currentPage-2) * pageSize;
    var limit = pageSize;
    theCurrentPage = theCurrentPage -1;
    $.ajax({
       url: "/analysis/detailOfExpert/papersWithPages",
       type: "get",
       dataType: "json",
       data: {
           "name" : name,
           "institution" : institution,
           "skip": skip,
           "limit": limit
       },
       success: function (papers) {
            var papersData = papers.data;
            var len = papersData.length;
            var $papersList = $("#paper-list-div").empty();
            for(var i=0; i<len; i++){
                $papersList.append(
                "<a href= \"#\" class=\"list-group-item\">" +
                    "<i class=\"fa fa-star fa-fw\"></i>" + papersData[i].title +
                    "<span class=\"pull-right text-muted small\">" + "引用量：" + "<em>" + papersData[i].quote + "次" + "</em>" +
                "</span>" +
                "</a>"
                );
            }
       }
    });
}

function loadNextPage(pageSize, currentPage, name, institution) {
    var skip = currentPage * pageSize;
    var limit = pageSize;
    theCurrentPage = theCurrentPage +1;
    $.ajax({
        url: "/analysis/detailOfExpert/papersWithPages",
        type: "get",
        dataType: "json",
        data: {
            "name" : name,
            "institution" : institution,
            "skip": skip,
            "limit": limit
        },
        success: function (papers) {
            var papersData = papers.data;
            var len = papersData.length;
            var $papersList = $("#paper-list-div").empty();
            for(var i=0; i<len; i++){
                $papersList.append(
                    "<a href=\"" + papersData[i].link + "\" class=\"list-group-item\">" +
                    "<i class=\"fa fa-star fa-fw\"></i>" + papersData[i].title +
                    "<span class=\"pull-right text-muted small\">" + "引用量：" + "<em>" + papersData[i].quote + "次</em>" +
                    "</span>" +
                    "</a>"
                );
            }
        }
    });
}
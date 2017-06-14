/**
 * Created by zhzy on 2016/12/25.
 */
var authors;
var theCurrentPage = 0;
var allPagesCount = 3;
var thePageSize = 6;
document.getElementById("searchKeywords").onclick = function () {
    clear();
    var name = $("#keywordName").val();
    if(name==""){
        return;
    }
    if(!onSearchCheck()){
        return;
    }
    keywords = getQueryData();
    theCurrentPage = 0;
    loadNextPage(theCurrentPage);
}

document.getElementById("query-result-button-one").onclick = function () {
    if(theCurrentPage > 1){
        loadPreviousPage(theCurrentPage);
    }
    setButtonValue();
}

document.getElementById("query-result-button-two").onclick = function () {
    if(theCurrentPage < allPagesCount){
        loadNextPage(theCurrentPage);
    }
    setButtonValue();
}

function setButtonValue() {
    if(theCurrentPage > 1){
        document.getElementById("query-result-button-one").innerHTML = "上一页";
    } else{
        document.getElementById("query-result-button-one").innerHTML = "首页";
    }
    if(theCurrentPage < allPagesCount){
        document.getElementById("query-result-button-two").innerHTML = "下一页";
    }else{
        document.getElementById("query-result-button-two").innerHTML = "尾页";
    }
}

function getQueryData() {
    var name = $("#keywordName").val();
    if(checkSpaceInStr(name)){
        name = name.replace(/\s/g, "");
    }
    var keywordsDetail;
    $.ajax({
        url : "/queryOfKeyword/commitQuery",
        data : {"name": name},
        dataType: "json",
        async : false,
        success : function (keywordsData) {
            keywordsDetail = keywordsData;
        }
        }
    );
    return keywordsDetail;
}

function loadPreviousPage() {
    theCurrentPage = theCurrentPage - 1;
    var keywordsList = $("#keyword-list-div").empty();
    for(var i=(theCurrentPage-1) * thePageSize; i<theCurrentPage * thePageSize; i++){
        $(
            "<div class=\"col-lg-6 col-sm-12\">" +
            "<div class=\"panel panel-default\">" +
            "<div class=\"panel-heading\" style='height: 75px'>" +
            "<div class=\"row\">" +
            "<div class=\"col-lg-2 col-xs-2\" style=\"text-align: center\">" +
            "<i class=\"fa fa-star fa-4x\"></i>" +
            "</div>" +
            "<div class=\"col-lg-10 col-xs-10\">" +
            "<div id=\"small_stats\" class=\"cf\">" +
            "<div class=\"stat\"> <i class=\"fa fa-link\"></i> <span class=\"value\">" + keywords[i].involvetimes + "</span> </div>" +
            "<div class=\"stat\"> <i class=\"fa fa-star\"></i> <span class=\"value\">" + keywords[i].keyword.name + "</span> </div>" +
            "<input type='hidden' value= '"+ keywords[i].keyword.name + "' id='queryKeywordId'>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>"
        ).appendTo(keywordsList).click(function () {
            var name = $(this).find("#queryKeywordId").val();
            window.open("/keywordDetail?name=" + name);
        });
    }
}

function loadNextPage() {
    theCurrentPage = theCurrentPage + 1;
    var keywordsList = $("#keyword-list-div").empty();
    for(var i=(theCurrentPage-1) * thePageSize; i< theCurrentPage * thePageSize; i++){
        $(
            "<div class=\"col-lg-6 col-sm-12\">" +
            "<div class=\"panel panel-default\">" +
            "<div class=\"panel-heading\" style='height: 75px'>" +
            "<div class=\"row\">" +
            "<div class=\"col-lg-2 col-xs-2\" style=\"text-align: center\">" +
            "<i class=\"fa fa-star fa-4x\"></i>" +
            "</div>" +
            "<div class=\"col-lg-10 col-xs-10\">" +
            "<div id=\"small_stats\" class=\"cf\">" +
            "<div class=\"stat\"> <i class=\"fa fa-link\"></i> <span class=\"value\">" + keywords[i].involvetimes + "</span> </div>" +
            "<div class=\"stat\"> <i class=\"fa fa-star\"></i> <span class=\"value\">" + keywords[i].keyword.name + "</span> </div>" +
            "<input type='hidden' value= '"+ keywords[i].keyword.name + "' id='queryKeywordId'>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>"
        ).appendTo(keywordsList).click(function () {
            var name = $(this).find("#queryKeywordId").val();
            window.open("/keywordDetail?name=" + name);
        });
    }
}

//正则匹配非法字符
function isSearch(s)
{
    var patrn=/[^`￥！……&×（）《》：/、~!@#$%^&*()+=|\\\][\]\{\}:;'\,.<>/?]*[`￥！……&×（）《》：/、~!@#$%^&*()+=|\\\][\]\{\}:;'\,.<>/?]/;
    if (!patrn.exec(s)) return false
    return true
}
//对输入框进行校验，校验成功返回true
function onSearchCheck()
{
    var flag = true;
    var name = $("#keywordName").val();

    if(isSearch(name )){
        document.getElementById('warning').style.display='block';
        flag =false;
    }

    return flag;
}
//将提示信息和搜索结果框重新隐藏起来
function clear() {

    document.getElementById('warning').style.display='none';

}
//检测字符串中是否有空格
function checkSpaceInStr(str) {
    var indexOfSpace = str.indexOf(" ");
    if(indexOfSpace >= 0){
        return true;
    }
    return false;
}
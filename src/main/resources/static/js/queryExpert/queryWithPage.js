/**
 * Created by zhzy on 2016/12/25.
 */
var authors;
var theCurrentPage = 0;
var allPagesCount = 3;
var thePageSize = 6;
document.getElementById("searchAuthors").onclick = function () {
    authors = getQueryData();
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
    var name = $("#authorsName").val();
    var institution = $("authorsInstitution").val();
    var authorsDetail;
    $.ajax({
        url : "/queryOfExpert/commitQuery",
        data : {"name": name,
                "institution": institution},
        dataType: "json",
        async : false,
        success : function (authorsData) {
            authorsDetail = authorsData;
        }
        }
    );
    return authorsDetail;
}

function loadPreviousPage() {
    theCurrentPage = theCurrentPage - 1;
    $authorsList = $("#author-list-div").empty();
    for(var i=(theCurrentPage-1) * thePageSize; i<theCurrentPage * thePageSize; i++){
        $authorsList.append(
        "<div class=\"col-lg-6 col-sm-12\">" +
            "<div class=\"panel panel-default\">" +
                "<div class=\"panel-heading\">" +
                    "<div class=\"row\">" +
                        "<div class=\"col-xs-2\" style=\"text-align: center\">" +
                        "<i class=\"fa fa-user fa-4x\"></i>" +
                        "<i class=\"fa fa-plus\" id=\"add\"></i>" +
                        "</div>" +
                        "<div class=\"col-lg-5 col-md-5\">" +
                        "<a href=\"detailOfExpert.vm\">" +
                        "<div>" +
                        "<span style=\"font-size: large\">" + authors[i].name + "</span>" +
                        "</div>" +
                        "</a>" +
                        "<div>" +
                        "<span style=\"font-size: large\">" + authors[i].institution + "</span>" +
                        "</div>" +
                        "</div>" +
                        "<div class=\"col-lg-5 col-md-5\">" +
                        "<div>" +
                        "<span style=\"font-size: large\">发表文章：</span>" +
                        "<span style=\"font-size: large\">" + authors[i].papersCount + "</span>" +
                        "</div>" +
                        "<div>" +
                        "<span style=\"font-size: large\">被引用次数：</span>" +
                        "<span style=\"font-size: large\">" + authors[i].quoteCount + "</span>" +
                        "</div>" +
                        "</div>" +
                    "</div>" +
                "</div>" +
            "</div>" +
            "</div>"
        );
    }
}

function loadNextPage() {
    theCurrentPage = theCurrentPage + 1;
    $authorsList = $("#author-list-div").empty();
    for(var i=(theCurrentPage-1) * thePageSize; i< theCurrentPage * thePageSize; i++){
        $authorsList.append(
            "<div class=\"col-lg-6 col-sm-12\">" +
            "<div class=\"panel panel-default\">" +
            "<div class=\"panel-heading\">" +
            "<div class=\"row\">" +
            "<div class=\"col-xs-2\" style=\"text-align: center\">" +
            "<i class=\"fa fa-user fa-4x\"></i>" +
            "<i class=\"fa fa-plus\" id=\"add\"></i>" +
            "</div>" +
            "<div class=\"col-lg-5 col-md-5\">" +
            "<a href=\"detailOfExpert.vm\">" +
            "<div>" +
            "<span style=\"font-size: large\">" + authors[i].name + "</span>" +
            "</div>" +
            "</a>" +
            "<div>" +
            "<span style=\"font-size: large\">" + authors[i].institution + "</span>" +
            "</div>" +
            "</div>" +
            "<div class=\"col-lg-5 col-md-5\">" +
            "<div>" +
            "<span style=\"font-size: large\">发表文章：</span>" +
            "<span style=\"font-size: large\">" + authors[i].papersCount + "</span>" +
            "</div>" +
            "<div>" +
            "<span style=\"font-size: large\">被引用次数：</span>" +
            "<span style=\"font-size: large\">" + authors[i].quoteCount + "</span>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>"
        );
    }
}
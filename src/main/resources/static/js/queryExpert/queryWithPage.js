/**
 * Created by zhzy on 2016/12/25.
 */

var authors;
document.getElementById("searchAuthors").onclick = function () {
    authors = getQueryData();
    goPage(1, 6)
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

function goPage(pno, psize) {
    var allPageCount = 3;

}
/**
 * Created by zhzy on 17-4-5.
 */

function comparsionPage() {

    var id1 = $("#addprofessor").children().first().find("#queryAuthorId").val()
    var id2 = $("#addprofessor").children().last().find("#queryAuthorId").val()
    window.open("/ComparisonofExpert/commitComparison?id1="+id1+"&&id2="+id2);
}

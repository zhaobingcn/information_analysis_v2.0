/**
 * Created by zhzy on 17-4-5.
 */

function comparsionPage() {
    document.getElementById('warning').style.display='none';
    var id1 = $("#addprofessor").children().first().find("#queryAuthorId").val()
    var id2 = $("#addprofessor").children().last().find("#queryAuthorId").val()
    if(id1 == id2){
        document.getElementById('warning').style.display='block';
    }
    if($("#addprofessor").children().length >= 2 && id1 != id2){
        window.open("/analysis/ComparisonOfExpert/commitComparison?id1="+id1+"&&id2="+id2);
    }
}

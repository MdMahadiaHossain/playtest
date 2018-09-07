/// <reference path ="jquery.js"/>

$( document ).ready(function() {
    $("button").click(function(){
        $.ajax({url: "/ajxcall", success: function(result){
            $("#demo").html(result);
        } });
    
    });
});

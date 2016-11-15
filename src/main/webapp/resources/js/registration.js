/**
 * Created by romab on 10/3/16.
 */



$(document).ready(function () {

    var errors = []

    $("#firstName").change(function () {
        if ($("#firstName").val().length < 2) {
            $("#warningMessage").css("display", "block");
            $("#warningMessage p").text("Name is too short");
            
        }
    })


});



















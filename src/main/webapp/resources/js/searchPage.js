/**
 * Created by romab on 10/23/16.
 */

// autocompletion for search page
$(document).ready(function() {

    fullNamesAutocomplete();


    $(document).on('click', '.dropdown-menu', function (e) {
        e.stopPropagation();
    });

    $('select').on('change input', changePlaceHolder);
    
    function changePlaceHolder() {
        if ($(this).val() == "byFullName") {
            $(myInput1).attr("placeholder","Start to type user name");
            fullNamesAutocomplete();
        }
        // if ($(this).val() == "login") {
        //     $(myInput1).attr("placeholder","Search by login");
        // }
        if ($(this).val() == "byDiscipline") {
            $(myInput1).attr("placeholder","Start to type discipline name");
            disciplinesAutocomplete();
        }
    }

    $('#btnSearch').prop("disabled", true);

    $(function() {
        $("input[id='myInput1']").keyup(function countRemainingChars(){
            var number = $("input[id='myInput1']").val().length;
            if(number > 0){
                $('#btnSearch').prop("disabled", false);
            }
            if(number == 0){
                $('#btnSearch').prop("disabled", true);
                
            }
        });
    });
});

function disciplinesAutocomplete() {
    $("#myInput1").autocomplete({
        source : function(request, response) {
            $.ajax({
                url : "/autocomplete/searchPage",
                type : "GET",
                data : {
                    disciplines: request.term
                },
                dataType : "json",
                success : function(data) {
                    response(data);
                }
            });
        }
    });
    // $(".ui-autocomplete").css("z-index", "2147483647");
}

function fullNamesAutocomplete() {
    $("#myInput1").autocomplete({
        source : function(request, response) {
            $.ajax({
                url : "/autocomplete/searchPage",
                type : "GET",
                data : {
                    users: request.term
                },
                dataType : "json",
                success : function(data) {
                    response(data);
                }
            });
        }
    });
}








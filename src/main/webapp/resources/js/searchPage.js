/**
 * Created by romab on 10/23/16.
 */

// autocompletion for search page
$(document).ready(function () {

    fullNamesAutocomplete();

    $(document).on('click', '.dropdown-menu', function (e) {
        e.stopPropagation();
    });

    $('select').on('change input', changePlaceHolder);


    $("#btnSearch").on("click", sendSearchRequest);


    $('#btnSearch').prop("disabled", true);

    $(function () {
        $("input[id='myInput1']").keyup(function countRemainingChars() {
            var number = $("input[id='myInput1']").val().length;
            if (number > 0) {
                $('#btnSearch').prop("disabled", false);
            }
            if (number == 0) {
                $('#btnSearch').prop("disabled", true);

            }
        });
    });
});

function sendSearchRequest() {

    var requestParameters = {
        searchOption: $("#searchOption").val(),
        userTypeOption: $("#userTypeOption").val(),
        searchString: $("#myInput1").val()
    };

    $.ajax({
        type: "POST",
        url: "/api/search",
        data: JSON.stringify(requestParameters),
        contentType: "application/json",
        success: function (response) {
            displayResults(response)
        }
    });
}

function displayResults(response) {

    var resultContainer = $("#resultContainer");
    resultContainer.empty();

    $.each(response, function (key, val) {

        var disciplines = val.disciplines;

        // console.log(val);

        var description = val.description === undefined ? "" : val.description;

        var answerBlock = '<div class="row well result-section"> ' +
            '<div class="col-lg-12 col-md-12 col-sm-12"> ' +
            '<div class="col-lg-2 col-md-2 col-sm-3"> ' +
            '<a href="/profile/' + val.id + '"> ' +
            '<img id="avatar" src="/image/avatar/' + val.id + '"width="140px" height="140px" class="img-circle" alt="Avatar"> ' +
            '</a> </div> ' +
            '<div class="col-lg-2 col-md-2 col-sm-3 name-result"> ' +
            '<p>' + val.firstName + '</p> ' +
            '<p>' + val.lastName + '</p> ' +
            '<p>' + val.userType + '</p> </div> ' +
            '<div class="col-lg-6 col-md-6 col-sm-12 description-bkg"> ' +
            '<pre style="border: none; background: none; margin-bottom: 0; padding: 0%">' + description + '</pre> </div> ' +
            '<div class="col-lg-2 col-md-2 col-sm-12"> ' +
            '<div class="pull-right disciplines-result"> ';

        if (disciplines.length > 0) {

            for (var i = 0; i < disciplines.length; i++) {
                var temp = '<div> <img height="35px" width="35px" src="/image/icon/' + disciplines[i].id + '"> ' +
                    '<p>' + disciplines[i].name + '</p> </div> ';

                answerBlock += temp;
            }

        }
        answerBlock += '</div> </div> </div> </div>';


        resultContainer.append(answerBlock);

    });
}

function changePlaceHolder() {
    if ($(this).val() == "byFullName") {
        $("#myInput1").attr("placeholder", "Start to type user name, autocomplete will help you");
        fullNamesAutocomplete();
    }

    if ($(this).val() == "byDiscipline") {
        $("#myInput1").attr("placeholder", "Start to type discipline name, autocomplete will help you");
        disciplinesAutocomplete();
    }
}

function disciplinesAutocomplete() {

    $("#myInput1").autocomplete({
        source: function (request, response) {
            $.ajax({
                url: "api/autocomplete/disciplines/" + request.term,
                type: "GET",
                dataType: "json",
                success: function (data) {
                    response(data);
                }
            });
        }
    });


}

function fullNamesAutocomplete() {

    $("#myInput1").autocomplete({
        source: function (request, response) {
            $.ajax({
                url: "api/autocomplete/full_names/" + request.term,
                type: "GET",
                dataType: "json",
                success: function (data) {
                    response(data);
                }
            });
        }

    });

}








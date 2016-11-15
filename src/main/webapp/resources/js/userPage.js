/**
 * Created by romab on 10/1/16.
 */

// autocompletion for search page
$(document).ready(function () {

    $(function () {
        $.ajax({
            url: "/api/disciplinesToAdd/" + currentUserId + "",
            dataType: "json",
            success: function (data) {
                fillAddList(data)
            }
        });
    });

    var src = $("#avatar").attr("src");
    var currentUserId = src.split("/")[3];

    $(function () {
        $.ajax({
            url: "/api/feedbacks/" + currentUserId + "",
            dataType: "json",
            success: function (data) {
                fillFeedbackList(data)
            }
        });
    });

    $(function () {
        $.ajax({
            url: "/api/disciplines/" + currentUserId + "",
            dataType: "json",
            success: function (data) {
                fillDisciplinesList(data)
            }
        });
    });

    $("#add").on('click',function () {
        console.log($("#discToAdd").val())
    })


    
    

});


function fillFeedbackList(data) {

    var feedbacksDiv = $("#feedbacks");

    $.each(data, function (key, val) {

        console.log(val.authorFirstName);

        var feedbackBlock = '<div class="col-lg-12"> ' +
            '<div  class="col-lg-3"> ' +
            '<div class="well"> <p id="authorName">' + val.authorFirstName + '</p> ' +
            '<a href="/profile/' + val.authorId + '"> ' +
            '<img src="/image/avatar/' + val.authorId + '" class="img-circle" height="65"width="65" alt="Avatar"> </a> </div> </div> ' +
            '<div class="col-lg-9"> ' +
            '<div class="well"> ' +
            '<p id="feedbackText" class="text-left">' + val.text + '</p> </div> </div> </div>';

        feedbacksDiv.append(feedbackBlock);


    });

}


function fillAddList(data) {

    var options = $("#discToAdd");
    $.each(data, function (key, val) {
        options.append('<option>' + val + '</option>');
    });

}


var usersDisciplines = [];

function fillDisciplinesList(data) {

    var disciplinesDiv = $("#disciplinesList");

    $.each(data, function (key, val) {

        var disciplineLi = '<li class="list-group-item"> ' +
            '<img src="/image/icon/' + val.id + '" ' +
            'height="35" width="35" alt="icon">' + val.name + ' </li>';

        disciplinesDiv.append(disciplineLi);

        usersDisciplines.push(val);
    });
    
    fillRemoveList();
}

function fillRemoveList () {
    console.log($("#discToRemove").length);

    var discToRemoveList = $("#discToRemove");

    for (var i = 0; i < usersDisciplines.length; i++) {
        var option = '<option>' + usersDisciplines[i].name + '</option>';
        $("#discToRemove").append(option);
    }

}


    



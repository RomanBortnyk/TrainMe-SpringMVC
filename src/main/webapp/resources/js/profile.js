/**
 * Created by romab on 10/27/16.
 */

var currentProfileUserId;

$(document).ready(function() {

    var pathname = window.location.pathname;
    currentProfileUserId = pathname.split("/")[2];
    
    
    $(function () {
        $.ajax({
            url: "/api/disciplines/" + currentProfileUserId + "",
            dataType: "json",
            success: function (data) {
                fillDisciplinesList(data)
            }
        });
    });

    $(function () {
        $.ajax({
            url: "/api/feedbacks/" + currentProfileUserId + "",
            dataType: "json",
            success: function (data) {
                fillFeedbackList(data)
            }
        });
    });

    $("#saveFeedback").on("click", function () {
        
        addNewFeedback();

    });

    $("#sendMessage").on('click', function () {

        sendMessageAndCheck();
    });
    
});


function fillDisciplinesList(data) {

    var disciplinesDiv = $("#disciplinesList");

    $.each(data, function (key, val) {

        var disciplineLi = '<li class="list-group-item"> ' +
            '<img src="/image/icon/' + val.id + '" ' +
            'height="35" width="35" alt="icon">' + val.name + ' </li>';

        disciplinesDiv.append(disciplineLi);

    });
}

function fillFeedbackList(data) {

    var feedbacksDiv = $("#feedbacks");

    $.each(data, function (key, val) {

        console.log(val.authorFirstName);

        var feedbackBlock = '<div class="col-lg-12"> ' +
            '<div  class="col-lg-3"> ' +
            '<div class="well"> <p id="authorName">' + val.authorFirstName + '</p> ' +
            '<a href="/profile/' + val.authorId + '"> ' +
            '<img src="/image/avatar/' + val.authorId + '" class="img-circle" height="65" width="65" alt="Avatar"> </a> </div> </div> ' +
            '<div class="col-lg-9"> ' +
            '<div class="well"> ' +
            '<p id="feedbackText" class="text-left">' + val.text + '</p> </div> </div> </div>';

        feedbacksDiv.append(feedbackBlock);


    });
    
}

function addNewFeedback() {
    var destUsrId = currentProfileUserId;
    var newFeedbackTxt = $("#newFeedbackText").val();

    var data = {
        destinationUserId: destUsrId,
        newFeedbackText: newFeedbackTxt
    };


    $.ajax({
        type: "POST",
        url: "/api/feedback",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (response) {

            $("#newFeedbackTextWindow").modal("hide");

            var authorFirstName = response.authorFirstName;
            var authorId = response.authorId;
            var text = response.text;

            $('<div id="feedbackRow" class="row"> <div class="col-lg-12">' +
                '<div class="col-lg-3">' +
                '<div class="well">' +
                '<p>'+ authorFirstName +'</p>' +
                '<a href="/profile/'+ authorId +'">' +
                '<img src="/image/avatar/' +authorId+ '" class="img-circle" height="65" ' +
                'width="65" alt="Avatar"> </a>' +
                '</div>' +
                '</div>' +
                '<div class="col-lg-9">' +
                '<div class="well">' +
                '<p id="feedbackText" class="text-left">' + text + '</p>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>').insertBefore("#addFeedback");


            $("#newFeedbackText").val("");

        }
    });
}

//function sends a message and  checks does conversation already exist
// if not - creates new conversation with current user
function sendMessageAndCheck() {

    var messageField = $('#newMessageText');
    if (messageField.val() !== "") {

        
        var message = {
            
            //actualy current profile user id
            //because on server side json of this object has chatId field
            chatId: currentProfileUserId,
            
            messageText: messageField.val()
        };

        $.ajax({
            type: "POST",
            url: '/profile/message',
            data: JSON.stringify(message),
            contentType: "application/json",
            success: function (res) {
                   // console.log(res);
                
            }
        });
        messageField.val("");
        $("#newMessage").modal("hide");
    }
}




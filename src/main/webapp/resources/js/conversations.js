/**
 * Created by romab on 11/1/16.
 */

var url;
var sock;
var stomp;
var currentUserLogin;
var activeChatId;

$(document).ready(function () {

    currentUserLogin = $("#login").text();


    url = 'http://' + window.location.host + '/messenger';
    sock = new SockJS(url);
    stomp = Stomp.over(sock);

    stomp.connect({}, function (frame) {
        // console.log('Connected: ' + frame);
        var subscription = stomp.subscribe("/user/queue/messages-updates", function (message) {
            processIncomingMessage(message);
        });

    });


    $(window).resize(function () {
        $h = $(window).outerHeight()
        $('.chat').css({
            height: $h - $('.navbar').outerHeight() - $('footer').outerHeight() - $('.decor-default h6').outerHeight() - $('.answer-add').outerHeight() - 60
        });
        $('.friends-list').css({
            height: $('.chat').outerHeight() + $('.decor-default h6').outerHeight() + 80
        });
    });

    $(window).resize();

    fillChatList();


    $(".chat-users").on("click", ".user", function () {

        if (!$(this).hasClass("activeUser")) {
            $(".chat-users .user").removeClass("activeUser");
            $(this).addClass("activeUser");
            cleanMessageList();
            refreshMessagesList();

            $("#chatTitle").text($(this).find("#firstName").text() + " "
                + $(this).find("#lastName").text());

            activeChatId = $(".activeUser").attr("id");
        }

    });

    $("#sendIcon").on('click', function () {

        var messageText = $("#inputMessageField").val();
        if (messageText !== "" && activeChatId !== undefined) {

            var message = {
                chatId: activeChatId,
                messageText: messageText,
                destinationUserId: 1
            };

            var payload = JSON.stringify(message);
            sendOverWebSocket(payload);
        }


    });

    $('#inputMessageField').keypress(function (event) {
        if (event.keyCode == 13 && activeChatId !== undefined) {
            alert('Entered');
        }
    });


});

function sendOverWebSocket(message) {

    // stomp.send("/app/message", {}, payload);
    // stomp.send('/user/'+currentUserLogin+'/queue/messages-updates',{},message);
    stomp.send('/app/send', {}, message);

    $("#inputMessageField").val("");

}


function fillChatList() {

    $.ajax({
        type: "GET",
        url: '/api/chats/byLogin/' + currentUserLogin,
        dataType: "json",
        success: function (result) {

            var chatListDiv = $("#chatList");

            $.each(result, function (key, val) {

                // console.log(val.id);

                var chatBlock = '<div id="' + val.chatId + '" class="user">' +
                    '<div class="avatar"> ' +
                    '<img src="/image/avatar/' + val.userId + '" alt="User name"> ' +
                    '</div> <div id="firstName" class="name">' + val.firstName + '</div> ' +
                    '<div id="lastName" class="name">' + val.lastName + '</div> </div>';

                chatListDiv.append(chatBlock);

            });
        }
    });
}

function processIncomingMessage(message) {

    console.log(message.text);

    var content = JSON.parse(message.body);

    var id = message.authorId;
    var firstName = message.authorFirstName;
    var lastName = message.authorLastName;
    var text = message.text;
    var chatId = message.chatId;


    if (message.chatId.toString() === activeChatId) {

        var messageBlock = '<div class="answer left ' + (currentUserId === id ? "" : "bkg") + '"> ' +
            '<div class="avatar"> ' +
            '<img src="/image/avatar/' + id + '" alt="User name"> ' +
            '</div> ' +
            '<div class="name">' + firstName + '</div> ' +
            '<div class="text">' + text + '</div> ';

        $('.chat-body').append(messageBlock);

        var objDiv = document.getElementById("chat");
        objDiv.scrollTop = objDiv.scrollHeight;

    }
}


// function send() {
//
//     var messageField = $('#inputMessageField');
//     if (messageField.val() !== "") {
//         var chatId = $(".activeUser").attr("id");
//
//         var message = {
//             chatId: chatId,
//             messageText: messageField.val()
//         };
//
//         $.ajax({
//             url: '/sendEvent/message',
//             method: "POST",
//             data: JSON.stringify(message),
//             contentType: "application/json",
//             success: function (res) {
//                 console.log("message was sent");
//             }
//
//         });
//         messageField.val("");
//     }
//
// }

function cleanMessageList() {
    $(".chat-body").empty()
}

function refreshMessagesList() {

    var currentChatId = $(".activeUser").attr('id');

    $.ajax({
        url: "/api/messages/" + currentChatId,
        type: "GET",
        dataType: "json",
        success: function (data) {

            var activeUserName = $(".activeUser").find("#firstName").text();

            $.each(data, function (index, element) {

                var id = element.authorId;
                var name = element.authorFirstName;
                var text = element.text;

                var large = '<div class="answer left ' + (activeUserName === name ? "bkg" : "") + '"> ' +
                    '<div class="avatar"> ' +
                    '<img src="/image/avatar/' + id + '" alt="User name"> ' +
                    '</div> ' +
                    '<div class="name">' + name + '</div> ' +
                    '<div class="text">' + text + '</div> ';

                $('.chat-body').append(large)

            });

            var objDiv = document.getElementById("chat");
            objDiv.scrollTop = objDiv.scrollHeight;
        }

    });
}







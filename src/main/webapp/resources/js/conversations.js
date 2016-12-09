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

        var inputField = $("#inputMessageField");

        if (inputField.val() !== "" && activeChatId !== undefined) {

            sendOverWebSocket(inputField.val());
            inputField.val("");
        }

        

    });

    $('#inputMessageField').keypress(function (event) {
        var inputField = $("#inputMessageField");
        
        if (event.keyCode == 13 && activeChatId !== undefined && inputField.val() !== "") {
            sendOverWebSocket(inputField.val());
            inputField.val("");
        }
        
    });


});


// var inputMessageField = $("#inputMessageField");

function sendOverWebSocket(messageText) {

    var destinationUserLogin;
    for (var i=0; i< conversations.length; i++){
        if (conversations[i].chatId == activeChatId){
            destinationUserLogin = conversations[i].login;
        }
    }

    var message = {
        chatId: activeChatId,
        messageText: messageText,
        destinationUserLogin: destinationUserLogin
    };

    var payload = JSON.stringify(message);

    stomp.send('/app/send', {}, payload);

    // if(activeChatId != message.chatId){
    //     var messageBlock = '<div class="answer left"> ' +
    //         '<div class="avatar"> ' +
    //         '<img src="/image/avatar/' + id + '" alt="User name"> ' +
    //         '</div> ' +
    //         '<div class="name">' + firstName + '</div> ' +
    //         '<div class="text">' + text + '</div> ';
    //
    //     $('.chat-body').append(messageBlock);
    //
    //     var objDiv = document.getElementById("chat");
    //     objDiv.scrollTop = objDiv.scrollHeight;
    // }


}

var conversations = [];

function fillChatList() {

    $.ajax({
        type: "GET",
        url: '/api/chats/byLogin/' + currentUserLogin,
        dataType: "json",
        success: function (result) {

            var chatListDiv = $("#chatList");

            $.each(result, function (key, val) {

                var chatId = val.id;
                var userId = val.userId;
                var firstname = val.firstname;
                var lastname = val.lastname;
                var login = val.login;

                conversations.push({
                    chatId: chatId,
                    userId: userId,
                    login:login,
                    firstname:firstname,
                    lastname:lastname
                });

                var chatBlock = '<div id="' + chatId + '" class="user">' +
                    '<div class="avatar"> ' +
                    '<img src="/image/avatar/' + userId + '" alt="User name"> ' +
                    '</div> <div id="firstName" class="name">' + firstname + '</div> ' +
                    '<div id="lastName" class="name">' + lastname + '</div> </div>';

                chatListDiv.append(chatBlock);

            });
        }
    });
}

function processIncomingMessage(message) {

    console.log(message.text);

    var content = JSON.parse(message.body);

    var id = content.authorId;
    var firstName = content.authorFirstName;
    var lastName = content.authorLastName;
    var text = content.text;
    var chatId = content.chatId;
    var authorLogin = content.authorLogin;


    if (content.chatId.toString() === activeChatId) {

        var messageBlock = '<div class="answer left ' + (currentUserLogin == authorLogin ? "" : "bkg") + '"> ' +
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
                var authorLogin = element.authorLogin;

                var large = '<div class="answer left ' + (currentUserLogin == authorLogin ? "" : "bkg") + '"> ' +
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







<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Conversations</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <script src="/resources/js/jquery-3.1.1.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/resources/css/font-awesome.min.css">
    <script src="/resources/js/conversations.js"></script>

    <link rel="stylesheet" href="/resources/css/conversations.css">

</head>
<body>
<%@ page isELIgnored="false" %>
<%@include file="header.html" %>
<jsp:useBean id="currentSessionUser" class="trainMe.model.User" scope="session"></jsp:useBean>


<div class="content container-fluid bootstrap snippets">
    <div class="row row-broken">
        <div class="col-sm-3 col-xs-12">
            <div class="col-inside-lg decor-default friends-list" style="outline: none;" tabindex="5000">
                <h6>My conversations</h6>
                <div class="chat">
                    <div id="chatList" class="chat-users" style="padding-top: 10px;">

                    </div>
                </div>

            </div>
        </div>
        <div class="col-sm-9 col-xs-12 " style="outline: none;" tabindex="5001">
            <div class="col-inside-lg decor-default">
                <h6 id="chatTitle"></h6>
                <div id="chat" class="chat">
                    <div class="chat-body">

                    </div>
                </div>
                <div class="answer-add">
                    <input id="inputMessageField" placeholder="Write a message">
                    <%--<span  class="answer-btn answer-btn-1"></span>--%>
                    <span id="sentIcon" class="answer-btn answer-btn-2"></span>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.html" %>

<script type="text/javascript">

    long_polling();

    var currentUserId = ${currentSessionUser.id};

    function long_polling() {
        $.ajax({
            url: "/listen",
            method:"POST",
            dataType: "json",
            success: function (message) {
                processMessage(message);
                long_polling();
            }
        });
    }

    function processMessage(message) {

        console.log(message.text);


        var id = message.authorId;
        var firstName = message.authorFirstName;
        var lastName = message.authorLastName;
        var text = message.text;
        var chatId = message.chatId;

        var activeChatId = $(".activeUser").attr("id");

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

    $(function fillChatList() {

        $.ajax({
            url: '/api/chats/' + currentUserId,
            dataType: "json",
            success: function (result) {


                var chatListDiv = $("#chatList");

                $.each(result, function (key, val) {

                    console.log(val.id);

                    var chatBlock = '<div id="'+val.chatId+'" class="user">' +
                            '<div class="avatar"> ' +
                            '<img src="/image/avatar/'+val.userId+'" alt="User name"> ' +
                            '</div> <div id="firstName" class="name">'+val.firstName+'</div> ' +
                            '<div id="lastName" class="name">'+val.lastName+'</div> </div>';

                    chatListDiv.append(chatBlock);

                });
            }
        });
    });

</script>

</body>
</html>

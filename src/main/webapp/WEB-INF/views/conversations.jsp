<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

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
    <script src="/resources/js/sockjs-0.3.4min.js"></script>
    <script src="/resources/js/stomp.js"></script>

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
                    <span id="sendIcon" class="answer-btn answer-btn-2"></span>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.html" %>

<script type="text/javascript">



</script>

</body>
</html>

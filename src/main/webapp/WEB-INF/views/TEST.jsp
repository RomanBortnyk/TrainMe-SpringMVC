<%--
  Created by IntelliJ IDEA.
  User: romab
  Date: 11/15/16
  Time: 6:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/resources/js/jquery-3.1.1.min.js"></script>
    <script src="/resources/js/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="/resources/css/jquery-ui.min.css">
    <script src="/resources/js/tst.js"></script>
    <script src="/resources/js/sockjs-0.3.min.js"></script>
    <script src="/resources/js/stomp.js"></script>


</head>
<body>

<input id="myInput1" value="tettegdgdfgd">
<input type="submit" id="send" value="message"/>

<script>

    var url = 'http://' + window.location.host + '/messenger';
    var sock = new SockJS(url);
    var stomp = Stomp.over(sock);


    stomp.connect({},function (frame) {
        console.log('Connected: ' + frame);

    });


    $("#send").on('click',function () {

        var message = {
            chatId:1,
            messageText:$("#myInput1").val(),
            destinationUserId: 1
        };

        var payload = JSON.stringify(message);

//        stomp.connect('guest', 'guest', function(frame) {
            stomp.send("/app/message", {}, payload);
//        });


        $("#myInput1").val("");

    });




</script>


</body>
</html>

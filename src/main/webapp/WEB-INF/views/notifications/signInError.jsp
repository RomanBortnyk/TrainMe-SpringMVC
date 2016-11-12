<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Invalid Login</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/notifications.css">
</head>
<body>

<div class="wrapper">
    <form class="form-signin">
        <h3>Error: ${error}</h3>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><a href="/">Back to the main page</a></button>
    </form>
</div>

</body>
</html>

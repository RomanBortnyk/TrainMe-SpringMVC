<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
    <script src="/resources/js/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/notifications.css">
</head>
<body>
<div class="wrapper">

    <%--<spring:url value="/"></spring:url>--%>
    <c:choose>
        <c:when test="${error == null}">
            <form class="form-signin">
                <h3>User <%=request.getParameter("firstName")%> <%=request.getParameter("lastName")%> was successfully created</h3>
                <button class="btn btn-lg btn-primary btn-block" type="submit"><a href="/">Now you can sign in</a></button>
            </form>
        </c:when>

        <c:otherwise>
            <form class="form-signin">
                <h3 style="text-align: center">${error}
                    <br> change your input and try again
                </h3>
                <button class="btn btn-lg btn-primary btn-block" type="submit"><a href="/registration">Back to registration page</a></button>
            </form>
        </c:otherwise>

    </c:choose>

</div>
</body>
</html>
</html>

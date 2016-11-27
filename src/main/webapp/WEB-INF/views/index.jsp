<%--
  Created by IntelliJ IDEA.
  User: romab
  Date: 11/11/16
  Time: 5:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-COMPATIBLE" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>TrainMe</title>

    <%--<spring:url value="/resources/js/jquery-3.1.1.min.js" var="jquery" />--%>
    <%--<spring:url value="/resources/js/jquery.1.10.2.min.js" var="jqueryJs" />--%>
    <%--<spring:url value="/resources/js/main.js" var="mainJs" />--%>

    <%--<script src="${jquery}" ></script>--%>
    <script src="/resources/js/jquery-3.1.1.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="/resources/css/index.css" rel="stylesheet">


</head>
<body>

<div class="container">

    <div class="row title">
        <div>
            <h1 align="center">TrainMe</h1>
            <h2 align="center">Improve your sport skills</h2>
        </div>
    </div>

    <div class="row">

        <div class="col-xs-10 col-xs-offset-1 col-lg-4 col-lg-offset-4 outer_form">

            <form  style="padding-top: 7%;" class="form-horizontal signInForm" name="f" action="/login" method="POST">

                <div class="form-group">
                    <div class="col-xs-12">
                        <c:if test="${param.error != null}">
                            <div style="margin-bottom: 3%;" class="alert alert-danger">
                                <p style="text-align: center;">Invalid username or password</p>
                            </div>
                        </c:if>
                        <c:if test="${param.logout != null}">
                            <div style="margin-bottom: 3%;" class="alert alert-success">
                                <p style="text-align: center">You have been logged out successfully</p>
                            </div>
                        </c:if>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-xs-12">
                        <!--<label>Login:</label>-->
                        <input type="text" class="form-control" id="username" name="username" value="vlad" placeholder="Enter login">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-12">
                        <!--<label>Password:</label>-->
                        <input type="password" name="password" class="form-control" id="password" value="vlad" placeholder="Enter password">
                    </div>
                </div>

                <div class="form-group">
                    <div class=" col-xs-4 col-xs-offset-4">
                        <button type="submit" class="btn btn-info">Sign In</button>
                    </div>
                </div>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            </form>

            <div class="col-xs-8 col-xs-offset-3 col-lg-6">
                <a href="registration"> Don't have account?</a>
            </div>

        </div>
    </div>
</div>

</body>
</html>

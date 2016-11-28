<%--
  Created by IntelliJ IDEA.
  User: romab
  Date: 11/11/16
  Time: 6:02 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="/resources/css/registrarion.css" rel="stylesheet">

    <script type="text/javascript" src="/resources/js/jquery-3.1.1.min.js" ></script>
    <script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="/resources/js/registration.js"></script>


</head>
<body>

<div class="container">

    <div class="row title">
        <div class="col-xs-12 col-sm-6 col-sm-offset-3" >
            <h1 align="center">TrainMe</h1>
            <h2 align="center">Improve your sport skills</h2>
        </div>
    </div>

    <div class="row">

        <div class="col-xs-10 col-xs-offset-1 col-lg-4 col-lg-offset-4">

            <form class="form-horizontal signInForm" onsubmit="return validate()" action="register" method="POST">

                <div class="form-group">
                    <div class="col-xs-12">
                        <div id="warningMessage"  style="display: none;" class="alert alert-warning">
                            <strong>Warning!</strong>
                            <%--<p>warning text</p>--%>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-12">
                        <input id="firstName" name="firstName" placeholder="First name" type="text" class="form-control" >
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-12">
                        <input id="lastName" name="lastName" placeholder="Last name" class="form-control"  >
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-12">
                        <input type="text" id="birthday" name="birthdayDate" class="form-control" placeholder="Birthday date">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-12">
                        <input class="form-control" id="email" name="email" placeholder="Email">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-12">

                        <input class="form-control" id="login" name="login"  placeholder="Login (at least 4 characters)">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-12">

                        <input type="password" id="password" name="password" class="form-control"  placeholder="Enter password (at least 6 characters)">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-12">
                        <input type="password" id="repeatPass" class="form-control"  placeholder="Repeat password">
                    </div>
                </div>

                <fieldset class="form-group">

                    <div class="col-xs-6 col-xs-offset-4">
                        <label>Account type</label>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios1" value="customer" checked>
                                Customer
                            </label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios2" value="coach">
                                Coach
                            </label>
                        </div>
                    </div>

                </fieldset>

                <div class="form-group">
                    <div class="col-xs-4 col-xs-offset-4">
                        <button type="submit" class="btn btn-primary">Register</button>
                    </div>
                </div>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            </form>

        </div>
    </div>
</div>


</body>
</html>

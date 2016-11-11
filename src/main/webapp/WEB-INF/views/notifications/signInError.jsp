<%--
  Created by IntelliJ IDEA.
  User: romab
  Date: 10/1/16
  Time: 7:20 PM
  To change this template use File | Settings | File Templates.
  --%>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <head>
    <title>Invalid Login</title>
    <link rel="stylesheet" href="../../resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../resources/css/errors.css">
  </head>
  <body>
    <%
    String message ="";
    if ( request.getAttribute("incorrectPass")!=null) message = "Password incorect";
    if (request.getAttribute("unexistLogin") != null) message = "Login does not exist";
    %>
    <div class="wrapper">
      <form class="form-signin">       
        <h3>Error:  <%= message%></h3>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><a href="index.html">Back to the main page</a></button>   
      </form>
    </div>
  </body>
  </html>

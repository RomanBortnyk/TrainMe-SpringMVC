<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <title>Search</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../resources/css/bootstrap.min.css">
    <script src="../resources/js/jquery-3.1.1.min.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
    <script src="../resources/js/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="../resources/css/jquery-ui.min.css">

    <link href="../resources/css/userPage.css" rel="stylesheet">
    <link href="../resources/css/font-awesome.css" rel="stylesheet">

    <script type="text/javascript" src="../resources/js/searchPage.js"></script>

</head>
<body>
<%@ page isELIgnored="false" %>
<%@ include file="header.html" %>


    <div class="container">
    <div class="row">
      <h1 class="text-center" style="margin-top: 50px;">TrainMe Search</h1>
      <form action="/search" method="post">
        <div class="col-lg-8 col-md-8 col-sm-8 col-lg-offset-2 col-md-offset-2 col-sm-offset-2">

          <div class="input-group" style="height: 40px;">
            <input id="myInput1" name="searchString" type="text" class="form-control" placeholder="Start type user name" style="height: 40px;">
            

            <div class="input-group-btn">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="height: 40px; border-radius: 0%"><span class="caret"></span></button>
                <button id="btnSearch" type="submit" class="btn btn-default" style="height: 40px; border-bottom-right-radius: 4px; border-top-right-radius: 4px" ><i class="fa fa-search" aria-hidden="true"></i></button>

              <div class="dropdown-menu dropdown-menu-right">
                <div class="form-group">
                  <p>Search option</p>

                  <select id="searchOption" name="searchOption" class="form-control">
                    <option value="byFullName" selected>by full name</option>
                    <%--<option value="login">by login</option>--%>
                    <option value="byDiscipline">by discipline</option>
                  </select>
                </div>
                <div class="form-group">
                  <p style="float: left; margin-top: 10px;">User type</p>

                  <select name="userTypeOption" class="form-control">
                    <option selected>all</option>
                    <option>coach</option>
                    <option>customer</option>
                  </select>
                </div>
                
              </div>

            </div><!-- /btn-group -->

          </div>
        </div>
      </form>
    </div>
  </div>

  <div class="container">

      <c:forEach var="resultEntry" items="${resultMap}">
      <div class="row well result-section">
          <div class="col-lg-12 col-md-12 col-sm-12">
              <div class="col-lg-2 col-md-2 col-sm-3">
                  <a href="/profile/${resultEntry.key.getId()}">
                      <img id="avatar" src="/image/avatar/${resultEntry.key.getId()}"
                           width="140px" height="140px" class="img-circle" alt="Avatar">
                  </a>
              </div>
              <div class="col-lg-2 col-md-2 col-sm-3 name-result">
                  <p>${resultEntry.key.getFirstName()}</p>
                  <p>${resultEntry.key.getLastName()}</p>
                  <p>${resultEntry.key.getUserType()}</p>
              </div>
              <div class="col-lg-6 col-md-6 col-sm-12 description-bkg">
                  <pre style="border: none; background: none; margin-bottom: 0; padding: 0%">${resultEntry.key.getDescription()}</pre>
              </div>
              <div class="col-lg-2 col-md-2 col-sm-12">
                  <div class="pull-right disciplines-result">

                      <c:forEach var="discipline" items="${resultEntry.value}">
                          <div>
                              <img height="35px" width="35px" src="/image/icon/${discipline.getId()}">
                              <p>${discipline.getName()}</p>
                          </div>
                      </c:forEach>
                  </div>
              </div>
          </div>
      </div>
      </c:forEach>
  </div>

<%@include file="footer.html" %>

</body>
</html>


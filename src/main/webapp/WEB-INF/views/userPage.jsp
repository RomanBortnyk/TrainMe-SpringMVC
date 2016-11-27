<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>User page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <script src="/resources/js/jquery-3.1.1.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <link href="/resources/css/userPage.css" rel="stylesheet">
    <link href="/resources/css/font-awesome.css" rel="stylesheet">
    <script src="/resources/js/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="/resources/css/jquery-ui.min.css">
    <script type="text/javascript" src="/resources/js/userPage.js"></script>


</head>
<body>
<%@ page isELIgnored="false" %>
<%--<jsp:useBean id="currentSessionUser" class="trainMe.model.User" scope="session"></jsp:useBean>--%>

<%@ include file="header.html" %>

<div class="container text-center">
    <div class="row">
        <div class="col-lg-4 well">
            <div class="well">

                <div class="col-lg-12 avatar">
                    <img id="avatar" src="/image/avatar/${authenticatedUser.id}"
                         width="265" height="265" class="img-circle" alt="Avatar">
                </div>

                <button type="button" id="addButton" class=" btn btn-default btn-sm" data-toggle="modal"
                        data-target="#changePhoto">Change avatar
                </button>
                <!-- Modal -->
                <div class="modal fade" id="changePhoto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                     aria-hidden="true">
                    <div class="vertical-alignment-helper">
                        <div class="modal-dialog vertical-align-center">

                            <div class="modal-content">

                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"><span
                                            aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                                    </button>
                                    <h4 class="modal-title">Change photo</h4>
                                    <h6 class="modal-title" id="">
                                        image size should be less than 2 MB
                                    </h6>
                                </div>

                                <div class="modal-body">
                                    <form action="/modify/avatar" enctype="multipart/form-data" method="post">
                                        <input type="file" name="newAvatar" style="display: inline-block;">
                                        <button type="submit" class="btn btn-primary">Save changes</button>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <p></p>
                <p>${authenticatedUser.firstName}</p>
                <p>${authenticatedUser.lastName}</p>
                <p>${authenticatedUser.birthdayDate}</p>
                <p>${authenticatedUser.userType}</p>

            </div>

            <div class="well">
                <h4>${authenticatedUser.userType.equals("customer") ? "Interests" : "Coach specialization"}</h4>
                <ul id="disciplinesList" class="list-group">

                    <%--<li class="list-group-item">--%>
                        <%--<img src="/image/icon/${link.getDiscipline().getId()}" height="35" width="35" alt="icon">--%>
                        <%--${link.getDiscipline().getName()}--%>
                    <%--</li>--%>

                </ul>

                <button id="changeInterestsButton" type="button" style="margin-bottom: 15px;"
                        class="btn btn-default btn-sm" data-toggle="modal"
                        data-target="#changeInterests">
                    Change ${authenticatedUser.userType.equals("customer") ? "interests" : "specialization"}
                </button>
                <!-- Modal -->
                <div class="modal fade" id="changeInterests" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                     aria-hidden="true">
                    <div class="vertical-alignment-helper">
                        <div class="modal-dialog vertical-align-center">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"><span
                                            aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                                    </button>
                                    <h4 class="modal-title">
                                        Change ${authenticatedUser.userType.equals("customer") ? "interests" : "specialization"}</h4>
                                </div>
                                <div class="modal-body interests-section">
                                    <div class="row" style="margin-top: 20px;">
                                        <form action="/modify/discipline/add" method="post">
                                            <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                                                <select id="discToAdd" name="disciplineToAdd" class="form-control">
                                                    <option disabled selected hidden>--</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                                                <button class="btn btn-success" style="width: 100%;">Add</button>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="row" style="margin-top: 30px; margin-bottom: 30px;">
                                        <form action="/modify/discipline/remove" method="post">
                                            <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                                                <select id="discToRemove" name="disciplineToRemove" class="form-control">
                                                    <option disabled selected hidden>--</option>
                                                    <%--<c:forEach var="link" items="${disciplineLinks}">--%>
                                                        <%--<option disabled selected hidden>--</option>--%>
                                                        <%--<option>${link.getDiscipline().getName()}</option>--%>
                                                    <%--</c:forEach>--%>

                                                </select>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                                                <button class="btn btn-danger" style="width: 100%;">Delete</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-lg-8">
            <div id="description" class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default text-left">
                        <div style="padding: 1%" class="panel-body">
                            <pre style="border: none; background: none; padding: 0%">${authenticatedUser.description}</pre>
                        </div>
                    </div>
                </div>
                <button id="changeDescrButton" type="button" style="margin-bottom: 15px; float: left; margin: 0 15px;"
                        class="btn btn-default btn-sm" data-toggle="modal" data-target="#statusText">Change description
                </button>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="statusText" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="vertical-alignment-helper">
                    <div class="modal-dialog vertical-align-center">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span
                                        aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                                </button>
                                <h4 class="modal-title" id="myModalLabel">Change description</h4>
                            </div>
                            <div class="modal-body interests-section">
                                <div class="row">
                                    <form action="/modify/description" method="post">
                                        <div class="form-group">
                                            <textarea style="text-align: justify" class="form-control"
                                                      name="newDescription" rows="5"
                                                      id="comment">${authenticatedUser.description}</textarea>
                                        </div>
                                        <button type="submit" class="btn btn-primary btn-md" style="float: right;">
                                            Save
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <h3>Feedbacks</h3>

            <div id="feedbacks" class="row">
                <%--<div class="col-lg-12">--%>

                <%--<div  class="col-lg-3">--%>
                <%--<div class="well">--%>
                <%--<p id="authorName"></p>--%>

                <%--<a href="/profile/${feedback.getAuthor().getId()}">--%>
                <%--<img src="/image/avatar/${feedback.getAuthor().getId()}" class="img-circle" height="65"--%>
                <%--width="65" alt="Avatar">--%>
                <%--</a>--%>
                <%--</div>--%>
                <%--</div>--%>

                <%--<div class="col-lg-9">--%>
                <%--<div class="well">--%>
                <%--<p id="feedbackText" class="text-left"></p>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
            </div>

        </div>
    </div>
</div>
<%@include file="footer.html" %>
</body>
</html>
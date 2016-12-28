<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Expotential backoff</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <script src="/resources/js/jquery-3.1.1.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <link href="/resources/css/userPage.css" rel="stylesheet">
    <link href="/resources/css/font-awesome.css" rel="stylesheet">
    <script src="/resources/js/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="/resources/css/jquery-ui.min.css">
    <%--<script src="/resources/js/expotentialBackoff.js"></script>--%>
    <script src="/resources/js/Chart.js"></script>

</head>
<body>


<div class="container text-center">
    <div class="row">
        <div class="col-lg-12 well">


            <h1>Expotential backoff</h1>
            <div class="col-lg-4">
                <input id="attempts" placeholder="N of attempts">
                <button id="startSimulation" type="button" class=" btn btn-default btn-sm">Start</button>
            </div>
            <div id="report-container" class="col-lg-8" style="child-align: left">

            </div>

            <div class="row">
                <div class="col-lg-10 col-lg-offset-1">
                    <%--<button id="build">Build chart</button>--%>

                    <canvas id="buyers" width="500" height="250"></canvas>

                </div>
            </div>


        </div>

    </div>
</div>


<script>

    var attemptsArray = [];
    var delayArray = [];


//   $("#build").on('click', function () {
//
//    });

    function buildChart () {


        var chartData = {
//        labels : ["January","February","March","April","May","June"],
            labels : attemptsArray,

            datasets : [
                {
                    fillColor : "rgba(172,194,132,0.4)",
                    strokeColor : "#ACC26D",
                    pointColor : "#fff",
                    pointStrokeColor : "#9DB86D",
                    label: "",

//                data : [203,156,99,251,305,247]
                    data : delayArray
                }
            ]
        };

        var context = document.getElementById('buyers').getContext('2d');

        var myNewChart = new Chart(context , {
            type: "line",
            data: chartData
        });

    }

    var reportContainer = $("#report-container");

    var count = 1;

    $("#startSimulation").on('click', function () {

        count = 1;
        var attempts = $("#attempts").val();
        requestUntilSuccess("error", attempts, 0);


    });


    function requestUntilSuccess(status, attempts, delay) {

        var attempts = parseInt(attempts);
        attempts--;
        var status = status;
        var delay = delay;


        $.ajax({
            type: "GET",
            url: "/api/expotential/" + status,
            contentType: "application/json",
            success: function (response) {

                reportContainer.append(
                        '<p align="left" style="color: green; overflow: hidden">'+count+
                        '. Success: ' + JSON.stringify(response) +
                        ' delay:'+delay/1000+'s'+
                        '</p> '
                );

                attemptsArray.push(count);
                delayArray.push(delay/1000);

                count++;
                console.log(response);

                buildChart();

            },
            error: function (xhr) {

                reportContainer.append(
                        '<p align="left" style="color: red"> '+count+
                        '. Error: ' + xhr.status + ' ' +
                         xhr.statusText +
                        ' delay:'+delay/1000+'s'+
                        '</p> '
                );

                attemptsArray.push(count);
                delayArray.push(delay/1000);

                count++;
                console.log(xhr.status);
                console.log(xhr.statusText);

                if (attempts == 1) {
                    status = "ok";
//                    setTimeout(requestUntilSuccess, calcDelay(count), status, attempts, calcDelay(count));
                    requestUntilSuccess (status, attempts, calcDelay(count));



                }else {
//                    setTimeout(requestUntilSuccess, calcDelay(count), status, attempts, calcDelay(count));
                    requestUntilSuccess (status, attempts, calcDelay(count));

                }

            }
        });
    }

    function calcDelay(attempt) {

        if (attempt == 1){
            return 0;
        }else {

            var expotentialInterval = 1.75;

            var delay = parseInt(Math.pow(expotentialInterval, attempt)*1000);
            return  delay; // miliseconds
        }

    }


</script>

</body>
</html>
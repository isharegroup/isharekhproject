<%--
  Created by IntelliJ IDEA.
  User: HTPP
  Date: 03/30/2018
  Time: 7:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>videos detail</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/ui/main.css" />

    <link href='http://fonts.googleapis.com/css?family=Work+Sans:400,600,700' rel='stylesheet' type='text/css'/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!--correct -->
    <link rel="stylesheet" type="text/css" href="../css/ui/reset.css" />
    <link rel="stylesheet" type="text/css" href="../css/ui/style.css" />
    <script type="text/javascript" src="../dist/js/ui/modernizr.js"></script>
    <style type="text/css">
        body {font-family: Verdana, sans-serif; margin:0}
        .jumbotron .h1, .jumbotron h1 {
            font-size: 63px;
            margin-top: 123px;
        }
        .jumbotron {
            padding-top: 48px;
            padding-bottom: 48px;
            background-color: #3cd83e;
            /* background-image: url("https://www.w3schools.com/howto/img_nature_wide.jpg");*/
        }
        .pagination {
            display: inline-block;
        }

        .pagination a {
            color: black;
            float: left;
            padding: 8px 16px;
            text-decoration: none;
        }

        .pagination a.active {
            background-color: #4CAF50;
            color: white;
        }

        .pagination a:hover:not(.active) {
            background-color: #ddd;
        }
        .slideshow-container {
            position: relative;
            margin: auto;
        }
        .prev {
            cursor: pointer;
            position: absolute;
            top: 25%;
            width: auto;
            padding: 16px;
            margin-top: -22px;
            color: white;
            font-weight: bold;
            font-size: 18px;
            transition: 0.6s ease;
            border-radius: 0 3px 3px 0;
        }
        .next {
            cursor: pointer;
            position: absolute;
            top: 25%;
            width: auto;
            padding: 16px;
            margin-top: -22px;
            color: white;
            font-weight: bold;
            font-size: 18px;
            transition: 0.6s ease;
            border-radius: 0 3px 3px 0;
        }

        /* Position the "next button" to the right */
        .next {
            right: 0;
            border-radius: 3px 0 0 3px;
        }

        /* On hover, add a black background color with a little bit see-through */
        .prev:hover {
            background-color: rgba(0,0,0,0.8);
        }
        .next:hover {
            background-color: rgba(0,0,0,0.8);
        }

        /* Number text (1/3 etc) */
        .numbertext {
            color: #f2f2f2;
            font-size: 12px;
            padding: 8px 12px;
            position: absolute;
            top: 0;
        }

        /* The dots/bullets/indicators */
        .dot {
            cursor: pointer;
            height: 15px;
            width: 15px;
            margin: 0 2px;
            background-color: #bbb;
            border-radius: 50%;
            display: inline-block;
            transition: background-color 0.6s ease;
        }

        .active {
            background-color: #717171;
        }
        .dot:hover {
            background-color: #717171;
        }

        /* Caption text */
        .text {
            color: #f2f2f2;
            font-size: 15px;
            padding: 8px 12px;
            position: absolute;
            bottom: 8px;
            width: 100%;
            text-align: center;
        }

        /* Fading animation */
        .fade {
            -webkit-animation-name: fade;
            -webkit-animation-duration: 1.5s;
            animation-name: fade;
            animation-duration: 1.5s;
        }

        @-webkit-keyframes fade {
            from {opacity: .4}
            to {opacity: 1}
        }

        @keyframes fade {
            from {opacity: .4}
            to {opacity: 1}
        }

        /* On smaller screens, decrease text size */
        @media only screen and (max-width: 300px) {
            .prev, .next,.text {font-size: 11px}
        }
        .navbar-inverse .navbar-nav>li>a {
            color: #1030D6;
            font: 16px arial, sans-serif;
        }
        .navbar-nav>li>a {
            padding-top: 19px;
            padding-bottom: 15px;
        }
        /*dropdown list*/
        .dropbtn {
            background-color: #64c914;
            color: white;
            padding: 20px;
            font-size: 16px;
            border: none;
        }
        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f1f1f1;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown-content a:hover {background-color: #ddd}

        .dropdown:hover .dropdown-content {
            display: block;
        }

        .dropdown:hover .dropbtn {
            background-color: #3e8e41;
        }
        .backend-image
        {
            position:absolute;
            z-index: 1;
        }
        .frontend-image
        {
            width: 20% !important;
            z-index: 2;
            position: absolute;
            margin-top: 65px;
            margin-left: 40%;
        }
        .selected:hover .frontend-image{
            background-color: rgba(125, 227, 172, 0.8) !important;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" style="background-color: rgb(100,200,20);">
    <div class="container-fluid" style="background-color: rgb(100,200,20);">
        <div class="navbar-header" style="background-color: rgb(100,200,20);">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#" style="color: rgba(249,249,255,0.97);">
                Logo
            </a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <c:forEach items="${newstypes}" var="newstype">
                <div class="dropdown">
                    <ul class="dropbtn">${newstype.desEn}</ul>
                </div>
            </c:forEach>
            <%--<c:forEach items="${newsTypesPHS}" var="newstype">
                <div class="dropdown">
                    <ul class="dropbtn">${newstype.desEn}</ul>
                    <c:forEach items="${newsTypesIS}" var="newstyp">
                        <div class="dropdown-content">
                            <a href="#">${newstyp.desEn}</a>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>--%>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/login" style="color: rgba(249,249,255,0.97);"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </ul>
        </div>


    </div>
</nav>

<br></br>
<nav class="cd-gallery"></nav>
<div class="container">
    <div class="row">
        <div class="col-6">
            <h1 style="background-color: red;height: 61px;">Hello WorldHello WorldHello WorldHello WorldHello WorldHello WorldHello WorldHello WorldHello Wo</h1>
        </div>
        <div class="row">
            <div class="col-sm-8">
                <iframe class="selected" style="width: 100%; height: 400px;" src="//www.youtube.com/embed/tgbNymZ7vqY?autoplay=1" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen>
                </iframe>
            </div>
            <div class="col-sm-4">
                <div class="row">
                    <div class="col-12 col-md-12">
                        <h1>Hello WorldHello WorldHello WorldHello WorldHello WorldHello WorldHello WorldHello WorldHello Wo</h1>
                    </div>
                    <div class="col-12 col-md-12">
                        <h1 style="background-color: red;height: 61px;">Hello WorldHello WorldHello WorldHello WorldHello WorldHello WorldHello WorldHello WorldHello Wo</h1>
                    </div>
                    <div class="col-12 col-md-12">
                        Hello WorldHello WorldHello WorldHello WorldHello WorldHello WorldHello WorldHello WorldHello Wo
                    </div>
                </div>
            </div>
        </div>
        <div class="col-6">
            <h1 style="background-color: red;height: 261px;">Hello WorldHello WorldHello WorldHello WorldHello WorldHello WorldHello WorldHello WorldHello Wo</h1>
        </div>
    </div>
</div>

<script type="text/javascript" src="../dist/js/ui/main.js"></script>
<script type="text/javascript" src="../dist/js/ui/jquery-2.1.4.js"></script>
<footer class="container-fluid text-center" style="height: 50px; background-color: rgb(100,200,20);">
    <br>
    <center>Copy right isharekh.com;         <b>Phone: </b><em>097 66 17 899</em></center>
</footer>
</body>
</html>
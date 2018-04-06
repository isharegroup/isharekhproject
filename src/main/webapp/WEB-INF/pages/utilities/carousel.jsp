<%--
  Created by IntelliJ IDEA.
  User: r.ron
  Date: 2/9/2018
  Time: 4:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
    <title>admin</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style type="text/css">
        .carousel-inner.onebyone-carosel { margin: auto; width: 100%; }
        .onebyone-carosel .active.left { left: -25.00%; }
        .onebyone-carosel .active.right { left: 25.00%; }
        .onebyone-carosel .next { left: 25.00%; }
        .onebyone-carosel .prev { left: -25.00%; }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#myCarousel').carousel({
                interval: 2000
            })
            $('.carousel-inner .item').each(function () {
                var next = $(this).next();
                if (!next.length) {
                    next = $(this).siblings(':first');
                }
                next.children(':first-child').clone().appendTo($(this));

                for (var i=0;i<2;i++) {
                    next=next.next();
                    if (!next.length) {
                        next = $(this).siblings(':first');
                    }

                    next.children(':first-child').clone().appendTo($(this));
                }
            });
        });
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div id="myCarousel" class="carousel fdi-Carousel slide">
            <!-- Carousel items -->
            <div class="carousel fdi-Carousel slide" id="eventCarousel" data-interval="0">
                <div class="carousel-inner onebyone-carosel">
                    <div class="item active">
                        <div class="col-md-3">
                            <a href="#"><img src="http://placehold.it/250x250" class="img-responsive center-block"></a>
                            1
                        </div>
                    </div>
                    <div class="item">
                        <div class="col-md-3">
                            <a href="#"><img src="http://placehold.it/250x250" class="img-responsive center-block"></a>
                            2
                        </div>
                    </div>
                    <div class="item">
                        <div class="col-md-3">
                            <a href="#"><img src="http://placehold.it/250x250" class="img-responsive center-block"></a>
                            3
                        </div>
                    </div>
                    <div class="item">
                        <div class="col-md-3">
                            <a href="#"><img src="http://placehold.it/250x250" class="img-responsive center-block"></a>
                            4
                        </div>
                    </div>
                    <div class="item">
                        <div class="col-md-3">
                            <a href="#"><img src="http://placehold.it/250x250" class="img-responsive center-block"></a>
                            5
                        </div>
                    </div>
                    <div class="item">
                        <div class="col-md-3">
                            <a href="#"><img src="http://placehold.it/250x250" class="img-responsive center-block"></a>
                            6
                        </div>
                    </div>
                    <div class="item">
                        <div class="col-md-3">
                            <a href="#"><img src="http://placehold.it/250x250" class="img-responsive center-block"></a>
                            7
                        </div>
                    </div>
                </div>
                <a class="left carousel-control" href="#eventCarousel" data-slide="prev"></a>
                <a class="right carousel-control" href="#eventCarousel" data-slide="next"></a>
            </div>
            <!--/carousel-inner-->
        </div><!--/myCarousel-->
    </div>
</div></body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: r.ron
  Date: 2/10/2018
  Time: 10:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="/css/login.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<form action="/registration" method="get">
    <button class="btn btn-md btn-warning btn-block" type="Submit">Go To Registration Page</button>
</form>

<div class="container">
    <img src="/images/login.jpg" class="img-responsive center-block" width="300" height="300" alt="Logo" />
    <form action="/login" method="POST" class="form-signin">
        <h3 class="form-signin-heading" text="Welcome"></h3>
        <br/>

        <input type="text" id="email" name="email"  placeholder="Email"
               class="form-control" /> <br/>
        <input type="password"  placeholder="Password"
               id="password" name="password" class="form-control" /> <br />

        <button class="btn btn-lg btn-primary btn-block" name="Submit" value="Login" type="Submit" text="Login">Login</button>
    </form>
</div>
</body>
</html>

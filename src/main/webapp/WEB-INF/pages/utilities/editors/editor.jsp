<%--
  Created by IntelliJ IDEA.
  User: r.ron
  Date: 3/14/2018
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/UI/editors/editor.js"></script>
    <script>
        $(document).ready(function() {
            $("#txtEditor").Editor();
        });
    </script>
    <link rel="stylesheet" href="/css/ui/editors/editor.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link href="editor.css" type="text/css"/>
    <title>geditor</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <h2 class="demo-text">LineControl Demo</h2>
        <div class="container">
            <div class="row">
                <div class="col-lg-12 nopadding">
                    <textarea id="txtEditor"></textarea>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid footer">
    <p class="pull-right">&copy; Suyati Technologies <script>document.write(new Date().getFullYear())</script>. All rights reserved.</p>
</div>
</body>
</html>

<%-- 
    Document   : login
    Created on : May 28, 2016, 11:59:28 AM
    Author     : PhucTDSE61834
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <style>
        .row{
            display: block;
            margin: 5px;
        }
        .span-label{
            display: inline-block;
            width: 150px;
        }
    </style>
    <body>
        <h2>Login Page</h2>
        <form action="ProcessServlet" method="POST">
            <div class="row">
                <span class="span-label">Username</span>
                <input type="text" name="txtUsername" value="" />
            </div>
            <div class="row">
                <span class="span-label">Password</span>
                <input type="password" name="txtPassword" value="" />
            </div>
            <div class="row">
                <span class="span-label"></span>
                <input type="submit" value="Login" name="myAction"/>
                <input type="submit" value="Reset" />
            </div>
        </form>
        <h4 style="color: red">
            ${requestScope.LoginErr}
        </h4>
    </body>
</html>

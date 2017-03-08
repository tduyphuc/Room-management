<%-- 
    Document   : request
    Created on : May 28, 2016, 12:01:40 PM
    Author     : PhucTDSE61834
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Request</title>
        <style>
            .row{
                display: inline-block;
                margin: 5px;
            }
            .span-label{
                display: inline-block;
                width: 100px;
            }
        </style>
    </head>
    <body>
        <c:set var="acc" value="${sessionScope.ACCOUNT}"/>
        <c:url var="logoutLink" value="ProcessServlet">
            <c:param name="myAction" value="Logout"/>
        </c:url>

        <c:if test="${empty acc}">
            <c:url var="loginPage" value="ProcessServlet"/>
            <jsp:forward page="${loginPage}"/>
        </c:if>

        <c:if test="${not empty acc and acc.role != 3}">
            <c:url var="invalid" value="ErrorPage/403.html"/>
            <jsp:forward page="${invalid}"/>
        </c:if>

        <c:if test="${empty param.roomID}">
            <c:url var="invalid1" value="ErrorPage/401.html"/>
            <jsp:forward page="${invalid1}"/>
        </c:if>

        <h3 style="color: red">Welcome ${acc.username}</h3>
        <a href="${logoutLink}">Log out</a><br/>
        <h2>Damage Report of Room</h2>
        <h3 style="color: brown">Room: ${param.roomID}</h3>

        <div class="row">
            <form action="ProcessServlet" method="POST">
                <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}" />
                <c:if test="${not empty param.show}">
                    <input type="hidden" name="show" value="${param.show}" />
                </c:if>
                <input type="hidden" name="roomID" value="${param.roomID}" />
                <span class="span-label" style="float: left">Reason </span>
                <textarea name="txtReason" rows="10" cols="30">${param.txtReason}</textarea>
                <br/>
                <div class="row" style="clear: both">
                    <span class="span-label"></span>
                    <input type="submit" value="Report" name="myAction" />
                </div>
            </form>
        </div>

        <h4 style="color: red">
            ${param.errCreate}
        </h4> 

        <c:url var="backLink" value="ProcessServlet">
            <c:if test="${not empty param.show}">
                <c:param name="myAction" value="Show All"/>
            </c:if>
            <c:if test="${empty param.show}">
                <c:param name="myAction" value="Search"/>
            </c:if>
            <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
        </c:url>
        <a href="${backLink}">Go back to room manager page</a>
    </body>
</html>

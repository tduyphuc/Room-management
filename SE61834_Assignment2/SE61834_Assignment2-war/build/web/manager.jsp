<%-- 
    Document   : manager
    Created on : May 28, 2016, 12:00:22 PM
    Author     : PhucTDSE61834
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotel manager</title>
    </head>
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
    <body>
        <c:set var="acc" value="${sessionScope.ACCOUNT}"/>
        <c:set var="show" value="${requestScope.SHOW}"/>
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
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


        <h3 style="color: red">Welcome ${acc.username}</h3>
        <a href="${logoutLink}">Log out</a><br/>
        <h2>Hotel ABC</h2>
        <div class="row">
            <form action="ProcessServlet" class="row">
                <span class="span-label">Room </span>
                <input type="text" name="txtSearchValue" 
                       value="${searchValue}" />
                <input type="submit" value="Search" name="myAction" />
                <input type="submit" value="Show All" name="myAction" />
            </form>

        </div>
        <div style="color: red">
            ${requestScope.emptyValueErr}
        </div>

        <c:if test="${(not empty searchValue) or (not empty show)}">
            <c:set var="list" value="${requestScope.SEARCHROOM}"/>
            <c:if test="${not empty list}">
                <h3>Room Information</h3>
                <table border="1" style="text-align: center">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Room</th>
                            <th>Description</th>
                            <th>Hour Price</th>
                            <th>Day Price</th>
                            <th>Change Category</th>
                            <th>Damage Report</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="entity" items="${list}" varStatus="counter">
                        <form action="ProcessServlet">
                            <tr>
                            <input type="hidden" name="roomID" value="${entity.roomID}" />
                            <input type="hidden" name="txtSearchValue" value="${searchValue}" />
                            <c:if test="${not empty show}">
                                <input type="hidden" name="show" value="${show}" />
                            </c:if>
                            <td>${counter.count}</td>
                            <td>${entity.roomID}</td>
                            <td>${entity.description}</td>
                            <td>${entity.hourPrice}</td>
                            <td>${entity.dayPrice}</td>
                            <td>
                                <input type="hidden" name="cateID" value="${entity.categoryID}" />
                                <input type="submit" value="Change" name="myAction" />
                            </td>
                            <td>
                                <input type="submit" value="Request" name="myAction" />
                            </td>
                            </tr>
                        </form>
                    </c:forEach> 
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty list}">
            <h3>Not found !</h3>
        </c:if>
    </c:if>
</body>
</html>

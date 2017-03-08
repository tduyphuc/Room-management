<%-- 
    Document   : staff
    Created on : May 28, 2016, 12:00:42 PM
    Author     : PhucTDSE61834
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Maintenance Room</title>
    </head>
    <body>
        <c:set var="acc" value="${sessionScope.ACCOUNT}"/>
        <c:set var="searchDate" value="${param.txtDate}"/>
        <c:url var="logoutLink" value="ProcessServlet">
            <c:param name="myAction" value="Logout"/>
        </c:url>

        <c:if test="${empty acc}">
            <c:url var="loginPage" value="ProcessServlet"/>
            <jsp:forward page="${loginPage}"/>
        </c:if>

        <c:if test="${not empty acc and acc.role != 1}">
            <c:url var="invalid" value="ErrorPage/403.html"/>
            <jsp:forward page="${invalid}"/>
        </c:if>

        <h3 style="color: red">Welcome ${acc.username}</h3>
        <a href="${logoutLink}">Log out</a><br/>
        <h2>Hotel ABC</h2>

        <div class="row">
            <form action="ProcessServlet" class="row">
                <span class="span-label">Date </span>
                <input id="txtDate" type="text" name="txtDate" placeholder="dd/MM/yyyy" 
                       pattern="[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}" title="dd/MM/yyyy"
                       value="${searchDate}"/>
                <c:if test="${empty searchDate}">
                    <script language="javascript">
                        var today = new Date();
                        document.getElementById("txtDate").value =
                                today.getDate() + "/" + (today.getMonth() + 1) + "/" + today.getFullYear();
                    </script>
                </c:if>
                <input type="submit" value="Filter" name="myAction" />
            </form>

        </div>

        <div style="color: red; margin-top: 15px">
            ${requestScope.errFilter}
        </div>

        <c:if test="${not empty searchDate}">
            <c:set var="list" value="${requestScope.MAINTENANCE}"/>
            <c:if test="${not empty list}">
                <h3>Damage Report Information</h3>
                <div style="color: red; margin-top: 15px">
                    ${param.errUpdate}
                </div>
                <form action="ProcessServlet" method="POST">
                    <input type="hidden" name="txtDate" value="${searchDate}" />
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Room</th>
                                <th>Reason</th>
                                <th>Mend</th>
                                <th>Cost</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="entity" items="${list}" varStatus="counter">
                                <tr>
                            <input type="hidden" name="id" value="${entity.id}" />
                            <td>${counter.count}</td>
                            <td>${entity.roomID}</td>
                            <td>${entity.reason}</td>
                            <td><input type="text" name="txtMend${entity.id}" value="${entity.mend}" maxlength="250"/></td>
                            <td><input type="text" name="txtCost${entity.id}" value="${entity.cost}" pattern="[0-9]{1,9}([.][0-9]{1,2})?" title="x(max 9 numbers).y(max 2 numbers)" maxlength="12"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <input type="submit" value="Finish" name="myAction"/>
                </form>
            </c:if>
                <c:if test="${empty list and empty requestScope.errFilter}">
                <h3>Don't have any report</h3>
            </c:if>
        </c:if>
    </body>
</html>

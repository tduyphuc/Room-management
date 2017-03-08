<%-- 
    Document   : changeCate
    Created on : May 28, 2016, 12:01:19 PM
    Author     : PhucTDSE61834
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Cate</title>
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

        <c:if test="${empty param.roomID or empty param.cateID}">
            <c:url var="invalid1" value="ErrorPage/401.html"/>
            <jsp:forward page="${invalid1}"/>
        </c:if>

        <h3 style="color: red">Welcome ${acc.username}</h3>
        <a href="${logoutLink}">Log out</a><br/>
        <h2>Change Category</h2>
        <h3 style="color: brown">Room: ${param.roomID}</h3>
        <h3 style="color: red">
            ${param.errUpdate}
        </h3>
        <div class="row">
            <form action="ProcessServlet" class="row">

                <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}" />
                <input type="hidden" name="roomID" value="${param.roomID}" />
                <c:if test="${not empty param.show}">
                    <input type="hidden" name="show" value="${param.show}" />
                </c:if>

                <span class="span-label">Category </span>               
                <c:set var="list" value="${requestScope.CATELIST}"/>
                <c:if test="${not empty list}">
                    <select name="category">
                        <c:forEach var="cate" items="${list}">
                            <option value="${cate.categoryID}" 
                                    <c:if test="${cate.categoryID eq param.cateID}">
                                        selected="selected"
                                    </c:if>>
                                ${cate.categoryName}
                            </option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="Save" name="myAction" />
                </c:if>

            </form>
        </div>
    </body>
</html>

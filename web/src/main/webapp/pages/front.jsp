<%@ page import="com.portal.Attributes" %>
<%@ page import="com.portal.commands.LoginCommand" %>
<%@ page import="com.portal.commands.IndexCommand" %>
<%@ page import="com.portal.commands.RegisterCommand" %>
<%@ page import="com.portal.commands.FormNewsAddCommand" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="newsitem" class="beans.New" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
    <title>News-Portal</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <meta charset="utf-8"/>
    <title>news portal</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- Bootstrap -->
    <!-- Latest compiled and minified CSS -->
    <link type="text/css" rel="stylesheet" href="resources/css/style.css">
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/normalize.css">


</head>
<body>
<div class="page-wrapper">
    <div id="content">


        <table class="table">
            <caption class="caption">
                <h1>${usertype}</h1>
                <img src="resources/images/TrendingLogo3.png" alt="newsportal"
                     style="position: absolute; top:20px; left: 30px;" width="400"/>
            </caption>
            <tr>
                <th class="itemtitle">
                    <div id="title_menu">
                        <h3 align="left">top-news:</h3>
                    </div>
                </th>
                <th class="menutitle">
                    <h3 align="left">${newsitem.title}</h3>
                </th>
            </tr>
            <tr>
                <td class="menu">
                    <div align="left" class="scrollable-menu">
                        <ul class="list-unstyled">
                            <c:forEach var="nw" items="${newslist}">
                                <li class="itemLink" item_id="${nw.id}">

                                    <h4><a class="itemLink1" href="index?item_id=${nw.id}">${nw.title4menu}</a></h4>

                                </li>
                            </c:forEach>
                        </ul>
                    </div>

                </td>
                <td class="item">
                    <div id="item">
                        <p align="left">${newsitem.item}</p>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <c:if test="${usertype eq 'GUEST'}">
                        <form action="index" method="post">
                            <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=LoginCommand.NAME%>">
                            <input class="btn btn-default" type="submit" name="login" value="Sign In">
                        </form>
                    </c:if>
                    <c:if test="${usertype eq 'GUEST'}">
                    <form action="index" method="post">
                        <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=RegisterCommand.NAME%>">
                        <input class="btn btn-default" type="submit" name="register" value="Register">
                    </form>
                    </c:if>
                    <c:if test="${usertype eq 'USER'}">
                        <form action="index" method="post">
                            <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=FormNewsAddCommand.NAME%>">
                            <input class="btn btn-default" type="submit" name="register" value="Add News">
                        </form>
                    </c:if>

                </td>
                <td></td>
            </tr>
        </table>

    </div>
    <div class="page-buffer"></div>
</div>
<div class="page-footer">
    <p>Александр Гирс, 2015</p>
</div>


</div>
</body>
</html>
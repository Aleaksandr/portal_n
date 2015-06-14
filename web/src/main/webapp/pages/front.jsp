<%@ page import="com.portal.util.Attributes" %>
<%@ page import="com.portal.commands.*" %>
<%@ page import="com.portal.commands.RegisterCommand" %>
<%@ page import="com.portal.commands.UserinfoCommand" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="newsitem" class="pojos.News" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>

    <title>News-Portal</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <meta charset="utf-8"/>
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
        <div class="logout">
            <c:if test="${usertype eq 'USER'|| usertype eq 'ADMINISTRATOR'}">
                <form action="FrontController" method="post">
                    <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=LogoutCommand.NAME%>">
                    <input class="btn btn-default" type="submit" name="login" value="Log Out">
                </form>
            </c:if>
        </div>
        <table class="table">
            <caption class="caption">
                <h1>${usertype}</h1>
                <img src="resources/images/TrendingLogo3.png" alt="newsportal"
                     style="position: absolute; top:20px; left: 30px;" width="400"/>
            </caption>

            <tr>
                <td class="menu">
                    <div class="panel panel-default">
                        <div class="panel-heading">Top news today</div>
                        <ul class="list-group">
                            <c:forEach var="nw" items="${newslist}">
                                <a class="list-group-item" href="FrontController?item_id=${nw.id}">${nw.title4menu}</a>
                            </c:forEach>
                        </ul>
                        <form style="width: 49%" action="FrontController" method="post">
                            <button style="width: 100%" class="btn btn-default" id="prev">Prev</button>
                            <input type="hidden" name="page" value="prev">
                        </form>
                        <form style="width: 49%" action="FrontController" method="post">
                            <button style="width: 100%" class="btn btn-default" id="next">Next</button>
                            <input type="hidden" name="page" value="next">
                        </form>
                    </div>

                </td>
                <td class="item">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">${newsitem.title}</h3>
                        </div>
                        <div class="panel-body">
                            <p>${newsitem.item}</p>
                            <c:if test="${usertype eq 'ADMINISTRATOR'}">
                                <form action="FrontController" method="post">
                                    <input type="hidden" name="<%=Attributes.COMMAND%>"
                                           value="<%=DelNewsCommand.NAME%>">
                                    <input type="hidden" name="comId" value="${comment.id}">
                                    <input class="btn btn-default" type="submit" name="deleteitem"
                                           value="Delete News">
                                </form>
                                <form action="FrontController" method="post">
                                    <input type="hidden" name="<%=Attributes.COMMAND%>"
                                           value="<%=FormNewsUpdCommand.NAME%>">
                                    <input class="btn btn-default" type="submit" name="correctitem"
                                           value="Correct">
                                </form>
                            </c:if>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <c:if test="${usertype eq 'GUEST'}">
                        <form style="margin-bottom: 4px" action="FrontController" method="post">
                            <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=LoginCommand.NAME%>">
                            <input class="btn btn-default" type="submit" name="login" value="Sign In">
                        </form>
                    </c:if>
                    <c:if test="${usertype eq 'GUEST'}">
                        <form action="FrontController" method="post">
                            <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=RegisterCommand.NAME%>">
                            <input class="btn btn-default" type="submit" name="register" value="Register">
                        </form>
                    </c:if>
                    <c:if test="${usertype eq 'USER' || usertype eq 'ADMINISTRATOR'}">
                        <form style="margin-bottom: 4px" action="FrontController" method="post">
                            <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=FormNewsAddCommand.NAME%>">
                            <input class="btn btn-default" type="submit" name="register" value="Add News">
                        </form>
                    </c:if>
                    <c:if test="${usertype eq 'USER' || usertype eq 'ADMINISTRATOR'}">
                        <form action="FrontController" method="post">
                            <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=UserinfoCommand.NAME%>">
                            <input class="btn btn-default" type="submit" name="register" value="User setting">
                        </form>
                    </c:if>
                    <c:if test="${usertype eq 'USER' || usertype eq 'ADMINISTRATOR'}">
                        <form action="FrontController" method="post">
                            <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=FormCommentAddCommand.NAME%>">
                            <input type="hidden" name="newsIdForComment" value="${nw.id}">
                            <input class="btn btn-default" type="submit" name="addcomment" value="Add comment">
                        </form>
                    </c:if>

                </td>
                <td>
                    <blockquote>
                        <ul class="list-unstyled">
                            <c:forEach var="comment" items="${commentlist}">
                                <li>
                                    <h5><p>${comment.comment}</p></h5>
                                    <footer>
                                        <c:if test="${usertype eq 'ADMINISTRATOR'}">
                                            <form action="FrontController" method="post">
                                                <input type="hidden" name="<%=Attributes.COMMAND%>"
                                                       value="<%=DelCommentCommand.NAME%>">
                                                <input type="hidden" name="comId" value="${comment.id}">
                                                <input class="btn btn-default" type="submit" name="deletecomment"
                                                       value="Delete comment">
                                            </form>
                                            <form action="FrontController" method="post">
                                                <input type="hidden" name="<%=Attributes.COMMAND%>"
                                                       value="<%=FormCommentUpdCommand.NAME%>">
                                                <input type="hidden" name="comId" value="${comment.id}">
                                                <input class="btn btn-default" type="submit" name="correctcomment"
                                                       value="Correct">
                                            </form>
                                        </c:if>
                                        <p class="text-right">
                                            <c:forEach var="comuser" items="${userlist}">
                                                <c:if test="${comment.user_id == comuser.id}">
                                                    ${comuser.userDetail}
                                                </c:if>
                                            </c:forEach>
                                        </p>
                                    </footer>
                                </li>
                            </c:forEach>
                        </ul>
                    </blockquote>
                </td>
            </tr>
        </table>
    </div>
    <div class="page-buffer"></div>
</div>
<div class="page-footer">
    <p>Hirs Aleaksandr, 2015</p>
</div>
</div>
</body>
</html>
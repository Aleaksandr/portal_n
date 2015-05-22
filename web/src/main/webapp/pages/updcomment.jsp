<%@ page import="com.portal.util.Attributes" %>
<%@ page import="com.portal.commands.UpdateCommentCommand" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="updcomment" class="beans.Comment" scope="session"></jsp:useBean>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Add News</title>
    <link type="text/css" rel="stylesheet" href="resources/css/adnews.css">

</head>

<body>
<div class="addnews">
    <h1>Add comment</h1><br>

    <form action="FrontController" method="post">
        <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=UpdateCommentCommand.NAME%>">
        <textarea rows="15" cols="87" name="commentcorrect">${updcomment.comment}</textarea>
        <input type="submit" name="updcomment" value="Correct">
    </form>

</div>
</body>

</html>
<%@ page import="com.portal.util.Attributes" %>
<%@ page import="com.portal.commands.AddCommentCommand" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>


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
        <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=AddCommentCommand.NAME%>">
        <textarea rows="15" cols="87" name="comment"></textarea>
        <input type="submit" name="addcomment" value="Add Comment">
    </form>

</div>
</body>

</html>
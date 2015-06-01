<%@ page import="com.portal.util.Attributes" %>
<%@ page import="com.portal.commands.UpdateNewsCommand" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="updnews" class="pojos.News" scope="session"></jsp:useBean>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Correct News</title>
    <link type="text/css" rel="stylesheet" href="resources/css/adnews.css">
</head>

<body>
<div class="addnews">
    <h1>Correct news</h1><br>

    <form action="FrontController" method="post">
        <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=UpdateNewsCommand.NAME%>">
        <textarea rows="1" cols="87" name="titlenewscorrect">${updnews.title}</textarea>
        <textarea rows="20" cols="87" name="newscorrect">${updnews.item}</textarea>
        <input type="submit" name="updnews" value="Correct">
    </form>
</div>
</body>

</html>
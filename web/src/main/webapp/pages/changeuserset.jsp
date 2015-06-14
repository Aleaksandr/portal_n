<%@ page import="com.portal.util.Attributes" %>
<%@ page import="com.portal.commands.UpdateUserCommand" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Change User Setting</title>
    <link type="text/css" rel="stylesheet" href="resources/css/login.css">

</head>

<body>
<div class="login-card">
    <h1>Change User Setting</h1><br>

    <h3><font color="red">${message}</font></h3>

    <form action="FrontController" method="post">
        <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=UpdateUserCommand.NAME%>">
        <input type="text" name="email" placeholder="${user.email}">
        <input type="text" name="pass" placeholder="${user.pass}">
        <input type="submit" name="login" value="Update">
    </form>

</div>

</body>
</html>
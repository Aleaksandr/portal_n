<%@ page import="com.portal.util.Attributes" %>
<%@ page import="com.portal.commands.AddUserCommand" %>
<%@ page import="com.portal.commands.LoginCommand" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Register</title>
    <link type="text/css" rel="stylesheet" href="resources/css/login.css">

</head>

<body>
<div class="login-card">
    <h1>Register</h1><br>

    <h3><font color="red">${message}</font></h3>

    <form action="FrontController" method="post">
        <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=AddUserCommand.NAME%>">
        <input type="text" name="email" placeholder="E-mail">
        <input type="password" name="pass" placeholder="Password"><br>
        <p align="center">--------------------detail----------------------</p>
        <input type="text" name="fname" placeholder="Name">
        <input type="text" name="sname" placeholder="Surname">
        <input type="submit" name="login" value="Register">

    </form>
    <form action="FrontController" method="post">
        <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=LoginCommand.NAME%>">
        <input class="btn btn-default" type="submit" name="login" value="<< Back to login">
    </form>
</div>

</body>
</html>
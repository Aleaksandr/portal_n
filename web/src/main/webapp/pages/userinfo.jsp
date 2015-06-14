<%@ page import="com.portal.util.Attributes" %>
<%@ page import="com.portal.commands.IndexCommand" %>
<%@ page import="com.portal.commands.ChangeusersettingCommand" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>User Info</title>
    <link type="text/css" rel="stylesheet" href="resources/css/login.css">

</head>

<body>
<div class="login-card">
    <h1>User Info</h1><br>

    <h3><font color="red">${message}</font></h3>

    <form action="FrontController" method="post">
        <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=IndexCommand.NAME%>">
        <p>Loggin (E-mail): ${user.email}</p>
        <p>Password: ${user.pass}</p>
        <p>Name : ${usDet.getFName()}</p>
        <p>Surname: ${usDet.getSName()}</p>
        <input type="submit" name="login" value="Ok">
    </form>
    <form action="FrontController" method="post">
        <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=ChangeusersettingCommand.NAME%>">
        <input class="btn btn-default" type="submit" name="login" value="Change setting">
    </form>
</div>

</body>
</html>
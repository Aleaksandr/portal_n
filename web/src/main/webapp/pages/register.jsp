<%@ page import="com.portal.Attributes" %>
<%@ page import="com.portal.commands.CheckCommand" %>
<%@ page import="com.portal.commands.AddUserCommand" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Log-in</title>
  <link type="text/css" rel="stylesheet" href="resources/css/login.css">

</head>

<body>
<div class="login-card">
  <h1>Register</h1><br>

  <form action="index" method="post">
    <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=AddUserCommand.NAME%>">
    <input type="text" name="email" placeholder="E-mail">
    <input type="password" name="pass" placeholder="Password">
    <input type="submit" name="login" value="Register">
  </form>

</div>

</body>
</html>
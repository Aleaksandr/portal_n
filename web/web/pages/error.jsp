<%@ page import="com.study.vital.storage.entities.Product" %>
<%@ page import="com.study.vital.webApp.Attributes" %>
<%@ page import="com.study.vital.webApp.commands.IndexCommand" %>
<%@ page import="sun.misc.BASE64Encoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>e-shop</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <meta charset="utf-8" />
    <title>e-shop</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="resources/css/style.css">
    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="resources/css/bootstrap.min.css">
</head>
<body>
<header class="hero-unit">
    <h1 style="margin-left: 100px;">e-Shop</h1>
    <img src="resources/images/silsten_eshop.png" alt="eshop" style="position: absolute; top:20px; right: 30px;" width="150" />
</header>

<div class="row" >
    <div class="tab-content" style="padding: 0 30px 0 30px;">
        <h1>Извините, продукт отсутствует на складе</h1>
    </div>

    <ul class="pager" style="margin-left: 30px;">
        <li class="previous" style="cursor: pointer;">
            <form name="backForm" action="index" method="GET">
                <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=IndexCommand.NAME%>">
            </form>
            <a class="productLink">Выбрать что-нибудь другое</a>
        </li>
    </ul>
</div>

<script type="text/javascript" src="resources/script/jquery-2.0.0.js"></script>
<script src="resources/script/bootstrap.min.js"></script>
<script type="text/javascript">
        $(".productLink").off("click");
        $(".productLink").on("click", function(evt) {
            var currentTarget = evt.currentTarget;
            var li = $(currentTarget).parent("li");
            var form = li.children("form")[0];
            $(form).submit();
        });
</script>
</body>
</html>
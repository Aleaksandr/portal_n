<%@ page import="com.study.vital.storage.assist.Categories" %>
<%@ page import="com.study.vital.storage.entities.Product" %>
<%@ page import="com.study.vital.webApp.Attributes" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.study.vital.webApp.commands.ProductDetailCommand" %>
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
<%
    Map<Categories, List<Product>> map = (Map<Categories, List<Product>>)request.getAttribute(Attributes.PRODUCT_MAP);
%>
<header class="hero-unit">
    <h1 style="margin-left: 100px;">e-Shop</h1>
    <img src="resources/images/silsten_eshop.png" alt="eshop" style="position: absolute; top:20px; right: 30px;" width="150" />
</header>

<div class="row">
    <div class="span4" style="padding: 0;">
        <div class="tabbable tabs-left span4">
            <ul class="nav nav-tabs span3">
                <%
                    for (Categories category : Categories.values()) {
                %>

                <li class=<%=category.getCode()==1?"active":""%>><a href="#<%=category.name()%>" data-toggle="tab"><%=Categories.getText(category)%></a></li>

                <%
                    }
                %>
            </ul>
        </div>

    </div>
    <div class="span8">
        <div class="tab-content">
            <%
                for (Categories category : Categories.values()) {
                    List<Product> products = map.get(category);
            %>

            <div class="tab-pane <%=category.getCode()==1?"active":""%>" id="<%=category.name()%>">
                <ul class="nav nav-tabs nav-stacked" style="cursor: pointer;">
                <%
                    for(Product product: products) {
                %>
                    <li>
                        <form name="product_<%=product.getId()%>Form" action="index" method="GET">
                            <input type="hidden" name="<%=Attributes.TYPE%>" value="<%=category.getCode()%>">
                            <input type="hidden" name="<%=Attributes.ID%>" value="<%=product.getId()%>">
                            <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=ProductDetailCommand.NAME%>">
                        </form>
                        <a class="productLink"><%=product.getTitle()%> </a>
                    </li>


                <%
                    }
                %>
                </ul>
            </div>

            <%
                }
            %>
        </div>
    </div>
</div>

<footer id="footer">
    <p>Виталий Адаменко, БГУ 2013</p>
</footer>

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
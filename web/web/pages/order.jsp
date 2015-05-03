<%@ page import="com.study.vital.webApp.Attributes" %>
<%@ page import="com.study.vital.webApp.commands.IndexCommand" %>
<%@ page import="com.study.vital.webApp.commands.OrderCommand" %>
<%@ page import="com.study.vital.webApp.commands.SuccessCommand" %>
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
    <link type="text/css" rel="stylesheet" href="resources/css/bootstrap.min.css">
</head>
<body>
<%
    String productName = (String)request.getAttribute(Attributes.PRODUCT_NAME);
    Integer productId = (Integer)request.getAttribute(Attributes.ID);
%>
<header class="hero-unit">
    <h1 style="margin-left: 100px;">e-Shop</h1>
    <img src="resources/images/silsten_eshop.png" alt="eshop" style="position: absolute; top:20px; right: 30px;" width="150" />
</header>

<div class="row">
    <div class="">
        <div class="tab-content" style="padding: 0 30px 0 30px;">

            <div id="notify" class="alert alert-error" style="display: none;">

            </div>

            <div class="row-fluid">
                <h3><%=productName%></h3>
                <form class="form-horizontal">
                    <div class="control-group">
                        <label class="control-label" for="inputFirstName">Имя</label>
                        <div class="controls">
                            <input class="span6" type="text" id="inputFirstName" placeholder="Иван" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputSecondName">Фамилия</label>
                        <div class="controls">
                            <input class="span6" type="text" id="inputSecondName" placeholder="Иванов" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputPhone">Контактный телефон</label>
                        <div class="controls">
                            <input class="span6" type="text" id="inputPhone" placeholder="Мобильный или стационарный" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputAddress">Адрес</label>
                        <div class="controls">
                            <textarea class="span6" style="resize: none;" id="inputAddress" placeholder="Город, улица, дом, квартира" ></textarea>
                        </div>
                    </div>
                </form>

                <ul class="pager">
                    <li class="previous" style="cursor: pointer;">
                        <form id="backForm" name="backForm" action="index" method="GET">
                            <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=IndexCommand.NAME%>">
                        </form>
                        <a class="productLink" href="#myModal" role="button" class="btn" data-toggle="modal">Отказаться от заказа</a>
                    </li>
                    <li class="next"  style="cursor: pointer;">
                        <form name="orderForm" action="index" method="GET">
                            <input type="hidden" name="<%=Attributes.ID%>" value="<%=productId%>">
                            <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=SuccessCommand.NAME%>">
                        </form>
                        <a class="productLink">Подтвердить заказ</a>
                    </li>
                </ul>

                <!-- Modal -->
                <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">Вы уверены?</h3>
                    </div>
                    <div class="modal-body">
                        <p>Пожалуйста, подтвердите, что хотите отказатьсяч от покупки</p>
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">Закрыть</button>
                        <button id="return" class="btn btn-danger">Я уверен</button>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="resources/script/jquery-2.0.0.js"></script>
<script src="resources/script/bootstrap.min.js"></script>
<script type="text/javascript">
    $("#return").off("click");
    $("#return").on("click", function(evt) {
        $("#backForm").submit();
    });

    $(".productLink").off("click");
    $(".productLink").on("click", function(evt) {
        var currentTarget = evt.currentTarget;
        var li = $(currentTarget).parent("li");
        var form = li.children("form")[0];

        if(li[0].className != "previous") {
            var firstName = $("#inputFirstName").val();
            var secondName = $("#inputSecondName").val();
            var phone = $("#inputPhone").val();
            var address = $("#inputAddress").val();
            var notifier = $("#notify");

            if(!firstName) {
                notifier.html("Пожалуйста, введите Имя");
                notifier.fadeIn();
                return
            }

            if(!secondName) {
                notifier.html("Пожалуйста, введите Фамилию");
                notifier.fadeIn();
                return
            }

            if(!phone) {
                notifier.html("Пожалуйста, введите Номер Телефона");
                notifier.fadeIn();
                return
            }

            if(!address) {
                notifier.html("Пожалуйста, введите Адрес");
                notifier.fadeIn();
                return
            }

            notifier.fadeOut();

            $(form).submit();
        }
    });
</script>
</body>
</html>
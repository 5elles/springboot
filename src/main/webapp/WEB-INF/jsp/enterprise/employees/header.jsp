<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>employees</title>
        <link rel="stylesheet" href="/css/style.css">
    </head>
    <body>
    <c:set var="admin" scope="session" value="ROLE_ADMIN"/>
    <c:set var="manager" scope="session" value="ROLE_MANAGER"/>
    <c:set var="customer" scope="session" value="ROLE_CUSTOMER"/>


    <div class="main">
        <section class="hero">
            <div class="container">
                <div class="hero__message">
                    Your Money or Your Life
                </div>
            </div>
        </section>

        <section>
            <ul class="header">

                <c:forEach var="item" items="${personData.roles}">
                    <c:if test="${item == admin || item == manager}">
                        <li><a class="active" href="/customers">Клиенты</a></li>
                    </c:if>
                </c:forEach>
                <li><a class="header_li" href="/employees">Сотрудники</a></li>
                <li><a class="header_li" href="/staffingTable">Штатное расписание</a></li>
                <li><a class="header_li" href="/findEmployee">Поиск сотрудника</a></li>
                <li><a class="header_li" href="/home">Домой</a></li>
                <li><a class="header_li" href="/logout">Выход</a></li>
            </ul>
        </section>
            <%String style = "border=\"1\" cellspacing=\"0\" cellpadding=\"12\"";%>

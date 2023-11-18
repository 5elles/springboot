<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>person</title>
        <link rel="stylesheet" href="/css/style.css">
    </head>
    <body>
    <section class="hero">
        <div class="container">
            <div class="hero__message">
                Your Money or Your Life
            </div>
        </div>
    </section>

    <section>
        <ul class="header">
<%--            реализовать условие отображения вкладки с клиентами в зависимости от роли пользователя--%>
            <li><a class="active" href="/customers"></a></li>
            <li><a class="header_li" href="/employees"></a></li>
            <li><a class="header_li" href="/staffingTable"></a></li>
            <li><a class="header_li" href="">Выход</a></li>
        </ul>
    </section>
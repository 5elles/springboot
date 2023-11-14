<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title></title>
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
<%--            реализовать условие отображения вкладки с персоналом в зависимости от роли пользователя--%>
            <li><a class="active" href="/employees">Управление персоналом</a></li>
            <li><a class="header_li" href="/customers">Клиенты</a></li>
            <li><a class="header_li" href="/operationsLog">Учёт банковских операций</a></li>
            <li><a class="header_li" href="">Поиск лица</a></li>
        </ul>
    </section>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>IBank</title>
        <link rel="stylesheet" href="/css/style.css">
    </head>
    <body>
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
                <li><a class="active" href="#home">Домой</a></li>
                <li><a class="header_li" href="/logout">Выход</a></li>
            </ul>
        </section>
        <%String style = "border=\"1\" cellspacing=\"0\" cellpadding=\"12\"";%>

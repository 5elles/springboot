<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>registration</title>
    <style>
        .main {
            position: relative;
            /*187px + 10% (19px) высота footer*/
            min-height: calc(100vh - 187px - 19px);
        }

        html * {
            margin: 0;
            padding: 0;
        }

        .container {
            display: flex;
            align-items: center;
            flex-direction: column;
            padding: 16px;
        }

        form {
            font-family: Arial, Helvetica, sans-serif;
            display: flex;
            width: 600px;
            max-width: 100%;
            flex-direction: column;
        }

        form fieldset {
            padding: 20px;
            border: 1px solid #9a9a9a;
        }

        form .field {
            width: 100%;
            display: flex;
            margin-bottom: 10px;
            justify-content: space-between;
            align-items: center;
            column-gap: 10px;
        }

        form label {
            font-weight: bold;
            font-size: 12px;
            font-family: "Helvetica", serif;
            display: inline-block;
            width: 200px;
            text-align: left;
        }

        form button {
            margin-top: 10px;
            margin-left: auto;
            padding: 12px 36px;
            border-radius: 8px;
            border: 1px solid #9a9a9a;
        }

        .tables .table:not(:last-child) {
            margin-bottom: 30px;
        }

        table {
            font-family: Arial, Helvetica, sans-serif;
            margin: auto;
            border-collapse: collapse;
            border-spacing: 1px;
            width: 100%;
            border: 1px solid #ddd;
        }

        th {
            padding: 16px;
        }

        td {
            text-align: left;
            padding: 16px;
        }

        li {
            list-style-type: none;
        }


        .btn {
            border: 1px solid black;
            border-radius: 5px;
            background-color: white;
            color: black;
            padding: 10px 28px;
            font-size: 16px;
            cursor: pointer;
            text-decoration: none;
        }


        .info {
            border-color: #2196F3;
            color: dodgerblue;
        }

        .info:hover {
            background: #2196F3;
            color: white;
        }

        /* Style inputs */
        input, select {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }


        .hero {
            padding-top: 40px;
            padding-bottom: 40px;
            background-image: url('https://i.ibb.co/PM6cMBn/image.png');
            background-size: cover;
            background-position: center center;
        }

        .hero__message {
            font-family: Roboto, sans-serif;
            font-style: italic;
            font-weight: 500;
            font-size: 36px;
            line-height: 56px;
            text-align: center;
            color: #fff;
        }

        .header {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #0a2149;
            position: sticky;
            top: 0;
        }

        .header li {
            padding: 14px 16px;
            float: left;
            color: white;
        }

        .header li a {
            color: inherit;
            text-decoration: none;
        }

        .header li:hover {
            background-color: white;
            color: #0a2149;
            transition: background-color linear 200ms, color ease-in-out 500ms;
        }

        .header div {
            padding: 14px 16px;
            float: right;
            color: white;
        }


        .footer {
            position: absolute;
            bottom: 0;
            left: 0;
            right: 0;
            background-color: #0a2149;
            text-align: center;
            padding: 10px;
            color: white;
            font-family: "Helvetica Now", serif;
            transform: translateY(110%);
        }

        .footer > div {
            display: flex;
            flex-direction: column;
            row-gap: 20px;
        }
        .footer a {
            text-decoration: none;
            color: #fff;
            font-size: 48px;
        }

        article p {
            width: 800px;
            max-width: 100%;
            /*display: block;*/
            margin-block-start: 1em;
            margin-block-end: 1em;
            margin-inline-start: 0px;
            margin-inline-end: 0px;
            text-align: justify;
            margin: 20px 0;
            padding: 0;
        }
    </style>
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
            <li><a class="header_li" href="/home">Домой</a></li>
            <li><a class="header_li" href="/registration">Регистрация</a></li>
            <li><a class="header_li" href="/logout">Выход</a></li>
        </ul>
    </section>
    <div>
        <div class="container">
            <h3>Пожалуйста, авторизуйтесь</h3>
            <form method="post" name="user" action="/login">
                <input type="text" id="id-number" name="username" placeholder="введите ID гражданина..." required>
                <input type="password" id="password" name="password" placeholder="введите пароль..." required>
                <button class="btn info" type="submit">Отправить</button>
            </form>
        </div>
    </div>
    <section class="footer">
        <div>
            <p class="company">Cool Bank</p>
            <p class="mail">
                <a id="mail" href="mailto:aaaaaa@aa.com">Bank</a>
            </p>
            <p class="city">Hrodna <br>vul. Zamkavaja, bud.1<br>+375152111111</p>
        </div>
    </section>
</div>
</body>
</html>

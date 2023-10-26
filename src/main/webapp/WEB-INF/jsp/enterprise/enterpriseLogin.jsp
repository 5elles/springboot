<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>log in</title>
</head>
<body>
Здесь будет форма
<form class="form-1">
    <p class="field">
        <input type="text" name="login" placeholder="Логин или емэйл">
        <i class="icon-user icon-large"></i>
    </p>
    <p class="field">
        <input type="password" name="password" placeholder="Пароль">
        <i class="icon-lock icon-large"></i>
    </p>
    <p class="submit">
        <button type="submit" name="submit"><i class="icon-arrow-right icon-large"></i></button>
    </p>
</form>

<a href="/home?id=1">Личный кабинет сотрудника</a>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>home page</title>
    </head>
    <body>
    Добро пожаловать, <b>${employee.person.firstName} ${employee.person.middleName}</b>!<br>


    <a href="/employees">Управление персоналом</a><br>

    <a href="/customers">Работа с клиентами</a><br>
    </body>
</html>

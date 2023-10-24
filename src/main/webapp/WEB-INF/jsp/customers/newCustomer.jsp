<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <title>customers</title>
    </head>
    <body>
        <b>Новый сотрудник:</b>
        <br>
        <p>
            <form th:method="POST" th:action="@{/customers}" th:object="${customer}">
                <label for="agreementNumber">№ договора: </label><input type="text" th:field="*{agreementNumber}" id="agreementNumber"><br>
                <label for="agreementDate">Дата: </label><input type="text" th:field="*{agreementDate}" id="agreementDate"><br>
                <input type="submit" value="Create!">
            </form>
        </p>
    </body>
</html>
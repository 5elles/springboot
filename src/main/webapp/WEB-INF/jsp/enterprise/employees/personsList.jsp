<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>search result</title>
        <link rel="stylesheet" href="/css/style.css">
    </head>
    <body>
    <div>
        <div>
            <table border="1" cellspacing="0" cellpadding="12">
                <tr>
                    <th colspan="6" align="center">результат поиска</th>
                </tr>
                <c:choose>
                    <c:when test="${data.size() > 0}">
                        <tr>
                            <th>статус</th>
                            <th>паспортные данные</th>
                        </tr>
                        <c:forEach items="${data}" var="item">
                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${item.employeeId != null}">
                                            сотрудник
                                        </c:when>
                                        <c:otherwise>
                                            <form action="/showNewWageRateForm?pid=${item.id}" method="post">
                                                <button class="btn success">оформить</button>
                                            </form>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${item.employeeId != null}">
                                            <a href="/employee?id=${item.employeeId}">${item.lastName} ${item.firstName} ${item.middleName}</a>
                                        </c:when>
                                        <c:otherwise>
                                            ${item.lastName} ${item.firstName} ${item.middleName}
                                        </c:otherwise>
                                    </c:choose>
                                    , ${item.dateOfBirth.getDayOfMonth()}.${item.dateOfBirth.getMonthValue()}.${item.dateOfBirth.getYear()}
                                    г.р. <br>
                                    ID ${item.citizenIdNumber}, паспорт ${item.passportNumber}
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="6">
                                <div class="text-red">
                                    <p>
                                        поиск не дал результатов
                                    </p>
                                </div>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                <tr>
                    <td colspan="2">
                        <form class="container" action="/newPersonEmployee">
                            <button class="btn success">добавить новое физлицо в систему</button>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <br>
    </body>
</html>
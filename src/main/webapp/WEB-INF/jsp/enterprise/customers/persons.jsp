<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>search result</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${data.size() > 0}">
                <div>
                    <div>
                        <table border="1" cellspacing="0" cellpadding="12">
                            <tr>
                                <td colspan="6" align="center">Результат поиска</td>
                            </tr>
                            <tr>
                                <th>статус</th>
                                <th>паспортные данные</th>
                            </tr>
                            <c:forEach items="${data}" var="item">
                                <tr>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.customerId > 0}">
                                                клиент
                                            </c:when>
                                            <c:otherwise>
                                                <form action="/newcustomer?pid=${data.id}">
                                                    <button>оформить</button>
                                                </form>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.customerId > 0}">
                                                <a href="/customer?id=${item.customerId}">${item.lastName} ${item.firstName} ${item.middleName}</a>
                                            </c:when>
                                            <c:otherwise>
                                                ${item.lastName} ${item.firstName} ${item.middleName}
                                            </c:otherwise>
                                        </c:choose>
                                        , ${item.dateOfBirth.getDayOfMonth()}.${item.dateOfBirth.getMonthValue()}.${item.dateOfBirth.getYear()} г.р. <br>
                                        ID ${item.citizenIdNumber}, паспорт ${item.passportNumber}
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                Поиск не дал результатов
            </c:otherwise>
        </c:choose>

        <br>
        <form action="/newperson">
            <button>Создать новый профиль</button>
        </form>
    </body>
</html>
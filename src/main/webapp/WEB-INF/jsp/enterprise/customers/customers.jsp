<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>customers</title>
    </head>


    <body>
        <div>
            <div>
                <h5>поиск лица</h5>
                <form id="searching_form" name="lastName" action="/persons" method="get" >
                    <label for="lastName">фамилия </label>
                    <input type="text" name="lastName" id="lastName" required/> <br/>

                    <label for="firstName">имя </label>
                    <input type="text" name="firstName" id="firstName" required/><br/>

                    <label for="middleName">отчество </label>
                    <input type="text" name="middleName" id="middleName" required/><br/>

                    <input style="width: 250px; margin: 20px" type="submit" value="найти" name="search" />
                </form>
            </div>
        </div>
        <div>
            <div>
                <table border="1" cellspacing="0" cellpadding="12">
                    <tr>
                        <td colspan="6" align="center">Список клиентов банка</td>
                    </tr>
                    <tr>
                        <th>паспортные данные</th>
                        <th>договор</th>
                    </tr>
                    <c:forEach items="${customers}" var="item">
                        <tr>
                            <td>
                                <a href="/customer?id=${item.id}">${item.lastName} ${item.firstName} ${item.middleName}</a>, ${item.dateOfBirth.getDayOfMonth()}.${item.dateOfBirth.getMonthValue()}.${item.dateOfBirth.getYear()} г.р. <br>
                                ID ${item.citizenIdNumber}, паспорт ${item.passportNumber}
                            </td>

                            <td align="center">
                                    ${item.agreementNumber} от ${item.agreementDate.getDayOfMonth()}.${item.agreementDate.getMonthValue()}.${item.agreementDate.getYear()} г.<br>
                                    статус:
                                        <c:choose>
                                            <c:when test="${item.closureDate == null}">
                                                актуальный
                                            </c:when>
                                            <c:otherwise>
                                                архивный
                                            </c:otherwise>
                                        </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <br>
        <a href="/operationsLog">Журнал учета банковских операций</a>
    </body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Employee</title>
</head>

<body>
<b>Досье</b>
<p>
    Фамилия:    <b>${employee.person.lastName}</b> <br>
    Имя:         <b>${employee.person.firstName}</b><br>
    Отчество:   <b>${employee.person.middleName}</b> <br>
    Дата рожд.: ${employee.person.dateOfBirth} <br>
    ID:         ${employee.person.citizenIdNumber} <br>
    Паспорт:    ${employee.person.passportNumber} <br>
</p>
<br>
<ul>
    <c:forEach items = "${employees}" var = "employee">
        <li>
            id: ${employee.id} <br>
            ${employee.person.lastName} ${employee.person.firstName} ${employee.person.middleName}<br>
            Дата рожд.: ${employee.person.dateOfBirth} <br>
            ID:         ${employee.person.citizenIdNumber} <br>
            Паспорт:    ${employee.person.passportNumber} <br>
            Должности: <br>
            <ol>
                <c:forEach items = "${wagerates}" var="wageRate">
                    <li>
                        <c:choose>
                            <c:when test=" ${wageRate.finishDate == null}">
                                c ${wageRate.startDate} по н.в. - ${wageRate.position.positionName}. Оклад:  ${wageRate.position.salary} руб. Ставка: ${wageRate.rate}."
                            </c:when>
                            <c:otherwise>
                                c ${wageRate.startDate} по ${wageRate.finishDate} - ${wageRate.position.positionName}. Оклад:  ${wageRate.position.salary} руб. Ставка: ${wageRate.rate}."
                            </c:otherwise>
                        </c:choose>

                    </li>
                </c:forEach>
            </ol>

        </li>
    </c:forEach>
</ul>
</body>
</html>

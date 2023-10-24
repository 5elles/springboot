<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>person</title>
</head>
<body>
<b>Досье</b>
<br>
<p>
    Фамилия: <b>${person.lastName}</b><br>
    Имя: <b>${person.firstName}</b> <br>
    Отчество: <b>${person.middleName}</b> <br>
    Дата рождения: <b>${person.dateOfBirth}</b> <br>
    ID гражданина: <b>${person.citizenIdNumber}</b> <br>
    Паспорт №: <b>${person.passportNumber}</b> <br>

    </p>
<p>
    Отношение к банку:<br>
    Сотрудник:
    <c:choose>
    <c:when test="${person.isStaff == 0}"><b>нет</b></c:when>
    <c:otherwise><b>да</b></c:otherwise>
    </c:choose>;

    Клиент:
    <c:choose>
        <c:when test="${person.isClient == 0}"><b>нет</b></c:when>
        <c:otherwise><b>да</b></c:otherwise>
    </c:choose>
    <br>
</p>

<p>
    Контактные данные:
    Телефон:
    <ul>
        <c:forEach items="${person.contact.phoneNumbers}" var="entity">
            <li>
                    ${entity.phoneNumber}
            </li>
        </c:forEach>
    </ul>

    Email:
    <ul>
        <c:forEach items="${person.contact.emails}" var="entity">
            <li>
                    ${entity.email}
            </li>
        </c:forEach>
    </ul>

</p>


<p>
    Адреса регистрации:
<ol>
    <c:forEach items="${person.addresses}" var="entity">
        <li>
                ${entity.settlement.region.country.countryName},
                ${entity.settlement.region.regionName},
                ${entity.settlement.settlementType.shortName} ${entity.settlement.settlementName},
                ${entity.streetType.shortName} ${entity.streetName}
               д. ${entity.houseNumber}
               кв. ${entity.apartmentNumber}
        </li>
    </c:forEach>
</ol>
</p>


</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>
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
        <b>Отношение к банку:</b><br>
        Сотрудник:
        <c:choose>
        <c:when test="${person.isStaff == 0}">нет</c:when>
        <c:otherwise><a href="employee?id=${employeesID}">да</a></c:otherwise>
        </c:choose>;

        Клиент:
        <c:choose>
            <c:when test="${person.isClient == 0}">нет</c:when>
            <c:otherwise><a href="customer?id=${customersID}">да</a></c:otherwise>
        </c:choose>
        <br>
    </p>

    <p>
        <p><b>Контактные данные:</b></p> <br>
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
        <b>Адреса регистрации:</b>
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

<%@ include file="footer.jsp" %>
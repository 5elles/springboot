<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>customer</title>
    </head>
    <body>
    <table border="1" cellspacing="0" cellpadding="12">
        <tr>
            <td colspan="4" align="center">персональные данные клиента</td>
        </tr>
        <tr>
            <th colspan="3"><b>${customer.person.lastName} ${customer.person.firstName} ${customer.person.middleName}</b></th>
            <td>${customer.person.dateOfBirth.getDayOfMonth()}.${customer.person.dateOfBirth.getMonthValue()}.${customer.person.dateOfBirth.getYear()}</td>
        </tr>
        <tr>
            <td>личный №</td>
            <td colspan="3"><b>${customer.person.citizenIdNumber}</b></td>

        </tr>
        <tr>
            <td>паспорт </td>
            <td colspan="3"><b>${customer.person.passportNumber}</b></td>
        </tr>
        <tr></tr>
        <tr><td colspan="4" align="center">контактные данные</td></tr>
        <tr>
            <td rowspan="${contact.phoneNumbers.size()}">телефон</td>
            <c:forEach items="${contact.phoneNumbers}" var="item">
            <td colspan="3">${item.phoneNumber}</td>
        </tr>
        </c:forEach>
        <tr>
            <td rowspan="${contact.emails.size()}">email</td>
            <c:forEach items="${contact.emails}" var="item">
            <td colspan="3">${item.email}</td>
        </tr>
        </c:forEach>
        <tr><td colspan="4" align="center">адрес</td></tr>
        <tr>
            <td colspan="4">
                <ol>
                    <c:forEach items="${customer.person.addresses}" var="entity">
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
            </td>
        </tr>
    </table>


    <p>
    <table border="1" cellspacing="0" cellpadding="12">
        <tr>
            <td colspan="6" align="center">банковские счета клиента</td>
        </tr>
        <tr>
            <th>Дата открытия</th>
            <th>Номер счета</th>
            <th>Валюта счета</th>
            <th>Баланс в валюте счета</th>
            <th>Баланс в BYN</th>
            <th>Дата закрытия</th>
        </tr>
        <c:forEach items = "${customer.bankAccounts}" var = "entity">
            <tr>
                <td>${entity.openingDate.getDayOfMonth()}.${entity.openingDate.getMonthValue()}.${entity.openingDate.getYear()}</td>
                <td><a href="/bankAccount?aid=${entity.id}&cid=${customer.id}">${entity.accountNumber}</a></td>
                <td>${entity.currency.currencyAbbreviation}</td>
                <td>${entity.currentBalance}</td>
                <td>${entity.currency.currencyRate * entity.currentBalance}</td>
                <td>${entity.closureDate.getDayOfMonth()}.${entity.closureDate.getMonthValue()}.${entity.closureDate.getYear()}</td>
            </tr>
        </c:forEach>

    </table>
    </p>

    </body>
</html>
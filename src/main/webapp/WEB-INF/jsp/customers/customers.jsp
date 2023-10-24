<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>customers</title>
</head>
    <body>
        Список всех клиентов
    <br>
        <p>
            <ol>
                <c:forEach items="${customers}" var="customer">
                    <li>
                        <a href="/customers/${customer.id}">${customer.person.citizenIdNumber}</a>
                        , ${customer.person.lastName} ${customer.person.firstName} ${customer.person.middleName},
                            ${customer.person.dateOfBirth}.
                        Договор обслуживания № ${customer.agreementNumber} от ${customer.agreementDate}
                    </li>

                </c:forEach>
            </ol>
        </p>
    </body>
</html>
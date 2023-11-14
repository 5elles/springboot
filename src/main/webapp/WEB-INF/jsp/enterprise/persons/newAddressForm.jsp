<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
    <head>
        <meta charset="UTF-8">
        <title>form</title>
        <script src="/js/index.js" type="text/javascript"></script>
        <link rel="stylesheet" href="/css/style.css">
    </head>
    <body>
        <div>
            <div class="container">
                <h3>регистрация нового адреса</h3>
                <form id="add_form" class="address" action="createNewAddress?cid=${cid}" method="post">


                <label for="countryName">страна</label>
                <select name="countryName" id="countryName" >
                    <c:forEach items="${data.countries}" var="item">
                        <option id=${item.id} value=${item.id}>${item.countryName}</option>
                    </c:forEach>
                </select>


                <label for="regionName">регион</label>
                <select name="regionName" id="regionName" required="">
                    <c:forEach items="${data.regions}" var="item">
                        <option id=${item.countryId} value=${item.id}>${item.regionName}</option>
                    </c:forEach>
                </select>

                <label for="settlementTypeName">вид населенного пункта</label>
                <select name="settlementTypeName" id="settlementTypeName" required="">
                    <c:forEach items="${data.settlementTypes}" var="item">
                        <option id=${item.id} value="${item.id}">${item.settlementTypeName}</option>
                    </c:forEach>
                </select>

                <label for="settlementName">название населенного пункта</label>
                <select name="settlementName" id="settlementName" required="">
                    <c:forEach items="${data.settlements}" var="item">
                        <option id=${item.id} value="${item.id}">${item.settlementName}</option>
                    </c:forEach>
                </select>

                <label for="streetType">тип улицы</label>
                <select name="settlementName" id="streetType" required="">
                    <c:forEach items="${data.streetTypes}" var="item">
                        <option id=${item.id} value="${item.id}">${item.streetTypeName}</option>
                    </c:forEach>
                </select>

                <label for="streetName">название улицы</label>
                <input type="text" id="streetName" name="streetName" placeholder="Название улицы без указания типа...">

                <label for="houseNumber">номер дома</label>
                <input type="text" id="houseNumber" name="houseNumber" placeholder="Номер дома, корпус...">

                <label for="streetName">номер квартиры</label>
                <input type="text" id="apartment_number" name="apartmentNumber" placeholder="Номер квартиры...">

                <button type="submit" name="Add">save</button>
                </form>
            </div>
        </div>
    </body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>

<script src="/js/index.js" type="text/javascript"></script>


<div class="container">
        <h3>регистрация нового адреса</h3>
        <form id="add_form" class="address" action="createNewAddress${suffix}${cid}" method="post">

        <div class="field">
            <label for="countryName">страна</label>
            <select name="countryName" id="countryName" value="">
                <option value="">выберите страну</option>
                <c:forEach items="${data.countries}" var="item">
                    <option value=${item.id}>${item.countryName}</option>
                </c:forEach>

            </select>
        </div>


            <div class="field">
                <label for="regionName">регион</label>
                <select name="regionName" id="regionName" required="" value="" disabled>
                    <option value="">выберите регион</option>

                    <c:forEach items="${data.regions}" var="item">
                        <option id=${item.countryId} value=${item.id}>${item.regionName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="field">
                <label for="settlementTypeName">вид населенного пункта</label>
                <select name="settlementTypeName" id="settlementTypeName" required="">
                    <c:forEach items="${data.settlementTypes}" var="item">
                        <option id=${item.id} value="${item.id}">${item.settlementTypeName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="field">
                <label for="settlementName">название населенного пункта</label>
                <select name="settlementId" id="settlementName" required="" value="" disabled>
                    <option value="">выберите название населенного пункта</option>
                    <c:forEach items="${data.settlements}" var="item">
                        <option id=${item.regionId} value="${item.id}">${item.settlementName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="field">
                <label for="streetType">тип улицы</label>
                <select name="streetTypeId" id="streetType" required="">
                    <c:forEach items="${data.streetTypes}" var="item">
                        <option id=${item.id} value="${item.id}">${item.streetTypeName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="field">
                <label for="streetName">название улицы</label>
                <input type="text" id="streetName" name="streetName" placeholder="Название улицы без указания типа...">
            </div>
            <div class="field">
                <label for="houseNumber">номер дома</label>
                <input type="text" id="houseNumber" name="houseNumber" placeholder="Номер дома, корпус...">
            </div>
            <div class="field">
                <label for="streetName">номер квартиры</label>
                <input type="text" id="apartment_number" name="apartmentNumber" placeholder="Номер квартиры...">
            </div>
            <div class="field">
                 <input type="hidden" id="hidden" name="personId" value="${pid}">
                <button class="success" type="submit" name="Add">save</button>
            </div>
        </form>
    </div>
<%@ include file="footer.jsp" %>
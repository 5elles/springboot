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
    <div>
        <h3>Address form</h3>
        <form id="add_form" name="address" action="/newAddress" method="post" >
            <label for="countryName">страна </label>
            <select name="countryName" id="countryName" required="">
                <c:forEach items="${data.countries}" var="item" >
                    <option id=${item.id} value="country">${item.countryName}</option>
                </c:forEach>
            </select><br/>

            <label for="regionName">регион </label>
            <select name="regionName" id="regionName" required = "">
            <c:forEach items="${data.regions}" var="item" >
<%--                <c:if test="${countryName.id} == ${item.countryId}">--%>
                    <option id=${item.countryId} value="regionName">${item.regionName}</option>
<%--                </c:if>--%>
            </c:forEach>
            </select><br/>


            <label for="settlementTypeShortName">settlement Type Short Name: </label>
            <input type="text" name="settlementTypeShortName" id="settlementTypeShortName" required/><br/>

            <label for="settlementName">settlement Name</label>
            <input type="text" name="settlementName" id="settlementName" required/><br/>

            <label for="streetTypeShortName">street Type Short Name</label>
            <input type="text" name="streetTypeShortName" id="streetTypeShortName" required/><br/>

            <label for="streetName">street Name: </label>
            <input type="text" name="streetName" id="streetName" required/><br/>

            <label for="houseNumber">house Number: </label>
            <input type="text" name="houseNumber" id="houseNumber" required/><br/>

            <label for="apartmentNumber">apartment Number: </label>
            <input type="text" name="apartmentNumber" id="apartmentNumber" required/><br/>

            <input type="hidden" name="fromAccountId" id="fromAccountId" value="${personId}"/><br/>

            <input style="width: 250px; margin: 20px" type="submit" value="save" name="Add" />
        </form>
    </div>
</div>
</body>
</html>
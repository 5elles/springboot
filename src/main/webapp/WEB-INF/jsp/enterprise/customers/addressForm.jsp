<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>form</title>
</head>
<body>
<div>
    <div>
        <h3>Address form</h3>
        <form id="add_form" name="address" action="/newAddress" method="post" >
            <label for="countryName">country Name </label>
            <input type="text" name="countryName" id="countryName" required /> <br/>

            <label for="regionName">region Name: </label>
            <input type="text" name="regionName" id="regionName" required/><br/>

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
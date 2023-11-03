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
                <h3>форма для Person</h3>
                <form id="add_form" name="payment" action="/newperson" method="post" >
                    <label for="lastName">lastname </label>
                    <input type="text" name="lastName" id="lastName" required /> <br/>

                    <label for="firstName">firstName: </label>
                    <input type="text" name="firstName" id="firstName" required/><br/>

                    <label for="middleName">middleName: </label>
                    <input type="text" name="middleName" id="middleName" required/><br/>

                    <label for="dateOfBirth">date of birth: </label>
                    <input type="date" name="dateOfBirth" id="dateOfBirth" required/><br/>

                    <label for="citizenIdNumber">citizen ID: </label>
                    <input type="text" name="citizenIdNumber" id="citizenIdNumber" required/><br/>

                    <label for="passportNumber">passport number: </label>
                    <input type="text" name="passportNumber" id="passportNumber" required/><br/>

                    <input style="width: 250px; margin: 20px" type="submit" value="save" name="Add" />
                </form>
            </div>
        </div>
    </body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
    <head>
        <meta charset="UTF-8">
        <title>new tel</title>
        <link rel="stylesheet" href="/css/style.css">
    </head>
    <body>
        <div>
            <div class="container">
                <h3>Добавление нового номера телефона</h3>
                <form id="add_form" class="form" name="newPhoneNumber" action="/savePhoneNumber?cid=${cid}" method="post" >
                    <fieldset>
                        <legend>телефон</legend>
                        <div class="field">
                            <label for="phoneNumber">номер</label>
                            <input type="tel" name="phoneNumber" id="phoneNumber" required/>
                        </div>

                        <div class="field">
                            <input hidden="hidden" type="text" name="personId" id="personId" value="${pid}" required/>
                        </div>
                    </fieldset>
                    <button class="btn success" type="submit" name="Add">сохранить</button>
                </form>
            </div>
        </div>
    </body>
</html>
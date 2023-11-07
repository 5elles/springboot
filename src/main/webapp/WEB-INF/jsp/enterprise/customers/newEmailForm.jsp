<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
    <head>
        <meta charset="UTF-8">
        <title>new email</title>
        <link rel="stylesheet" href="/css/style.css">
    </head>
    <body>
        <div>
            <div class="container">
                <h3>Добавление нового номера телефона</h3>
                <form id="add_form" class="form" name="newEmail" action="/saveEmail?cid=${cid}" method="post" >
                    <fieldset>
                        <legend>Электронная почта</legend>
                        <div class="field">
                            <label for="email">Email</label>
                            <input type="email" name="email" id="email" required/>
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
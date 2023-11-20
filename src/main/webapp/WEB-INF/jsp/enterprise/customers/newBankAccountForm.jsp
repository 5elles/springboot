<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>
        <div>
            <div class="container">
                <h3>открыть новый счет</h3>
                <form id="addNewAccountForm" class="form" name="newaccount" action="/createBankAccount?cid=${cid}" method="post" >
                    <fieldset>
                        <legend>реквизиты счета</legend>
                        <div class="field">
                            <label for="accountNumber">номер счета</label>
                            <input type="text" name="accountNumber" id="accountNumber" required />
                        </div>

                        <div class="field">
                            <label for="openingDate">дата открытия</label>
                            <input type="date" name="openingDate" id="openingDate" required/>
                        </div>

                        <div class="field">
                            <label for="currentBalance">стартовый баланс</label>
                            <input type="number" name="currentBalance" id="currentBalance"/>
                        </div>

                        <div class="field">
                            <label for="currencyID">валюта счета</label>
                            <select name="currencyID" id="currency" required="">
                                <c:forEach items="${currencies}" var="item" >
                                    <option id="currencyID"  name="currencyID" value="${item.id}">${item.currencyName}</option>
                                </c:forEach>
                            </select>
                        </div>
                            <input type="hidden" name="customerID" value="${cid}"/>
                    </fieldset>

                    <button class="btn success" type="submit" name="Add">сохранить</button>
                </form>
            </div>
        </div>
<%@ include file="footer.jsp" %>
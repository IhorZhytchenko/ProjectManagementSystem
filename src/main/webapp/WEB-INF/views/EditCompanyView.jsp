<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Company</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container w3-yellow">
    <a href="${pageContext.servletContext.contextPath}/companies" class="w3-button w3-margin w3-large w3-blue">Всі компанії</a>
    <h1>Редагування компанії</h1>
</div>

<br/>

<div class="w3-container w3-yellow w3-padding">
    <form method="post" action="${pageContext.servletContext.contextPath}/editCompany?id=<c:out value="${company.id}"></c:out>">
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h3>Назва:</h3>
            <input class="w3-input" type="text" name="name" value="<c:out value="${company.name}"></c:out>">
        </div>

        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h3>Місто:</h3>
            <input class="w3-input" type="text" name="city" value="<c:out value="${company.city}"></c:out>">
        </div>

        <div class="w3-container">
            <input type="submit" class="w3-button w3-blue w3-right w3-large" value="Зберегти"/>
        </div>
    </form>
</div>

</body>
</html>

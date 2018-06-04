
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Main</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/developers" class="w3-margin-top w3-button w3-green ">Розробники</a>
<a href="${pageContext.servletContext.contextPath}/companies" class="w3-margin-top w3-button w3-yellow ">Компанії</a>
<a href="${pageContext.servletContext.contextPath}/projects" class="w3-margin-top w3-button w3-blue ">Проекти</a>


</body>
</html>

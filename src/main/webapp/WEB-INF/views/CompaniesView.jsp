<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Companies</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/" class="w3-margin-top w3-button w3-grey w3-right">На головну</a>
<div class="w3-container w3-yellow w3-margin-bottom">
    <h1>Компанії</h1>
</div>

<div class="w3-container w3-padding">
    <table class="w3-table w3-striped w3-bordered">
        <c:forEach items="${companies}" var="company">
            <tr>
                    <td><c:out value="${company.name}"  ></c:out></td>
                    <td><a class="w3-button w3-yellow" href="${pageContext.servletContext.contextPath}/editCompany?id=<c:out value="${company.id}"></c:out>">Редагувати</a></td>
                    <td><form method="post" action="${pageContext.servletContext.contextPath}/companies?id=<c:out value="${company.id}"></c:out>">
                        <input type="submit" class="w3-button w3-red w3-large" value="Delete"/>
                    </form></td>

            </tr>

        </c:forEach>

    </table>

    <a href="${pageContext.servletContext.contextPath}/createCompany" class="w3-margin-top w3-button w3-yellow ">Додати нову компанію</a>
</div>
</body>
</html>

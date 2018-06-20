<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
    <title>Register in Tweeter</title>
</head>
<style>
    body{font-family: Tohoma, sans-serif; background-color: darkgray}
    #backgroundIMG{text-align: center}
    #formForRegister{text-align: right; top: 200px; left: 600px;position:absolute; color: midnightblue}
    #nick{margin: 10px}
    #email{margin: 10px}
    #pass{margin: 10px}
    #pass2{margin: 10px}
    #submit{position: absolute; left: 160px}
    h4{color: crimson}
</style>
<body>
<div id="backgroundIMG">
    <img src="css/RegisterView.png" , width="600">
</div>
<form id="formForRegister" action="register" method="post">
    <c:set var="error" value="${param['err']}"/>
    <h4>
        <c:choose>
        <c:when test="${error eq 'nonick'}">Nie podano nicka</c:when>
        <c:when test="${error eq 'noemail'}">Nie podano adresu email</c:when>
        <c:when test="${error eq 'nopass'}">Nie podano hasła</c:when>
        <c:when test="${error eq 'differentPasswords'}">Podane hasła są różne</c:when>
        </c:choose>
    </h4>
    Nick:<input type="text" name="nick" id="nick"><br>
    Email:<input type="text" name="email" id="email"><br>
    Hasło:<input type="text" name="pass" id="pass"><br>
    Powtórz hasło:<input type="text" name="pass2" id="pass2"><br>
    <input type="submit" id="submit" name="submit" value="Zarejestruj">
</form>
</body>
</html>

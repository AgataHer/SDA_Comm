<%@ page import="com.sdacademy.utils.Utils" %>
<%@ page import="com.adacademy.tweeter.dao.UserDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<% Utils.activateSession(request);
UserDao userDao = UserDao.getInstance();
Long userId = Long.valueOf((Long) request.getAttribute("userId"));
request.setAttribute("user", userDao.get(userId).get());
%>
<c:if test="${sessionOk}">
    <c:choose>
    <c:when test="${user!=null}">
            <form method="post" action="editUser">
                <h3>Zmien swoje dane</h3>
                Nick: <input type="text" name="nick" id="nick" value='<c:out value="${user.nick}"/>'><br>
                Email: <input type="text" name="email" id="email" value='<c:out value="${user.email}"/>'><br>
                Hasło: <input type="password" name="pass" id="pass"><br>
                <input type="hidden" name="userId" value='<c:out value="${user.id}"/>'>
                <input type="submit" id="submit" name="submit" value="Zapisz zmiany">
            </form>
    </c:when>
        <c:otherwise>
            <h3 style="color: cadetblue">Coś jest nie tak z id usera !</h3>
        </c:otherwise>
    </c:choose>
</c:if>

</body>
</html>

<%@ page import="com.adacademy.tweeter.dao.UserDao" %>
<%@ page import="com.sdacademy.utils.Utils" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Tweet</title>
</head>
<style>
    body{font-family: Tohoma, sans-serif; background-color: darkgray}
    .backgroundIMG{text-align: center}
    h3{top:400px; left:650px; text-align: center; position: absolute}
    .formAddTweet{position: absolute; top:200px; left:620px}
    #submit{position: absolute; left:110px; margin: 10px}
</style>
<body>
<%Utils.activateSession(request);%>
<c:if test="${!sessionOk}">
    <div class="backgroundIMG">
        <img src="css/AddTweetNotOk.png" , width="600">
    </div>
    <h3><a href="/index.jsp">Wróć do ekranu głównego</a></h3>
</c:if>
<c:if test="${sessionOk}">
    <div class="backgroundIMG">
        <img src="css/AddTweetOk.png" , width="600">
    </div>
    <form class = "formAddTweet" method="post" action="addTweet">
        <textarea name="message" cols="40" rows="15" maxlength="1000"></textarea><br>
        <input type="submit" name="submit" id="submit" value="Opublikuj"/>
    </form>
</c:if>
</body>
</html>

<%@ page import="com.sdacademy.utils.Utils" %>
<%@ page import="com.adacademy.tweeter.dao.TweetDao" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Edit tweet</title>
</head>
<style>
    body{font-family: Tohoma, sans-serif; background-color: darkgray}
    .backgroundIMG{text-align: center}
    .formEditTweet{position: absolute; top:200px; left:620px}
    #submit{position: absolute; left:110px; margin: 10px}
</style>
<body>
<%Utils.activateSession(request);
    TweetDao tweetDao = TweetDao.create();
    Long id = Long.valueOf(request.getParameter("id"));
    request.setAttribute("tweet", tweetDao.get(id).get());
%>
<%--<c:if test="${!sessionOk}">--%>
    <%--<h3 style="color: darkred"> Aby publikowac Tweety nalezy zalogować się do systemu </h3>--%>
    <%--<h4><a href="/index.jsp">Wróć do ekranu głównego</a></h4>--%>
<%--</c:if>--%>
<c:if test="${sessionOk}">
    <div class="backgroundIMG">
        <img src="css/EditTweet.png" , width="600">
    </div>
    <c:choose>
        <c:when test="${tweet!=null}">
            <form class="formEditTweet" method="post" action="editTweet">
                <textarea name="message" cols="40" rows="15" maxlength="1000"><c:out value="${tweet.message}"/></textarea>
                <input type="hidden" name="tweetId" value='<c:out value="${tweet.id}"/>'>
                <input type="submit" name="submit" id="submit" value="Zapisz"/>
            </form>
        </c:when>
        <c:otherwise>
            <h3 style="color: cadetblue">Tweet o podanym ID nie istnieje !</h3>
        </c:otherwise>
    </c:choose>

</c:if>

</body>
</html>

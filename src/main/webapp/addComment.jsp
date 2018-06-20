<%@ page import="com.sdacademy.utils.Utils" %>
<%@ page import="com.adacademy.tweeter.dao.TweetDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
    <title>Add Comment</title>
</head>

<style>
    body{font-family: Tohoma, sans-serif; background-color: darkgray}
    .backgroundIMG{text-align: center}
    h3{top:400px; left:650px; text-align: center; position: absolute}
    .formAddComment{position: absolute; top:200px; left:620px}
    #submit{position: absolute; left:110px; margin: 10px}
</style>
<body>
<%Utils.activateSession(request);
    TweetDao tweetDao = TweetDao.create();
    Long id = Long.valueOf(request.getParameter("id"));
    request.setAttribute("tweet", tweetDao.get(id).get());
%>
<c:if test="${sessionOk}">
    <div class="backgroundIMG">
        <img src="css/AddTweetOk.png" , width="600">
    </div>
    <form class = "formAddComment" method="post" action="addComment">
        <textarea name="tweetMessage" cols="40" rows="15" maxlength="1000"><c:out value="${tweet.message}"/></textarea><br>
        <input type="hidden" name="tweetId" value='<c:out value="${tweet.id}"/>'>
        <textarea name="message" cols="40" rows="15" maxlength="1000"></textarea><br>
        <input type="submit" name="submit" id="submit" value="Skomentuj"/>
    </form>
</c:if>

</body>
</html>

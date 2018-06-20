<%@ page import="com.sdacademy.utils.Utils" %>
<%@ page import="com.adacademy.tweeter.dao.TweetDao" %>
<%@ page import="java.util.Optional" %>
<%@ page import="com.adacademy.tweeter.model.Tweet" %>
<%@ page import="java.util.List" %>
<%@ page import="com.adacademy.tweeter.dao.CommentDao" %>
<%@ page import="com.adacademy.tweeter.model.Comments" %>
<%@ page import="com.adacademy.tweeter.dao.UserDao" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<jsp:useBean id="myDate" class="java.util.Date"/>

<html>
<style>
    body{font-family: Tohoma, sans-serif; background-color: darkgray}
    .infoBackground{top: 0px; right: 200px; position: absolute}
    .links{text-align: right;color: midnightblue}
    h2, h4{color: midnightblue; margin-left: 20px}
    .tableWithTweets{border-style: dotted; border-color:crimson; border-width: thick; text-align:justify;
        word-wrap: break-word; width: 500px; margin-left: 20px; border-collapse: collapse}
    .tableWithTweets td{border-style: dotted; border-color:crimson; border-width: thick;padding: 10px}
    .commentsView{background: gold; font-size: small}


</style>
<body>
<% Utils.activateSession(request);
    UserDao userDao = UserDao.getInstance();
    boolean isID = Boolean.valueOf((boolean)request.getAttribute("sessionOk"));
    if (isID) {
        Long userId = Long.valueOf((Long) request.getAttribute("userId"));
        if(userId!=null) {
            request.setAttribute("user", userDao.get(userId).get());
        }
    }
    TweetDao tweetDao = TweetDao.create();
    Optional<List<Tweet>> allTweets = tweetDao.getAll();
    if (allTweets.isPresent()){
        request.setAttribute("tweetList" ,allTweets.get());
    }
    CommentDao commentDao = CommentDao.create();
    Optional<List<Comments>> allComments = commentDao.getAll();
    if (allComments.isPresent()){
        request.setAttribute("commentsList", allComments.get());
    }
%>
<div class="infoBackground">
    <img src="css/SDACommunication.png" alt="Infografika zawierająca 4 kroki możliwych działań do podjęcia w komunikatorze SDA.
| Krok 1: Rejestracja | Krok 2: Logowanie | Krok 3: Publikowanie | Krok 4: Dodatkowe opcje|
Na końcu zjanduje się logo SDA COMMUNICATION by Agata Herbuś" width="600">
</div>

<div class="links">
<c:if test="${sessionOk}">
<h2><a href="editUser.jsp">Zmien dane</a></h2>
<h2><a href="logout">Wyloguj</a></h2>
</c:if>
<c:if test="${!sessionOk}">
<h2><a href="login.jsp">Zaloguj</a></h2>
<h2><a href="register.jsp">Zarejestruj sie</a></h2>
</c:if>
</div>
<div>
    <c:if test="${sessionOk}">
    <h2>Witaj <c:out value="${user.nick}"/>! </h2>
        </c:if>
    <h2>Wiadomosci</h2>
    <h4><a href="/addTweet.jsp">Dodaj wiadomosc</a></h4>
    <table class="tableWithTweets">
        <tr>
            <td>Autor</td>
            <td>Wiadomosc</td>
            <td>Data</td>
            <td>Opcje</td>
        </tr>
        <c:forEach items="${tweetList}" var="tweetDB">
            <tr>
                <td><c:out value="${tweetDB.author.nick}"/></td>
                <td class="massage"><c:out value="${tweetDB.message}"/></td>
                <c:set target="${myDate}" property="time" value="${tweetDB.creationTS}"/>
                <td><c:out value="${myDate}"/></td>
                <c:if test="${sessionOk}">
                    <c:if test="${userId == tweetDB.author.id}">
                        <td>
                            <a href='removeTweet?id=<c:out value="${tweetDB.id}"/>'>Usun</a>
                            <a href='editTweet.jsp?id=<c:out value="${tweetDB.id}"/>'>Edycja</a>
                            <a href='addComment.jsp?id=<c:out value="${tweetDB.id}"/>'>Skomentuj</a>
                        </td>
                    </c:if>
                    <c:if test="${userId != tweetDB.author.id}">
                        <td><a href='addComment.jsp?id=<c:out value="${tweetDB.id}"/>'>Skomentuj</a></td>
                    </c:if>
                </c:if>
            </tr>
                <c:forEach items="${commentsList}" var="comment">
                        <tr class="commentsView">
                        <c:if test="${comment.tweet.id == tweetDB.id}">
                            <td><c:out value="${comment.author.nick}"/></td>
                            <td><c:out value="${comment.text}"/></td>
                            <c:set target="${myDate}" property="time" value="${comment.creationTS}"/>
                            <td><c:out value="${myDate}"/></td>
                            <c:if test="${userId == comment.author.id}">
                                <td>
                                    <a href='removeComment?id=<c:out value="${comment.id}"/>'>Usun</a>
                                    <a href='editComment.jsp?id=<c:out value="${comment.id}"/>'>Edycja</a>
                                </td>
                            </c:if>
                        </c:if>
                    </tr>
                </c:forEach>
        </c:forEach>
    </table>
</div>
</body>
</html>

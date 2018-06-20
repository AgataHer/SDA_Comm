<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tweeter Login</title>
</head>
<style>
    body{font-family: Tohoma, sans-serif; background-color: darkgray}
    #backgroundIMG{text-align: center}
    #logForm{text-align: center; top: 250px; left: 650px; position: absolute; color: midnightblue}
    #email{margin: 10px}
    #pass{margin: 10px}
</style>
<body>
<div id="backgroundIMG">
    <img src="css/LogInView.png" , width="600">
</div>
<form id="logForm" action="login" method="post">
    Login: <input type="text" name="email" id="email"><br>
    Hasło: <input type="text" name="pass" id="pass"><br>
    <input type="submit" id="submit" name="submit" value="Login">
</form>
</body>
</html>

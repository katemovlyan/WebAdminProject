<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 18.12.2016
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Material Login Form</title>

    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>
</head>
<body>
<!-- Mixins-->
<!-- Pen Title-->
<div class="pen-title">
    <h1>Material Login Form</h1><span>Pen <i class='fa fa-code'></i> by <a href='http://andytran.me'>Andy
    Tran</a></span>
</div>
<div class="rerun"><a href="">Rerun Pen</a></div>
<div class="container">
    <div class="card"></div>
    <div class="card">
        <h1 class="title">Login</h1>
        <form method="post">
            <div class="input-container">
                <input type="text" id="Username" name="username" required="required"/>
                <label for="Username">Username</label>
                <div class="bar"></div>
            </div>
            <div class="input-container">
                <input type="password" id="Password" name="password" required="required"/>
                <label for="Password">Password</label>
                <div class="bar"></div>
            </div>
            <div class="button-container">
                <button><span>Go</span></button>
            </div>
            <div class="footer"><a href="#">Forgot your password?</a></div>
        </form>
    </div>
    <div class="card alt">
        <div class="toggle"></div>
        <h1 class="title">Register
            <div class="close"></div>
        </h1>
        <form method="post" action="?register">
            <div class="input-container">
                <input type="text" id="Username" name="username" required="required"/>
                <label for="Username">Username</label>
                <div class="bar"></div>
            </div>
            <div class="input-container">
                <input type="text" id="Surname" name="surname" required="required"/>
                <label for="Surname">Surname</label>
                <div class="bar"></div>
            </div>
            <div class="input-container">
                <input type="password" id="Password" name="password" required="required"/>
                <label for="Password">Password</label>
                <div class="bar"></div>
            </div>
            <div class="input-container">
                <input type="password" id="RepeatPassword" name="repeate-password" required="required"/>
                <label for="RepeatPassword">Repeat Password</label>
                <div class="bar"></div>
            </div>
            <div class="input-container">
                <input type="email" id="EMail" name="email" required="required"/>
                <label for="EMail">E-Mail</label>
                <div class="bar"></div>
            </div>
            <div class="input-container">
                <input type="text" id="Phonenumber" name="phonenumber" required="required"/>
                <label for="Phonenumber">Phonenumber</label>
                <div class="bar"></div>
            </div>
            <div class="button-container">
                <button><span>Next</span></button>
            </div>
        </form>
    </div>
</div>
<!-- Portfolio--><a id="portfolio" href="http://andytran.me/" title="View my portfolio!"><i class="fa fa-link"></i></a>
<!-- CodePen--><a id="codepen" href="http://codepen.io/andytran/" title="Follow me!"><i class="fa fa-codepen"></i></a>

<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>


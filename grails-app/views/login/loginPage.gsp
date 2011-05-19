<%@ page import="com.intelligrape.nikhil.util.Constants; org.codehaus.groovy.grails.commons.ConfigurationHolder" contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://www.facebook.com/2008/fbml">
<head>
    <title>Simple GSP page</title>
    <script type="text/javascript" src="http://connect.facebook.net/en_US/all.js"></script>
</head>
<body>
<h1>This is from Login Page</h1>
<h2>Your App Id is ${ConfigurationHolder.config.facebook.appId}</h2>

<div class="login-box">
    <g:if test="${user}">
        <span>WELCOME ${user.name}</span>
        <span><g:link controller="login" action="logOut">Log-Out</g:link></span>
    </g:if>
    <g:else>
        <a href="${createLink(controller: 'login', action: 'faceBookLogin')}">
            %{--<fb:login-button>login via facebook</fb:login-button>--}%
            <img src= ${resource(dir: 'images', file: 'facebook-logo.jpg')} />
        </a>login via facebook
    </g:else>
    <div><h4><a href="${createLink(controller: 'homePage', action: 'homepage')}">home-page</a></h4></div>
</div>

<div>
    <g:link controller="user" action="userPage">TO MY PAGE</g:link>
</div>

<div id="fb-root"></div>
<script type="text/javascript">
    FB.init({
    appId  : '${ConfigurationHolder.config.facebook.appId}',
    status : true, // check login status
    cookie : true, // enable cookies to allow the server to access the session
    xfbml  : true  // parse XFBML
    });

</script>
</body>
</html>
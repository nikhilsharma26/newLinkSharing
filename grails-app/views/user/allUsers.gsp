<%@ page import="com.intelligrape.nikhil.User" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>ALL USERS</title>
    <script src="http://connect.facebook.net/en_US/all.js#xfbml=1"> </script>
</head>
<body>
<h1>Select Users</h1>
<g:each in="${userList}" var="user" status="status">
    <% user = (User) user %>
    <div id="userImage-${status}">
        <img src="http://graph.facebook.com/${user.facebookId}/picture" />
    </div>
    <span>
        <a href="${createLink(controller:'user', action: 'discussionBoard', params:[userFacebookId:user.facebookId])}">
            <h4>${user.name}</h4>
        </a>
    </span>
</g:each>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
      <title>User Page</title>
  </head>
  <body>
    <div>
        <ul>
            <li><g:link controller="user" action="requestFriends" params="[select:'friends']"> FRIENDS </g:link></li>
            <li>MY LIKES</li>
        </ul>
    </div>
  </body>
</html>
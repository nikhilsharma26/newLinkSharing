<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
      <title>Simple GSP page</title>
  </head>
  <body>
    <h1>This or that form</h1>
  <g:each in="${thisOrThatTopicList}" var="currentTopic" status="index">
    <a href="${createLink(controller:'user',action:'thisOrThatBoard' , params:[currentTopicId:currentTopic.id])}">
        <h4>${currentTopic.topic}</h4>
    </a>
  </g:each>
  </body>
</html>
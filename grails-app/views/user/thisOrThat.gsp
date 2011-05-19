<%@ page import="org.codehaus.groovy.grails.commons.ConfigurationHolder; com.intelligrape.nikhil.ThisOrThatTopic" contentType="text/html;charset=UTF-8" %>
<html>
  <head>
      <title>Simple GSP page</title>
      <script src="http://connect.facebook.net/en_US/all.js#xfbml=1"> </script>
  </head>
  <body>
  <% thisOrThatTopic = (ThisOrThatTopic)thisOrThatTopic %>
    <h1>This Or That topic ${thisOrThatTopic.topic}</h1>
    <div id="image-container">
        <div id="picture-1">
            <img style="width:450px; height:300px;float:left;" src="${createLink(controller:'image',action:'showImageForThisOrThatTopicPicture1',params:[topicId:thisOrThatTopic.id])}" >
        </div>
        <div id="picture-2">
            <img style="width:450px; height:300px;float:right;" src="${createLink(controller:'image',action:'showImageForThisOrThatTopicPicture2',params:[topicId:thisOrThatTopic.id])}" >
        </div>
    </div>
  <div id="fb-root"></div>
    ${ConfigurationHolder.config.grails.serverURL}/user/thisOrThat
    <div id="hide" style="margin:auto;">
        <fb:comments href="${ConfigurationHolder.config.grails.serverURL}/user/thisOrThat?topicId=${thisOrThatTopic.id.encodeAsURL()}" num_posts="8" width="500"> </fb:comments>
    </div>

  </body>
</html>
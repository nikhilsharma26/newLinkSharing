<%@ page import="org.codehaus.groovy.grails.commons.ConfigurationHolder" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Discuss</title>
    <script src="http://connect.facebook.net/en_US/all.js#xfbml=1"> </script>
    <meta property="og:title" content="Picture">
    <meta property="og:type" content="Picture">
    <meta property="og:image" content="${ConfigurationHolder.config.grails.serverURL}/web-app/images/220px-Chennai_street_children.jpg" >
    <meta property="og:url" content="${ConfigurationHolder.config.grails.serverURL}/user/discuss" >
    <meta property="og:site_name" content="TEST" >
    <meta property="og:description" content="Just a Test" >
    <meta property="fb:app_id" content="194116870625527">
    %{--<meta property="og:admins" content="100001378023600" />--}%
</head>
<body>
<h1>WE SHOULD DISCUSS THIS</h1>
${imageId}
<img src="${createLink(controller: 'image', action: 'showImage', params: [imageId: imageId])}" style="width:200px; height:300px; border:5px solid green;margin:auto;"/>
<div id="fb-root"></div>
    ${ConfigurationHolder.config.grails.serverURL}/user/discuss
    <div id="hide">
        <fb:comments href="${ConfigurationHolder.config.grails.serverURL}/user/discuss?imageId=${imageId.encodeAsURL()}" num_posts="8" width="500" publish_feed="true" > </fb:comments>
    </div>
    <div id="fb-root"></div><fb:like href="${ConfigurationHolder.config.grails.serverURL}/user/discuss?imageId=${imageId.encodeAsURL()}" send="true" width="450" show_faces="true" font=""> </fb:like>
</body>
</html>
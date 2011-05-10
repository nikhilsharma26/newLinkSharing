<%@ page import="org.codehaus.groovy.grails.commons.ConfigurationHolder; org.omg.CORBA.CurrentHolder; com.intelligrape.nikhil.User" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Discussion Board</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'basic.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'basic_ie.css')}"/>
    <script src="http://connect.facebook.net/en_US/all.js#xfbml=1"> </script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'basic.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.simplemodal.js')}"></script>
</head>
<body>
<h1>DISCUSSION BOARD</h1>
<% user = (User) user %>
<g:each in="${user.childContacts}" var="child" status="status">
<div id="containerBox-${status}" style="border:4px solid red; width:500px;float:left;">
    %{--<g:each in="${user.childContacts}" var="child" status="status">--}%
        <h2>${child.id}</h2>
        <img src="${createLink(controller: 'image', action: 'showImage', params: [imageId: child.id])}" style="width:200px; height:300px; border:5px solid green;margin:auto;" onclick="showImageInModalWindow(containerBox-${status});" />
    %{--</g:each>--}%
    <div id="fb-root"></div>
    ${ConfigurationHolder.config.grails.serverURL}/user/discussionBoard
    <div id="hide">
        <fb:comments href="${ConfigurationHolder.config.grails.serverURL}/user/discussionBoard" num_posts="8" width="500"> </fb:comments>
    </div>
    <div id="fb-root"></div><fb:like href="${ConfigurationHolder.config.grails.serverURL}/user/discussionBoard?option=${status.encodeAsURL()}" send="true" width="450" show_faces="true" font=""> </fb:like>
</div>
</g:each>

<script type="text/javascript">

    jQuery(document).ready(function() {
//        jQuery("#hide").hide();
    });

    function showImageInModalWindow(id) {
        alert("hi");
//        jQuery("#hide").show();
        jQuery("#containerBox").modal({onclose: function() {
              jQuery("#hide").hide();
        }
        });
    }
</script>
</body>
</html>
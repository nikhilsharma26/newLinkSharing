<%@ page import="org.codehaus.groovy.grails.commons.ConfigurationHolder; org.omg.CORBA.CurrentHolder; com.intelligrape.nikhil.User" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Discussion Board</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'basic.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'basic_ie.css')}"/>
    <script src="http://connect.facebook.net/en_US/all.js#xfbml=1"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'basic.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.simplemodal.js')}"></script>
</head>
<body>
<div style="width:900px; float:left"><h1>DISCUSSION BOARD</h1></div>
<span style="width:100px; float:right;"><a href="${createLink(controller: 'login', action: 'logOut')}"><h3>sign out</h3></a></span>
<span style="width:100px; float:right;"><a href="${createLink(controller: 'user', action: 'allUsers')}"><h3>all users</h3></a></span>

<% user = (User) user %>
<div>
<g:each in="${user.childContacts}" var="child" status="status">
    <div id="containerBox-${status}" style="border:4px solid red; margin:10px; width:500px;float:left;">
        <h2>${child.id}</h2>
        <img src="${createLink(controller: 'image', action: 'showImage', params: [imageId: child.id])}" style="width:200px; height:300px; border:5px solid green;margin:auto;" onclick="showImageInModalWindow(containerBox -${status});"/>
        <a href="${createLink(controller: 'user', action: 'discuss', params: [imageId: child.id])}">Discuss Me</a>
    </div>
</g:each>
</div>

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
<%@ page import="org.codehaus.groovy.grails.commons.ConfigurationHolder; com.intelligrape.nikhil.ThisOrThatTopic" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Simple GSP page</title>
    <script src="http://connect.facebook.net/en_US/all.js#xfbml=1"></script>
    <g:javascript library="jquery" plugin="jquery"/>
</head>
<body>
<table>
    <% thisOrThatTopic = (ThisOrThatTopic) thisOrThatTopic %>
    <tr>
        <td colspan="6">
            <h1>${thisOrThatTopic.topic}</h1>
        </td>
    </tr>
    <tr>
        <td colspan="6">
            <h2>${thisOrThatTopic.initialComments}</h2>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <span>${thisOrThatTopic.picture1Topic}</span>
        </td>
        <td colspan="2"></td>
        <td colspan="2"><span>${thisOrThatTopic.picture2Topic}</span></td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <img width=200 src="${createLink(controller: 'image', action: 'showImageForThisOrThatTopicPicture1', params: [topicId: thisOrThatTopic.id])}">
        </td>
        <td colspan="2" align="center"><img style="width:100px" src="${resource(dir: 'images', file: 'VSlogo.jpg')}"/></td>
        <td colspan="2" align="center">
            <span><img style="width:200px" src="${createLink(controller: 'image', action: 'showImageForThisOrThatTopicPicture2', params: [topicId: thisOrThatTopic.id])}"></span>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            ${thisOrThatTopic.picture1Description}
        </td>
        <td colspan="2"></td>
        <td colspan="2" align="center">
            ${thisOrThatTopic.picture2Description}
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <img id="vote1" width="75" src="${resource(dir: 'images', file: 'vote_for_me.jpg')}" style="cursor:pointer"rel onclick="vote();" rel="${createLink(controller: 'thisOrThat', action: 'vote')}"/>
        </td>
        <td colspan="2">

        </td>
        <td colspan="2" align="center">
            <img width="75" src="${resource(dir: 'images', file: 'vote_for_me.jpg')}" style="cursor:pointer" onclick="vote();"/>
        </td>
    </tr>
    <div id="fb-root"></div>
    <tr>
        <td colspan="2"></td>
        <td colspan="2"><div id="hide" style="margin:auto;">
            <fb:comments href="${ConfigurationHolder.config.grails.serverURL}/user/thisOrThat?topicId=${thisOrThatTopic.id.encodeAsURL()}" num_posts="8" width="500"></fb:comments>
        </div></td>
        <td colspan="2"></td>

    </tr>
</table>

<script type="text/javascript">
    jQuery('#vote1').click(function() {
        var link = jQuery(this).attr('rel');
//        jQuery.ajax(url: link);
        jQuery.ajax({url: link, success: function(data) {
            alert(data)
        }});
    });
</script>
</body>
</html>
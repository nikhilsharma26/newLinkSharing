<%@ page import="org.codehaus.groovy.grails.commons.ConfigurationHolder" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>HOME PAGE</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'homepage.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'basic.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'basic_ie.css')}"/>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'basic.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.simplemodal.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.validate.js')}"></script>
</head>
<body>
<g:javascript library="prototype"/>
<div id="container">
    <div id="header">WELCOME TO HOMEPAGE OF ${ConfigurationHolder.config.grails.projectName} ${formSubmit}
        <div class="horizontalLinks">
            <ul>
                <li><a href="#">Link 1</a></li>
                <li><a href="#">Link 2</a></li>
                <li><a href="#">Link 3</a></li>
                <li><a href="#">Link 4</a></li>
                <li><a href="#">Link 5</a></li>
            </ul>
        </div>
    </div>
    <div id="horizontalnav">
        <div class="navlinks">
            <ul>
                <li><a href="#">Subscribe to mail</a></li>
                <li><a href="#" onclick="fillForm();">FILL FORM</a></li>
                <li><a href="${createLink(controller:"user" , action:"discussionBoard")}" >My Board</a></li>
                <li><a href="#">NAV link4</a></li>
            </ul>
        </div>
    </div>
    <div id="leftnav">THIS IS THE LEFT NAV</div>
    <div id="loginBox"></div>
    <div id="rightnav">THIS IS THE RIGHT NAV</div>
    <div id="body"><h1>flash formsubmit is ${flash.formSubmit}</h1>
        <div id="thank-you">
            <g:if test="${flash.formSubmit == 'success'}">
            <span>THANK YOU FOR SUBMITTING FORM</span>
            </g:if>
        </div>
        <p>Please click on some stats below.</p>
    </div>
    <div id="footer">THIS IS THE FOOTER</div>
</div>

<div id="form-id">
    <g:form id="form" url="[action:'handleForm', controller: 'childContact']" name="form" enctype="multipart/form-data">
        <div id="table">
            <div id="row1"><span>Name</span><g:textField name="name" /></div>
            <div id="row2"><span>Location</span><g:select from="${['NORTHDELHI','EASTDELHI','CENTRALDELHI','SOUTHDELHI','WESTDELHI']}" name="location"/></div>
            <div id="row3" style="border:1px solid red;"><div id="row3container"><span style="border: 1px solid green; display:inline-block; vertical-align:middle; ">Description</span></div><g:textArea name="description" rows="5" cols="30"/></div>
            <div id="row4">Browse<input type="file" name="image" class="required"/></div>
            <div id="row5"><span>Upload</span>
                <div class="modalCloseImg">
                    <g:submitButton name="submit" value="Upload"/>
                </div>
            </div>
            <div class="modalCloseImg"></div>
        </div>
    </g:form>
</div>


<script type="text/javascript">
    jQuery(document).ready(function() {
        jQuery("#form").validate();
        jQuery("#form-id").hide();
        if (${flash.formSubmit == 'success'}) {
            jQuery("#thank-you").modal({onOpen: function (dialog) {
                dialog.overlay.fadeIn('slow', function () {
                    dialog.data.hide();
                    dialog.container.fadeIn('slow', function () {
                        dialog.data.slideDown('slow');
                    });
                });
            }});
        }
    });

    function fillForm() {
        alert("hello");
        jQuery("#form-id").modal({onOpen: function (dialog) {
            dialog.overlay.fadeIn('slow', function () {
                dialog.data.hide();
                dialog.container.fadeIn('slow', function () {
                    dialog.data.slideDown('slow');
                });
            });
        }});
    }
</script>

</body>
</html>
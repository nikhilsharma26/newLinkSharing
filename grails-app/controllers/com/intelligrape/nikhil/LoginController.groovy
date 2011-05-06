package com.intelligrape.nikhil

import org.codehaus.groovy.grails.commons.ConfigurationHolder
import com.intelligrape.nikhil.util.Constants

class LoginController {

    def defaultAction = "loginPage"
    def userService

    static final String facebookCallbackUrl = "${ConfigurationHolder.config.grails.serverURL}/login/authenticateApp"
//    static final String accessTokenUrl = "${ConfigurationHolder.config.grails.serverURL}/login/processClientToken"

    def index = {
        redirect(action: loginPage)
    }

    def loginPage = {
        User user = null
        println "### in session ${session[Constants.LOGIN_USER_ID]}"
        if (session[Constants.LOGIN_USER_ID]) {
            log.error("### in if using log")
            println("### in if using println")
            user = userService.getUser(session[Constants.LOGIN_USER_ID])
        }
        render(view: "loginPage", model: [user: user])
    }

    def faceBookLogin = {
        println "### call back url is ${ConfigurationHolder.config.grails.serverURL}/login/authenticateApp"
        println "### from face book login action"
        println "### request.getHeader('REFERER') is ${request.getHeader('REFERER')}"
        session.putValue('redirectUrl', request.getHeader('REFERER'))
        String facebookAuthorizeUrl = "https://www.facebook.com/dialog/oauth?client_id=${ConfigurationHolder.config.facebook.appId}&redirect_uri=${facebookCallbackUrl}"
        redirect(url: facebookAuthorizeUrl)
    }

    def authenticateApp = {
        println "### getting facebook token "
        String authCode = params.code
        println "### The code is ${authCode}"
        String appAuthentication = "https://graph.facebook.com/oauth/access_token?client_id=${ConfigurationHolder.config.facebook.apiKey}&redirect_uri=${facebookCallbackUrl}&client_secret=${ConfigurationHolder.config.facebook.apiSecretKey}&code=${authCode}"
        URL url = new URL(appAuthentication)
        String responseToken = url.text
        println "### the responseToken is ${responseToken}"
        redirect(action: decideWhereToGo, params: ["responseToken": responseToken])
//        redirect(action: processClientToken, params:["responseToken":responseToken] )
    }

    def decideWhereToGo = {
        String responseToken = params.responseToken
        log.error("### in decide where to go ")
        log.error("### session.redirectUrl ${session.redirectUrl} ")
        if (session.redirectUrl == "http://localhost:8080/newLinkSharing/login/loginPage") {
            redirect(action: processClientToken, params:["responseToken":responseToken] )
        }
    }

    def processClientToken = {
        println "### the response is ${params.responseToken}"
        String responseToken = params.responseToken
        if (responseToken.contains("access_token")) {
            println ""
            String[] clientToken = responseToken.split('access_token=')
            User user = userService.createOrGetUserForAccessToken(clientToken[1].split("&expires=")[0])
            user.save(flush: true)
            session[Constants.LOGIN_USER_ID] = user.id
            println "value in session is ${session[Constants.LOGIN_USER_ID]}"
            redirect(url: session.redirectUrl)
//            render(view:'/user/userPage')
        }
    }

    def logOut = {
        session.removeAttribute(Constants.LOGIN_USER_ID)
        redirect(url:request.getHeader('REFERER'))
    }
}

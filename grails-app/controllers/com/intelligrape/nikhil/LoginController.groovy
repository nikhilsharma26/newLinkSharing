package com.intelligrape.nikhil

import org.codehaus.groovy.grails.commons.ConfigurationHolder
import com.intelligrape.nikhil.util.Constants

class LoginController {

    def defaultAction = "loginPage"
    def userService
    def facebookService

    String facebookCallbackUrl = "${ConfigurationHolder.config.grails.serverURL}/login/authenticateApp"

    def index = {
        redirect(action: loginPage)
    }

    def loginPage = {
        User user = null
        println "### in session ${session[Constants.LOGIN_USER_ID]}"
        if (session[Constants.LOGIN_USER_ID]) {
            log.error("### in if using log")
            println("### in if using println")
            user = userService.getUser((long)session[Constants.LOGIN_USER_ID])
        }
        render(view: "loginPage", model: [user: user])
    }

    def faceBookLogin = {
        String optionToShow = params.optionToShow
        println "??? params.select ${params.optionToShow}"
        println "### call back url is ${ConfigurationHolder.config.grails.serverURL}/login/authenticateApp"
        println "### from face book login action"
        println "### request.getHeader('REFERER') is ${request.getHeader('REFERER')}"
        facebookCallbackUrl += "?optionToShow=${optionToShow?.encodeAsURL()}"
        println "########### facebook call back url is ${facebookCallbackUrl}"
        session.putValue('redirectUrl', request.getHeader('REFERER'))
        String facebookAuthorizeUrl = "https://www.facebook.com/dialog/oauth?client_id=${ConfigurationHolder.config.facebook.appId}&redirect_uri=${facebookCallbackUrl}"
//        redirect(url: facebookAuthorizeUrl, params: ["optionToShow": optionToShow])

        redirect(url: facebookAuthorizeUrl)
    }

    def authenticateApp = {
        println "### getting facebook token "
        String authCode = params.code
        String optionToShow = params.optionToShow
        println "????????? params.optionToShow ${params.optionToShow}"
        println "### The code is ${authCode}"
        facebookCallbackUrl += "?optionToShow=${optionToShow?.encodeAsURL()}"
        println "<<<<<<<<<<<<<<<<<<<<<<<<facebook call back url is ${facebookCallbackUrl} "
        String appAuthentication = "https://graph.facebook.com/oauth/access_token?client_id=${ConfigurationHolder.config.facebook.apiKey}&redirect_uri=${facebookCallbackUrl}&client_secret=${ConfigurationHolder.config.facebook.apiSecretKey}&code=${authCode}"
        URL url = new URL(appAuthentication)
        String responseToken = url.text
        println "### the responseToken is ${responseToken}"
        redirect(action: "decideWhereToGo", params: ["responseToken": responseToken, "optionToShow": optionToShow])
    }

    def decideWhereToGo = {
        println "### params are ${params}"
        String optionToShow = params.optionToShow
        String responseToken = params.responseToken
        log.error("### in decide where to go ")
        log.error("### session.redirectUrl ${session.redirectUrl} ")
        String accessToken = facebookService.getAccessToken(params.responseToken)
        if (session.redirectUrl == "http://localhost:8080/newLinkSharing/login/loginPage") {
            redirect(action: "processClientToken", params: ["accessToken": accessToken])
        }
        else if (session.redirectUrl == "http://localhost:8080/newLinkSharing/user/userPage") {
            log.error("### i should go to user controller's decide where to go ")
            redirect(controller: "user", action: "decideWhereToGo", params: ["optionToShow": optionToShow, "accessToken": accessToken])
        }
    }

    def processClientToken = {
        User user = userService.createOrGetUserForAccessToken(params.accessToken)
        user.save(flush: true)
        println "##### user id is ${user.id}"
        session[Constants.LOGIN_USER_ID] = user.id
        println "value in session is ${session[Constants.LOGIN_USER_ID]}"
        redirect(url: session.redirectUrl)
    }

    def logOut = {
        session.removeAttribute(Constants.LOGIN_USER_ID)
        redirect(url: request.getHeader('REFERER'))
    }
}

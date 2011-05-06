package com.intelligrape.nikhil

import com.intelligrape.nikhil.util.Constants

class UserController {

    def userService

    def index = {
        redirect(action: 'userPage')
    }

    def userPage = {

    }

    def requestFriends = {
        println "??? params ${params.select}"
        String optionToShow = params.select
        redirect(controller: "login", action: "faceBookLogin", params: ["optionToShow": optionToShow])
    }

    def decideWhereToGo = {
        log.error("### i am in decide where to go")
        String accessToken = params.accessToken
        println "### params are ${params}"
        if (params.optionToShow == "friends") {
            log.error("### i am in if")
            redirect(action: "showFriends", params: ["accessToken": accessToken])
        }
    }

    def showFriends = {
        String accessToken = params.accessToken
        String userId = session[Constants.LOGIN_USER_ID]
        def friends = userService.getFriends(accessToken, userId)
        render "I got You , ${friends}"
    }
}

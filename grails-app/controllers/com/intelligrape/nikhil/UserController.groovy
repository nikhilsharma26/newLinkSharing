package com.intelligrape.nikhil

import com.intelligrape.nikhil.util.Constants

class UserController {

    def userService
    def facebookService

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
//        String userId = session[Constants.LOGIN_USER_ID]
        long currentUserId = (long) session[Constants.LOGIN_USER_ID]
//        def friends = facebookService.getFriends(accessToken, userId)
//        facebookService.getFriends(accessToken, userId)
        println "!!!!!!!!!!! in session , we have ${session[Constants.LOGIN_USER_ID]}"
        facebookService.getFriends(accessToken, currentUserId)
        render "I got You , "
    }

    def discussionBoard = {
        String userFacebookId = params.userFacebookId
        User user = User.findByFacebookId(userFacebookId)
        render(view: 'myBoard', model: [user: user])
    }

    def myBoard = {
        long userId = (long) session[Constants.LOGIN_USER_ID]
        println "userId ${userId}"
        User user = User.findById(userId)
        render(view: 'myBoard', model: [user: user])
    }

    def discuss = {
        String imageId = params.imageId
        render(view: 'discuss', model: [imageId: imageId])
    }

    def allUsers = {
        List<User> userList = User.list()
        println "userList is ${userList}"
        render(view: "allUsers", model: [userList: userList])
    }
}

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

    def thisOrThat = {

    }

    def getUserPicturesFromFacebook = {


    }

    def thisOrThatBoard = {
        ThisOrThatTopic thisOrThatTopic = ThisOrThatTopic.findById(params.currentTopicId)
        render(view: 'thisOrThat', model: [thisOrThatTopic: thisOrThatTopic, picture1Votes: thisOrThatTopic.picture1Votes, picture2Votes: thisOrThatTopic.picture2Votes])
    }

    def thisOrThatForms = {
        List<User> userList = User.findAll()
        println "userlist ------>${userList}"
        render(view: 'thisOrThatForms', model: [userList: userList])
    }

    def discussionBoardThisOrThat = {
        User user = User.findByFacebookId(params.userFacebookId)
        render(view: 'myThisOrThatBoard', model: [user: user])
    }

    def userThisThatBoard = {
        User user = User.findByFacebookId(params.userFacebookId)
        println "### user is ${user.name}"
        List<ThisOrThatTopic> thisOrThatTopicList = ThisOrThatTopic.findAllByUser(user)
        println "#### user's topics are ${thisOrThatTopicList}"
        render(view: '/thisOrThat/myThisOrThatBoard', model: [currentUser: user, thisOrThatTopicList: thisOrThatTopicList])
    }
}

package com.intelligrape.nikhil

class UserController {

    def userService

    def index = {
        redirect(action:'userPage')
    }

    def userPage = {

    }

    def showFriends = {
        redirect(controller:"login" ,action:"faceBookLogin" )
    }
}

package com.intelligrape.nikhil
import com.intelligrape.nikhil.util.Constants

class ThisOrThatController {

    def index = { }

    def handleThisOrThatForm = {
        ThisOrThatTopic thisOrThatTopic = new ThisOrThatTopic(params)
        long userId = (long) session[Constants.LOGIN_USER_ID]
        User user = User.get(userId)
        user.addToThisOrThatTopic(thisOrThatTopic)
        if (user.save(flush: true)) {
            flash.thisOrThatFormSubmit = 'success'
        }
        else {
            user.errors.allErrors.each {
                println "it ----> ${it}"
            }
            flash.thisOrThatFormSubmit = 'failure'
        }
        redirect(controller: 'homePage', action: 'homepage')
    }

    def myThisOrThatBoard = {
        long userId = (long) session[Constants.LOGIN_USER_ID]
        println "### userId is ${userId}"
        User user = User.findById(userId)
        List<ThisOrThatTopic> thisOrThatTopicList = ThisOrThatTopic.findAllByUser(user)
        [currentUser: user, thisOrThatTopicList: thisOrThatTopicList]
    }

    def vote = {
        render "hello"
    }
}

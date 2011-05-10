package com.intelligrape.nikhil

import com.intelligrape.nikhil.util.Constants

class ChildContactController {

    def index = { }

    def homepage = {
        println "### going to the home page and params are ${params}"
        String formSubmit = params.formSubmited ?: "notSubmit"
        render(view: 'homepage')
    }

    def handleForm = {
        println("### from handle form params are-------> ${params}")
        ChildContact childContact = new ChildContact(params)
        println "-----------------------"
        long userId = (long)session[Constants.LOGIN_USER_ID]
        User user = User.get(userId)
        println "%%%%%%%%% user is ${user?.name}"
        user.addToChildContacts(childContact)
        println "### after add to contacts"
        if (user.save(flush: true)) {
            flash.formSubmit = 'success'
        }
        else {
            user.errors.allErrors.each {
                log.error( "### error ${it}")
            }
            flash.formSubmit = 'failure'
        }
        redirect(controller: "homePage", action: "homepage")
    }
}

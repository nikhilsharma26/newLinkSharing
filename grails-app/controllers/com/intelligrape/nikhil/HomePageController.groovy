package com.intelligrape.nikhil

class HomePageController {

    def index = {
        redirect(action: 'homepage')
    }

    def homepage = {
        println "### going to the home page and params are ${params}"
        String formSubmit = params.formSubmited ?: "notSubmit"
        render(view: 'homepage')
    }
}

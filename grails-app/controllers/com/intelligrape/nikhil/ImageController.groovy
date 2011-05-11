package com.intelligrape.nikhil

class ImageController {

    def index = { }

    def showImage = {
        println "#### in show Image"
        println "### "
        def image = ChildContact.findById(params.imageId).image
//        response.setContentType(ChildContact.findById(params.id).type)
        response.outputStream << image
    }
}

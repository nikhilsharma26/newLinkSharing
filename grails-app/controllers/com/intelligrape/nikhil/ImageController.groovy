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

    def showImageForThisOrThatTopicPicture1 = {
        println "#### image for this or that"
        def image = ThisOrThatTopic.findById(params.topicId).picture1
        response.outputStream << image
    }

    def showImageForThisOrThatTopicPicture2 = {
        println "#### image for this or that"
        def image = ThisOrThatTopic.findById(params.topicId).picture2
        response.outputStream << image
    }
}

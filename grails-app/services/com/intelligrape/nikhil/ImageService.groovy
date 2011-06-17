package com.intelligrape.nikhil

class ImageService {

    static transactional = true

    String fetchPrimaryImage(ThisOrThatTopic topic) {
        println "!!!! picture 1 is ${topic.picture1}"
        topic.picture1
    }
}

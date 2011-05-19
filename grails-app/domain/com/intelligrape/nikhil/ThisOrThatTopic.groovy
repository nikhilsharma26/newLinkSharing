package com.intelligrape.nikhil

class ThisOrThatTopic {

    byte[] picture1
    byte[] picture2
    String topic
    String initialComments

    static constraints = {
        picture1(nullabe: true, size: 0..5000000)
        picture2(nullabe: true, size: 0..5000000)
        topic(nullable: true)
        initialComments(nullable: true)
    }

    static belongsTo = [user: User]
}

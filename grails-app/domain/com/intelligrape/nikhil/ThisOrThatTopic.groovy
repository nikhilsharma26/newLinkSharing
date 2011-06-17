package com.intelligrape.nikhil

class ThisOrThatTopic {

    byte[] picture1
    byte[] picture2
    String topic
    String picture1Topic
    String picture1Description
    String picture2Topic
    String picture2Description
    String initialComments
    int picture1Votes
    int picture2Votes

    static constraints = {
        picture1(nullabe: true, size: 0..5000000)
        picture2(nullabe: true, size: 0..5000000)
        topic(nullable: true)
        initialComments(nullable: true)
        picture1Topic(nullable: true)
        picture2Topic(nullable: true)
        picture1Description(nullable: true)
        picture2Description(nullable: true)
        picture1Votes(nullable: true)
        picture2Votes(nullable: true)
    }

    static belongsTo = [user: User]
}

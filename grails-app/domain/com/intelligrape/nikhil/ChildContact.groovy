package com.intelligrape.nikhil

class ChildContact {

    byte[] image
    String name
    String location
    String description
//    String type

    static constraints = {
        name(nullable: true)
        description(nullable: true)
        image(nullabe: true, size: 0..5000000)
    }

    static belongsTo = [user: User]

}

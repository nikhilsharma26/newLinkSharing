package com.intelligrape.nikhil

class UserFriend {
    String facebookId
    String name

    static constraints = {
    }

    static belongsTo = {
        user: User
    }
}

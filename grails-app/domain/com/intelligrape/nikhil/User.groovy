package com.intelligrape.nikhil

class User {
//    String userId
    String facebookAccessToken
    String name
    String currentLocation
    String homtownLocation
    String gender
    String facebookId
    String birthday

    static constraints = {
        currentLocation(nullable: true)
        homtownLocation(nullable: true)
        gender(nullable: true)
        birthday(nullable: true)
    }

    static hasMany = [friends:UserFriend,
                      childContacts:ChildContact]

    void showUserPicture() {

    }
}

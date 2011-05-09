package com.intelligrape.nikhil.util

import org.codehaus.groovy.grails.web.json.JSONElement

class FacebookUser {
    String facebookId
    String name
    String first_name
    String last_name
    String link
    String username
    String hometownName
    String currentLocationName
    String gender
    String email
    String birthday
    String profilePictureUrl

    FacebookUser(JSONElement user )
    {
        facebookId = user.id
        name = user.name
        first_name = user.first_name
        last_name = user.last_name
        link = user.link
        username = user.username
        hometownName = user.hometown.name
        currentLocationName = user.location.name
        gender = user.gender
        email =  user.email
        birthday = user.birthday
    }
}

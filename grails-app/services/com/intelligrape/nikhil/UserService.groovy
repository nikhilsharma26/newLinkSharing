package com.intelligrape.nikhil
import com.intelligrape.nikhil.util.FacebookUser

class UserService {

    static transactional = true
    def facebookService

    User createOrGetUserForAccessToken(String userToken) {
        println "### userToken is ${userToken}"
        FacebookUser facebookUser = facebookService.getProfile(userToken)
        User user = User.findByFacebookId(facebookUser.id)
        if (user) {
            println "### you are an existing member"
        }
        else {
            println "### you are a new member"
            user = new User(facebookAccessToken: userToken, name: facebookUser.name, currentLocation: facebookUser.currentLocationName, homtownLocation: facebookUser.hometownName, gender: facebookUser.gender, facebookId: facebookUser.id, birthday: facebookUser.birthday)
        }
        return user
    }

    User getUser(String userId) {
        log.error("### trying to user ")
        User.findById(userId)
    }

    def getFriends(String accessToken, String userId) {
        log.error("### in get friends")
        return "Roni,Puneet,Divya,Shweta,Kushal"
    }
}

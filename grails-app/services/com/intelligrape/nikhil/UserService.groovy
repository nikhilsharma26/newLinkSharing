package com.intelligrape.nikhil
import com.intelligrape.nikhil.util.FacebookUser

class UserService {

    static transactional = true
    def facebookService

    User createOrGetUserForAccessToken(String userToken) {
        println "### userToken is ${userToken}"
        FacebookUser facebookUser = facebookService.getProfile(userToken)
//        User user = User.findByFacebookId(facebookUser.userId)
        User user = User.findByFacebookId(facebookUser.facebookId)
        if (user) {
            println "### you are an existing member"
        }
        else {
            println "### you are a new member"
//            user = new User(facebookAccessToken: userToken, name: facebookUser.name, currentLocation: facebookUser.currentLocationName, homtownLocation: facebookUser.hometownName, gender: facebookUser.gender, facebookId: facebookUser.userId, birthday: facebookUser.birthday)
            user = new User(facebookAccessToken: userToken, name: facebookUser.name, currentLocation: facebookUser.currentLocationName, homtownLocation: facebookUser.hometownName, gender: facebookUser.gender, facebookId: facebookUser.facebookId, birthday: facebookUser.birthday)
        }
        return user
    }

//    User getUser(long userId) {
    User getUser(long id) {
        log.error("### trying to user ")
        User.findById(id)
    }

}

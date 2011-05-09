package com.intelligrape.nikhil.appUtils.CO

import com.intelligrape.nikhil.UserFriend
import com.intelligrape.nikhil.User

class UserCO {
    String facebookId
    String name

    void saveUserFromCO(User user) {
        UserFriend.withNewSession {
            if(User.findByFacebookId(this.facebookId)) {
                log.error("Already have this friend")
            }
            else {
//                user.addToFriends(userFriend)
                UserFriend userFriend = new UserFriend(facebookId:this.facebookId, name:this.name, user:user )
                userFriend.save(flush:true)
                println("Saved this friend ${this.name}")
            }
        }
    }
}

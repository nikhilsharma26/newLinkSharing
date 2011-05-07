package com.intelligrape.nikhil.appUtils.CO

import com.intelligrape.nikhil.UserFriend

class UserCO {
    String facebookId
    String name

    void save() {
        UserFriend.withNewSession {
            new UserFriend(facebookId:this.facebookId, name:this.name).save(flush:true)
        }
    }
}

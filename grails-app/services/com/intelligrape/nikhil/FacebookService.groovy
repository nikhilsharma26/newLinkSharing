package com.intelligrape.nikhil

import org.codehaus.groovy.grails.web.json.JSONElement
import grails.converters.JSON
import com.intelligrape.nikhil.util.FacebookUser
import com.intelligrape.nikhil.appUtils.CO.UserCO
import com.intelligrape.nikhil.util.Constants

class FacebookService {

    static transactional = true
    private static final String GRAPH_BASIC_URL = "https://graph.facebook.com/"
    private static final String USER_BASIC_CONNECTION = "https://graph.facebook.com/me/"

    FacebookUser getProfile(String accessToken, String facebookId = 'me') {
        log.debug("Getting facebook profile for user ${facebookId}")
        String fbUrl = "${GRAPH_BASIC_URL + facebookId}?access_token=${accessToken}"
        URL url = new URL(fbUrl)
        String jsonResponse
        FacebookUser faceBookUser = null
        jsonResponse = getResponseFromUrl(url)
        if (jsonResponse) {
            JSONElement userJson = JSON.parse(jsonResponse)
            faceBookUser = new FacebookUser(userJson)
//            faceBookUser.profilePictureUrl = "http://graph.facebook.com/${faceBookUser.userId}/picture?type=large"
            faceBookUser.profilePictureUrl = "http://graph.facebook.com/${faceBookUser.facebookId}/picture?type=large"
        }
        return faceBookUser
    }

//    FacebookUser[] getFriends(String accessToken, String userId) {
//     void getFriends(String accessToken, String userId) {
     void getFriends(String accessToken, long currentUserId) {
//        String id = userId
//        String id = currentUserId
//        User user = User.findByUserId(userId)
        User user = User.findById(currentUserId)
        String fbUrl = "${USER_BASIC_CONNECTION}friends?access_token=${accessToken}"
        URL url = new URL(fbUrl)
        String jsonResponse
        jsonResponse = getResponseFromUrl(url)
        if (jsonResponse) {
            JSONElement userJson = JSON.parse(jsonResponse)
            userJson.data.each {userData->
                UserCO userCO = new UserCO(facebookId: userData.id ,name: userData.name)
                println("@@@@@ the user is ${user.name}")
                userCO.saveUserFromCO(user)
                // todo make a CO and show it , can also store it
                println "it---->${userData}"
            }
        }
    }

    String getResponseFromUrl(URL url) {
        String response;
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        try {
            int respCode = conn.responseCode
            if (respCode == 400) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                def jsonResp = JSON.parse(br.text)
            } else {
                response = conn.getInputStream().getText()
            }
        } finally {
            conn.disconnect()
        }
        return response;
    }

    String getAccessToken(String responseToken) {
        String accessToken = ""
        if (responseToken.contains("access_token")) {
            println ""
            String[] clientToken = responseToken.split('access_token=')
            accessToken = clientToken[1].split("&expires=")[0]
        }
        return accessToken
    }
}

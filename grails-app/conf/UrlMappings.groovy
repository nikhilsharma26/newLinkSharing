import com.intelligrape.nikhil.util.Constants

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }

		"/"(view:"/index")

//        "/" {
//            if (session[Constants.LOGIN_USER_ID])
//                controller = 'homePage'
//            else
//                (view: '/index')
//        }
        "500"(view: '/error')


    }
}

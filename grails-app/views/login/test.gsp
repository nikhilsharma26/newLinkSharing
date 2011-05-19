<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://www.facebook.com/2008/fbml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>123</title>
</head>
<body>
<div id="fb-root"></div>
<script type="text/javascript">
    window.fbAsyncInit = function() {
        FB.init({appId: '194116870625527', status: true, cookie: true, xfbml: true});

        /* All the events registered */
        FB.Event.subscribe('auth.login', function(response) {
            // do something with response
            login();
        });
        FB.Event.subscribe('auth.logout', function(response) {
            // do something with response
            logout();
        });

        FB.getLoginStatus(function(response) {
            if (response.session) {
                // logged in and connected user, someone you know
                login();
            }
        });
    };
    (function() {
        var e = document.createElement('script');
        e.type = 'text/javascript';
        e.src = document.location.protocol +
                '//connect.facebook.net/en_US/all.js';
        e.async = true;
        document.getElementById('fb-root').appendChild(e);
    }());

    function login() {
        FB.api('/me', function(response) {
            document.getElementById('login').style.display = "block";
            document.getElementById('login').innerHTML = response.name + " succsessfully logged in!";
        });
    }
    function logout() {
        document.getElementById('login').style.display = "none";
    }

    //stream publish method
    function streamPublish(name, description, hrefTitle, hrefLink, userPrompt) {
        FB.ui(
        {
            method: 'stream.publish',
            message: '',
            attachment: {
                name: name,
                caption: '',
                description: (description),
                href: hrefLink
            },
            action_links: [
                { text: hrefTitle, href: hrefLink }
            ],
            user_prompt_message: userPrompt
        },
             function(response) {

             });

    }
    function showStream() {
        FB.api('/me', function(response) {
            //console.log(response.id);
            streamPublish(response.name, 'Thinkdiff.net contains geeky stuff', 'hrefTitle', 'http://thinkdiff.net', "Share thinkdiff.net");
        });
    }

    function share() {
        var share = {
            method: 'stream.share',
            u: 'http://thinkdiff.net/'
        };

        FB.ui(share, function(response) {
            console.log(response);
        });
    }

    function graphStreamPublish() {
        var body = 'Reading New Graph api & Javascript Base FBConnect Tutorial from Thinkdiff.net';
        FB.api('/me/feed', 'post', { message: body }, function(response) {
            if (!response || response.error) {
                alert('Error occured');
            } else {
                alert('Post ID: ' + response.id);
            }
        });
    }

    function fqlQuery() {
        FB.api('/me', function(response) {
            var query = FB.Data.query('select name, hometown_location, sex, pic_square from user where uid={0}', response.id);
            query.wait(function(rows) {

                document.getElementById('name').innerHTML =
                        'Your name: ' + rows[0].name + "<br />" +
                                '<img src="' + rows[0].pic_square + '" alt="" />' + "<br />";
            });
        });
    }

    function setStatus() {
        status1 = document.getElementById('status').value;
        FB.api(
        {
            method: 'status.set',
            status: status1
        },
              function(response) {
                  if (response == 0) {
                      alert('Your facebook status not updated. Give Status Update Permission.');
                  }
                  else {
                      alert('Your facebook status updated');
                  }
              }
                );
    }
</script>
<br/><br/><br/>
<div id="login" style="display:none"></div>
<div id="name"></div>

</body>
<h1>hello</h1>
</html>
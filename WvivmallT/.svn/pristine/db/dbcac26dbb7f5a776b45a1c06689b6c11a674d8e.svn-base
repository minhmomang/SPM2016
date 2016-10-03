$(function(){
	$("#idloginfacebook").click(function(){
		_login();
	});
});
  (function(thisdocument, scriptelement, id) {
    var js, fjs = thisdocument.getElementsByTagName(scriptelement)[0];
    if (thisdocument.getElementById(id)) return;
    
    js = thisdocument.createElement(scriptelement); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js"; //you can use 
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));
    
  window.fbAsyncInit = function() {
  FB.init({
   // appId      : '1641488349403101', //local hót
	appId      : '1079675335400464', //Your jobitvn.com
    cookie     : true,  // enable cookies to allow the server to access 
                        // the session
    xfbml      : true,  // parse social plugins on this page
    oauth: true,
    version    : 'v2.4' // use version 2.1
  });

  // These three cases are handled in the callback function.
  FB.getLoginStatus(function(response) {
    statusChangeCallback(response);
  });

  };
    
  // This is called with the results from from FB.getLoginStatus().
  function statusChangeCallback(response) {
    if (response.status === 'connected') {
      // Logged into your app and Facebook.
    	FB.logout(function(data){
    		
    	});
    } else if (response.status === 'not_authorized') {
      // The person is logged into Facebook, but not your app.
      document.getElementById('status').innerHTML = 'Please log ' +
        'into this app.';
    }
  }  
  
  function _login() {
    FB.login(function(response) {
    	
       // handle the response
       if(response.status==='connected') {
        _i();
       }
     }, {scope: 'email'});
 }
 
 function _i(){
	 FB.api('/me?fields=id,name,gender,email,birthday', function(response) {
    	var fullname = response.name;
    	var email = response.email;
        var pdata = {
				'fullname':fullname,    				
				'email':email
		};
		Return_get('register','insert_facebook',pdata,'GET',function(data){
				unblockbg();
				if(data!=null){					
					var error = data.error;
					if(error==0){				
						
						$.session.set('userid',data.userid);
						window.location.href='my-account.html';	
					}
					else{
						if(error==1){
							$("#lbemail").text('Email Exists');
						}	
						else if(error==-1){							
							showdialog('Login Error');
						}
					}
				}
			});
    });
 }

function logout()
{
    gapi.auth.signOut();
    location.reload();
}
function login_google() 
{
  var myParams = {
    //localhost: 'clientid' : '318635761331-1asgknl47a1j5ca2ng7qo890a9hutapu.apps.googleusercontent.com',
    'clientid' : '318635761331-77k6cbidcmk1p6rt726fii4b74sc8i3j.apps.googleusercontent.com',//jobitvn.com	  
    'cookiepolicy' : 'single_host_origin',
    'callback' : 'loginCallback',
    'approvalprompt':'force',
    'scope' : 'https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.profile.emails.read'
  };
  gapi.auth.signIn(myParams);
}
 
function loginCallback(result)
{
    if(result['status']['signed_in'])
    {
        var request = gapi.client.plus.people.get(
        {
            'userId': 'me'
        });
        request.execute(function (resp)
        {
            var email = '';
            if(resp['emails'])
            {
                for(i = 0; i < resp['emails'].length; i++)
                {
                    if(resp['emails'][i]['type'] == 'account')
                    {
                        email = resp['emails'][i]['value'];
                    }
                }
            }
            var fullname = resp['displayName'];
            var pdata = {
    				'fullname':fullname,    				
    				'email':email
    		};
    		Return_get('register','insert_google',pdata,'GET',function(data){
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
 
}
function onLoadCallback()
{
    gapi.client.setApiKey('AIzaSyBHSHf56LvOVzl_s6G5wYxeLUrgD_e2CXI');
    gapi.client.load('plus', 'v1',function(){});
}
(function() {
    var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
    po.src = 'https://apis.google.com/js/client.js?onload=onLoadCallback';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
   
 })();
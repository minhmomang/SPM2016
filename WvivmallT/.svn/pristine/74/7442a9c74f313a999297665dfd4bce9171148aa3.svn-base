$(document).ready(function(){
	var height = $(window).height();
	height = height - 417;
	var _h = height / 2;
	$("#contain_login").css('margin-top',_h-60);
	$("#signin").click(function(){
		blockbg();
		var username=($("input[name='txtusername']").val());
        var pass=calcMD5($("input[name='txtpass']").val());

        var pdata={'username':username,'pass':pass};
        Return_get("Admin_loginController","login_admin",pdata,'GET',function(data){ 
        	unblockbg();
            if(data!=null){
                 if(data.f==="0"){
                    window.location.href = "../admin/index.html";
                 }else{
                    $("#testp").html(data.mes);
                }
           }
        });//end ajax post	
	});
	            //check login
          Return_get("Admin_loginController","getSession_admin","",'GET',function(data){ 
               if(data.isSessionAdmin=="error_getsession_profile_admin"){
            	  return;
               }
               else
                window.location.href = "../admin/index.html";
            	   //alert("123123");
          });//end check    
          $("#btn_test").click(function(){
              Return_get("Admin_loginController","get_Test","",'GET',function(data){ 
                alert(data.test);
          });//end check    
            
            
          });    	    	  
});//end ready
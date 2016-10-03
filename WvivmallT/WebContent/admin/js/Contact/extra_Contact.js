
$(function() {
  
  
   var type='A';
    var session_admin=$.session.get("profile_session_admin"); 
    var adminemail=$.session.get("adminemail"); 
	 exe_load_header(function(output) {
	   if(output==true){
   				 load_data_by_param();
              
	   }
	 });
   function load_data_by_param(){
 		      var param = getUrlParameter('contactid');	
               var pdata = {'contactid':param};
		Return_get("ContactController","get_contact",pdata,"GET",function(data){
			if(data!=null){
				$("#txtfullname").val(data.fullname);
				$("#txtemail").val(data.email);
				$("#txtphone").val(data.phone);
				$("#txtcontent").val(data.content);		
				$("#txtemailto").val(data.email);
				updatestate(data.contactid);
				}
		});//end Get
     }//end load param 
function  updatestate(contactid){
      var pdata = {'contactid':contactid};
    		Return_get("ContactController","updatestate",pdata,"GET",function(data){
    		  	if(data!=null){
    		  	   
    		  	}
    		  });
              
}    

 function get_json_contact(){
    var obj={};
  
    obj.email=adminemail;
    obj.email_to=$("#txtemailto").val();
    obj.title=$("#txtemail-title").val();
    obj.content=  $("#txtcontent_send").val();
   var str= JSON.stringify(obj);
	
    return str;
    
 }  
function check_befor_Send(){
    var title=$("#txtemail-title").val();
    var content=$("#txtcontent_send").val();
   // alert(content);
    if(title==null||title==''){
        alert("Tiêu đề trống");
        return false;
    }
     if(content.trim()==null||content.trim()==''){
        alert("Nội dung trống");
        return false;
    }
    return true;
}

$("#btn_sendmail").click(function(){
	

   if(!check_befor_Send()){
        return;
    }
    blockbg();
     var json_contact=get_json_contact();
      var pdata="{'json_contact':'"+json_contact+"'}";   
      //alert(pdata);      
       Return_get("ContactController","send_mail",pdata,"POST",function(data){
			if(data!=null){
				unblockbg();
				var error = parseInt(data.result);				
				if(error == 0 ){					
					$('#myModal').modal('hide');
					showdialog("dialogmanual",0, "Send email success!",'','');
					return;
				}					
				else{
					showdialog("dialogmanual",0, "Send email fail!",'','');
					return;
				}
			}
			else{
				showdialog("dialogmanual",0, "Send email fail!",'','');
				return;				
			}
		});
 });  
    
$("#btn-cancel").click(function(){
          window.location.href = "admin/Contact/contactmanager.html";
 });   

 });//end document    
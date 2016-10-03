$(function(){
	$("#idbirthday").datepicker();
	 $("#idbirthday").datepicker("option", "dateFormat", "yy-mm-dd");
	exe_load_header_order(function(out){		
		check_info_login(function(out1){
			
			if(out1==true){
				load_info_user();		
			}
			else{				
				showdialogfunctionok('dialogmanual','Bạn chưa đăng nhập, xin vui lòng đăng nhập!',function(){
					window.location.href=ReturnHosing_apache()+"signin.html";
				});
			}
		});		
	});	
	function load_info_user(){
		Return_get('MemberController','get_info_user_by_email','','GET',function(data){
			if(data!=null){
				$("#name").val(data.strFullname);
				$("#Email").val(data.strEmail);
				$("#idbirthday").val(data.strBirthday);
				$("#address").val(data.strAddress);
				var gender = data.gender;
				
				if(gender=='1'){ //nam
					$("#rdomale").prop('checked',true);
				}
				else if(gender=='0'){ //nu
					$("#rdofemale").prop('checked',true);
				}
			}
		});
	}
	$("#btnchangeinfo").click(function(){
		var fullname = $("#name").val();
		var email = $("#Email").val();
		var birthday = $("#idbirthday").val();
		var address = $("#address").val();
		var gender = $("input:radio[name ='rdogender']:checked").val();
		var oldpassword = $("#oldpassword").val();
		var newpassword = $("#newpassword").val();
		var retypepassword= $("#retypepassword").val();	
		var chkchangepass = $("#chkchangepass").prop('checked');
		if(chkchangepass==true){
			if(oldpassword==null || oldpassword==''){
				showdialog('dialogmanual',0,'Vui lòng nhập thông tin mật khẩu cũ','','');
				return;
			}
			if(newpassword==null || newpassword==''){
				showdialog('dialogmanual',0,'Vui lòng nhập thông tin mật khẩu mới','','');
				return;				
			}
			if(retypepassword==null || retypepassword==''){
				showdialog('dialogmanual',0,'Vui lòng nhập xác nhận mật khẩu mới','','');
				return;				
			}
			if(newpassword!=retypepassword){
				showdialog('dialogmanual',0,'Mật khẩu không khớp','','');
				return;
			}
		}
		var obj ={
			'strEmail':email,
			'strFullname':fullname,
			'strBirthday':birthday,
			'strAddress':address,
			'gender':gender,
			'passold':oldpassword,
			'paasnew':newpassword
		};
		 var str= JSON.stringify(obj);
		 var pdata="{'str':'"+str+"'}"; 
		 blockbg();
		 Return_get("MemberController","change_info_acc",pdata,"POST",function(data){
			if(data!=null){
				unblockbg();
				var error = parseInt(data.result);				
				if(error == 0 ){					
					load_info_user();
					showdialog('dialogmanual',0,'Save Success','','');					
				}					
				else{
					showdialog('dialogmanual',0,data.message,'','');					
				
				}
			}
			else{
				showdialog('dialogmanual',0,'Save Not Success','','');
			}
		});
		
	});
	$("#chkchangepass").change(function() {
	    if(this.checked) {
	        $("#boxchange").css('display','block');
	    }
	    else{
	    	$("#boxchange").css('display','none');
	    }
	    	
	});
});
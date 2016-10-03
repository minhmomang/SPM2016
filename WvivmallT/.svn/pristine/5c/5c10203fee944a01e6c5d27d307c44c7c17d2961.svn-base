$(function(){
	exe_load_header_order(function(out){	
		check_info_login(function(out1){
			
			if(out1==true){
				load_layout_data_general($.session.get("email_login"));	
			}
			else{				
				showdialogfunctionok('dialogmanual','Bạn chưa đăng nhập, xin vui lòng đăng nhập!',function(){
					
					window.location.href=ReturnHosing_apache()+"signin.html";
				});
				
			}
		});		
		
	});	
	
	
	
	function load_layout_data_general(y_email){
		Return_get("MemberController","get_info_member","","GET",function(data){
			if(data!=null&&data!="null"){			
				$("#sfullname").val(data.strFullname);
				$("#semail").text(data.strEmail);
				$("#sphone").val(data.strPhone);
				$("#saddress").val(data.strAddress);
			}				
		});			
		
	}
	$("#btnsubmit").click(function(){
		
		var pdata={
				'email':$("#semail").text(),
				'fullname':$("#sfullname").val(),
				'phone':$("#sphone").val(),
				'address':$("#saddress").val()
			};
		
		Return_get("MemberController","update_member",pdata,"GET",function(data){
			if(data!=null&&data!="null"){			
				if(data.result=="0"){
					showdialogfunctionok('dialogmanual','Cập nhật thông tin thành công',function(){
						location.reload();
					});
				}
			}				
		});		
		
	});
});
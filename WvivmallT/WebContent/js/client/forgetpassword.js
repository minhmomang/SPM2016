$(function(){
	exe_load_header(function(output){
		if(output==true){		
							
		}
	});
	$("#btnforget").click(function(){
		
		if(check_login()){
			blockbg();
			var email = $("#txtemail").val();			
			var pdata = {
				'email':email				
			};
			Return_get('MemberController','change_forget_password',pdata,'GET',function(data){
				unblockbg();
				if(data!=null){					
					var error = data.result;
					if(error==0){	
						$("#txtemail").val('');
						showdialog('dialogmanual', 0, 'Mật khẩu đã gửi tới địa chỉ email của bạn. Xin vui lòng xác nhận.', '', '');
					}
					else{
						showdialog('dialogmanual', 0, 'Gửi mật khẩu không thành công.', '', '');
					}
				}
			});
		}
	});
	function check_login(){
		var email = $("#txtemail").val();
		
		if(email==null || email==''){
			$("#lbemail").text('Vui lòng nhập email hoặc số điện thoại');
			return false;
		}
		else{
			$("#lbemail").text('');
		}
		
		return true;
	}
});
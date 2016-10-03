$(function(){
	exe_load_header(function(output){
		if(output==true){		
			get_info();
		}
		
	});

	function get_info(){
		Return_get('MemberController','demo','','GET',function(data){
			
		});
	}
	function write_log(callback,user){
		$.get("http://ipinfo.io", function(response) {
			var ip = response.ip;
			var hostname = response.hostname;
			var city = response.city;
			var region = response.region;
			var country = response.country;
			var loc = response.loc;
			var org = response.org;
			var obj = {
				'ip':ip,
				'hostname':hostname,
				'city':city,
				'region':region,
				'country':country,
				'loc':loc,
				'org':org,				
				'user':user
			};
			var pdata = {
					'str':JSON.stringify(obj)
			}
			Return_get('MemberController','write_log',pdata,'GET',function(data){				
				if(data!=null){					
					var error = data.error;
					if(error==0){			
						callback(true);			
					}
					else{
						callback(false);
					}
				}
			});
		}, "jsonp");
	}
	$("#signin").click(function(){
		
		if(check_login()){
			blockbg();
			var cookies = 'N';
			if ($("#setCookies_login").is(':checked')){
				cookies = 'Y';
			}
			var email = $("#txtemail").val();
			var pass = $("#txtpassword").val();
			var pdata = {
				'email':email,
				'pass':pass,
				'cookies':cookies
			};
			Return_get('MemberController','check_login',pdata,'GET',function(data){
				
				if(data!=null){					
					var error = data.error;
					if(error==1){			
						var email2 = data.email;						
						var fullname  = data.fullname;
						//write log
						call_login_all_hosting(email,pass);
						write_log(function(out){
							if(out){
								unblockbg();
								showdialogfunctionok('dialogmanual', 'Đăng nhập thành công.', function(out){
									window.location.href=ReturnHosing_apache()+'index.html';
								});
							}
						},email);				
					}
					else if(error==2){
						unblockbg();
						showdialog('dialogmanual', 0, 'Tài khoản chưa xác nhận, vui lòng xác nhận!.', '', '');
					}
					else{
						unblockbg();
						showdialog('dialogmanual', 0, 'Đăng nhập không thành công.', '', '');
					}
				}
			});
		}
	});
	function check_login(){
		var email = $("#txtemail").val();
		var pass = $("#txtpassword").val();
		if(email==null || email==''){
			$("#lbemail").text('Vui lòng nhập email hoặc số điện thoại');
			return false;
		}
		else{
			$("#lbemail").text('');
		}
		if(pass==null || pass==''){
			$("#lbpassword").text('Nhập mật khẩu');
			return false;
		}
		else{
			$("#lbpassword").text('');
		}
		return true;
	}
});
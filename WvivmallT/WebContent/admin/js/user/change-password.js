$(function(){
	exe_load_header(function(output) {
        if (output == true) {
        	get_user_login();
        }
    });
	function get_user_login() {
        Return_get("Admin_loginController", "getSession_admin", "", 'GET', function(data) {
            if (data != null) {
                if (data.isSessionAdmin == "error_getsession_profile_admin") {
					//$.session.set("profile_session_admin", data.isSessionAdmin);
                    //$.session.set("session_lang", data.lang);
                    window.location.href = "admin/login.html";
                } else {		
                    $("#txtusername").val(data.username);		
                }
            }
        }); //end ajax post	 
    }
	$("#btnchange").click(function(){
		var user = $("#txtusername").val();
        var pass = $("#txtpassword").val();

        if (pass == null || pass == '') {
            showdialog('dialogmanual', 0, 'Nhập mật khẩu', '', '');
            callback(false);
            return;
        }
        blockbg();
        var pdata = {
            'type': 'E',
            'user': user,
            'pass': pass
        };
        Return_get("UserController", "insert_user", pdata, "GET", function(data) {
            if (data != null) {
                unblockbg();
                var error = parseInt(data._error);

                if (error == 0) {
                	$("#txtpassword").val('');
                    $("#txtpassword").val('');
                    showdialog('dialogmanual', 0, 'Cập nhật thành công', '', '');
                    
                } else if (error == -2) {
                    showdialog('dialogmanual', 0, 'Người dùng không tồn tại', '', '');
                    
                } else {
                    showdialog('dialogmanual', 0, 'Cập nhật thất bại', '', '');
                   
                }
                
            } else {
                showdialog('dialogmanual', 0, 'Cập nhật thất bại', '', '');
                
            }
        });
	});
});
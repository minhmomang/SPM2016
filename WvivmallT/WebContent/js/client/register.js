$(function() {
    exe_load_header(function(output) {
        if (output == true) {
            $("#txtdateborn").datepicker({
                dateFormat: 'dd/mm/yy',
                onClose :function(date,obj){
                	 var name = date;
                     if (name == '' || name == "") {
                         $("#lbdateborn").text('Vui lòng ngày sinh');
                     } else {
                         $("#lbdateborn").text('');
                     }
                }
            });
           
            create_capchar();
        }
    });

    function create_capchar() {
        var url = ReturnHosing_tomcat();
        url = url + "CapcharController/createcapchar.htm";
        $("#captcha img").attr("src", url + "?" + Math.random());
    }
    $("#txtemail").focusin(function() {
        $(this).val('');
        $(this).css('color', 'black');
    });
    $("#txtemail").focusout(function() {
        var username = $(this).val();
        if (username == '' || username == "") {
            $("#lbemail").text('Vui lòng nhập email !');
        }else if(!isValidEmailAddress(username)){
        	  $("#lbemail").text('Vui lòng nhập đúng email !');
        }
        else {
            $("#lbemail").text('');
        }
    });
    $("#txtfullname").focusin(function() {
        $(this).val('');
        $(this).css('color', 'black');
    });
    $("#txtfullname").focusout(function() {
        var name = $(this).val();
        if (name == '' || name == "") {
            $("#lbfullname").text('Vui lòng nhập họ tên');
        } else {
            $("#lbfullname").text('');
        }
    });
    $("#txtPassword").focusin(function() {
        $(this).val('');
        $(this).css('color', 'black');
    });
    $("#txtpassword").focusout(function() {
        var name = $(this).val();
        var passconfirm = $("#txtpasswordconfirm").val();
        if (name == '' || name == "") {
            $("#lbpassword").text('Vui lòng nhập mật khẩu');

        } else if (name.length < 6) {
            $("#lbpassword").text('Mật khẩu tối thiểu 6 ký tự');
        } else if (name != passconfirm) {
            $("#lbpassword").text('Mật khẩu không khớp');
        } else {
            $("#lbpassword").text('');
            $("#lbpasswordconfirm").text('');
        }
    });

    $("#txtpasswordconfirm").focusin(function() {
        $(this).val('');
        $(this).css('color', 'black');
    });
    $("#txtpasswordconfirm").focusout(function() {
        var name = $(this).val();
        var passconfirm = $("#txtpassword").val();
        if (name == '' || name == "") {
            $("#lbpassword").text('Vui lòng nhập mật khẩu');

        } else if (name.length < 6) {
            $("#lbpassword").text('Mật khẩu tối thiểu 6 ký tự');
        } else if (name != passconfirm) {
            $("#lbpassword").text('Mật khẩu không khớp');
        } else {
            $("#lbpassword").text('');
            $("#lbpasswordconfirm").text('');
        }
    });
    $("#txtphone").focusin(function() {
        $(this).val('');
        $(this).css('color', 'black');
    });
    $("#txtphone").focusout(function() {
        var name = $(this).val();
        if (name == '' || name == "") {
            $("#lbphone").text('Vui lòng nhập số điện thoại');
        } else {
            $("#lbphone").text('');
        }
    });
    $("#txtdateborn").focusin(function() {
        $(this).css('color', 'black');
    });
  
    $("#txtcapchar").focusin(function() {

        $("#txtcapchar").val('');
        $("#txtcapchar").css('color', 'black');
    });
    $("#txtcapchar").focusout(function() {
        var capchar = $("#txtcapchar").val();
        if (capchar != null && capchar != "") {
            var pdata = {
                'strcapchar': capchar
            };
            Return_get("CapcharController", "check_capchar", pdata, "GET", function(data) {
                if (data != null) {
                    if (parseInt(data) == 0) {
                        $("#lberrorcapchar").text("");

                    } else {
                        $("#lberrorcapchar").text('Capchar không đúng');

                    }
                } else {
                    $("#lberrorcapchar").text('Lỗi');

                }
            });
        } else {
            $("#lberrorcapchar").text('Vui lòng nhập capchar');

        }
    });

    function check_capchar(callback) {
        //check capchar
        var capchar = $("#txtcapchar").val();
        if (capchar != null && capchar != "") {
            var pdata = {
                'strcapchar': capchar
            };
            Return_get("CapcharController", "check_capchar", pdata, "GET", function(data) {
                if (data != null) {
                    if (parseInt(data) == 0) {
                        $("#lberrorcapchar").text("");
                        callback(true);
                    } else {
                        $("#lberrorcapchar").text('Capchar không đúng');
                        callback(false);
                    }
                } else {
                    $("#lberrorcapchar").text('Lỗi');
                    callback(false);
                }
            });
        } else {
            $("#lberrorcapchar").text('Vui lòng nhập capchar');
            callback(false);
        }
    }
    $("#btnrefresh").click(function() {
        create_capchar();
    });

    function checkform() {
        var email = $("#txtemail").val();
        var fullname = $("#txtfullname").val();
        var pass = $("#txtpassword").val();
        var pass2 = $("#txtpasswordconfirm").val();
        var phone = $("#txtphone").val();
        var dateborn = $("#txtdateborn").val();
        var capchar = $("#txtcapchar").val();
        var check = true;
        //check email
        if (email == "" || email == null) {
            check = false;
            $("#lbemail").text('Vui lòng nhập email .');
            return check;
        }else if(!isValidEmailAddress(email)){
      	  $("#lbemail").text('Vui lòng nhập đúng email !'); 
      	  check = false;
      	  return check;
        } else {
            $("#lbemail").text('');
        }
        //fullname
        if (fullname == "" || fullname == null) {
            check = false;
            $("#lbfullname").text('Vui lòng nhập họ tên!');
            return check;
        } else {
            $("#lberrorfullname").text('');
        }
        //check pass
        if (pass == '' || pass == "") {
            $("#lbpassword").text('Vui lòng nhập mật khẩu!');
            check = false;
            return check;
        } else if (pass.length < 6) {
            $("#lbpassword").text('Mật khẩu tối thiểu 6 ký tự!');
            check = false;
            return check;
        } else if (pass != pass2) {
            showtext("#lbpassword", "text", "r17");
            //	$("#lberrorpassword").text('Mật khẩu không khớp!');
            check = false;
            return check;
        } else {
            $("#lbpassword").text('');
            $("#lbpasswordconfirm").text('');
        }
        //check pass confirm		
        if (pass2 == '' || pass2 == "") {

            $("#lbpasswordconfirm").text('Vui lòng nhập lại mật khẩu!');
            check = false;
            return check;
        } else if (pass2.length < 6) {

            $("#lbpasswordconfirm").text('Mật khẩu tối thiểu 6 ký tự!');
            check = false;
            return check;
        } else if (pass != pass2) {
            $("#lbpasswordconfirm").text('Mật khẩu không khớp!');
            check = false;
            return check;
        } else {
            $("#lbpassword").text('');
            $("#lbpasswordconfirm").text('');
        }
        //check phone
        if (phone == '' || phone == "") {
            $("#lbphone").text('Vui lòng nhập số điện thoại!');
            check = false;
            return check;
        } else {
            $("#lbphone").text('');
        }
        //check date born
        if (dateborn == '' || dateborn == "") {
            $("#lbdateborn").text('Nhập ngày sinh!');
            check = false;
            return check;
        } else {
            if (!ValidateDate(dateborn)) {
                check = false;
                $("#lbdateborn").text('Ngày sinh không hợp lệ!');
                return check;
            } else {
                $("#lberrordateborn").text('');
            }
        }


        return check;
    }
    $("#btnresigeter").click(function() {

        if (checkform()) {
            check_capchar(function(output) {
                if (output == true) {
                    var email = $("#txtemail").val();
                    var fullname = $("#txtfullname").val();
                    var pass = $("#txtpassword").val();

                    var phone = $("#txtphone").val();
                    var dateborn = $("#txtdateborn").val();
                    blockbg();
                    var obj = {
                        'strEmail': email,
                        'strFullname': fullname,
                        'strPassword': pass,
                        'strPhone': phone,
                        'strBirthday': dateborn
                    };
                    var pdata = {
							'str':JSON.stringify(obj)
							
					};
                    Return_get("MemberController", "insert_member", pdata, "GET", function(data) {
                        unblockbg();
                        if (data != null) {

                            var error = data.result;
                            if (error == "0") {
                                clearcontent();
                                showdialog('dialogmanual', 0, 'Vui lòng kiểm tra email của bạn để hoàn thành việc đăng ký.', '', '');
                            }else if (error == "-2") {
                                showdialog('dialogmanual', 0, 'Tên đăng nhập đã tồn tại.', '', '');
                            } else {
                                showdialog('dialogmanual', 0, 'Đăng ký không thành công.', '', '');
                            }
                        } else {
                            showdialog('dialogmanual', 0, 'Đăng ký không thành công.', '', '');
                        }
                    });
                } else {
                    showdialog('dialogmanual', 0, 'Capchar không đúng', '', '');
                }
            });
        } else {
            showdialog('dialogmanual', 0, 'Thông tin đăng ký không hợp lệ.', '', '');
            return;
        }
    });
    function clearcontent(){
    	$("#txtemail").val('');
        $("#txtfullname").val('');
        $("#txtpassword").val('');
        $("#txtpasswordconfirm").val('');
        $("#txtphone").val('');
        $("#txtdateborn").val('');
    }
    function check_capchar(callback) {
        //check capchar
        var capchar = $("#txtcapchar").val();
        if (capchar != null && capchar != "") {
            var pdata = {
                'strcapchar': capchar
            };
            Return_get("CapcharController", "check_capchar", pdata, "GET", function(data) {
                if (data != null) {
                    if (parseInt(data) == 0) {
                        $("#lberrorcapchar").text("");
                        callback(true);
                    } else {
                        $("#lberrorcapchar").text('Capchar không đúng');
                        callback(false);
                    }
                } else {
                    $("#lberrorcapchar").text('Lỗi');
                    callback(false);
                }
            });
        } else {
            $("#lberrorcapchar").text('Vui lòng nhập capchar');
            callback(false);
        }
    }
});
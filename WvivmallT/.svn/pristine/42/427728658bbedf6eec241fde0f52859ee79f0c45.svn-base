$(function() {
	exe_load_header(function(output) {
		clear_content();
	})

	$("#btnsend").click(function(){
	if(check_form()){
		check_capchar(function(output){
			if(output==true){
				var email = $("#txtemail").val();
				var fullname = $("#txtfullname").val();
				var phone = $("#txtphone").val();
				var title = $("#txttitle").val();
				var content = $("#txtcontent").val();
				var obj={
						'email':email,
						'fullname':fullname,
						'phone':phone,
						'title':title,
						'content':content
				};
				var pdata = {
							'str':JSON.stringify(obj)	
					};
				Return_get("ContactController","send_contact",pdata,"GET",function(data){
					if(data!=null){
						var error=data.result;
						if(error==0){
							showdialog('dialogmanual',0,'Gửi liên hệ thành công','','');
							clear_content();
						}
						else{
							showdialog('dialogmanual',0,'Không thành công, vui lòng kiểm tra thông tin','','');
						}
					}
					else{
						showdialog('dialogmanual',0,'Không thành công, vui lòng kiểm tra thông tin','','');
					}
				});
			}
		});
	}
	else{
			showdialog('dialogmanual',0,'Không thành công, vui lòng nhập đầy đủ thông tin','','');
			return;
		}
	});
	
	function create_capchar() {
		var url = ReturnHosing_tomcat();
		url = url + "CapcharController/createcapchar.htm";
		$("#captcha img").attr("src", url + "?" + Math.random());
	}
	;
	$("#btnrefresh").click(function() {
		create_capchar();
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
	function clear_content(){
		$("#txtemail").val('');
		$("#txtfullname").val('');
		$("#txtphone").val('');
		$("#txttitle").val('');
		$("#txtcontent").val('');
		$("#txtcapchar").val('');
		create_capchar();
	}
	function check_form(){
    	var email=$("#txtemail").val();
    	var fullname=$("#txtfullname").val();
    	var phone = $("#txtphone").val();
    	var title = $("#txttitle").val();
    	var content=$("#txtcontent").val();
    	var check = true;
    	if(email==null || email==""){
    		$("#lbemail").text('Vui lòng nhập email');
    		check=false;
    		return check;
    	}
    	if(fullname==null || fullname==""){
    		$("#lbfullname").text('Vui lòng nhập tên');
    		check=false;
    		return check;
    	}
    	if(phone==null || phone==""){
    		$("#lbphone").text('Vui lòng nhập số điện thoại');
    		check=false;
    		return check;
    	}
    	if(title==null || title==""){
    		$("#lbtitle").text('Vui lòng nhập tiêu đề');
    		check = false;
    		return check;
    	}
    	if(content==null || content==""){
    		$("#lbcontent").text('Vui lòng nhập nội dung');
    		check=false;
    		return check;
    	}
    	 return check;  	
    }
	
	$("#txtemail").focusin(function(){
		$(this).val('');
		$(this).css('color','black');
	$("#txtemail").focusout(function(){
		var email = $(this).val();
		if(email==null || email ==""){
			$("#lbemail").css('color','red');
			$("#lbemail").text('Vui lòng nhập email');
		}
		else{
			$("#lbemail").text('');
		}
	})
	});
	$("#txtfullname").focusin(function(){
		$(this).val('');
		$(this).css('color','black');
	$("#txtfullname").focusout(function(){
		var name = $(this).val();
		if(name==null || name ==""){
			$("#lbfullname").css('color','red');
			$("#lbfullname").text('Vui lòng nhập họ tên');
		}
		else{
			$("#lbfullname").text('');
		}
	})
	});
	$("#txtphone").focusin(function(){
		$(this).val('');
		$(this).css('color','black');
	$("#txtphone").focusout(function(){
		var phone = $(this).val();
		if(phone==null || phone ==""){
			$("#lbphone").css('color','red');
			$("#lbphone").text('Vui lòng nhập số điện thoại');
		}
		else{
			$("#lbphone").text('');
		}
	})
	});
	$("#txttitle").focusin(function(){
		$(this).val('');
		$(this).css('color','black');
	$("#txttitle").focusout(function(){
		var title = $(this).val();
		if(title==null || title ==""){
			$("#lbtitle").css('color','red');
			$("#lbtitle").text('Vui lòng nhập tiêu đề');
		}
		else{
			$("#lbtitle").text('');
		}
	})
	});
	$("#txtcontent").focusin(function(){
		$(this).val('');
		$(this).css('color','black');
	$("#txtcontent").focusout(function(){
		var content = $(this).val();
		if(content==null || content ==""){
			$("#lbcontent").css('color','red');
			$("#lbcontent").text('Vui lòng nhập nội dung');
		}
		else{
			$("#lbcontent").text('');
		}
	})
	});
	$("#txtcapchar").focusin(function(){
		$(this).val('');
		$(this).css('color','black');
	$("#txtcapchar").focusout(function(){
		var capchar = $(this).val();
		if(capchar==null || capchar ==""){
			$("#lberrorcapchar").css('color','red');
			$("#lberrorcapchar").text('Vui lòng nhập capchar');
		}
		else{
			$("#lberrorcapchar").text('');
		}
	})
	});
})
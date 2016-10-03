$(document).ready(function() {
	
	function clear_content(){
		$("#txtemail_footer").val("");
		$("#txtfullname_footer").val("");
	}
	
	function checkform(){
		
		var check=true;
		var email = $("#txtemail_footer").val();
		var name = $("#txtfullname_footer").val();
		if(email==null || email==""){
			showdialog('dialogmanual',0,'Xin nhập email','','');
			check = false;
			return check;
		}
		if(name==null || name==""){
			showdialog('dialogmanual',0,'Xin nhập họ tên','','');
			check = false;
			return check;
		}
		return check;
	}
	$("#btnsend_footer").click(function(){
		
		
		
		if(checkform()){	
			var email = $("#txtemail_footer").val();
			var name = $("#txtfullname_footer").val();
			var pdata={
					'email':email,
					'fullname':name
			};
			 Return_get("MemberController", "insert_customer", pdata, "GET", function(data) {
				 if(data!=null){
					 var error= data.result;
					 if(error==0){
						 showdialog('dialogmanual', 0, 'Đăng ký nhận tin nhắn qua email thành công', '', '');
						 clear_content();
					 }
					 else if(error==2){
						 showdialog('dialogmanual', 0, 'Email này đã tồn tại', '', '');
						 clear_content();
					 }
					 else {
						 showdialog('dialogmanual', 0, 'Đăng ký không thành công', '', '');
						 clear_content();
					 }
				 }
		})
		}
	})
	$("#linkabout1").click(function(){
		var url = ReturnHosing_apache_vivmall()+'about.html';
		window.location.href=url;
	});
	$("#linkregulation").click(function(){
		var url = ReturnHosing_apache_vivmall()+'regulation.html';
		window.location.href=url;
	});
	$("#linkcontact").click(function(){
		var url = ReturnHosing_apache_vivmall()+'contact.html';
		window.location.href=url;
	});
	$("#linksaleoff").click(function(){
		var url = ReturnHosing_apache_vivmall()+'saleoff.html';
		window.location.href=url;
	});
	$("#linknewstech").click(function(){
		var url = ReturnHosing_apache_newsvmall()+'index.html';
		window.location.href=url;
	});
	$("#linkjob").click(function(){
		var url = ReturnHosing_apache_jobvmall()+'index.html';
		window.location.href=url;
	});
});



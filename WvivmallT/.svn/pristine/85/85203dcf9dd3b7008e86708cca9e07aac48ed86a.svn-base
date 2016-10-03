$(function(){
	exe_load_header(function(out){
		load_payment_method();
		load_tranfer_method();
		check_session_email();
	});
	function check_session_email(){
		Return_get("MemberController","get_info_login_order",'',"GET",function(data){
			if(data!=null){
				
				if(data.fullname!='' && data.fullname != undefined){
	    			 $("#txtfullnameorder").val(data.fullname);
		    		 $("#txtfullnameorder").attr('readonly','readonly');
	    		}
				if(data.email!='' && data.email != undefined){
	    			 $("#txtemailorder").val(data.email);
		    		 $("#txtemailorder").attr('readonly','readonly');
	    		}
				if(data.phone!='' && data.phone != undefined){
	    			 $("#txtphoneorder").val(data.phone);
		    		 $("#txtphoneorder").attr('readonly','readonly');
	    		}
				
			}
		});
	}
	
	
	function load_payment_method(){
		Return_get("ExtraController","get_payment_method",'',"GET",function(data){
			if(data!=null){
				load_text(function(str){
					for(var i=0;i<data.length;i++)
					{
						var html = str;
						html = html.replace('@id',data[i].ID);
						html = html.replace('@name',data[i].Name);						
						$("#contain_payment_method").append(html);
					}						
					
				});
				
			}
		});
	}
	function load_tranfer_method(){
		Return_get("ExtraController","get_tranfer_method",'',"GET",function(data){
			if(data!=null){
				load_text1(function(str){
					for(var i=0;i<data.length;i++)
					{
						var html = str;
						html = html.replace('@id',data[i].ID);
						html = html.replace('@name',data[i].Name);						
						$("#contain_delivery_method").append(html);
					}						
					
				});
				
			}
		});
	}
	function load_text(callback){
		var url = ReturnHosing_apache()+'txt/payment_method.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	function load_text1(callback){
		var url = ReturnHosing_apache()+'txt/delivery_method.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	$("#txtfullnameorder").focusin(function(){			
		$("#txtfullnameorder").val('');
		$("#txtfullnameorder").css('color','black');
	});
	$("#txtfullnameorder").focusout(function(){
		var name = $("#txtfullnameorder").val();			
		if(name=="" ||name == null){		  
			$("#lberrorfullname").text("Vui lòng nhập họ tên!");
		}				
		else{
			$("#lberrorfullname").text("");
		} 				
	});
	$("#txtphoneorder").focusin(function(){
		$("#txtphoneorder").val('');
		$("#txtphoneorder").css('color','black');
	});
	$("#txtphoneorder").focusout(function(){
		var name = $("#txtphoneorder").val();			
		if(name=="" ||name == null){		 	  
			$("#lberrorphone").text("Vui lòng nhập số điện thoại!");
		}				
		else{
			$("#lberrorphone").text("");
		} 				
	});
	
	$("#txtaddressorder").focusin(function(){
		$("#txtaddressorder").val('');
		$("#txtaddressorder").css('color','black');
	});
	$("#txtaddress2order").focusin(function(){
		$("#txtaddress2order").val('');
		$("#txtaddress2order").css('color','black');
	});
	$("#txtaddress2order").focusout(function(){
		var name = $("#txtaddress2order").val();			
		if(name=="" ||name == null){		 
			$("#lberroraddress2").text("Vui lòng nhập địa chỉ giao hàng!");
		}				
		else{
			$("#lberroraddress2").text("");
		} 				
	});
	function checkemail(callback) 
	{
//			var email = $("#txtemailorder").val();
//			if (email == null || email == "") {
//				callback(true);
//			} else {
//
//				var pdata = {
//					'email':email
//				};
//				Return_get("ExtraController","check_email_exists_order",pdata,"GET",function(data){
//					if(data!=null){
//						if (data != null) {
//							if (parseInt(data) == 1) {							    
//								$("#lberroremail").text('Email đã tồn tại!');
//								callback(false);
//							} else {
//								callback(true);
//							}
//						} else {						 
//							$("#lberroremail").text('Xảy ra lỗi trong quá trình check email!');
//							callback(false);
//						}						
//					}
//				});
//			}
		callback(true);
	}
	function checkpayment()
	{
		
		var check = true;		
		var fullname = $("#txtfullnameorder").val();			
		if(fullname=="" ||fullname == null){		 	  
			$("#lberrorfullname").text("Vui lòng nhập họ tên!");
			check = false;
			return check;
		}				
		else{
			$("#lberrorfullname").text("");
		}
		
		var phone = $("#txtphoneorder").val();			
		if(phone=="" ||phone == null){		 
			$("#lberrorphone").text("Vui lòng nhập số điện thoại!");
			check = false;
			return check;
		}				
		else{
			$("#lberrorphone").text("");
		}
		
		var address = $("#txtaddress2order").val();			
		if(address=="" ||address == null){		
			$("#lberroraddress2").text("Vui lòng nhập địa chỉ giao hàng!");
			check = false;
			return check;
		}				
		else{
			$("#lberroraddress2").text("");
		}
		var delivery = $('input[name="groupdelivery"]:checked').attr('id');
		if(delivery==undefined || delivery=='' || delivery==null) {
			$("#lberrordelivery").text("Vui lòng chọn phương thức vận chuyển");
			check = false;
			return check;
		}
		else{
			$("#lberrorpayment").text("");
		}
		var payment = $('input[name="grouppayment"]:checked').attr('id');
		if(payment==undefined || payment=='' || payment==null) {
			$("#lberrorpayment").text("Vui lòng chọn phương thức giao hàng");
			check = false;
			return check;
		}
		else{
			$("#lberrorpayment").text("");
		}
		
		return check;
	}
	$("#btnokorder").click(function(){
		
		if(checkpayment()){
			checkemail(function(output){
				if(output==true){						
					
					var fullname = $("#txtfullnameorder").val();
					var email = $("#txtemailorder").val();
					var phone = $("#txtphoneorder").val();
					var address ='';
					var address2 = $("#txtaddress2order").val();
					var delivery = $('input[name="groupdelivery"]:checked').attr('id');
					var arrdelivery = delivery.split('_');
					var delivery = arrdelivery[1];					
					var payment = $('input[name="grouppayment"]:checked').attr('id');
					var arrpayment = payment.split('_');
					payment = arrpayment[1];		
					var invoice = $("#chkinvoice").prop('checked');
					var chkinvoice = 0;
					if(invoice==true)
					{
						chkinvoice=1;
					}	
					var notes = $("#txtnotes").val();
					var obj = {
							'fullname':fullname,
							'email':email,
							'phone':phone,
							'address':address,
							'address2':address2,
							'delivery':delivery,
							'payment':payment,
							'invoice':chkinvoice,
							'notes':notes
					};
					var pdata = {
							'str':JSON.stringify(obj)
							
					};
					blockbg();
					Return_get("OrderController","insert_order",pdata,"GET",function(data){
						unblockbg();
						if(data!=null){
							if(parseInt(data.result)==0){			
								showdialogfunctionok('dialogmanual','Đặt hàng thành công!',function(){
									// check ngan luong
									if(data.payment !=null && data.payment !='' && data.payment != undefined){
										var urlnl = data.url;													
										
										PopupCenter(urlnl,'xtf','1000','800');
										window.location.href="index.html";
									}	
									else{
										var orderid = data.order_id;
										window.location.href="order_detail.html?id="+orderid;
									}
								});
							}
							else if(parseInt(data.result)==-2){
								showdialog('dialogmanual',0,'Không có sản phẩm trong giỏ hàng!','','');		
								
							}	
							else if(parseInt(data.result)==-4){
								showdialog('dialogmanual',0,'Không tồn tại phương thức thanh toán!','','');		
								
							}	
							else if(parseInt(data.result)==-3){
								showdialog('dialogmanual',0,'Thông tin đơn hàng không đúng, hoặc không có sản phẩm trong giỏ hàng!','','');		
								
							}
							else{							   
								showdialog('dialogmanual',0,'Thêm đơn hàng không thành công!','','');									
							}								
						}
					});
					
				}
				else 
				{
					showdialog('dialogmanual',0,'Email đã tồn tại!','','');				    	
				}
			});
		}
		else{
			showdialog('dialogmanual',0,'Bạn vui lòng nhập đầy đủ thông tin cho đơn hàng!','','');			 			
		}
	});
	
});
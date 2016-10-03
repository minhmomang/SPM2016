
$(function() {	
	load_data();
	function load_data() {		
		var id = getUrlParameter('id');
		var subid = getUrlParameter('subid');
		var pdata = {
			'id':id,
			'subid':subid
		};
		Return_get("CommentController", "get_info_comment", pdata, 'GET', function(data) {		
			if (data != null) {
				$("#txtidcustomer").text(data.id_customer);
				$("#txtnamecustomer").text(data.name_customer);
				var pdate = data.date.split('-');
				var pfdate = pdate[2]+'/'+pdate[1]+'/'+pdate[0]+' '+pdate[3]+':'+pdate[4]+':'+pdate[5]
				$("#txtdate").text(pfdate);
				$("#txtidproduct").text(data.product);
				$("#txtnameproduct").text(data.product_name);
				$("#txtcontentmessage").text(data.message);
			}
		});	    
		
	}
	function show_message_fn(mes,fn){
		$("#content_message").text(mes);
		$("#dialogmanual" ).dialog({
			 height: 250,
			 width: 500,
			 modal: true ,
			 autoOpen: true ,
			 title:'Thông báo',
			 resizable: false ,
			 buttons: {	   			
				"Đóng": fn
			}
		});	
	}
	function show_message_b(mes,fn){
		$("#content_message").text(mes);
		$("#dialogmanual" ).dialog({
			 height: 250,
			 width: 500,
			 modal: true ,
			 autoOpen: true ,
			 title:'Thông báo',
			 resizable: false ,
			 buttons: {	   			
				"Đóng": function() {
				   $(this).dialog("close");
			   }
			}
		});	
	}
	$("#btnreplymessage").click(function(){
		
		var id = getUrlParameter('id');
		var subid = getUrlParameter('subid');
		var customerid = $("#txtidcustomer").text();
		var product = $("#txtidproduct").text();
		var message = $("#txtcontentreply").val();
		var obj  = {};
		obj.id = id;
		obj.subid =subid;
		obj.customerid =customerid;		
		obj.product = product;
		obj.message = message;		
		var strdata = JSON.stringify(obj);
		
		var pdata="{'str':'"+strdata+"'}";
		blockbg();
		Return_get("CommentController","reply_comment",pdata,"POST",function(data){
			if(data!=null){
				unblockbg();
				var error = parseInt(data.result);					
				if(error == 0 ){	
					show_message_fn('Gửi thành công',function(){
						self.close();
					});
				}					
				else{
					show_message_b('Gửi không thành công');			
				
				}
			}
			else{
				show_message_b('Gửi không thành công');			
			}
		});
	});    
  
});
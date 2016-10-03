$(function(){
	
	
	exe_load_header_order(function(out){		
		check_info_login(function(out1){
			
			if(out1==true){
				load_info_his_order();				
			}
			else{				
				showdialogfunctionok('dialogmanual','Bạn chưa đăng nhập, xin vui lòng đăng nhập!',function(){
					window.location.href=ReturnHosing_apache()+"signin.html";
				});
			}
		});		
	});	
	
	
	
});
function load_text(callback){
	var url = ReturnHosing_apache()+'txt/orderhistory.txt';
	$.get(url,function(data){
		callback(data);
	});
}
function load_text_mobi(callback){
	var url = ReturnHosing_apache()+'txt/orderhistory_mobi.txt';
	$.get(url,function(data){
		callback(data);
	});
}

function load_info_his_order(){		
	$("#content-order-his").text('');
	Return_get('OrderController','get_list_order_by_user','','GET',function(data){
		if(data!=null){
			var mobile = ismobile();
			if(mobile== false&& window.screen.width>992){
			load_text(function(str){
				for(var i=0;i<data.length;i++){
					var html = str;
					html = html.replace(/\@orderid/g,data[i].orderId);
					html = html.replace('@dateorder',formatdate(data[i].orderDate));
					html = html.replace('@productname',data[i].productId);
					html = html.replace('@price',formatcurrency(data[i].amount));	
					html = html.replace('@status',data[i].orderStatus);
					$("#content-order-his").append(html);
				}	
			});
			}else{
				$(".cssmobile").remove();
				load_text_mobi(function(str){
					for(var i=0;i<data.length;i++){
						var html = str;
						html = html.replace(/\@orderid/g,data[i].orderId);
						html = html.replace('@dateorder',formatdate(data[i].orderDate));
						html = html.replace('@productname',data[i].productId);
						html = html.replace('@price',formatcurrency(data[i].amount));	
						html = html.replace('@status',data[i].orderStatus);
						$("#content-order-his").append(html);
					}	
				});
				
			}
			
			
		}
	});
}
function cancel_order(order){
	
	pdata={
			'order_id':order
	}
	Return_get("OrderController","cancel_order",pdata,"GET",function(data){
		if(data!=null)
			{
			status=data.result;
				if(status==1||status==0){
					pdata1={
							'email' : data.email,
							'order_id': order
					}
					showdialog('dialogmanual', 0, 'Hủy thành công', '', '');
					Return_get('MemberController','mail_cancel_order',pdata1,'GET',function(data){
					
					})
					load_info_his_order();
				}
				else if(status==3){
					showdialog('dialogmanual', 0, 'Đơn hàng đã được chuyển', '', '');
				}
				else if(status==-1){
					showdialog('dialogmanual', 0, 'Đơn hàng đã được hủy', '', '');
				}
				else{
					
					showdialog('dialogmanual', 0, 'Lỗi', '', '');
					load_info_his_order();
				}
			}
	})	
}

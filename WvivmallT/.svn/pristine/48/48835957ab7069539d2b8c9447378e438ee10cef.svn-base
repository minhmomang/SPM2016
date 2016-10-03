$(function(){
	exe_load_header_order(function(out){		
		check_info_login(function(out1){
			
			if(out1==true){
				load_product_later();
			}
			else{				
				showdialogfunctionok('dialogmanual','Bạn chưa đăng nhập, xin vui lòng đăng nhập!',function(){
					window.location.href=ReturnHosing_apache()+"signin.html";
				});
			}
		});		
	});	
	function load_product_later(){		
		
		Return_get('ProductController','new_product','','GET',function(data){
			if(data!=null){
				$("#content-save-product").text('');
				load_text(function(str){
					for(var i=0;i<data.length;i++){
						var html = str;
						html = html.replace('@imgurl',ReturnHosing_tomcat()+'upload/product/'+data[i].productImage);
						html = html.replace('@productid',data[i].productId);
						html = html.replace('@productid',data[i].productId);
						html = html.replace('@productid',data[i].productId);
						html = html.replace('@nameproduct',data[i].productName);
						html = html.replace('@priceproduct',formatcurrency(data[i].productPrice));
						html = html.replace('@description',data[i].productDes);
						$("#content-save-product").append(html);
					}	
					exec_event();
				});
				
			}
		});
	}
	function load_text(callback){
		var url = ReturnHosing_apache()+'txt/item_product_later.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	function exec_event(){
		$(".savebtn").click(function(){
			var id = $(this).attr('id');
			var arr = id.split('_');
			id = arr[1];
			var pdata =  {
					'product_id':id,
					'product_quantity':1
			};
			blockbg();
			Return_get("ShoppingCartController","add_shopping_cart",pdata,"GET",function(data){
				unblockbg();
				if (data != null) {
					showdialog('dialogmanual',0,'Thêm giỏ hàng thành công','','');	
				}
			});
		});
		$(".btncancel").click(function(){
			var id = $(this).attr('id');
			var arr = id.split('_');
			id = arr[1];
			var pdata =  {
					'product_id':id
			};
			blockbg();
			Return_get("ProductController","cancel_new_product",pdata,"GET",function(data){
				unblockbg();
				if (data != null) {					
					if(data.result=='0'){
						load_product_later();
						showdialog('dialogmanual',0,'Hủy bỏ thành công','','');		
					}
					else if(data.result=='-2'){
						showdialog('dialogmanual',0,'Sản phẩm không tồn tại!','','');		
					}
					else{
						showdialog('dialogmanual',0,'Hủy bỏ không thành công','','');		
					}
				}
			});
			
		});
	}
});

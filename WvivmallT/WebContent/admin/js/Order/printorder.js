$(function(){
	
	load_order();
	load_order_detail();
	function load_order(){
		
		var orderid = getUrlParameter('id');
		var pdata = {
			'orderid':	orderid
		};
		Return_get("OrderController", "find_order_by_id", pdata, 'GET', function(data) {		
			if (data != null) {
				$.map(data,function(item){					
					$("#lborderid").text(item.orderId);
					$("#lborderdate").text(formatedate(item.orderDate));
					$("#lbfullname").text(item.customername);
					$("#lbaddress").text(item.address_delivery);
					$("#lbphone").text(item.phone);					
					$("#lbpaymemt").text(item.payment_method);
					$("#lbinvoice").text(item.invoice);	
					$("#lbstatus").text(item.orderStatus);
				});	
			}
		});
	}
	function load_order_detail(){
		
		var orderid = getUrlParameter('id');
		var pdata = {
			'orderid':	orderid
		};
		Return_get("OrderController", "get_detail_order_by_id", pdata, 'GET', function(data) {		
			if (data != null) {
				var totalamount = 0;				
				$.map(data,function(item){				
					
					var str = '';
					str+='<tr>';				
					
					str+='<td style="width:10%;text-align:center">'+item.product_id+'</td>';
					str+='<td style="width:50%">'+item.product_name+'</td>';
					str+='<td style="width:10%;text-align:center">'+item.quantity+'</td>';
					str+='<td style="width:10%;text-align:center">'+formatcurrency(item.price)+'</td>';
					str+='<td style="width:10%;text-align:center">'+formatcurrency(item.amount)+'</td>';
					str+='</tr>';
					$("#content_order").append(str);
					totalamount+= parseFloat(item.amount);
				});	
				
				$("#totalamount").text(formatcurrency(totalamount));
			}
		});
	}
	$("#btnclose").click(function(){
		self.close();
	});
	$("#btnprocess").click(function(){
		var orderid = getUrlParameter('id');
		window.location.href="ExecPrintOrder.html?id="+orderid;
	});	
});

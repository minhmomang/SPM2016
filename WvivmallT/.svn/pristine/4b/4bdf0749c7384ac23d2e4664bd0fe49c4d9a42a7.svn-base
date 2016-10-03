$(function(){
	exe_load_header(function(out){
		var id = getUrlParameter('id');
		loadinfocustomer(id);
	});
	
	function loadinfocustomer(id) {
		var pdata = {
				'orderid':id
		}
		Return_get("OrderController","get_info_customer",pdata,"GET",function(data){
			if(data!=null){
				if (data != null) {	
					$("#lbcustomername").text(data.customername);
					$("#lbphone").text(data.phone);
					$("#lbaddressdelivery").text(data.address_delivery);
					$("#lbpaymentmethod").text(data.payment_method);					
				}				
			}
		});		
	}
});
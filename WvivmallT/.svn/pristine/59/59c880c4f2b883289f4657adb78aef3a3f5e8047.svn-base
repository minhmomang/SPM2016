$(function(){
	
	var _ismobile = ismobile();
	load_product_category();
	if(_ismobile==false){
		
		$("#ttlcurrency").css('display','block');
		$("#containcurrency").css('display','block');
		$("#ttlproductbest").css('display','block');
		$("#containproductbest").css('display','block');
		$(".title-main").css('display','block');
		get_list_new();		
	}	
	
	
	function load_product_category(){
		Return_get("ProductController","get_list_category",'',"GET",function(data){
			if(data!=null){
				$.map(data,function(item){
					if(item.ID!='00')
					{
						var html = '<li><a href="product.html?cate='+item.producttype+'">'+item.producttypename+'</a></li>';
						$("#contain_category").append(html);	
					}						
				});
			}
		});
	}
	function get_list_new(){
		
		Return_get("ProductController","get_list_new",'',"GET",function(data){
			if(data!=null){
				load_text(function(str){
					for(var i=0;i<data.length;i++)
					{
						var html = str;
						html = html.replace('@src',ReturnHosing_tomcat()+'upload/product/'+data[i].productImage);
						html = html.replace('@name',"");
						html = html.replace('@href','detail-product.html?id='+data[i].productId);
						$("#carouselv").append(html);
					}	
					loadScriptadv('js/jsCarousel-2.0.0.js',function(out){
						$('#carouselv').jsCarousel({ 
							onthumbnailclick: function(src) { }, 
							autoscroll: true, 
							masked: false, 
							itemstodisplay: 5, 
							orientation: 'v' 
						});
					});					
				});
				
			}
		});
	}
	function load_text(callback){
		var url = ReturnHosing_apache()+'txt/sell_best.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	function load_text_currency(callback){
		var url = ReturnHosing_apache()+'txt/currency.txt';
		$.get(url,function(data){
			callback(true,data);
		});
	}
	var listcurcode=["AUD", "EUR", "GBP","JPY","USD"];	 
	function contains(a, obj) {
	    var i = a.length;
	    while (i--) {
	       if (a[i] === obj) {
	           return true;
	       }
	    }
	    return false;
	} 
	
	function get_currentcy(){
		Return_get("CurrencyController","get_currentcy",'',"GET",function(data){
			if(data!=null){
				load_text_currency(function(out,str){
					if(out==true){							
						for(var i=0;i<data.length;i++){
							var html = str;	
							if(contains(listcurcode,data[i].currency_code)==true){
								html = html.replace('@currency_code',data[i].currency_code);
								html = html.replace('@buy_cash',data[i].buy_cash);
								html = html.replace('@buy_transfer',data[i].buy_transfer);
								html = html.replace('@sold_out',data[i].sold_out);
								$("#datacurrency").append(html);	
							}					
						}
					}
				});				
			}
		});
	}
});
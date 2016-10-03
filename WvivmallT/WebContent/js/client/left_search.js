$(function(){
	var _ismobile = ismobile();
	if(_ismobile==false){
		load_product_category();
		load_product_new();	
	}
	else{
		load_product_category();
		$("#ttlnewproduct").css('display','none');
		$("#containsalebest").css('display','none');
	}
		
	
	function load_product_category(){
		Return_get("ProductController","get_list_category",'',"GET",function(data){
			if(data!=null){
				$.map(data,function(item){
					if(item.ID!='00')					{
						var html = '<li><a href="product.html?cate='+item.producttype+'">'+item.producttypename+'</a></li>';
						$("#contain_category").append(html);	
						html = '<option value='+item.producttype+'>'+item.producttypename+'</option>';
						$("#category_search").append(html);
					}						
				});
			}
		});
	}
	function load_product_new(){
		Return_get("ProductController","get_list_new",'',"GET",function(data){
			if(data!=null){
				load_text(function(str){
					for(var i=0;i<data.length;i++)
					{
						var html = str;
						html = html.replace('@src',ReturnHosing_tomcat()+'upload/product/'+data[i].productImage);
						html = html.replace('@name',data[i].productName);
						var base_url_detail = url_detail;
						base_url_detail = base_url_detail.replace('@id',data[i].productId);
						base_url_detail = base_url_detail.replace('@url',window.location.href);
						html = html.replace(/\@href/g,encodeURI(base_url_detail));
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
		var url = ReturnHosing_apache()+'txt/product_new.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	
	
	function changeprice(){
		var price = $("#rangeprice").val();
		$("#maxprice").val(formatcurrency(price));	
	}
	$("#rangeprice").change(function(){
		var price = $("#rangeprice").val();
		$("#maxprice").val(formatcurrency(price));	
	});
	$("#minprice").keypress(function(){
		var price = $("#minprice").val();
		$("#minprice").val(formatcurrency(price));
	});
	$("#maxprice").keypress(function(){
		var price = $("#maxprice").val();
		$("#maxprice").val(formatcurrency(price));
	});
	$("#btnsearchadv").click(function(){
		 var key = $("#txtsearch").val();
		 var cate = $("#category_search").val();
		 var minprice = $("#minprice").val().replace(/\,/g, '');
		 var maxprice = $("#maxprice").val().replace(/\,/g, '');
		 key = key.replace(/\ /g,'_');		
		 window.location.href = "search_product.html?key="+key+"&cate="+cate+"&minprice="+minprice+"&maxprice="+maxprice;
	});
});
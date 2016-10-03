$(function(){
	
	var _ismobile = ismobile();
	load_product_category();
	if(_ismobile==false){
		$("#contain-myCarousel").css('display','block');		
		load_slide();
		load_skype();
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
	function load_slide(){
		var path=ReturnHosing_tomcat()+'upload/adv';
		Return_get("AdvController","get_list_slide_active",'',"GET",function(data){
			if(data!=null){	
				for(var i=0;i<data.length;i++){
					if(i==0){
						var imgdiv='<div class="item active"><img style="height:400px;" src="'+path+'/'+data[0].name+'" alt="'+data[0].name+'"></div>'
						$(".carousel-indicators").append('<li data-target="#myCarousel" data-slide-to="0" class="active"></li>');
						$(".carousel-inner").append(imgdiv);
					}else{
						var imgdiv2='<div class="item"><img style="height:400px;" src="'+path+'/'+data[i].name+'" alt="'+data[i].name+'"></div>'
						$(".carousel-indicators").append('<li data-target="#myCarousel" data-slide-to="'+i+'"></li>');
						$(".carousel-inner").append(imgdiv2);
					}					
				}
				
			}
					
		});
	}
});
$(function(){
	
	load_product_wantam();
	
function load_product_wantam(){
		
		var str='<div style="border: 1px solid lightgray;margin-top:3px;">'
			+'<a href="@href" style="text-decoration:none"><img alt="" src="@src" /><br />'
         +'<span class="thumbnail-text">@name</span>'
     + '</a>'
					+'</div>'		;
	
		Return_get("ProductController","get_list_product_rand","","GET",function(data){
			if(data!=null){
					for(var i=0;i<data.length;i++)
					{
						var html = str;
						html = html.replace('@src',ReturnHosing_tomcat()+'upload/product/'+data[i].productImage);
						html = html.replace('@name',data[i].productName);
						html = html.replace('@href','detailProduct.html?id='+data[i].productId);
						$("#carouselv").append(html);
					}

			}
		});
	}
	
	
});




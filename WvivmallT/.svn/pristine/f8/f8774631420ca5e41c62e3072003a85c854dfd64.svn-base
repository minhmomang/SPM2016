var page= 1;
var recordperpage = 16;
var total_page = 0;
var _ismobile = ismobile();
var cate ='%%';
$(function(){	
	
	exe_load_header_product(function(out){
		if(out==true){
			var key = getUrlParameter('cate');
			if(key!=undefined){
				cate = key;
			}
			if(_ismobile==false){
				load_skype();	
			}			
			
			load_social();
			load_product();
		}		
	});
	
	
	$("#nextpage").click(function(){
		page+=1;
		load_product();
	});
	$("#prevpage").click(function(){
		page-=1;
		load_product();
	});
});
function load_text(callback){
	var url = ReturnHosing_apache()+'txt/product2.txt';
	$.get(url,function(data){
		callback(true,data);
	});
}
function load_page(){	
	
	if(page==total_page)	{
		$("#prevpage").css('display','block');
		$("#nextpage").css('display','none');
	}
	if(page!=total_page)	{
		$("#prevpage").css('display','block');
		$("#nextpage").css('display','block');
	}
	if(page==1){
		$("#prevpage").css('display','none');
		$("#nextpage").css('display','block');
	}
	if(total_page==1){
		$("#prevpage").css('display','none');
		$("#nextpage").css('display','none');		
	}
	$("#contentpaging").text('');
	
	if(total_page<=8){
		pageing(1,total_page);
	}
	else{
		if(page+7<=total_page){
			var n = page+7;
			pageing(page,n);
		}
		else{
			pageing(total_page-7,total_page);
		}
	}
}
function pageing(start,n){		
	for(var i=start;i<=n;i++){
		var str = '<li><a class="@class" href="@href" onclick="@onclick">@page</a></li>';
		if(i==page){
			str = str.replace("@class",'visited');
			str = str.replace("@href",'');
			str = str.replace("@onclick",'');
		}
		else{
			str = str.replace("@href",'javascript:void(0)');
			str = str.replace("@onclick","javascript:viewpage("+i+");");
		}
		str = str.replace('@page',i);				
		$("#contentpaging").append(str).show();	
	}
}
function viewpage(p){
	page = p;
	load_product();
}
function load_product(){
	var pdata = {
			'cate':cate,
			'fromrecode':(page-1)*recordperpage,
			'recordperpage':recordperpage
	};
	$("#idloading").css('display','block');
	Return_get("ProductController","get_list_product_cate",pdata,"GET",function(data){
		if(data!=null){					
			 load_text(function(output,str){
     				if(output==true){
     					 //
     					 if(data.length>0){
     						
     						total_page = data[0].rownum /recordperpage;
     						total_page=Math.floor(total_page);
     						if(data[0].rownum  % recordperpage!=0){
     							total_page+=1;
     						}     						
     						 //
     						$("#hidden_content_product").css('display','none');
	     					$("#content_product").text('');
	     					loadScriptadv('js/jquery.elevatezoom.js',function(out){								
								if(out==true){
									var j=0;
									for(var i=0;i<data.length;i++)
									 { 
										var html = str;
										html = html.replace('@href','detail-product.html?id='+data[i].productId);	     						
										html = html.replace('@idprduct',data[i].productId);
										html = html.replace('@srcimg',ReturnHosing_tomcat()+'upload/product/'+data[i].productImage);
										html = html.replace('@srcimg',ReturnHosing_tomcat()+'upload/product/'+data[i].productimglarg);
										html = html.replace('@id',data[i].productId);
										html = html.replace('@name',data[i].productName);	     						
										var price = formatcurrency(data[i].productPrice);
										html = html.replace('@price',price);     			     												
										if(_ismobile==false){
											if(j==2){										
												html = html.replace('@numpading','0px');
											}
											else{
												
												html = html.replace('@numpading','5px');
											}	
										}
										else{
											html = html.replace('@numpading','5px');
										}
										
										$("#content_product").append(html);
										
										if(_ismobile==false){
											
											$('#img_'+data[i].productId).elevateZoom({
												zoomWindowPosition:1
											}); 
											
										}																					
										
									 }	     	
									$("#idloading").css('display','none');								
									load_page();		
								}
							});
     					 }	
     					 else{
     						hidden_content_page();
     					 }	     					 
	     					
     				} 
     		});
		}				
	});
}
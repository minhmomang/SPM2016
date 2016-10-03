
var path_imgcate = ReturnHosing_tomcat()+"upload/category";
var path_imgproduct = ReturnHosing_tomcat()+"upload/product";
var arraystartimg = [ "image/star1.png", "image/star2.png", "image/star3.png", "image/star4.png",
        "image/star5.png", "image/star6.png"
    ];
var arraycolor = ["#3184c5", "#d56530", "#619b4b", "#e6098b", "#7302bd", "#04beb3"];
var htmlitem = '';
var htmlitem2 = '';
var htmlitem3 = '';
var mobile = ismobile();
$(function(){
	exe_load_header(function(){
		
		if(mobile==false &&window.screen.width>992){
			load_text2(function(str){
				htmlitem = str;
			});
			load_text3(function(str){
				htmlitem2 = str;
			});
			load_text4(function(str){
				htmlitem3 = str;
			});
			load_text5(function(str){
				htmlitem4 = str;
			});
			load_category_layout();
		}
		else{
			load_mobile_text2(function(str){
				htmlitem2 = str;
			});
			load_mobile_text3(function(str){
				htmlitem3 = str;
			});
			load_mobile_item_box_product();
		}	
	
	}); 

	function load_mobile_item_box_product(){
		$("#wapper-content").html("");
		Return_get("ProductController","get_all_rand_product_forindex","","GET",function(datamain){
			
			for(var d=0;d<datamain.length;d++){
				var titlecate="<div class='col-xs-12 title-d' style='color:white;font-size:30px;background-color:"+arraycolor[d]+"' >"+datamain[d].producttypename+"</div>";
				//alert(titlecate);
				$("#wapper-content").append(titlecate);
				var data=datamain[d].list;
				for(var i=0;i<data.length;i++)
				 { 
					if(data[i].isPromo==0){
							var html = htmlitem2;
							var base_url_detail = url_detail;
							base_url_detail = base_url_detail.replace('@id',data[i].productId);
							base_url_detail = base_url_detail.replace('@url',window.location.href);
							html = html.replace(/\@href/g,encodeURI(base_url_detail));	     						
							html = html.replace('@idprduct',data[i].productId);
							html = html.replace('@cate',data[i].producttype);
							html = html.replace('@srcimg',ReturnHosing_tomcat()+'upload/product/'+data[i].productImage);
							html = html.replace('@srcimg',ReturnHosing_tomcat()+'upload/product/'+data[i].productimglarg);
							var base_url_detail = url_detail;
							base_url_detail = base_url_detail.replace('@id',data[i].productId);
							base_url_detail = base_url_detail.replace('@url',window.location.href);
							html = html.replace(/\@href/g,encodeURI(base_url_detail));
							html = html.replace('@id',data[i].productId);
							html = html.replace('@idvalue',data[i].productId);
							html = html.replace('@name',data[i].productName);	     						
							html = html.replace('@price',formatcurrency(data[i].productPrice));		     												
							$("#wapper-content").append(html);
				 }
					else if(data[i].isPromo==1){
								var html = htmlitem3;
								var base_url_detail = url_detail;
								base_url_detail = base_url_detail.replace('@id',data[i].productId);
								base_url_detail = base_url_detail.replace('@url',window.location.href);
								html = html.replace(/\@href/g,encodeURI(base_url_detail));	     						
								html = html.replace('@idprduct',data[i].productId);
								html = html.replace('@cate',data[i].producttype);
								html = html.replace('@srcimg',ReturnHosing_tomcat()+'upload/product/'+data[i].productImage);
								html = html.replace('@srcimg',ReturnHosing_tomcat()+'upload/product/'+data[i].productimglarg);
								html = html.replace('@id',data[i].productId);
								html = html.replace('@idvalue',data[i].productId);
								html = html.replace('@name',data[i].productName);
								var base_url_detail = url_detail;
								base_url_detail = base_url_detail.replace('@id',data[i].productId);
								base_url_detail = base_url_detail.replace('@url',window.location.href);
								html = html.replace(/\@href/g,encodeURI(base_url_detail));
								var price = formatcurrency(data[i].productPrice);
								html = html.replace('@price',price);   
								html = html.replace('@oldprice',formatcurrency(data[i].price_old));
						
								$("#wapper-content").append(html);
								
						
					}
				 }//end for i
				
			}//end for d
			regis_click_mua();
		
		});
	}
	
	
	function load_category_layout(callback) {	
		append_loading("#wapper-content");
        Return_get("ProductController", "get_list_category", "", "GET", function(data) {
            if (data != null) {
            	$("#wapper-content").text('');
            	load_text(function(str){            		
            		for(var i=0;i<data.length;i++){
            			if(data[i].ID !='00'){
            				var html = str;
                			html = html.replace('@icon',arraystartimg[i]);
                			html = html.replace(/@colorcate/g,arraycolor[i]);
                			html = html.replace('@namecate',data[i].producttypename);
                			html = html.replace(/\@cate/g, data[i].producttype);
                			$("#wapper-content").append(html);
                			load_product_cate(data[i].producttype,i,data[i].category_img); 
    
            			}  
            			
                    }
            		hover_box();
            		regis_click_mua();
            	});               
            }
        });
    }
	
	function load_product_cate(cate,pos,imgcat){
		var pdata = {
				'cate':cate
		};
		Return_get("ProductController","get_list_product_popup3",pdata,"GET",function(data){
			if(data!=null){			
				if(data.list.length==0){					
					$("#content-product_"+data.producttype).css('display','none');	
					$("#header-product_"+data.producttype).css('display','none');
				}
				else{
					//add img larger
					var strhtml1 = htmlitem3;
					strhtml1 = strhtml1.replace('@imgurl',path_imgcate+'/'+imgcat);					
					var cate = data.list[0].producttype;
					var productnum1 = 0;
					var id1 = "#"+cate+"_product"+productnum1;					
					$(id1).html(strhtml1);
					//
					for(var i=1;i<7;i++){
						var j = i-1;
						if(data.list[j]!= undefined){
							//alert("old : "+data.list[j].productPrice+" ---- new : "+data.list[j].newPrice+" ------------percent : "+data.list[j].pricePercent);
							if(data.list[j].isPromo=='0'){
								var strhtml = htmlitem4;
								strhtml = strhtml.replace('@imgurl',path_imgproduct+'/'+data.list[j].productImage);
								var name=data.list[j].productName;
								if(name.length>25){
									name = name.substring(0,20);
									name+="...";
								}
								strhtml = strhtml.replace('@nameproduct',name);
								strhtml = strhtml.replace(/\@cate/g,data.list[j].producttype);
								strhtml = strhtml.replace('@price',formatcurrency(data.list[j].productPrice));							
								strhtml = strhtml.replace(/\@productid/g, data.list[j].productId);
								var base_url_detail = url_detail;
								base_url_detail = base_url_detail.replace('@id',data.list[j].productId);
								base_url_detail = base_url_detail.replace('@url',window.location.href);
								strhtml = strhtml.replace(/\@href/g,encodeURI(base_url_detail));
								strhtml = strhtml.replace(' @imua_click', ' imua_click'+pos);
								var cate = data.list[j].producttype;
								var productnum = i;
								var id = "#"+cate+"_product"+productnum;					
								$(id).html(strhtml);
							}
							else if(data.list[j].isPromo=='1'){
								var strhtml = htmlitem;
								strhtml = strhtml.replace('@oldprice',data.list[j].price_old);
								strhtml = strhtml.replace('@imgurl',path_imgproduct+'/'+data.list[j].productImage);
								var base_url_detail = url_detail;
								base_url_detail = base_url_detail.replace('@id',data.list[j].productId);
								base_url_detail = base_url_detail.replace('@url',window.location.href);
								strhtml = strhtml.replace(/\@href/g,encodeURI(base_url_detail));
								//strhtml = strhtml.replace('@nameproduct',data.list[j].productName);
								var name=data.list[j].productName;
								if(name.length>25){
									name = name.substring(0,20);
									name+="...";
								}
								strhtml = strhtml.replace('@nameproduct',name);
								strhtml = strhtml.replace(/\@cate/g,data.list[j].producttype);
								strhtml = strhtml.replace('@price',formatcurrency(data.list[j].productPrice));							
								strhtml = strhtml.replace(/\@productid/g, data.list[j].productId);
								strhtml = strhtml.replace(' @imua_click', ' imua_click'+pos);
								var cate = data.list[j].producttype;
								var productnum = i;
								var id = "#"+cate+"_product"+productnum;					
								$(id).html(strhtml);
							}
							//hover_boxhp();
						}
						else{
							var cate = data.producttype;
							var productnum = i;
							var id = "#"+cate+"_product"+productnum;							
							var strhtml = htmlitem2;
							$(id).html(strhtml);
						//	$(id).css('display','none');								
						}						 
					}
					imua_click(pos);	
				}				
			}				
			else{
				$("#content-product_"+data.producttype).css('display','none');	
				$("#header-product_"+data.producttype).css('display','none');
			}
		});
	}
	function hover_box(){
		$(".box01").hover(function() {
			
			var nam=$(".name01");
			var muangay=$(".muangay01");
			var muangaybtn=$(".muangaybtn01");			
			
			var name=$(this).find(nam);
			var mua=$(this).find(muangay);
			
			name.css({
				"bottom" : "50px",
				"padding" : "20px 5px",
				"opacity" : "1",
				"transition":"1s"
			});
			
			mua.css("visibility", "visible");
			
			
		
		}, function() {
			$(".name01").css({
				"bottom" : "0px",
				"padding" : "5px 5px",
				"opacity" : "0.85"
			});
			$(".muangay01").css("visibility", "hidden");
		});		
		
	}
	
	
	
	function load_text(callback){
		var url = ReturnHosing_apache()+'txt/index.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	function load_text2(callback){
		var url = ReturnHosing_apache()+'txt/item-product.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	function load_text3(callback){
		var url = ReturnHosing_apache()+'txt/item-product2.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	function load_text4(callback){
		var url = ReturnHosing_apache()+'txt/item-product3.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	function load_text5(callback){
		var url = ReturnHosing_apache()+'txt/item-product4.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	function load_mobile_text2(callback){
		var url = ReturnHosing_apache()+'txt/product2.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	function load_mobile_text3(callback){
		var url = ReturnHosing_apache()+'txt/product3.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	function imua_click(pos){
		  $(".imua_click"+pos).click(function(){		   
			   var product_id=$(this).attr("data");   
			   var quantity=1;
			   var id = product_id;
			   if(id==null||id==''){
				   return;
			   }
			   else
			   {
				   var base_url_detail = url_detail;
				   base_url_detail = base_url_detail.replace('@id',product_id);
					base_url_detail = base_url_detail.replace('@url',window.location.href);
					window.open(encodeURI(base_url_detail), '_blank');
			   }
		  }); 
		  
	}
	function regis_click_mua(){

		$(".btnmua").click(function(){
			var product_id=$(this).data("value");
			var base_url_detail = url_detail;
			base_url_detail = base_url_detail.replace('@id',product_id);
			base_url_detail = base_url_detail.replace('@url',window.location.href);
			window.location.href=encodeURI(base_url_detail);
		});
	}	
	/*function checkSize(path){
		var filesize=0;
		var xhr
		xhr = $.ajax({
			  type: "HEAD",
			  url: path,
			  success: function(msg){
			   filesize = xhr.getResponseHeader('Content-Length')/1024;
			   alert(filesize);
			  }
			});
		alert(filesize);
	}*/
});

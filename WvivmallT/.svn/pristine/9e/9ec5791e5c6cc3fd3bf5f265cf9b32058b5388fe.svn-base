var check_timer_cart = false;
var check_timer_like = false;
$(function() {	
		load_category_layout();		
		get_service_info_login_server();	
		count_product_shopping_cart();
		//count_product_like();
		var _ismobile  = ismobile();
		if(_ismobile==false){
			$("#content-slide").load(ReturnUrlSlide(), function (responseText, textStatus, XMLHttpRequest) {
				
			});		
		}
		
		
	    var path_imgproduct = ReturnHosing_tomcat()+"upload/product";
	    var path = ReturnHosing_tomcat() + "upload/slide/";
	    var arraystartimg = ["1", "image/star1.png", "image/star2.png", "image/star3.png", "image/star4.png",
	        "image/star5.png", "image/star6.png"
	    ];
	    var arraycolor = ["1", "#3184c5", "#d56530", "#619b4b", "#e6098b", "#7302bd", "#04beb3"];
	    var list_category = [];
	    var list_product_popup = [];
	    	   
	    $("#linklogin").click(function(){
	    	
	    	var url = ReturnHosing_apache_account() + 'signin.html';
	    	var baseurl = window.location.href;
	    	url+="?baseurl="+baseurl;
	    	window.location.href=url;
	    });
	    
	    $("#linkregister").click(function(){
	    	var url = ReturnHosing_apache_account() + 'register.html';	    	
	    	window.location.href=url;
	    });
	    
	    $("#linklogout").click(function(){
	    	
	    	blockbg();
	    	set_service_info_login_server();
	    });
	    $("#linkshoppingcart").click(function(){
	    	window.location.href=ReturnHosing_apache_shoppingcart()+'shoppingCart.html';
	    });
	    $("#linkshoppingcart2").click(function(){
	    	window.location.href=ReturnHosing_apache_shoppingcart()+'shoppingCart.html';
	    });
	    $("#linklikeproduct").click(function(){
	    	window.location.href=ReturnHosing_apache_dvmall()+'like_product.html';
	    });
	    $("#linklikeproduct2").click(function(){
	    	window.location.href=ReturnHosing_apache_dvmall()+'like_product.html';
	    });
	    $("#linklikeproduct3").click(function(){
	    	window.location.href=ReturnHosing_apache_dvmall()+'like_product.html';
	    });
	    $("#linkviewproduct").click(function(){
	    	window.location.href=ReturnHosing_apache_dvmall()+'review_product.html';
	    });
	    $("#linkviewproduct2").click(function(){
	    	window.location.href=ReturnHosing_apache_dvmall()+'review_product.html';
	    });
	    $("#linkratingproduct").click(function(){
	    	window.location.href=ReturnHosing_apache_dvmall()+'rating_product.html';
	    });
	   
	   function count_product_shopping_cart(){
		   setInterval(function(){
				   if(check_timer_cart==false){
						check_timer_cart = true;
						get_count_product_shopping_cart();
				   }
				   
			}, 100);

	   }
	   function count_product_like(){		  
		   setInterval(function(){
				   if(check_timer_like==false){
						check_timer_like = true;
						get_count_product_like();
				   }
				   
			}, 100);

	   }
	   $("#linkhome").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'index.html';
		   window.location.href = url;
	   });
	   $("#linkbuyintro").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'introduce.html';
		   window.location.href = url;
	   });
	   $("#linkshopping").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'introduce.html';
		   window.location.href = url;
	   });
	   $("#linkquestion").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'introduce.html';
		   window.location.href = url;
	   });
	   $("#linkbuyproduct").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'introduce.html';
		   window.location.href = url;
	   });
	   $("#linkpaymentway").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'introduce.html';
		   window.location.href = url;
	   });
	   $("#linkdilivery").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'introduce.html';
		   window.location.href = url;
	   });
	   $("#linkaboutvivmall").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'introduce.html';
		   window.location.href = url;
	   });
	   $("#linkcheckorder").click(function(){
		   
		   var url = ReturnHosing_apache_shoppingcart()+'check-order.html';		   
		   window.location.href = url;
	   });
	   $("#linkgeneralaccount").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'general_Account.html';
		   window.location.href = url;
	   });
	   $("#linkinfoaccount").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'edit_account.html';
		   window.location.href = url;
	   });
	   $("#linkorderhistory").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'orderhistory.html';
		   window.location.href = url;
	   });
	   $("#linksaveproduct").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'save_product.html';
		   window.location.href = url;
	   });
	   $("#linkcommendproduct").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'comment_product.html';
		   window.location.href = url;
	   });
	   $("#linknewsproduct").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'new_product.html';
		   window.location.href = url;
	   });
	   $("#linknewproduct").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'product_new.html';
		   window.location.href = url;
	   });
	   $("#linkbuybest").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'product_mostbuy.html';
		   window.location.href = url;
	   });
	   $("#linkfindbest").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'key_product.html';
		   window.location.href = url;
	   });
	   $("#linktopproduct").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'day_product.html';
		   window.location.href = url;
	   });
	   $("#linkproductrecommend").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'product_recommend.html';
		   window.location.href = url;
	   });
	   $("#linkproductchoose").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'product_choose.html';
		   window.location.href = url;
	   });
	   $("#linkhightrating").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'product_highrating.html';
		   window.location.href = url;
	   });
	   $("#linkmostview").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'product_mostview.html';
		   window.location.href = url;
	   });
	   $("#linkfeedback").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'feedback.html';
		   window.location.href = url;
	   });
	   $("#btnsearchbasic").click(function(){		
			var key = $("#search-form").val();
			if(key==null || key=='' || key==undefined){
				showdialog('dialogmanual',0,'Vui lòng nhập từ khóa tìm kiếm','','');
				return;
			}
			else{
				window.location.href=ReturnHosing_apache()+'search_product.html?skey='+key;
			}
			
			
		});
	   $("#btnsearchbasicall").click(function(){		
			var key = $("#search-form-all").val();
			if(key==null || key=='' || key==undefined){
				showdialog('dialogmanual',0,'Vui lòng nhập từ khóa tìm kiếm','','');
				return;
			}
			else{
				window.location.href=ReturnHosing_apache_vivmall()+'search_product.html?skey='+key;
			}
			
			
		});
	   $("#btnshowmenu").click(function(){
			  //
			   var a = $(this).attr('opso_direc');
			   if(a=='up'){
				   $("#btnshowmenu img").attr('src','image/icon-down.gif');
				   $(this).attr('opso_direc','down');
			   }
			   else{
				   
				   $("#btnshowmenu img").attr('src','image/icon-up.gif');
				   $(this).attr('opso_direc','up');
			   }
			  $("#menu_main").slideToggle( "slow" );
	  });
	   function load_category_layout() {
	       
	        Return_get("ProductController", "get_list_category", "", "GET", function(data) {
	            if (data != null) {
	                list_category = data;
	                //alert(list_category.length);
	                for (var i = 0; i < data.length; i++) {
	                    var obj = data[i];
	                    var li = '<li ><a class="shmenusub" opso="'+obj.producttype+'" href="product_cate.html?id='+obj.producttype+'">' + obj.producttypename + '</a></li>';
	                    $("#content-menu").append(li);	                    
	                    
	                }
	                event_hover_li_cate();	                

	            }
	        });

	    }
	  
	    function event_hover_li_cate() {	      
	    	$(".shmenusub").hover(
	    		 function() {
	    			var id = $(this).attr('opso');
	    			$("#content-sub-menu").css('display','block');
	    			get_sub_menu(id);
	    		 }, function() {
	    			 $("#content-sub-menu").css('display','none');
	    			  
	    	});
	    	$("#content-sub-menu").hover(
		    	function() {
		    		$("#content-sub-menu").css('display','block');	
		    	}, function() {
		    	$("#content-sub-menu").css('display','none');
		    			  
		    });
	    } 
	   var list_sub=[];	   
	   function check_cate_exists(cate){
		   for(var i=0;i<list_sub.length;i++){
			   if(list_sub[i].cate==cate)
				   return true;
		   }
		   return false;
	   }
	   function set_image_sub(){
		   $("#imgsub1").text('');
		   $("#imgsub2").text('');
		   $("#imgsub3").text('');
	   }
	   function get_sub_menu(category_id){
		    set_image_sub();
		   
		   if(check_cate_exists(category_id)==false){
			   var pdata = {
					   "catid":category_id
			   }
			   Return_get("ProductController", "get_list_product_popup2",pdata, "GET", function(data) {
	               if(data!=null){
	            	   var item ={
	            			   'cate':data[0].producttype,
	            			   'data':data            			   
	            	   }
	            	   list_sub.push(item);	            	  
	            	   fn_view_html(data);
	               }
	               
	           });
		   }
		   else{
			   for(var i=0;i<list_sub.length;i++){
				   if(list_sub[i].cate==category_id){
					   var data = list_sub[i].data;
					   fn_view_html(data);
					   break;
				   }					 
			   }
			  
		   }
		   
	   }
	   function fn_view_html(data){
		    var str1 = '<a href="@href">';
			str1+='<img alt="" src="@src" style="height:250px;width:250px;margin-top:50px;" >'
			str1+='</a>';
			var str2 = '<a href="@href">';
			str2+='<img alt="" src="@src" style="height:150px;width:150px;margin-top:25px;" >'
			str2+='</a>';
		   for(var i=0;i<data.length;i++){
  		    
   		   switch (i) {
					case 0:	
						var strhtml1 = str1;
						strhtml1 = strhtml1.replace('@href','detailProduct.html?id='+data[i].productId);
						strhtml1 = strhtml1.replace('@src',path_imgproduct + "/" + data[i].productimglarg);
						$("#imgsub1").append(strhtml1);
						break;
					case 1:
						var strhtml2 = str2;
						strhtml2 = strhtml2.replace('@href','detailProduct.html?id='+data[i].productId);
						strhtml2 = strhtml2.replace('@src',path_imgproduct + "/" + data[i].productImage);
						$("#imgsub2").append(strhtml2);
						break;
					case 2:
						var strhtml3 = str2;
						strhtml3 = strhtml3.replace('@href','detailProduct.html?id='+data[i].productId);
						strhtml3 = strhtml3.replace('@src',path_imgproduct + "/" + data[i].productImage);
						$("#imgsub3").append(strhtml3);
						break;
					
					default:
						break;
				}
   	   }
	   }
	}); //end document
	function get_count_product_like(){	
		
		var strurl = ReturnHosing_tomcat_dvmall()+'Service/LikeProductService/GetNumProduct?callback=&';
		
		$.ajax({
			 url: strurl,
			 type: 'GET',
			 dataType: 'jsonp',
			 jsonp: 'callback',
			 crossDomain: true,			 
			 jsonpCallback: 'myCallbackLikeProduct',			  
			 error: function (XMLHttpRequest, textStatus, errorThrown) {
			      alert(errorThrown);
			 }
		});
	}
	function myCallbackLikeProduct(data){
		
		check_timer_like = false;
		if(data.result!=null){
			$("#count_product_love").text(data.result);	
		}
	}	
	
	
	function get_count_product_shopping_cart(){
		
		var strurl = ReturnHosing_tomcat_shoppingcart()+'Service/ShoppingCartService/GetNumShoppingCart?callback=&';
		
		$.ajax({
			 url: strurl,
			 type: 'GET',
			 dataType: 'jsonp',
			 jsonp: 'callback',
			 crossDomain: true,			 
			 jsonpCallback: 'myCallbackShoppingcart',			  
			 error: function (XMLHttpRequest, textStatus, errorThrown) {
			      alert(errorThrown);
			 }
		});
	}
	function myCallbackShoppingcart(data){		
		check_timer_cart = false;
		if(data.result!=null){
			$("#count_product_shopping_cart").text('('+data.result+')');	
		}
	}
	
    function get_service_info_login_server(){
    	
    	var strurl = ReturnHosing_service_account()+'Service/LogInService/GetServiceClient?callback=&';		
		$.ajax({
			 url: strurl,
			 type: 'GET',
			 dataType: 'jsonp',
			 jsonp: 'callback',
			 crossDomain: true,			 
			 jsonpCallback: 'myCallbackLogin',			  
			 error: function (XMLHttpRequest, textStatus, errorThrown) {
			      alert(errorThrown);
			 }
		});	
    }
    function set_service_info_login_server(){
    	
    	var strurl = ReturnHosing_apache_account()+'Service/LogInService/SetServiceClient?callback=&';		
		$.ajax({
			 url: strurl,
			 type: 'GET',
			 dataType: 'jsonp',
			 jsonp: 'callback',
			 crossDomain: true,			 
			 jsonpCallback: 'SetCallbackLogin',			  
			 error: function (XMLHttpRequest, textStatus, errorThrown) {
			      alert(errorThrown);
			 }
		});	
    }
    function myCallbackLogin(data){
		
		if(data!=null){
			var icon='<img  src="images/user.png">';
			var str = data.result;			
			if(str!='0' && str!='-1'){
				if(str.length>10){
					str=str.substring(0, 10);
					str+="...";
				}						
				$("#userlogin").html(icon+' xin chào: '+str);
				$(".infobelogin").css('display','none');
				$(".infoaflogin").css('display','block');
			}			
		}
	}
	function SetCallbackLogin(data){
		
		unblockbg();
		if(data!=null){
			if(data.result=='0'){
				showdialogfunctionok('dialogmanual','Đăng xuất thành công',function(){
					location.reload();
				});
			}
			else{
				showdialog('dialogmanual',0,'Đăng xuất không thành công','','');
			}
		}
		else{
			showdialog('dialogmanual',0,'Đăng xuất không thành công','','');
		}
	}
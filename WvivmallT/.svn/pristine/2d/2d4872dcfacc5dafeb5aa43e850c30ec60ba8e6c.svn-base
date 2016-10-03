	$(document).ready(function() {		
		set_bg_shoppingcart();		
		var htm_elem='<li class="menu"><a href="product_cate.html?id=@id" style="border-left: none; cursor: pointer">@name</a></li>';
		get_lcategory();				
		function set_bg_shoppingcart(){
			
			//$("#bgshoppingcart").css('background-color',ReturnHosing_apache()+'mobile/img/icon-shoppingcart.png');
			$("#bgshoppingcart").css('background-image','url("'+ReturnHosing_apache()+'mobile/img/icon-shoppingcart.png'+'")');
		}
		function get_lcategory(){
			 Return_get("ProductController", "get_list_category", "", "GET", function(data) {
				 if(data!=null){					  
					 for (var i = 0; i < data.length; i++) {
						  html = htm_elem;
						  html = html.replace('@id',data[i].producttype);
						  html = html.replace('@name',data[i].producttypename);
						  $("#idcategory_mobile").append(html);
		              }					 
					 
				 }
			 });
		}
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
		
		
		    $("#btnshow_sub_menu").click(function(){
		    	$("#sub_menu_info").toggle( "slow" );
		    });
		    $("#btnshow_key").click(function(){
		    	$("#key_find").toggle( "slow" );
		    });
	});
	
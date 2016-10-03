$(function(){
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
	
	
//	$('#search-form').keypress(function (e) {
//		 var key = e.which;
//		 if(key == 13)  // the enter key code
//		  {
//			 var key = $("#search-form").val();
//			 if(key==null || key=='' || key==undefined){
//				showdialog('dialogmanual',0,'Vui lòng nhập từ khóa tìm kiếm','','');
//				return;
//			}
//			else{
//				window.location.href='search_product.html?skey='+key;
//			}
//		    return false;  
//		  }
//		}); 
//	$("#btnsearch_mobile").click(function(){
//		
//		var key = $("#txt_search_mobile").val();		
//		if(key==null || key=='' || key==undefined){
//			showdialog('dialogmanual',0,'Vui lòng nhập từ khóa tìm kiếm','','');
//			return;
//		}
//		else{
//			window.location.href='search_product.html?skey='+key;
//		}
//	});
});
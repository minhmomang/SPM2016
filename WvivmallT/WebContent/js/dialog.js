var _mobile = ismobile();
function showdialog(div,type,mes,data_file,data_key ) {		
	if(_mobile==false){
		if(type==1){
			$opso_get(function(output,text){
				if(output==true){
					$("#content_message" ).text(text);
					$("#"+div ).dialog({
						 height: 250,
						 width: 500,
						 modal: true ,
						 autoOpen: true ,
						 title:'Thông báo',
						 resizable: false ,
						 buttons: {	   			
							"Đóng": function() {
							   $(this).dialog("close");
						   }
						}
					});											
				}
				else{
					$("#content_message" ).text('Get Message Error');
					$("#"+div ).dialog({
						 height: 250,
						 width: 500,
						 modal: true ,
						 autoOpen: true ,
						 title:'Thông báo',
						 resizable: false ,
						 buttons: {	   			
							"Đóng": function() {
							   $(this).dialog("close");
						   }
						}
					});
					
				}
			},data_file,data_key);
		}
		else{
			$("#content_message" ).text(mes);
			$("#"+div ).dialog({
				 height: 250,
				 width: 500,
				 modal: true ,
				 autoOpen: true ,
				 title:'Thông báo',
				 resizable: false ,
				 buttons: {	   			
					"Đóng": function() {
					   $(this).dialog("close");
				   }
				}
			});
		}
	}
	else{
		if(type==1){
			$opso_get(function(output,text){
				alert(text);
			},data_file,data_key);
		}
		else{
			alert(mes);
		}
	}	
}
function show_message_basic(div,mes){
	$("#content_message1" ).text(mes);
	$("#"+div ).dialog({
		 height: 250,
		 width: 500,
		 modal: true ,
		 autoOpen: true ,
		 title:'Thông báo',
		 resizable: false ,
		 buttons: {	   			
			"Đóng": function() {
			   $(this).dialog("close");
		   }
		}
	});
}
function showdialogexit(div, message) {	
	if(_mobile==false){
		$("#"+div).text(message);
		$("#"+div).dialog({
			resizable : false,
			autoOpen : true,
			height : 140,
			modal : true		
		});
	}
	else{
		alert(message);
	}
	
}
function showdialogfunctionok(div,mes,functinok)
{
	if(_mobile==false){
		$("#"+div).text(mes);			
		$("#"+div).dialog(
		{
			resizable : false,
			autoOpen : true,
			title:'Thông báo',
			height: 250,
			width: 500,
			modal : true,			   
			minHeight: 80,			    
			buttons: {
			  "Chấp nhận": functinok	      
			}
		});	
	}
	else{
		var r = confirm(mes);
		if(r==true){
			functinok();
		}
	}
	
}
function showdialogfunctionclose(div,mes)
{		
	if(_mobile==false){
		$(div).text(mes);			
		$(div).dialog(
		{
			resizable : false,
			autoOpen : true,
			height : 140,
			width:400,
			modal : true,			   
			minHeight: 100,			    
			buttons: {
			  "Đóng": function(){
				  $(div).dialog('close');
			  }	      
			}
		});	
	}
	else{
		alert(mes);
	}
	
}
function showdialogconfirm(mes){
	$("#contentdialogconfirmmanual").text(mes);
	$("#dialogconfirmmanual").window('open');
}
function Showdialogshoppingcart(page)
{		
	var dag = $('<div></div>')
    .html('<iframe  style="border: 0px; " src="' + page + '" width="100%" height="100%"></iframe>')				   
    .dialog({
         autoOpen: false,
         modal: true,
         height: 450,
         width: 800,
         title: 'Giỏ hàng của bạn',	                   
         buttons: [  
                   {                       
                       text: "Thanh toán",
                       width:100,
                       click: function(){
                    	   //check shopping cart
                    	   Return_get("ShoppingCartController","get_shopping_cart",'',"GET",function(data){
	                   			if(data.length==0){
	                   				showdialog("dialogmanual",2,"Giỏ hàng rỗng không thể thanh toán","dialog","r6" ) ;
	                   			}
	                   			else{
	                   				window.location.href='shoppingcart.html';
	                   			}
	                   		});
                       }
                   },
                   {                       
                       text: "Đóng",
                       width:60,
                       click: function () {
                           $(this).dialog('close');
                       }
                   }
             ]
     });
	dag.dialog('open');
}
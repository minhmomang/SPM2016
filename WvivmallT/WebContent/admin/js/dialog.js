function showdialog(div,type,mes,data_file,data_key ) {		
	
	if(type==1){
		$opso_get(function(output,text){
			if(output==true){
				$("#"+div ).text(mes);
				$("#"+div ).dialog({
					 position: { my: 'top', at: 'top+150' },
			   	     height: 250,
			   	     width: 500,
			   	     modal: true ,
			   	     autoOpen: true ,
			   	     title:'Thông báo',
			   	     resizable: false ,
			   	     buttons: {	   			
			   			"Close": function() {
		                   $(this).dialog("close");
		               }
			   	 	}
		 	    });											
			}
			else{
				$("#"+div ).text(mes);
				$("#"+div ).dialog({
					position: { my: 'top', at: 'top+150' },
			   	     height: 250,
			   	     width: 500,
			   	     modal: true ,
			   	     autoOpen: true ,
			   	     title:'Thông báo',
			   	     resizable: false ,
			   	     buttons: {	   			
			   			"Close": function() {
		                   $(this).dialog("close");
		               }
			   	 	}
		 	    });
				
			}
		},data_file,data_key);
	}
	else{
		$("#"+div ).text(mes);
		$("#"+div ).dialog({			
	   	     height: 250,
	   	     width: 500,
	   	     modal: true ,
	   	     closed: false ,
	   	     title:'Thông báo',
	   	     resizable: false ,
		   	  buttons: [{
	              text:'OK',
	              iconCls:'icon-ok',
	              handler:function(){
	            	  $("#"+div).dialog('close');
	              }	          
	          }]
 	    });
	}
}
function showdialogexit(div, message) {	
	$("#"+div ).text(mes);
	$("#"+div).dialog({
		resizable : false,
		autoOpen : true,
		height : 140,
		modal : true		
	});
}
function showdialogfunctionok(div,mes,functinok)
{

	$("#"+div ).text(mes);
	$("#"+div ).dialog({
   	     height: 250,
   	     width: 500,
   	     modal: true ,
   	     closed: false ,
   	     title:'Thông báo',
   	     resizable: false ,
	   	  buttons: [{
              text:'OK',
              iconCls:'icon-ok',
              handler:functinok       
          }]
	    });
}
function showdialogfunctionclose(div,mes)
{		
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
	      "Exit": function(){
	    	  $(div).dialog('close');
	      }	      
	    }
    });	
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
                          
                       }
                   },
                   {                       
                       text: "Thoát",
                       width:60,
                       click: function () {
                           $(this).dialog('close');
                       }
                   }
             ]
     });
	dag.dialog('open');
}
function showdialogconfirmfunc(div,title,fn){
		$("#"+div).text(title);
		$("#"+div ).dialog({
	   	     height: 250,
	   	     width: 500,
	   	     modal: true ,
	   	     closed: false ,
	   	     title:'Thông báo',
	   	     resizable: false ,
		   	  buttons: [{
	              text:'OK',
	              iconCls:'icon-ok',
	              handler:fn         
	          },{
	              text:'Cancel',
	              iconCls:'icon-cancel',
	              handler:function(){
	            	  $("#"+div).dialog('close');
	              }	          
	          }]
 	    });
}
function showdialogconfirmfunc2(div,title,fn,fn2){
		$("#"+div).text(title);
		$("#"+div ).dialog({
	   	     height: 250,
	   	     width: 500,
	   	     modal: true ,
	   	     closed: false ,
	   	     title:'Thông báo',
	   	     resizable: false ,
		   	  buttons: [{
	              text:'OK',
	              iconCls:'icon-ok',
	              handler:fn         
	          },{
	              text:'Cancel',
	              iconCls:'icon-cancel',
	              handler:fn2      
	          }]
 	    });
}
function showdialogfun(div,fn){	
		$("#"+div ).dialog({
	   	     height: 250,
	   	     width: 500,
	   	     modal: true ,
	   	     closed: false ,
	   	     title:'Thông báo',
	   	     resizable: false ,
		   	  buttons: [{
	              text:'OK',
	              iconCls:'icon-ok',
	              handler:fn         
	          },{
	              text:'Cancel',
	              iconCls:'icon-cancel',
	              handler:function(){
	            	  $("#"+div).dialog('close');
	              }	          
	          }]
 	    });
}
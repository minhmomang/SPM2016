var order_id_log = '';
$(function() {
	var list_order=[];
	var source = null;
     var lang=get_lang_current();
	 exe_load_header(function(output) {
	   if(output==true){	      
		   load_date_Box();
		   clear_value();
		   load_form_grid();	   
		   exec_load_data();		  
	   }
       
	 });
	 function clear_value(){
		$("#seldate").datebox("setValue","");
		$("#txtsearch").val('');
	 };
	 function load_date_Box(){
			$('#seldate').datebox({
			    required:true,
			    formatter:myformatter,
			    parser:myparser
			});
		}
	 
	 function exec_load_data(){
		blockbg();		
		load_data(function(output){
			if(output==true){
				unblockbg();
				source.localdata=list_order;        	
                $("#idorder").jqxGrid('updatebounddata');
         	    $('#idorder').jqxGrid('clearselection');
				
			}
		},'');
	}
	
	function load_data(callback,pdata) {		
		list_order= [];		 
		Return_get("OrderController", "get_list_order", pdata, 'GET', function(data) {		
			if (data != null) {
				list_order = data;
				callback(true);
			}else
            {
                	unblockbg();
            }
		});		       
	}
    
    
     
     function load_form_grid() {
        source = {
            localdata: list_order,
            datatype: "array",
            datafields: [{
                name: 'orderId',
                type: 'string'
            }, {
                name: 'orderDate',
                type: "date", 
                format: 'dd/MM/yyyy' 
            }, {
                name: 'customername',
                type: 'string'
            }, {
                name: 'address_delivery',
                type: 'string'
            },{
                name: 'payment_method',
                type: 'string'
            }, {
                name: 'invoice',
                type: 'string'
            }, {
                name: 'orderStatus',
                type: 'string'
            }, {
                name: 'orderStatusNL',
                type: 'string'
            }, {
                name: 'payment_typeNL',
                type: 'string'
            }, {
                name: 'refund_typeNL',
                type: 'string'
            }]

        };
        ///end source
        var dataAdapter = new $.jqx.dataAdapter(source);
        $("#idorder").jqxGrid({
            width: get_width(),
            source: dataAdapter,
            columnsresize: true,
            pageable: true,
            height: get_height(),            
            selectionmode: 'checkbox',
            columns: [ {
                text: 'Mã đơn hàng',
                datafield: 'orderId',
                width: 160				
            },{
                text: 'Ngày đặt hàng',
                datafield: 'orderDate',
                width: 150,
                cellsformat: 'dd/MM/yyyy'
            },{
                text: 'Tên khách hàng',
                datafield: 'customername',
                width: 250
            }, {
                text: 'Địa chỉ',
                datafield: 'address_delivery',
                width: 200				
            }, {
                text: 'Phương thức thanh toán',
                datafield: 'payment_method',
                width: 200				
            },{
                text: 'Hóa đơn',
                datafield: 'invoice',
                width: 150
            }, {
                text: 'Trạng thái đơn hàng',
                datafield: 'orderStatus',
                width: 200
            }, {
                text: 'Tình trạng thanh toán',
                datafield: 'orderStatusNL',
                width: 200
            }, {
                text: 'Hình thức thanh toán',
                datafield: 'payment_typeNL',
                width: 200
            }, {
                text: 'Phương thức hoàn trả',
                datafield: 'refund_typeNL',
                width: 200
            }
            ]
        });  
        
    } //end 
	
	
     function get_order() {
		var getselectedrowindexes = $("#idorder").jqxGrid('getselectedrowindexes');
		var orderId = '';
		if (getselectedrowindexes.length > 0) {
			var selectedRowData = $("#idorder").jqxGrid('getrowdata',
					getselectedrowindexes[0]);
			orderId = selectedRowData["orderId"];
		}
		return orderId;
	}
     
    $("#btnsearch").click(function(){
    	var id = $("#txtsearch").val();
    	var date = $("#seldate").datebox("getValue");
    	var pdata = {
    			'order_id':id,
    			'date':date
    	}
    	Return_get("OrderController","search_order",pdata,"GET",function(data){
    		if(data!=null){
    			list_order.splice(0,list_order.length);
    			list_order=data;
    			source.localdata=list_order;        	
                $("#idorder").jqxGrid('updatebounddata');
         	    $('#idorder').jqxGrid('clearselection');
    		}
    	});
    }); 
    $("#btndelivered").click(function(){
    	var id = get_order();
    	var type="4";
    	if (id == null || id == '') {		  
            showdialog("dialogmanual",0,"Xin chọn đơn hàng","","");			
			return;
		}
    	var pdata={'orderid':id,
    				'type':type};
    	Return_get("OrderController","process_order",pdata,"GET",function(data){
    		if(data!=null){
    			var result = parseInt(data);
				if(result==1){
					 showdialog("dialogmanual",0,"Đơn hàng đã xong, không thể xử lý","","");
					
					return;
				}
				else if(result==2){
					showdialog("dialogmanual",0,"Đơn hàng không tồn tại","","");					
					return;
				}					
				else if(result==3){
					showdialog("dialogmanual",0,"Đơn hàng đang được xử lý, không thể hủy","","");					
					return;
				}		
				else{					
					load_data(function(output){
						if(output==true){
							unblockbg();
							source.localdata=list_order;        	
			                $("#idorder").jqxGrid('updatebounddata');
			         	    $('#idorder').jqxGrid('clearselection');
			         	   showdialog("dialogmanual",0,"Xử lý đơn hàng thành công","","");		
						}
					},'');				
					
				}	
    		}else{
				showdialog("dialogmanual",0,"Xử lý đơn hàng thất bại","","");
				return;
			}
    	});
    });
     
    $("#btnorderprocess").click(function(){
    	var id = get_order();
		if (id == null || id == '') {		  
            showdialog("dialogmanual",0,"Xin chọn đơn hàng","","");			
			return;
		}
		viewpage(id);
    });
    function viewpage(id){
		
		PopupCenter(ReturnHosing()+'admin/Order/OrderDetail.html?id='+id,'xtf','1000','800'); 
	}
    $("#btncancel").click(function(){
    	
    	var id = get_order();
		if (id == null || id == '') {		  
            showdialog("dialogmanual",0,"Xin chọn đơn hàng","","");			
			return;
		}
		var type ="2";
		var pdata = {
			'orderid':	id,
			'type':type
		};
		Return_get("OrderController", "process_order", pdata, 'GET', function(data) {		
			if(data!=null){
				var result = parseInt(data);
				if(result==1){
					 showdialog("dialogmanual",0,"Đơn hàng đã xong, không thể xử lý","","");
					
					return;
				}
				else if(result==2){
					showdialog("dialogmanual",0,"Đơn hàng không tồn tại","","");					
					return;
				}					
				else if(result==3){
					showdialog("dialogmanual",0,"Đơn hàng đang được xử lý, không thể hủy","","");					
					return;
				}		
				else{					
					load_data(function(output){
						if(output==true){
							unblockbg();
							source.localdata=list_order;        	
			                $("#idorder").jqxGrid('updatebounddata');
			         	    $('#idorder').jqxGrid('clearselection');
			         	   showdialog("dialogmanual",0,"Xử lý đơn hàng thành công","","");		
						}
					},'');				
					
				}						
			}
			else{
				showdialog("dialogmanual",0,"Xử lý đơn hàng thất bại","","");
				return;
			}
		});
    });
    $("#btnfinish").click(function(){
    	
    	var id = get_order();
		if (id == null || id == '') {		  
            showdialog("dialogmanual",0,"Xin chọn đơn hàng","","");			
			return;
		}		
		var pdata = {
			'order':	id			
		};
		blockbg();
		Return_get("OrderController", "finish_order", pdata, 'GET', function(data) {		
			if(data!=null){
				unblockbg();
				var result = parseInt(data);
				if(result==-2){
					 showdialog("dialogmanual",0,"Đơn hàng không tồn tại","","");
					
					return;
				}
				else if(result==-2){
					showdialog("dialogmanual",0,"Xử lý không thành công","","");					
					return;
				}				
				else{					
					load_data(function(output){
						if(output==true){
							unblockbg();
							source.localdata=list_order;        	
			                $("#idorder").jqxGrid('updatebounddata');
			         	    $('#idorder').jqxGrid('clearselection');
			         	   showdialog("dialogmanual",0,"Xử lý đơn hàng thành công","","");		
						}
					},'');				
					
				}						
			}
			else{
				showdialog("dialogmanual",0,"Xử lý đơn hàng thất bại","","");
				return;
			}
		});
    });
    $("#btnprint").click(function(){
    	var id = get_order();
		if (id == null || id == '') {		  
            showdialog("dialogmanual",0,"Xin chọn đơn hàng","","");			
			return;
		}
		PopupCenter(ReturnHosing_apache()+'admin/Order/PrintOrder.html?id='+id,'xtf','1000','800');
    });
    function check_status_order(callback,orderid){
		
		var pdata = {'order_id':orderid};
		Return_get("OrderController","check_status_order_send_logistic",pdata,"GET",function(data){							 	 	
			 if(data!=null){				 
				 callback(true,data.result,data.message);
			 }
			 else{
				 callback(false,"","");
			 }			 
		});
	}
    
    function getorderbyid(callback,orderid){
		
    	var pdata = {'order_id':orderid};
    	Return_get("OrderController","conver_order_tojson",pdata,"GET",function(data){					 	 	
			 	if(data==null ){
			 		callback(false,"");
			 	}
			 	else{
			 		var strdata = JSON.stringify(data);
			 		callback(true,strdata);
			 	}
			 });
	}
	function getorderdetailbyid(callback,orderid){
		
		var pdata = {'order_id':orderid};
		Return_get("OrderController","conver_orderdtl_tojson",pdata,"GET",function(data){							 	 	
			 	if(data==null ){
			 		callback(false,"");
			 	}
			 	else{
			 		var strdata = JSON.stringify(data);
			 		callback(true,strdata);
			 	}
			 }
		);
	}
	
	$("#btnsend").click(function(){
		
		//
		var orderid = get_order();
		if(orderid==null || orderid==""){
			 showdialog("dialogmanual",0,"Xin chọn đơn hàng","","");			
				return;
		}
		order_id_log = orderid;
		//
		check_status_order(function(out,result,message){
			if(out==true){
				if(result!='1'){
					showdialog("dialogmanual",0,message,"","");
					return;
				}
				else{
					send_to_logistic(orderid);
				}
			}
			else{
				showdialog("dialogmanual",0,"Lỗi: Kiểm tra Trạng thái đơn hàng trước khi gửi xử lý","","");	
			}
		},orderid);		
	});
	function send_to_logistic(orderid){
		var strurl = ReturnHosing_service_w3pl()+'Srv3pl/SrvPO/ExecPO?callback=&';
		blockbg();
		getorderbyid(function(output,strjson){
			if(output==false){
				unblockbg();
				showdialog("dialogmanual",0,"Thông tin đơn hàng bị lỗi","","" );
			}	
			else{
				getorderdetailbyid(function(output2,strjsondtl2){
					if(output2==false){
						unblockbg();
						showdialog("dialogmanual",0,"Chi tiết đơn hàng bị lỗi","","" );
					}
					else{								
						var param = "po="+strjson;
						param +="&podtl="+strjsondtl2;
						strurl+=param;		
						
						$.ajax({
							 url: strurl,
							 type: 'GET',
							 dataType: 'jsonp',
							 jsonp: 'callback',
							 crossDomain: true,			 
							 jsonpCallback: 'myCallback',			  
							 error: function (XMLHttpRequest, textStatus, errorThrown) {
							      alert(errorThrown);
							 }
						});	
					}
				},orderid);						
			}		
		},orderid);	
	}

});
function update_status_order(callback,orderid){
	
	var pdata = {'order_id':orderid};
	Return_get("OrderController","update_status_order_after_send_logistic",pdata,"GET",function(data){							 	 	
		 if(data!=null){				 
			 callback(true,data.result,data.message);
		 }
		 else{
			 callback(false,"","");
		 }			 
	});
}
var myCallback = function(data) {
    unblockbg();
	var strdata = JSON.stringify(data);								      								       
    var pardata = JSON.parse(strdata);	        
    if(pardata!=null){	        	
    	if(pardata.name=="0")
    	{
    		
    		update_status_order(function(out,result,message){
    			if(out==true){
    				if(result=='0'){
    					$("#dialogmanual" ).text('Send to logistic success')
    					$("#dialogmanual" ).dialog({
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
    				            	  location.reload();
    				              }         
    				          }]
    				    });
    					return;
    				}
    			}
    			else{
    				showdialog("dialogmanual",0,"Lỗi: Cập nhật trạng thái order sau khi gửi xử lý ","","");	
    			}
    		},order_id_log);
    	}
    	else{             
    		showdialog("dialogmanual",0,"Gửi đơn hàng thành công","","" );
    	}
    } 
    else{
    	showdialog("dialogmanual",0,"Gửi đơn hàng thất bại","","" );
    }
};	

 var totalrecord2 = 0;
 var currentpage2 = 0;
 var recordperpage2 = 8;
 var check_search2= false;
 
 var totalrecord = 0;
 var currentpage = 0;
 var recordperpage = 8;
 var check_search = false;
 var arr_promo_type = [];
 var arr_promo_sub_type =[];
$(function() {
	var list_productpromotion=[];
	var source = null;
	var source2=null;
	var list_product=[];
    var lang=get_lang_current();
	 exe_load_header(function(output) {
	   if(output==true){	      
		  
		   load_form_grid2();	   
		   load_promotion_type(function(out){
			   if(out==true){
				   exec_load_data2();
			   }
		   });	
		   load_form_grid();	 	   
	   }
       
  });
	 
	 
  function load_promotion_type(callback) {
        Return_get("CategoryPromotionController", "get_promotion_parent", '', 'GET', function(data) {
            if (data != null) {
            	arr_promo_type = data;
                for (var i = 0; i < data.length; i++) {
                    if (data[i].ID != "00") {
                        var str = "<option  value=" + data[i].category_promotion_id + ">" + data[i].name + "</option>";
                        $("#txtcatepromoid").append(str);
                    }
                }
                callback(true);
            }
        });

    }
  function setheight(){
	 
	  for (var m = 0; m < list_productpromotion.length; m++) {
		  $("#idproductpromotion").jqxGrid('setrowheight', m, 60);		 
	  }
  }
  function setheight_product(){
      
	  for (var m = 0; m < list_product.length; m++) {
		  $("#idproduct").jqxGrid('setrowheight', m, 60);		 
	  }
  }
	function exec_load_data2(){
		blockbg();	
		load_change_Data();
	}
	
	function load_data_promotion(callback,pdata) {		
		list_productpromotion= [];		 
		Return_get("CategoryPromotionController", "get_list_productpromotion", pdata, 'GET', function(data) {		
			if (data != null) {
				list_productpromotion = data;
				callback(true);
			}else
            {
                	unblockbg();
            }
		});		       
	}
    
    
     
     function load_form_grid2() {
        source = {
            localdata: list_productpromotion,
            datatype: "array",
            datafields: [{
                name: 'productId',
                type: 'string'
            }, {
                name: 'productName',
                type: 'string'
            }, {
                name: 'productImage',
                type: 'string'
            }, {
                name: 'productPrice',
                type: 'string'
            }, {
                name: 'productquantity',
                type: 'string'
            },{
                name: 'producttype',
                type: 'string'
            },{
                name: 'price_discount',
                type: 'string'
            },{
                name: 'percent_discount',
                type: 'string'
            },{
                name: 'price_promo',
                type: 'string'
            }]

        };
        ///end source
        var dataAdapter = new $.jqx.dataAdapter(source);
        $("#idproductpromotion").jqxGrid({
            width: get_width(),
            source: dataAdapter,
            columnsresize: true,
            pageable: true,
            height: get_height(),            
            selectionmode: 'checkbox',
            showfilterrow: true,
            filterable: true,
            pagerrenderer: pagerrenderer,
            columns: [ {
                text: 'Hình',
                datafield: 'productImage',
                width: 150,
                cellsalign: 'center',
                align: 'center',
				cellsrenderer: imagerenderer 
            },{
                text: 'Mã SP',
                datafield: 'productId',
                cellsalign: 'center',
                align: 'center',
                width: 150
            },{
                text: 'Tên SP',
                datafield: 'productName',
                cellsalign: 'left',
                align: 'center',
                width: 250
            }, {
                text: 'Giá gốc',
                datafield: 'productPrice',
                cellsalign: 'center',
                align: 'center',
                width: 157,
				cellsrenderer: formatcellnum
            },{
                text: 'Trạng thái',
                datafield: 'producttype',
                cellsalign: 'center',
                align: 'center',
                width: 150
            }, 
            {
                text: 'Giảm PT',
                datafield: 'percent_discount',
                cellsalign: 'center',
                align: 'center',
                width: 100
            },{
                text: 'Giảm tiền',
                datafield: 'price_discount',
                cellsalign: 'center',
                align: 'center',
                width: 100,
                cellsrenderer: formatcellnum
            },
            {
                text: 'Giá mới',
                datafield: 'price_promo',
                cellsalign: 'center',
                align: 'center',
                width: 100,
                cellsrenderer: formatcellnum
            }
            ]
        });  
        
    } //end 
     var self = this;
     var pagerrenderer = function() {
         var element = $("<div style='margin-left: 10px; margin-top: 5px; width: 100%; height: 100%;'></div>");


         var leftButton = $("<div style='padding: 0px; float: left;'><div style='margin-left: 9px; width: 16px; height: 16px;'></div></div>");
         leftButton.find('div').addClass('jqx-icon-arrow-left');
         leftButton.width(36);
         leftButton.jqxButton({
             theme: theme
         });

         var rightButton = $("<div style='padding: 0px; margin: 0px 3px; float: left;'><div style='margin-left: 9px; width: 16px; height: 16px;'></div></div>");
         rightButton.find('div').addClass('jqx-icon-arrow-right');
         rightButton.width(36);
         rightButton.jqxButton({
             theme: theme
         });

         leftButton.appendTo(element);
         rightButton.appendTo(element);

         var label = $("<div style='font-size: 11px; margin: 2px 3px; font-weight: bold; float: left;'></div>");
         var pagenow = currentpage2 + 1;
         var torow = pagenow * recordperpage2;
         if (torow > totalrecord2) {
             torow = totalrecord2;
         }
         label.text((pagenow - 1) * recordperpage2 + "-" + torow + " of " + totalrecord2);
         label.appendTo(element);
         self.label = label;
         // update buttons states.
         var handleStates = function(event, button, className, add) {
             button.on(event, function() {
                 if (add == true) {
                     button.find('div').addClass(className);
                 } else button.find('div').removeClass(className);
             });
         };

         if (theme != '') {
             handleStates('mousedown', rightButton, 'jqx-icon-arrow-right-selected-' + theme, true);
             handleStates('mouseup', rightButton, 'jqx-icon-arrow-right-selected-' + theme, false);
             handleStates('mousedown', leftButton, 'jqx-icon-arrow-left-selected-' + theme, true);
             handleStates('mouseup', leftButton, 'jqx-icon-arrow-left-selected-' + theme, false);

             handleStates('mouseenter', rightButton, 'jqx-icon-arrow-right-hover-' + theme, true);
             handleStates('mouseleave', rightButton, 'jqx-icon-arrow-right-hover-' + theme, false);
             handleStates('mouseenter', leftButton, 'jqx-icon-arrow-left-hover-' + theme, true);
             handleStates('mouseleave', leftButton, 'jqx-icon-arrow-left-hover-' + theme, false);
         }

         rightButton.click(function() {
             $("#idproductpromotion").jqxGrid('gotonextpage');
             if (torow < totalrecord2) {
                 currentpage2 += 1;
                 go_to_page();
             }
         });

         leftButton.click(function() {
             $("#idproductpromotion").jqxGrid('gotoprevpage');
             if (currentpage2 > 0) {
                 currentpage2 -= 1;
                 go_to_page();
             }
         });

         return element;
    };
    function go_to_page() {
        blockbg();
        var current_record = currentpage2*recordperpage2;
		var pdata = {
			'f_row':current_record,
			'record':recordperpage2
		};
		if(check_search2==false){
			load_data_promotion(function(output){
				if(output==true){
					unblockbg();
					source.localdata=list_productpromotion;        	
	                $("#idproductpromotion").jqxGrid('updatebounddata');
	         	    $('#idproductpromotion').jqxGrid('clearselection');
					setheight();
				}
			},pdata);
		}
		else{
			var current_record = currentpage2*recordperpage2;
			
			var pdata1 = {
					'cate':$("#txtcatepromoid").val(),
					'f_row':current_record,
					'record':recordperpage2
				};
			load_data_promotion(function(output){
				if(output==true){
					unblockbg();
					source.localdata=list_productpromotion;        	
	                $("#idproductpromotion").jqxGrid('updatebounddata');
	         	    $('#idproductpromotion').jqxGrid('clearselection');
					setheight();
				}
			},pdata1);
		}
		
    }

	
   
   $("#btncreate").click(function() {    	
	   var p_category_promotion_id=$("#txtcatepromoid").val();
	   if (p_category_promotion_id == null || p_category_promotion_id == '') {
			showdialog('dialogmanual',0,'Vui lòng chọn danh mục giảm giá','','');
		   return;
		}
	   $("#iddialogproduct").css('display','block');		
	   $('#iddialogproduct').dialog({
           autoOpen: false,
           title:'List Product',
           width: 920,
           height:650,
          
       });      
	   $('#iddialogproduct').dialog('open');
	   load_product_type(function(out){			  
		   if(out==true){				   
			   loadsource2_when_loadcate();
		   }
	   });
   });  	  
	$("#btnremove").click(function() {
   	    var c = get_munti_selected();
		if (c == null || c == '') {
			showdialog('dialogmanual',0,'Chọn SP','','');
		   return;
		}
		showdialogconfirmfunc('dialogmanual','Bạn có muốn xóa không?',function(){			
	       if (c != null) {
	           $("#dialogconfirmmanual").window('close');
	           blockbg();
	           var p_category_promotion_id=$("#txtcatepromoid").val();
	           var pdata = {'str_product' : c,'p_category_promotion_id':p_category_promotion_id};
	           Return_get("CategoryPromotionController","delete_product_promotion",pdata,"GET",function(data) {
								if (data != null) {	
									unblockbg();
									var error = parseInt(data.result);	
									if (error == 0) {
										load_change_Data();
										showdialog('dialogmanual',0,'Xóa thành công','','');
									} else {									   
										showdialog('dialogmanual',0,'Xóa không thành công','','');
													
									}
								}
								else{
									
									showdialog('dialogmanual',0,'Xóa không thành công','','');
								}
	                       });
						}
		});
	});
               
	function get_munti_selected(){
        var c = '';
		var rows = $("#idproductpromotion").jqxGrid('selectedrowindexes');
        if(rows.length>0){
    		for ( var m = 0; m < rows.length; m++) {
    			var row = $("#idproductpromotion").jqxGrid('getrowdata', rows[m]);
    			c += "'" + row["productId"] + "'" + ",";
    		}
		c = c.substring(0, c.length - 1);
        }else{
        	showdialog('dialogmanual',0,'Chọn sản phẩm','','');
            return;
        }
		return c;
	}  
	
	$("#txtcatepromoid").change(function(){ 
		blockbg();		
		load_change_Data();
	});
	function get_info_promotion(){
		var id = $("#txtcatepromoid").val();
		for (var i = 0; i < arr_promo_type.length; i++) {
            if (arr_promo_type[i].category_promotion_id==id) {
            	var type = arr_promo_type[i].type;
            	var value = arr_promo_type[i].value;   		
        		var fromdate = arr_promo_type[i].ngay_ap_dung;   
        		var todate =  arr_promo_type[i].ngay_ket_thuc;   
        		var str = '';
        		if(type=='001'){
        			str+='Giảm: '+formatcurrency(value)+' VND. Từ ngày: '+fromdate+' Đến ngày: '+todate;
        		}
        		else{
        			str+='Giảm: '+value+' %. Từ ngày: '+fromdate+' Đến ngày: '+todate;
        		}        		
        		$("#info_promotion").text(str);
        		break;
            }
        }		
		
	}
	function get_info_sub_promotion(){		
		var id = $("#txtcatepromoidsub").val();
		for (var i = 0; i < arr_promo_sub_type.length; i++) {
            if (arr_promo_sub_type[i].category_promotion_id==id) {
            	var type = arr_promo_sub_type[i].type;
            	var value = arr_promo_sub_type[i].value;   		
        		var fromdate = arr_promo_sub_type[i].ngay_ap_dung;   
        		var todate =  arr_promo_sub_type[i].ngay_ket_thuc;   
        		var str = '';
        		if(type=='001'){
        			str+='Giảm: '+formatcurrency(value)+' VND. Từ ngày: '+fromdate+' Đến ngày: '+todate;
        		}
        		else{
        			str+='Giảm: '+value+' %. Từ ngày: '+fromdate+' Đến ngày: '+todate;
        		}        		
        		$("#info_promotionsub").text(str);
        		break;
            }
        }		
		
	}
	$("#txtcatepromoidsub").change(function(){ 
		blockbg();		
		load_change_sub_data();
	});
	function load_change_sub_data(){
		get_info_sub_promotion();
		var pdata = {
				'cate':$("#txtcatepromoidsub").val()
		};
			check_search2 = true;
			currentpage2 =0;
			get_page_search_promotion(function(out){
				if(out==true){
					var current_record = currentpage2*recordperpage2;
					
					var pdata1 = {
							'cate':$("#txtcatepromoidsub").val(),
							'f_row':current_record,
							'record':recordperpage2
						};
					load_data_cate(function(output){
						if(output==true){
							unblockbg();
							source.localdata=list_productpromotion;        	
			                $("#idproductpromotion").jqxGrid('updatebounddata');
			         	    $('#idproductpromotion').jqxGrid('clearselection');
							setheight();
						}
					},pdata1);
				}
			},pdata);
	}
	function get_sub_promotion(){
		$("#info_promotionsub").text('');
		var pdata = {
				'cate':$("#txtcatepromoid").val()
		};
		 Return_get("CategoryPromotionController", "get_promotion_sub",pdata, 'GET', function(data) {			 
	            if (data != null && data.length>0) {
	            	$("#subpromotion").css('display','block');
	            	$("#txtcatepromoidsub").text('');
	            	arr_promo_sub_type = data;
	            	var str = "<option  value='000'>---Chọn---</option>";
	            	$("#txtcatepromoidsub").append(str);
	                for (var i = 0; i < data.length; i++) {
	                	str = "<option  value=" + data[i].category_promotion_id + ">" + data[i].name + "</option>";
                        $("#txtcatepromoidsub").append(str);
	                }	                
	            }
	            else{
	            	$("#subpromotion").css('display','none');
	            }
	     });
	}
	function load_change_Data(){
		get_info_promotion();
		get_sub_promotion();
		var pdata = {
				'cate':$("#txtcatepromoid").val()
		};
			check_search2 = true;
			currentpage2 =0;
			get_page_search_promotion(function(out){
				if(out==true){
					var current_record = currentpage2*recordperpage2;
					
					var pdata1 = {
							'cate':$("#txtcatepromoid").val(),
							'f_row':current_record,
							'record':recordperpage2
						};
					load_data_cate(function(output){
						if(output==true){
							unblockbg();
							source.localdata=list_productpromotion;        	
			                $("#idproductpromotion").jqxGrid('updatebounddata');
			         	    $('#idproductpromotion').jqxGrid('clearselection');
							setheight();
						}
					},pdata1);
				}
			},pdata);
	}
	var imagerenderer = function (row, datafield, value) {
		   return '<img style="height:50px;width:60px" src="'+ReturnHosing()+'/upload/product/' + value + '"/>';
		};
		var formatcellnum = function (row, datafield, value) {
			   return formatcurrency(value);
			};	
	function load_data_cate(callback,pdata) {		
		list_productpromotion= [];		 
		Return_get("CategoryPromotionController", "get_list_productpromotion", pdata, 'GET', function(data) {		
			if (data != null) {
				list_productpromotion = data;
				callback(true);
			}else
            {
                	unblockbg();
            }
		});		       
	}
	function get_page_search_promotion(callback, pdata) {
        Return_get("CategoryPromotionController", "count_get_list_productpromotion", pdata, 'GET', function(data) {
            if (data != null) {
                totalrecord2 = data;
                callback(true);
            }
        });
    }

	
	/*-----------------------------------------------------------------------------------------------------*/
	
	   function load_form_grid() {
	        source2 = {
	            localdata: list_product,
	            datatype: "array",
	            datafields: [{
	                name: 'productId',
	                type: 'string'
	            }, {
	                name: 'productName',
	                type: 'string'
	            }, {
	                name: 'productImage',
	                type: 'string'
	            }, {
	                name: 'productPrice',
	                type: 'string'
	            }, {
	                name: 'productquantity',
	                type: 'string'
	            },{
	                name: 'producttype',
	                type: 'string'
	            }, {
	                name: 'orderproduct',
	                type: 'string'
	            }]

	        };
	        ///end source
	        var dataAdapter2 = new $.jqx.dataAdapter(source2);
	        $("#idproduct").jqxGrid({
	            width: 870,
	            height:480,
	            source: dataAdapter2,
	            columnsresize: true,
	            pageable: true,	                    
	            selectionmode: 'checkbox',
	            showfilterrow: true,
	            filterable: true,
	            pagerrenderer: pagerrenderer2,
	            columns: [ {
	                text: 'Hình',
	                datafield: 'productImage',
	                width: 150,
	                cellsalign: 'center',
	                align: 'center',
					cellsrenderer: imagerenderer 
	            },{
	                text: 'Mã SP',
	                datafield: 'productId',
	                cellsalign: 'center',
	                align: 'center',
	                width: 150
	            },{
	                text: 'Tên SP',
	                datafield: 'productName',
	                cellsalign: 'left',
	                align: 'center',
	                width: 250
	            }, {
	                text: 'Giá',
	                datafield: 'productPrice',
	                cellsalign: 'center',
	                align: 'center',
	                width: 157,
					cellsrenderer: formatcellnum
	            }, {
	                text: 'Số lượng',
	                datafield: 'productquantity',
	                cellsalign: 'center',
	                align: 'center',
	                width: 157,
					cellsrenderer: formatcellnum
	            },{
	                text: 'Trạng thái',
	                datafield: 'producttype',
	                cellsalign: 'center',
	                align: 'center',
	                width: 150
	            }, {
	                text: 'Số thứ tự',
	                datafield: 'orderproduct',
	                cellsalign: 'center',
	                align: 'center',
	                width: 100
	            }
	            ]
	        });  
	        $("#idproduct").closest("div.ui-jqgrid-view").children("div.ui-jqgrid-titlebar").children("span.ui-jqgrid-title").css("background-color", "black");

	        
	    } //end 
	     var self2 = this;
	     var pagerrenderer2 = function() {
	         var element = $("<div style='margin-left: 10px; margin-top: 5px; width: 100%; height: 100%;'></div>");


	         var leftButton = $("<div style='padding: 0px; float: left;'><div style='margin-left: 9px; width: 16px; height: 16px;'></div></div>");
	         leftButton.find('div').addClass('jqx-icon-arrow-left');
	         leftButton.width(36);
	         leftButton.jqxButton({
	             theme: theme
	         });

	         var rightButton = $("<div style='padding: 0px; margin: 0px 3px; float: left;'><div style='margin-left: 9px; width: 16px; height: 16px;'></div></div>");
	         rightButton.find('div').addClass('jqx-icon-arrow-right');
	         rightButton.width(36);
	         rightButton.jqxButton({
	             theme: theme
	         });

	         leftButton.appendTo(element);
	         rightButton.appendTo(element);

	         var label = $("<div style='font-size: 11px; margin: 2px 3px; font-weight: bold; float: left;'></div>");
	         var pagenow = currentpage + 1;
	         var torow = pagenow * recordperpage;
	         if (torow > totalrecord) {
	             torow = totalrecord;
	         }
	         label.text((pagenow - 1) * recordperpage + "-" + torow + " of " + totalrecord);
	         label.appendTo(element);
	         self2.label = label;
	         // update buttons states.
	         var handleStates = function(event, button, className, add) {
	             button.on(event, function() {
	                 if (add == true) {
	                     button.find('div').addClass(className);
	                 } else button.find('div').removeClass(className);
	             });
	         };

	         if (theme != '') {
	             handleStates('mousedown', rightButton, 'jqx-icon-arrow-right-selected-' + theme, true);
	             handleStates('mouseup', rightButton, 'jqx-icon-arrow-right-selected-' + theme, false);
	             handleStates('mousedown', leftButton, 'jqx-icon-arrow-left-selected-' + theme, true);
	             handleStates('mouseup', leftButton, 'jqx-icon-arrow-left-selected-' + theme, false);

	             handleStates('mouseenter', rightButton, 'jqx-icon-arrow-right-hover-' + theme, true);
	             handleStates('mouseleave', rightButton, 'jqx-icon-arrow-right-hover-' + theme, false);
	             handleStates('mouseenter', leftButton, 'jqx-icon-arrow-left-hover-' + theme, true);
	             handleStates('mouseleave', leftButton, 'jqx-icon-arrow-left-hover-' + theme, false);
	         }

	         rightButton.click(function() {
	             $("#idproduct").jqxGrid('gotonextpage');
	             if (torow < totalrecord) {
	                 currentpage += 1;
	                 go_to_page2();
	             }
	         });

	         leftButton.click(function() {
	             $("#idproduct").jqxGrid('gotoprevpage');
	             if (currentpage > 0) {
	                 currentpage -= 1;
	                 go_to_page2();
	             }
	         });

	         return element;
	    };
	    function go_to_page2() {
	        blockbg();
	        var current_record = currentpage*recordperpage;
			var pdata = {
				'f_row':current_record,
				'record':recordperpage
			};
			var current_record = currentpage*recordperpage;
			
			var pdata1 = {
					'cate':$("#txtProducttypeid").val(),
					'promotion':$("#txtcatepromoid").val(),
					'f_row':current_record,
					'record':recordperpage
				};
			load_data_cate2(function(output){
				if(output==true){
					
					source2.localdata=list_product;        	
	                $("#idproduct").jqxGrid('updatebounddata');
	         	    $('#idproduct').jqxGrid('clearselection');
	         	    setheight_product();
	         	    set_check_all_product();
	         	    unblockbg();
				}
			},pdata1);
			
	    }
	    
	    function get_page(callback, pdata) {
	        Return_get("ProductController", "count_get_list_product_manager", pdata, 'GET', function(data) {
	            if (data != null) {
	                totalrecord = data;
	                callback(true);
	            }
	        });
	    }
	    function get_munti_selected2(){
	        var c = [];
			var rows = $("#idproduct").jqxGrid('selectedrowindexes');
	        if(rows.length>0){
	    		for ( var m = 0; m < rows.length; m++) {
	    			var row = $("#idproduct").jqxGrid('getrowdata', rows[m]);
	    			c.push(row["productId"]);
	    		}
	        }else{
	        	showdialog('dialogmanual',0,'Vui lòng chọn sản phẩm','','');
	            return;
	        }
			return c;
		}  
	    
	    
	    $("#txtProducttypeid").change(function(){
	    	 
	    	 loadsource2_when_loadcate();
		});
	    function loadsource2_when_loadcate(){
	    	
	    	blockbg();	
			var pdata = {
				'cate':$("#txtProducttypeid").val(),
				'promotion':$("#txtcatepromoid").val()
			};
			check_search = true;
			currentpage =0;
			get_page_search2(function(out){				
				if(out==true){
					var current_record = currentpage*recordperpage;
					
					var pdata1 = {
							'cate':$("#txtProducttypeid").val(),
							'promotion':$("#txtcatepromoid").val(),
							'f_row':current_record,
							'record':recordperpage
					};
					load_data_cate2(function(output){
						
						if(output==true){
							
							source2.localdata=list_product;        	
							$("#idproduct").jqxGrid('updatebounddata');
		                	$('#idproduct').jqxGrid('clearselection');
		                	setheight_product();
		                	
		                	unblockbg();
						}
					},pdata1);
				}
			},pdata);
	    }
		function load_data_cate2(callback,pdata) {		
			list_product= [];		 
			Return_get("ProductController", "get_list_product_promotion", pdata, 'GET', function(data) {		
				if (data != null) {					
					list_product = data;
					callback(true);
				}else
	            {
	                	unblockbg();
	            }
			});		       
		}
		function get_page_search2(callback, pdata) {
	        Return_get("ProductController", "count_get_list_product_promotion", pdata, 'GET', function(data) {
	            if (data != null) {
	                totalrecord = data;
	                callback(true);
	            }
	        });
	    }
		
		  function load_product_type(callback) {
			    $("#txtProducttypeid").text('');
		        Return_get("ProductController", "get_list_category", '', 'GET', function(data) {
		            if (data != null) {
		                for (var i = 0; i < data.length; i++) {
		                    if (data[i].ID != "00") {
		                        var str = "<option value=" + data[i].producttype + ">" + data[i].producttypename + "</option>";
		                        $("#txtProducttypeid").append(str);
		                    }
		                }
		                callback(true);
		            }
		        });

		    }
		  
	
	
	
	function get_sing_selected(){
        var c = '';
		var rows = $("#idproductpromotion").jqxGrid('selectedrowindexes');
        if(rows.length>0){
    		var row = $("#idproductpromotion").jqxGrid('getrowdata', rows[0]);
    		c=row["productId"];
        }else{
        	showdialog('dialogmanual',0,'Select row','','');
            return;
        }
		return c;
	}  
	
	
	
	$("#btnedit").click(function(){
		var id = get_sing_selected();
		var pro_id= $("#txtcatepromoid").val();
		if (id == null || id == '') {		  
            showdialog("dialogmanual",0,"Please select product","","");			
			return;
		}
		var pdata={'id':id,
			'pro_id':pro_id	
		};
		Return_get("CategoryPromotionController","get_info_promo_product",pdata,"GET",function(data){
			if(data!=null && data!=''){
			var oldPrice = data.productPrice;
			$("#changeprice").css('display', 'block');
			$("#idoldprice").val(oldPrice);
	        $('#changeprice').dialog({
	            autoOpen: false,
	            title: 'Change product price & promotion',
	            width: 400,
	            height: 220,
	            buttons: [{
	                text: 'Save',
	                iconCls: 'icon-ok',
	                handler: function() {
	                	save_promo_change();
	                    /*exec_cate(function(out) {
	                        if (out) {
	                           exec_load_data();
	                            $(changeprice).dialog('close');
	                        }

	                    });*/
	                	
	                	unblockbg();
	                	
	                }
	            }, {
	                text: 'Cancel',
	                iconCls: 'icon-cancel',
	                handler: function() {
	                	clear_input();
	                    $("#changeprice").dialog('close');
	                }
	            }]
	        });
	        $(changeprice).dialog('open');
			}
		})
		
	})
	function save_promo_change(){
		blockbg();
		var id = get_sing_selected();
		var pro_id= $("#txtcatepromoid").val();
		var oldprice=$("#idoldprice").val();
		var choice = $("#slcpromo").val();
		var value=$("#idvalue").val();
		var price=0;
		var percent=0;
		
		if(oldprice==''||oldprice==null||value==''||value==null){
			showdialog('dialogmanual',0,'Please insert require value','','');
			unblockbg();
			return;
		}
		oldprice = parseFloat(oldprice);
		value = parseInt(value);
		if(choice=='1'){
			if(value>oldprice){
				showdialog('dialogmanual',0,'Gia moi khong nen cao hon gia cu !','','');
				return;
			}
			price=value;
			percent= Math.ceil(100 -(price/oldprice)*100);
			var pdata={
					'id':id,
					'pro_id':pro_id,
					'oldprice':oldprice,
					'percent':percent,
					'newprice':price
			}
			Return_get("CategoryPromotionController","save_promo_product",pdata,'GET',function(data){
				if(data!=null){
					
					if(data=='0'){
						showdialog('dialogmanual',0,'Successfull','','');
						load_change_Data();
					}
					else if(data =='-1'){
						showdialog('dialogmanual',0,'Failed','','');
						load_change_Data();
					}
				}
			})
		}else if(choice=='2'){
			if(value>100){
				showdialog('dialogmanual',0,'Phan tram giam gia > 100','','');
				return;
			}
			percent=Math.ceil(value);
			price=(oldprice/100) *(100-percent);
			var pdata={
					'id':id,
					'pro_id':pro_id,
					'oldprice':oldprice,
					'percent':percent,
					'newprice':price
			}
			Return_get("CategoryPromotionController","save_promo_product",pdata,'GET',function(data){
				if(data!=null){
					
					if(data==0){
						showdialog('dialogmanual',0,'Successfull','','');
						load_change_Data();
					}
					else if(data ==-1){
						showdialog('dialogmanual',0,'Failed','','');
						load_change_Data();
					}
				}
			})
		}
		clear_input();
		$("#changeprice").dialog('close');
	}
	function clear_input(){
		$("#idoldprice").val('');
		$("#idvalue").val('');
	}
	$("#btnsearch_promotion").click(function(){
		blockbg();	
		load_change_Data();		
	});
	$("#chkallproduct").click(function(){
		set_check_all_product();
	});
	function set_check_all_product(){		
		var ischeck = $("#chkallproduct").prop('checked');
		if(ischeck==true){			  
			  var i, count, $grid = $("#idproduct");
			  for (i = 0, count = list_product.length; i < count; i += 1) {
			      $grid.jqxGrid('selectrow', i);
			  }
		}
		else{
			 var i, count, $grid = $("#idproduct");
			 for (i = 0, count = list_product.length; i < count; i += 1) {
			      $grid.jqxGrid('unselectrow', i);
			 }
		}
	}
	$("#id_ok_append").click(function()
	{
		var ischeck = $("#chkallproduct").prop('checked');
		if(ischeck==true){
			
		}
		else{
			var p=get_munti_selected2();
			if(p.length==0){
				return;
			}
			var idcatpromotion=$("#txtcatepromoid").val();
			var listapproduct=[];		
			for (var i=0;i<p.length;i++){
				var item ={
						'product_id':p[i]
				}
				listapproduct.push(item);
			}
			
			var pdata={'strproductid':JSON.stringify(listapproduct),'idcatpromotion':idcatpromotion};
			Return_get("CategoryPromotionController", "insert_product_to_promotion", pdata, 'GET', function(data) {
				
				if (data != null) {
					$('#iddialogproduct').dialog('close');
				//	load_change_Data();
					if(data.result=="0"){					
						showdialog('dialogmanual',0,'Thêm sản phẩm vào chương trình khuyến mãi thành công','','');
					}
					else{
						showdialog('dialogmanual',0,'Thêm sản phẩm vào chương trình khuyến mãi không thành công','','');
					}
				}
			});		
		}	
		
		 
	})
});

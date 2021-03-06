$(function() {
    var type = 'A';
    var list_category = [];
    var source = null;
    var lang = get_lang_current();
    exe_load_header(function(output) {
        if (output == true) {
        	
            load_form_grid();
            exec_load_data();

        }

    });

    function exec_load_data() {
        blockbg();
        load_data(function(output) {
            if (output == true) {
                unblockbg();
                source.localdata = list_category;
                $("#idcategory").jqxGrid('updatebounddata');
                $('#idcategory').jqxGrid('clearselection');

            }
        }, '');
    }

    function load_data(callback, pdata) {
        list_category = [];
        Return_get("ProductController", "get_list_category_manager", pdata, 'GET', function(data) {
            if (data != null) {
                list_category = data;
                callback(true);
            } else {
                unblockbg();
            }
        });
    }
    function load_group_category(callback){
    	 $("#groupcategory").text('');
    	 Return_get("ProductController", "get_list_group_category",'', 'GET', function(data) {
             if (data != null) {
                 for(var i=0;i<data.length;i++){
                	 var str ='<option value="'+data[i].producttype+'">'+data[i].producttypename+'</option>';
                	 $("#groupcategory").append(str);
                	 if(i==0){
                		 load_group_sub_category(data[i].producttype,function(out){
                			 
                		 });
                	 }
                 }                 
             }
             callback(true);
         });
    }
    function load_group_category_update(callback){
   	 $("#groupcategory").text('');
   	 Return_get("ProductController", "get_list_group_category",'', 'GET', function(data) {
            if (data != null) {
                for(var i=0;i<data.length;i++){
               	 var str ='<option value="'+data[i].producttype+'">'+data[i].producttypename+'</option>';
               	 $("#groupcategory").append(str);
               	
                }                 
            }
            callback(true);
        });
   } 
    $("#groupcategory").change(function(){
    	var id = $(this).val();
    	blockbg();
    	load_group_sub_category(id,function(out){
    		unblockbg();	
    	});
    });
    function load_group_sub_category(id,callback){
    	var pdata = {
    			'parent':id
    	}
    	$("#groupcategorysub").text('');
   	 	Return_get("ProductController", "get_list_product_type_sub",pdata, 'GET', function(data) {
            if (data != null) {
                for(var i=0;i<data.length;i++){
	               	 var str ='<option value="'+data[i].producttype+'">'+data[i].producttypename+'</option>';
	               	 $("#groupcategorysub").append(str);
                }
                callback(true);
            }            
        });
    }
    


    function load_form_grid() {
        source = {
            localdata: list_category,
            datatype: "array",
            datafields: [{
                name: 'producttype',
                type: 'string'
            }, {
                name: 'producttypename',
                type: 'string'
            }, {
                name: 'group_category_id',
                type: 'string'
            }, {
                name: 'group_category_name',
                type: 'string'
            }, {
                name: 'category_id',
                type: 'string'
            }, {
                name: 'category_name',
                type: 'string'
            }, {
                name: 'category_img',
                type: 'string'
            },{
                name: 'isvisible',
                type: 'string'
            }]

        };
        ///end source
        var dataAdapter = new $.jqx.dataAdapter(source);
        $("#idcategory").jqxGrid({
            width: get_width(),
            source: dataAdapter,
            columnsresize: true,
            pageable: true,
            height: get_height(),
            selectionmode: 'checkbox',
            columns: [{
                text: 'Mã',
                datafield: 'producttype',
                cellsalign: 'center',
                align: 'center',
                width: 150
            }, {
                text: 'Tên',
                datafield: 'producttypename',
                cellsalign: 'left',
                align: 'center',
                width: 350
            }, {
                text: 'Nhóm danh mục',
                datafield: 'group_category_name',
                cellsalign: 'left',
                align: 'center',
                width: 350
            }, {
                text: 'Loại danh mục',
                datafield: 'category_name',
                cellsalign: 'left',
                align: 'center',
                width: 350
            },{
                text: 'Hình ảnh',
                datafield: 'category_img',
                cellsalign: 'left',
                align: 'center',
                width: 400
            },{
                text: 'Trạng thái',
                datafield: 'isvisible',
                cellsalign: 'center',
                align: 'center',
                width: 100
            }]
        });

    }

    function get_cate_product() {
        var getselectedrowindexes = $("#idcategory").jqxGrid('getselectedrowindexes');
        var product_id = '';
        if (getselectedrowindexes.length > 0) {
            var selectedRowData = $("#idcategory").jqxGrid('getrowdata',
                getselectedrowindexes[0]);
            product_id = selectedRowData["producttype"];
        }
        return product_id;
    }

    $("#btnrefresh").click(function(){
    	exec_load_data();
    })    
    function clear_input(){
    	$("#idtype").val("");
    	$("#idname").val("");
    	$("#idtype").css("display","inline-block");
    	$("#idname").css("display","inline-block");
    	$("#idtype").removeAttr( "disabled" );
    
    	document.getElementById("upload_immg").reset();
    	$("#strongmessage").text("");
    	
    }
    $("#btncreate").click(function() {
    	blockbg();
    	load_group_category(function(out){
    		if(out==true){
    				unblockbg();
    			 	type = 'A';
    		        set_control_upload();
    		        clear_input();
    		        
    		        $("#dlgcreate").css('display', 'block');
    		        $('#dlgcreate').dialog({
    		            autoOpen: false,
    		            title: 'Thêm danh mục',
    		            width: 400,
    		            height: 350,
    		            buttons: [{
    		                text: 'OK',
    		                iconCls: 'icon-ok',
    		                handler: function() {
    		                    exec_cate(function(out) {
    		                        if (out) {
    		                            exec_load_data();
    		                            $(dlgcreate).dialog('close');
    		                        }

    		                    });
    		                }
    		            }, {
    		                text: 'Cancel',
    		                iconCls: 'icon-cancel',
    		                handler: function() {
    		                    $("#dlgcreate").dialog('close');
    		                }
    		            }]
    		        });
    		        $(dlgcreate).dialog('open');	
    		}
    	})       
    });

    function exec_cate(callback) {
    	var groupcategory = $("#groupcategory").val();
    	var groupcategory_name = $("#groupcategory option:selected").text();    	
    	var groupcategorysub = $("#groupcategorysub").val();
    	var groupcategorysub_name = $("#groupcategorysub option:selected").text();
        var id = $("#idtype").val();
        var name = $("#idname").val();
        var category_img=$("#id_uploadsuccess").val();
        if(category_img==""||category_img==undefined){
        	category_img="null";
        }
        if (groupcategory == null || groupcategory == '') {
            showdialog('dialogmanual', 0, 'Vui lòng chọn nhóm danh mục', '', '');
            callback(false);
            return;
        }
        if (groupcategorysub == null || groupcategorysub == '') {
            showdialog('dialogmanual', 0, 'Vui lòng chọn danh mục', '', '');
            callback(false);
            return;
        }
        if (id == null || id == '') {
            showdialog('dialogmanual', 0, 'Nhập mã danh mục', '', '');
            callback(false);
            return;
        }

        if (name == null || name == '') {
            showdialog('dialogmanual', 0, 'Nhập tên danh mục', '', '');
            callback(false);
            return;
        }
        blockbg();
        var pdata = {
            'type': type,
            'id': id,
            'name': name,
            'groupcategory':groupcategory,
            'groupcategory_name':groupcategory_name,
            'groupcategorysub':groupcategorysub,
            'groupcategorysub_name':groupcategorysub_name,
            'category_img':category_img
        };
        Return_get("ProductController", "insert_category_product", pdata, "GET", function(data) {
            if (data != null) {
                unblockbg();
                var error = parseInt(data.result);

                if (error == 0) {
                    $("#idtype").val('');
                    $("#idname").val('');

                    showdialog('dialogmanual', 0, 'Nhập thành công', '', '');
                    callback(true);
                } else if (error == 1) {
                    showdialog('dialogmanual', 0, 'Mã danh mục đã tồn tại', '', '');
                    callback(true);
                } else {
                    showdialog('dialogmanual', 0,data.message, '', '');
                    callback(true);
                }
            } else {
                showdialog('dialogmanual', 0, 'Nhập thất bại', '', '');
                callback(true);
            }
        });

    }
    var idcurrent = '';

    function load_category_by_id(id, callback) {
        var pdata = {
            'id': id
        };
        Return_get("ProductController", "get_category_by_id", pdata, 'GET', function(data) {
            if (data != null) {
                $("#idtype").prop('disabled', true);
                $("#idtype").val(data.producttype);
                $("#idname").val(data.producttypename);
                $("#id_uploadsuccess").val(data.category_img);
                $("#strongmessage").text("Current img:"+data.category_img);
                $("#strongmessage").show();
                var groupcategory= data.group_category_id;
                var category = data.category_id;
                callback(true,groupcategory,category);
            }
        });
    }
    function set_control_upload(){
    	$("#txtimage").val('');
    	$("#id_uploadsuccess").val('');
    	$("#strongmessage").text('');
    	$("#strongmessage2").text('');
    }
    $("#btnupdate").click(function() {
    	//
    	blockbg();
    	set_control_upload();
        var id = get_cate_product();
       
        if (id == null || id == '') {
            showdialog('dialogmanual', 0, 'Xin chọn dòng', '', '');
            return;
        }
        type = 'E';
        idcurrent = id;
        load_category_by_id(id, function(out,groupcategory,category) {
        	// load 
        	load_group_category_update(function(out1){
        		$("#groupcategory").val(groupcategory);
        		load_group_sub_category(groupcategory,function(out){
        			if(out==true){
        				unblockbg();
        				$("#groupcategorysub").val(category);
        				$("#dlgcreate").css('display', 'block');
        	            $('#dlgcreate').dialog({
        	                autoOpen: false,
        	                title: 'Update Product Category',
        	                width: 400,
        	                height: 350,
        	                buttons: [{
        	                    text: 'OK',
        	                    iconCls: 'icon-ok',
        	                    handler: function() {
        	                        exec_update_cate(function(out) {
        	                            if (out) {
        	                                exec_load_data();
        	                                $("#idtype").prop('disabled', false);
        	                                $(dlgcreate).dialog('close');
        	                            }

        	                        });
        	                    }
        	                }, {
        	                    text: 'Cancel',
        	                    iconCls: 'icon-cancel',
        	                    handler: function() {
        	                        $("#dlgcreate").dialog('close');
        	                    }
        	                }]
        	            });
        	            $(dlgcreate).dialog('open');
        			}
        		});
        	});            
        });
    });

    function exec_update_cate(callback) {
    	var groupcategory = $("#groupcategory").val();
    	var groupcategory_name = $("#groupcategory option:selected").text();    	
    	var groupcategorysub = $("#groupcategorysub").val();
    	var groupcategorysub_name = $("#groupcategorysub option:selected").text();
        var id = $("#idtype").val();
        var name = $("#idname").val();
        var category_img=$("#id_uploadsuccess").val();
        if(category_img==""||category_img==undefined){
        	category_img="null";
        }
        if (groupcategory == null || groupcategory == '') {
            showdialog('dialogmanual', 0, 'Vui lòng chọn nhóm danh mục', '', '');
            callback(false);
            return;
        }
        if (groupcategorysub == null || groupcategorysub == '') {
            showdialog('dialogmanual', 0, 'Vui lòng chọn danh mục', '', '');
            callback(false);
            return;
        }
        if (id == null || id == '') {
            showdialog('dialogmanual', 0, 'Nhập mã danh mục', '', '');
            callback(false);
            return;
        }

        if (name == null || name == '') {
            showdialog('dialogmanual', 0, 'Nhập tên danh mục', '', '');
            callback(false);
            return;
        }
        blockbg();
        var pdata = {
            'type': type,
            'id': id,
            'name': name,
            'groupcategory':groupcategory,
            'groupcategory_name':groupcategory_name,
            'groupcategorysub':groupcategorysub,
            'groupcategorysub_name':groupcategorysub_name,
            'category_img':category_img
        };
        Return_get("ProductController", "insert_category_product", pdata, "GET", function(data) {
            if (data != null) {
                unblockbg();
                var error = parseInt(data.result);

                if (error == 0) {
                    $("#idtype").val('');
                    $("#idname").val('');

                    showdialog('dialogmanual', 0, 'Cập nhật thành công', '', '');
                    callback(true);
                } else if (error == 1) {
                    showdialog('dialogmanual', 0, 'Mã danh mục không tồn tại', '', '');
                    callback(true);
                }  else {
                    showdialog('dialogmanual', 0,data.message, '', '');
                    callback(true);
                }
            } else {
                showdialog('dialogmanual', 0, 'Cập nhật thất bại', '', '');
                callback(true);
            }
        });

    }
    $("#btnremove").click(function() {
    	
        var id = get_cate_product();
      
        if (id == null || id == '') {
            showdialog('dialogmanual', 0, 'Xin chọn dòng', '', '');
            return;
        }
        showdialogconfirmfunc('dialogmanual', 'Bạn có muốn xóa ?', function() {
            if (id != null) {

                blockbg();
                var pdata = {
                    'str': id
                };
                Return_get("ProductController", "delete_cate", pdata, "GET", function(data) {
                    if (data != null) {
                        unblockbg();
                        var error = parseInt(data.result);
                        if (error == 0) {
                            exec_load_data();
                            showdialog('dialogmanual', 0, 'Xóa thành công', '', '');
                        } else {
                            showdialog('dialogmanual', 0, 'Xóa thất bại', '', '');

                        }
                    } else {

                        showdialog('dialogmanual', 0, 'Xóa thất bại', '', '');
                    }
                });
            }
        });
    });
    $("#btnvisible").click(function() {
   	    var c = get_cate_product();
		if (c == null || c == '') {
			showdialog('dialogmanual',0,'Xin chọn dòng','','');
		   return;
		}
		showdialogconfirmfunc('dialogmanual','Bạn có muốn đăng lên ?',function(){			
	       if (c != null) {
	          
	           blockbg();
	           var pdata = {'str_catgory' : c};
	           Return_get("Catgory_controller","visible_catgory",pdata,"GET",function(data) {
								if (data != null) {	
									unblockbg();
									var error = parseInt(data.result);	
									if (error == 0) {
										exec_load_data();
										showdialog('dialogmanual',0,'Đăng lên thành công','','');
									} else {									   
										showdialog('dialogmanual',0,'Đăng lên thất bại','','');
													
									}
								}
								else{
									
									showdialog('dialogmanual',0,'Đăng lên thất bại','','');
								}
	                       });
						}
		});
	});
	$("#btnunvisible").click(function() {
   	    var c = get_cate_product();
		if (c == null || c == '') {
			showdialog('dialogmanual',0,'Xin chọn dòng','','');
		   return;
		}
		showdialogconfirmfunc('dialogmanual','Bạn có muốn gỡ xuống ?',function(){			
	       if (c != null) {
	          
	           blockbg();
	           var pdata = {'str_catgory' : c};
	           Return_get("Catgory_controller","unvisible_catgory",pdata,"GET",function(data) {
								if (data != null) {	
									unblockbg();
									var error = parseInt(data.result);	
									if (error == 0) {
										exec_load_data();
										showdialog('dialogmanual',0,'Gỡ xuống thành công','','');
									} else {									   
										showdialog('dialogmanual',0,'Gỡ xuống thất bại','','');
													
									}
								}
								else{
									
									showdialog('dialogmanual',0,'Gỡ xuống thất bại','','');
								}
	                       });
						}
		});
	});
    

    function get_munti_selected() {
        var c = '';
        var rows = $("#idcategory").jqxGrid('selectedrowindexes');
        if (rows.length > 0) {
            for (var m = 0; m < rows.length; m++) {
                var row = $("#idcategory").jqxGrid('getrowdata', rows[m]);
                c += "'" + row["producttype"] + "'" + ",";
            }
            c = c.substring(0, c.length - 1);
        }
        return c;
    }
    
  
    var url = ReturnHosing()+"ProductController/upload_image_category.htm";;
    $('input[name="txtimage"]').ajaxfileupload({
        'action': url,
        'onComplete': function(response) {
            
            var statusVal = JSON.stringify(response.status);

            if (statusVal == "false") {
                $("#strongmessage").html("<font color='red'>" + JSON.stringify(response.message) + " </font>");
            }
            if (statusVal == "true") {
                $("#strongmessage").html("<font color='green'>" + JSON.stringify(response.message) + " </font>");
            }
            var pram = JSON.stringify(response.pram);
            var first = pram.indexOf('"');
            var last = pram.lastIndexOf('"');
            pram = pram.substring(first + 1, last);
			
            $("#id_uploadsuccess").val(pram);

        },
        'onStart': function() {
        	var fileSize = this.get(0).files[0].size;
         	if(fileSize>1024*500){
         		showdialog('dialogmanual', 0, 'Hình ảnh này có kích cỡ lớn hơn cho phép. Xin chọn hình ảnh khác', '', '');
         		$('#strongmessage2').html("<font color='red'>" + "Kích cỡ cho phép : 500KB !" + " </font>");
         		$("#id_uploadsuccess").val('');
         		$('#strongmessage2').css('display','block');
         		$('#strongmessage').css('display','none');
         		return false;
         	}
         	else{
         		$('#strongmessage2').css('display','none');
         		$('#strongmessage').css('display','block');
         	}
        }
    });

});
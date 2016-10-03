var list_color = [];
var list_color_choose = [];
$(function() {
    var action = 'A';
    var list_product_type = [];
    var list_property = [];
    
    
    exe_load_header(function(output) {
        if (output == true) {
            CKEDITOR.replace('txtProductdes', {
                filebrowserImageUploadUrl: ReturnHosing() + "UploadImageController/upload_image.htm"
            });
            CKEDITOR.replace('editMoreinformation', {
                filebrowserImageUploadUrl: ReturnHosing() + "UploadImageController/upload_image.htm"
            });
            CKEDITOR.replace('editGuide', {
                filebrowserImageUploadUrl: ReturnHosing() + "UploadImageController/upload_image.htm"
            });
            set_load();
            default_load();
            var tcheck = setInterval(function(){ 
            	if(check_load()){
            		clearInterval(tcheck);
            		load_data_by_param();
            	}
            }, 1000);			
        }
    });
    var leng = 2;
    var arr_load = [];
    function set_load(){
    	for(var i=0;i<leng;i++){
    		arr_load[i]=false;
    	}
    }
    function default_load(){
    	 load_provider(0);
         load_product_type(1);
    }
    function check_load(){
    	for(var i=0;i<leng;i++){
    		if(arr_load[i]==false){
    			return false;
    		}
    	}
    	return true;
    }
	function load_data_by_param() {
        var product_id = getUrlParameter('product_id');
        if (product_id != null && product_id != '' && product_id != 'undefined') {
            var arr = product_id.split('_');
            action = arr[0];
            product_id = arr[1];

            if (action == "UPDATE") {
                blockbg();
                action = 'E';
                load_data(product_id);               


            } else if (action == "VIEW") {
                blockbg();
                $("#btn_submit").css('display', 'none');
                $("#btn_submit").attr('disabled', 'disabled');
                load_data(product_id);
            }
        } //end if
    } //end load param
	function load_data(productid){
		blockbg();
		
		var pdata = {
			'id':productid
		};
		Return_get("ProductController", "get_info_product_1", pdata, 'GET', function(data) {
            if (data != null) {
            	$("#txtProductid").prop('readonly',true);
                $("#txtProductid").val(data.productId);
				$("#txtProductname").val(data.productName);
				$("#txtProductprice").val(data.productPrice);
				$("#txtQuantity").val(data.productquantity);
				
				$("#txtProducttypeid").val(data.producTypeId);
				$("#txtProductproviderid").val(data.productProviderId);				
				CKEDITOR.instances.txtProductdes.setData(data.productDes);
				CKEDITOR.instances.editMoreinformation.setData(data.moreinfo);
				CKEDITOR.instances.editGuide.setData(data.productguide);
				$("#typeimglarg").val(data.typeimglarg);
				$("#iddisplayimg").css('display','inline');								 
				$("#imgcurrent").attr('src',ReturnHosing()+'upload/product/'+data.productImage);
				
				//unblockbg();
				var cate = get_category_id(data.producTypeId);		    	
		    	load_property(cate,function(out){
		    		//set property
		    		var property = data.property;
		    		if(property.length>0){
		    			var arr_prop = property.split('|');
		    			if(arr_prop.length>0){
		    				for(var i=0;i<arr_prop.length;i++){
		    					var item = arr_prop[i];
		    					var arr_item = item.split('&');
		    					if(arr_item.length>0){
		    						var id_prop = arr_item[0];
		    						var val_prop =arr_item[1];
		    						$("#property_"+id_prop).val(val_prop);
		    					}		    					
		    				}
		    			}
		    		}
		    		
		    		load_color(cate,function(out){
		    			var color = data.color;
		    			if(color.length>0){
			    			var arr_color = color.split('|');
			    			if(arr_color.length>0){
			    				for(var i=0;i<arr_color.length;i++){
			    					choose_color(arr_color[i]);		    					
			    				}
			    			}
			    		}
		    			load_branch(cate,function(out){
		    				//set data 
		    				$("#content_branch").val(data.branch);
		    				unblockbg();	
		            	});		    			
		        	})
		    		
		    	});
            }
        });
	}
    function load_provider(pos) {
        Return_get("ProviderController", "get_list_provider", '', 'GET', function(data) {
            if (data != null) {
                for (var i = 0; i < data.length; i++) {
                    if (data[i].ID != "00") {
                        var str = "<option value=" + data[i].ID + ">" + data[i].Name + "</option>";
                        $("#txtProductproviderid").append(str);
                    }
                }
                arr_load[pos]=true;
            }
        });
    }
    
    function load_product_type(pos) {
        Return_get("ProductController", "get_list_category", '', 'GET', function(data) {
            if (data != null) {
                for (var i = 0; i < data.length; i++) {
                    if (data[i].ID != "00") {
                        var str = "<option value=" + data[i].producttype + " data-cate='"+data[i].category_id+"'>" + data[i].producttypename + "</option>";
                        $("#txtProducttypeid").append(str);
                        if(i==0){
                        	var product_id = getUrlParameter('product_id');
                        	if (product_id == null || product_id == '' || product_id == 'undefined') {
                        		load_property(data[i].category_id,function(out){
                            		
                            	});	
                            	load_color(data[i].category_id,function(out){
                            		
                            	})
                            	load_branch(data[i].category_id,function(out){
                            		
                            	});
                        	}
                        	
                        }
                    }
                }
                list_product_type= data;
                arr_load[pos]=true;
            }
        });

    }
    function get_category_id(id){
    	for (var i = 0; i < list_product_type.length; i++) {
    		if(list_product_type[i].producttype==id){
    			return list_product_type[i].category_id;
    		}
    	}
    	return '';
    }
    $("#txtProducttypeid").change(function(){
    	var id = $(this).val();
    	var cate = get_category_id(id);
    	
    	blockbg();
    	load_property(cate,function(out){
    		load_color(cate,function(out){
    			load_branch(cate,function(out){
    				unblockbg();	
            	});
    			
        	})
    		
    	});
    });
    function load_property(id,callback){
    	var pdata = {
    			'product_type':id
    	}
    	 $("#content_property").text('');
    	 Return_get("ProductController", "get_property", pdata, 'GET', function(data) {
             if (data != null) {
                 for (var i = 0; i < data.length; i++) {
                     var str = '<div class="row" style="margin-top:10px">'
					 str+='<div class="col-md-4">@property_name</div>';
					 str+='<div class="col-md-8">'
					 str+='		<select class="form-control" id="property_@id">@content_property</select>';						
					 str+=' </div>';
					 str+='</div>';
					 str = str.replace('@id',data[i].id);
					 str = str.replace('@property_name',data[i].name);
					 if(data[i].list.length>0){
						 var content_property = '';
						 for (var j = 0; j < data[i].list.length; j++) {
							 var str1 = "<option value=" + data[i].list[j].id + ">" + data[i].list[j].name + "</option>";
							 content_property+=str1;
						 }
						 str = str.replace('@content_property',content_property);	 
					 }
					 $("#content_property").append(str);
                 }               
                 list_property = data;
                 callback(true);
             }
             else{
            	 list_property = [];
             }
         });
    }
    function load_color(id,callback){
    	
    	var pdata = {
    			'product_type':id
    	}
    	 $("#content_color").text('');
    	 Return_get("ProductController", "get_color", pdata, 'GET', function(data) {
             if (data != null) {
                 for (var i = 0; i < data.length; i++) {
                     var str_color = '<div id="color_@id" onclick="choose_color(@colorid)" data_op ="none" style="width:30px;height:30px;float:left;margin-right:15px;cursor:pointer;background:@color">';
                     str_color+='<img id="icon_close_@id"  src="admin/image/icon-check.png" style="heigh:20px;width:20px;position: absolute;margin-top:-10px;margin-left: 20px;border-radius:20px;cursor: pointer;display: none" />';
                     str_color+= '</div>';
                     
                     var str_img = '<div id="color_@id" onclick="choose_color(@colorid)" data_op ="none" style="width:30px;height:30px;float:left;margin-right:15px;cursor:pointer;background:red">';
                     str_img+='<img style="height:30px;width:30px;" src="@hrefsrc" />';
                     str_img+='<img id="icon_close_@id"  src="admin/image/icon-check.png" style="heigh:20px;width:20px;position: absolute;margin-top:-40px;margin-left: 20px;border-radius:20px;cursor: pointer;display: none" />';
                     str_img+='</div>';
                     var str_ap = '';
                     if(data[i].type=='1') // color
                     {
                    	 str_ap = str_color;
                    	 str_ap = str_ap.replace(/\@id/g,data[i].id);
                    	 str_ap = str_ap.replace(/\@colorid/g,"'"+data[i].id+"'");
                    	 str_ap = str_ap.replace('@color',data[i].color)
                     }
                     else{
                    	 str_ap = str_img;
                    	 var hrefsrc = Url_tomcat_vivmall()+'upload/color/'+data[i].img;
                    	 str_ap = str_ap.replace(/\@id/g,data[i].id);
                    	 str_ap = str_ap.replace(/\@colorid/g,"'"+data[i].id+"'");
                    	 str_ap = str_ap.replace('@hrefsrc',hrefsrc);
                     }
					 $("#content_color").append(str_ap);
                 }              
                 list_color = data;                 
                 callback(true);
             }            
         });
    }
    function load_branch(id,callback) {
    	var pdata = {
    			'product_type':id
    	}
    	 $("#content_branch").text('');
    	 Return_get("ProductController", "get_branch", pdata, 'GET', function(data) {
             if (data != null) {
                 for (var i = 0; i < data.length; i++) {
                	 var str = "<option value=" + data[i].id + ">" + data[i].name + "</option>";
                     $("#content_branch").append(str);
                 }             
                          
                 callback(true);
             }            
         });
       
    }
    var url = ReturnHosing() + 'ProductController/upload_image_product.htm';
    $('input[name="txtProductimage"]').ajaxfileupload({
        'action': url,
        'onComplete': function(response) {
        
            var statusVal = JSON.stringify(response.status);

            if (statusVal == "false") {
                $("#message").html("<font color='red'>" + JSON.stringify(response.message) + " </font>");
            }
            if (statusVal == "true") {
                $("#message").html("<font color='green'>" + JSON.stringify(response.message) + " </font>");
            }
            var pram = JSON.stringify(response.pram);
            var first = pram.indexOf('"');
            var last = pram.lastIndexOf('"');
            pram = pram.substring(first + 1, last);
			
            $("#filenameimg").text(pram);
        	
        },
        'onStart': function() {
        	var fileSize = this.get(0).files[0].size;
        	if(fileSize>1024*500){
        		showdialog('dialogmanual', 0, 'This image is out of accepted size. Select another !', '', '');
        		$('#message2').html("<font color='red'>" + "Accepted size : 500KB !" + " </font>");
        		$("#filenameimg").text('');
        		$('#message2').css('display','block');
         		$('#message').css('display','none');
        		return false;
        	}
        	else{
        		$('#message').css('display','block');
         		$('#message2').css('display','none');
        	}
        }
    });
	$('input[name="txtProductimagebig"]').ajaxfileupload({
        'action': url,
        'onComplete': function(response) {
            
            var statusVal = JSON.stringify(response.status);

            if (statusVal == "false") {
                $("#message_big").html("<font color='red'>" + JSON.stringify(response.message) + " </font>");
            }
            if (statusVal == "true") {
                $("#message_big").html("<font color='green'>" + JSON.stringify(response.message) + " </font>");
            }
			
            var pram = JSON.stringify(response.pram);			
            var first = pram.indexOf('"');
            var last = pram.lastIndexOf('"');
            pram = pram.substring(first + 1, last);
			
            $("#filenameimg_big").text(pram);
        	

        },
        'onStart': function() {
        	var fileSize = this.get(0).files[0].size;
        	if(fileSize>1024*500){
        		showdialog('dialogmanual', 0, 'This image is out of accepted size. Select another !', '', '');
        		$('#message_big2').html("<font color='red'>" + "Accepted size : 500KB !" + " </font>");
        		$("#filenameimg").text('');
        		$('#message_big2').css('display','block');
         		$('#message_big').css('display','none');
        		return false;
        	}
        	else{
        		$('#message_big').css('display','block');
         		$('#message_big2').css('display','none');
        	}
        }
        
    });
	$("#txtProductprice").keydown(function(event) {
    	// Allow only backspace and delete
    	if ( event.keyCode == 46 || event.keyCode == 8 ) {
    		// let it happen, don't do anything
    	}
    	else {
    		// Ensure that it is a number and stop the keypress
    		if (event.keyCode < 48 || event.keyCode > 57 ) {
    			event.preventDefault();	
    		}	
    	}
    });
	$("#txtQuantity").keydown(function(event) {
    	// Allow only backspace and delete
    	if ( event.keyCode == 46 || event.keyCode == 8 ) {
    		// let it happen, don't do anything
    	}
    	else {
    		// Ensure that it is a number and stop the keypress
    		if (event.keyCode < 48 || event.keyCode > 57 ) {
    			event.preventDefault();	
    		}	
    	}
    });
    function check_exists(callback, productid) {
        var pdata = {
            'id': productid
        }
        Return_get("ProductController", "check_product_exists", pdata, 'GET', function(data) {
            if (data != null) {
                if (parseInt(data) == 0) {
                    callback(false);
                } else {
                    callback(true);
                }
            } else {
                callback(false);
            }
        });
    }
	function check_before_save(){
			var productid = $("#txtProductid").val();
			var productname = $("#txtProductname").val();
			var productprice = $("#txtProductprice").val();
			var quantity = $("#txtQuantity").val();
			if(productid==null ||productid == '' ){
				return false;
			}
			if(productname==null ||productname == '' ){
				return false;
			}
			if(productprice==null ||productprice == '' ){
				return false;
			}
			if(quantity==null ||quantity == '' ){
				return false;
			}
			return true;
		}
		function clearcontent(){
			$("#txtProductid").val('');
			 $("#txtProductname").val('');
			 $("#txtProductdes").val('');
			 $("#txtProductprice").val('');
			 $("#txtProductproviderid").val('');
			 $("#txtQuantity").val('');
			 CKEDITOR.instances.txtProductdes.setData('');
			 CKEDITOR.instances.editMoreinformation.setData('');
			 CKEDITOR.instances.editGuide.setData('');
	}
	function htmlEntities(str) {
        return String(str).replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;').replace(/'/g, '&blink;');
    }
    $("#btn_submit").click(function() {
    	
    	var str_property = '';
    	for (var i = 0; i < list_property.length; i++) {
    		var pro_val = $("#property_"+list_property[i].id).val();
    		var str = list_property[i].id+'&'+pro_val;
    		str_property+=str;
    		str_property+='|';
    	}
    	if(str_property.length>0){
    		str_property=str_property.substring(0,str_property.length-1);
    	}    
    	// color
    	var str_color = '';
    	if(list_color_choose.length>0){
    		for(var i=0;i<list_color_choose.length;i++){
        		str_color +=list_color_choose[i].id+'|';
        	}    		
    	}
    	if(str_color.length>0){
    		str_color=str_color.substring(0,str_color.length-1);
    	}
    	var branch = $("#content_branch").val();
        var productid = $("#txtProductid").val();
        var productname = $("#txtProductname").val();
	
        var productprice = $("#txtProductprice").val();
        var quantity = $("#txtQuantity").val();
        var producttypeid = $("#txtProducttypeid").val();
        var productimage = $("#filenameimg").text();
		var productimagebig = $("#filenameimg_big").text();        
        var productdes = htmlEntities(CKEDITOR.instances.txtProductdes.getData());
        var productproviderid = $("#txtProductproviderid").val();
        var moreinformation = htmlEntities(CKEDITOR.instances.editMoreinformation.getData());
        var guide = htmlEntities(CKEDITOR.instances.editGuide.getData());
        var typeimglarg = $("#typeimglarg").val();
        var desshort = "temporary";
        if (check_before_save() == false) {
            showdialog('dialogmanual', 0, 'Enter infomation required', '', '');
            return;
        }
        
        if (action == "A") {
			
            check_exists(function(output) {
				
                if (output == true) {
                    unblockbg();
                    showdialog('dialogmanual', 0, 'Product ID exists!', '', '');
                    return;
                } else {
					var obj = {
						"productId": productid,
                        "productName": productname,
                        "producTypeId": producttypeid,
                        "productImage": productimage,
                        "productDes": productdes,
                        "productPrice": productprice,
                        "productProviderId": productproviderid,
                        "productquantity": quantity,
                        "moreinfo": moreinformation,
                        "productguide": guide,
                        "productDescShort": desshort,
						'productimglarg':productimagebig,
						'typeimglarg':typeimglarg,
						'property':str_property,
						'color':str_color,
						'branch':branch
					};
					var pdata = "{'str':'" + JSON.stringify(obj) + "','type':'" + action + "'}";
                    Return_get("ProductController", "insert_product", pdata, 'POST', function(data) {
                        unblockbg();
                        if (data != null) {
                            if (parseInt(data) == 0) {
                                if (action == "A") {
                                    showdialog('dialogmanual', 0, 'Thêm thành công!', '', '');
                                    clearcontent();
                                } else {
                                    showdialog('dialogmanual', 0, 'Cập nhật không thanh công', '', '');

                                }
                            } else {
                                showdialog('dialogmanual', 0, 'Thêm sản phẩm không thanh công', '', '');
                            }

                        }
                    });

                }
            }, productid);
        }
        if (action == "E") {
			
            check_exists(function(output) {
                if (output == false) {
                    unblockbg();
                    showdialog('dialogmanual', 0, 'Product ID not exists!', '', '');
                    return;
                } else {
                    var obj = {
						"productId": productid,
                        "productName": productname,
                        "producTypeId": producttypeid,
                        "productImage": productimage,
                        "productDes": productdes,
                        "productPrice": productprice,
                        "productProviderId": productproviderid,
                        "productquantity": quantity,
                        "moreinfo": moreinformation,
                        "productguide": guide,
                        "productDescShort": desshort,
                        'productimglarg':productimagebig,
                        'typeimglarg':typeimglarg,
                        'property':str_property,
						'color':str_color,
						'branch':branch
					};
                    var pdata = "{'str':'" + JSON.stringify(obj) + "','action':'" + action + "'}";
                    Return_get("ProductController", "insert_product", pdata, 'POST', function(data) {
                        unblockbg();
                        if (data != null) {
                            if (parseInt(data) == 0) {
                                if (action == "A") {
                                    showdialog('dialogmanual', 0, 'Insert Success!', '', '');
                                    clearcontent();
                                } else {
                                     var url = ReturnHosing_apache();
									 showdialogconfirmfunc2('dialogmanual','Cập nhật sản phẩm thành công, bạn có muốn thêm mới?',function(){
										 window.location.href=url+'admin/product/Productnew.html';
									 },function(){
										  window.location.href=url+'admin/product/Product.html';
									 });
                                }
                            } else {
                                showdialog('dialogmanual', 0, 'Cập nhật sản phẩm không thanh công!', '', '');
                            }

                        }
                    });

                }
            }, productid);
        }
    });
});
function check_color_exists(id){
	for(var i=0;i<list_color.length;i++){
		if(list_color[i].id==id){
			return true;
		}
	}
	return false;
}
function check_color_exists_choose(id){
	for(var i=0;i<list_color_choose.length;i++){
		if(list_color_choose[i].id==id){
			return true;
		}
	}
	return false;
}
function remove_color_exists_choose(id){
	var list = [];
	for(var i=0;i<list_color_choose.length;i++){
		if(list_color_choose[i].id!=id){
			list.push(list_color_choose[i]);
		}
	}
	list_color_choose = list;	
}
function choose_color(id){	
	if(check_color_exists(id)){
		var data_op = $("#color_"+id).attr('data_op');
		if(data_op=='none'){
			$("#color_"+id).attr('data_op','up');
			$("#icon_close_"+id).css('display','block');
			//add item
			if(!check_color_exists_choose(id)){
				var item = {
						'id':id
				}
				list_color_choose.push(item);
			}
		}
		else{
			$("#color_"+id).attr('data_op','none');
			$("#icon_close_"+id).css('display','none');
			if(check_color_exists_choose(id)){
				remove_color_exists_choose(id);
			}
		}
	}
	else{
		showdialog('dialogmanual', 0, 'Mã màu không tồn tại', '', '');
	}
	
	
}

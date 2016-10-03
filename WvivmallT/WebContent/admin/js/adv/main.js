$(function() {
	var path = ReturnHosing() + "upload/adv/";
    var session_admin = $.session.get("profile_session_admin");
    exe_load_header(function(output) {
        if (output == true) {
            get_list_slider();
        }

    });
    function get_item_tr(id, name, path,status) {
        var str = '';
        str += '<tr >';
        str += '<td style="width:10%;text-align:center"><input type="checkbox" class="ads_Checkbox" value="' + id + '"></td>';
        str += '<td style="width:20%;text-align:center;color:black">' + id + '</td>';
        str += '<td style="width:30%;text-align:center;color:black">' + status + '</td>';
        str += '<td style="width:40%;text-align:center"><img src="' + path + '' + name + '" style="width:100%;height:150px;"></td>';
        str += '</tr>';
        return str;
    }
    function get_list_slider() {
        Return_get("AdvController", "get_list_slide", '', 'GET', function(data) {
        	 $("#idshowlist").text('');
            if (data != null) {                
                $.each(data, function(i, item) {
                    var str = get_item_tr(item.id, item.name, path,item.status=='0'?'Active':'Not active');
                    $("#idshowlist").append(str);
                });

            }
        });
    }
    $('#allcheckbox_in').click(function() {
        $('input:checkbox').prop('checked', this.checked);
    });
    $("#btnremove").click(function() {
        var str = get_munti_Selected();
        if (str.length == 0) {
            showdialog('dialogmanual', 0, 'Vui lòng chọn hình muốn xóa', '', '');
            return;
        }
        var pdata = {
            'str': str
        };

        Return_get("AdvController", "delete_slide", pdata, "GET", function(data) {
            if (data != null) {
                unblockbg();
                var error = parseInt(data.result);
                if (error == 0) {
                	get_list_slider();
                    showdialog('dialogmanual', 0, 'Xóa thành công', '', '');
                } else {

                    showdialog('dialogmanual', 0, data.message, '', '');
                 
                }
            } else {
                showdialog('dialogmanual', 0, 'Xóa không thành công', '', '');
               
            }
        });
    });
    function check_befor_save() {
        var name = $("#id_upload_success").val();
        if (name == '' || name == null) {
            return true;
        }
        return false;
    }
    $("#idbtn_okupload").click(function() {
        if (check_befor_save()) {
            showdialog('dialogmanual', 0, 'Vui lòng chọn file muốn upload', '', '');
            return;
        }
        
        var json_slide = get_json_slide();
        var pdata = {
            'str': json_slide
        };

        Return_get("AdvController", "save_slide", pdata, "GET", function(data) {
	        if (data != null) {
	            unblockbg();
	            var error = parseInt(data.result);
	            if (error == 0) {
	                get_list_slider();
	                showdialog('dialogmanual', 0, 'Lưu thành công', '', '');
	            } else {
	
	                showdialog('dialogmanual', 0, data.message, '', '');
	               
	            }
	        } else {
	            showdialog('dialogmanual', 0, 'Lưu không thành công', '', '');
	            
	        }
	    });	
    });  
    function get_json_slide(){
        var obj={};
        //obj.id=
        obj.name=$("#id_upload_success").val();
        obj.creator=session_admin;
        var str= JSON.stringify(obj);
        return str;
     }  
    function get_munti_Selected() {
	    var c = "";
	    $('.ads_Checkbox:checked').each(function() {
	        c += "'" + $(this).val() + "'" + ",";
	    });
	    if (c.length > 0) {
	        c = c.substring(0, c.length - 1);
	    }
	    return c;
	}

	var url_upload = ReturnHosing() + "AdvController/upload_image.htm";
	$('input[id="input_upload_feature"]').ajaxfileupload({
	'action': url_upload,
	'onComplete': function(response) {
	
	    // showMyImage( $('input[id="input_upload_feature"]'));
	    var filename = JSON.stringify(response.pram);
	    //alert(filename); 
	    var filens = String(filename).replace(/;/g, '').replace(/"/g, '');
	    $("#thumbnil").attr('src', path + "" + filens);
	    //  alert(filens);
	    $("#id_upload_success").val(filens);
	    //alert(filename);
	},
		'onStart': function() {}
	});
	$("#btnlock").click(function() {
        var str = get_munti_Selected();
        if (str.length == 0) {
            showdialog('dialogmanual', 0, 'Vui lòng chọn hình muốn ẩn', '', '');
            return;
        }
        var pdata = {
            'str': str
        };

        Return_get("AdvController", "lock", pdata, "GET", function(data) {
            if (data != null) {
                unblockbg();
                var error = parseInt(data.result);
                if (error == 0) {
                	get_list_slider();
                    showdialog('dialogmanual', 0, 'Ẩn thành công', '', '');
                } else {

                    showdialog('dialogmanual', 0, data.message, '', '');
                  
                }
            } else {
                showdialog('dialogmanual', 0, 'Ẩn không thành công', '', '');
                
            }
        });
    });
	$("#btnunlock").click(function() {
        var str = get_munti_Selected();
        if (str.length == 0) {
            showdialog('dialogmanual', 0, 'Vui lòng chọn hình để hiện', '', '');
            return;
        }
        var pdata = {
            'str': str
        };

        Return_get("AdvController", "unlock", pdata, "GET", function(data) {
            if (data != null) {
                unblockbg();
                var error = parseInt(data.result);
                if (error == 0) {
                	get_list_slider();
                    showdialog('dialogmanual', 0, 'Hiện thành công', '', '');
                } else {

                    showdialog('dialogmanual', 0, data.message, '', '');
        
                }
            } else {
                showdialog('dialogmanual', 0, 'Hiện không thành công', '', '');
      
            }
        });
    });
	
}); //end document
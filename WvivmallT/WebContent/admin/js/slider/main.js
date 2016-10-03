$(function() {
	var path = ReturnHosing() + "upload/slide/";
    var session_admin = $.session.get("profile_session_admin");
    exe_load_header(function(output) {
        if (output == true) {
            get_list_slider();
        }

    });
    function get_item_tr(id, name, path,isvisible) {
        var str = '';
        str += '<tr >';
        str += '<td style="width:10%;text-align:center"><input type="checkbox" class="ads_Checkbox" value="' + id + '"></td>';
        str += '<td style="width:10%;text-align:center;color:black">' + id + '</td>';
        str += '<td style="width:30%;text-align:center;color:black">' + name + '</td>';
        str += '<td style="width:10%;text-align:center;color:black">' + isvisible + '</td>';
        str += '<td style="width:40%;text-align:center"><img src="' + path + '' + name + '" style="width:100%;height:150px;"></td>';
        str += '</tr>';
        return str;
    }
    function get_list_slider() {
        Return_get("SlideController", "get_list_slide", '', 'GET', function(data) {
        	 $("#idshowlist").text('');
            if (data != null) {                
                $.each(data, function(i, item) {                	
                    var str = get_item_tr(item.id, item.name, path,item.isvisible);
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
            showdialog('dialogmanual', 0, 'Select Image Remove', '', '');
            return;
        }
        var pdata = {
            'str': str
        };
        blockbg();
        Return_get("SlideController", "delete_slide", pdata, "GET", function(data) {
            if (data != null) {
                unblockbg();
                var error = parseInt(data.result);
                if (error == 0) {
                	get_list_slider();
                    showdialog('dialogmanual', 0, 'remove success', '', '');
                } else {

                    showdialog('dialogmanual', 0, data.message, '', '');
                   
                }
            } else {
                showdialog('dialogmanual', 0, 'Rmmove fail', '', '');
               
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
            showdialog('dialogmanual', 0, 'Select file upload', '', '');
            return;
        }
        
        var json_slide = get_json_slide();
        var pdata = {
            'str': json_slide
        };
        blockbg();
        Return_get("SlideController", "save_slide", pdata, "GET", function(data) {
	        if (data != null) {
	            unblockbg();
	            var error = parseInt(data.result);
	            if (error == 0) {
	                get_list_slider();
	                showdialog('dialogmanual', 0, 'Save success', '', '');
	            } else {
	
	                showdialog('dialogmanual', 0, data.message, '', '');
	                
	            }
	        } else {
	            showdialog('dialogmanual', 0, 'Save Fail', '', '');
	        
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

	var url_upload = ReturnHosing() + "SlideController/upload_image.htm";
	$('input[id="input_upload_feature"]').ajaxfileupload({
	'action': url_upload,
	'onComplete': function(response) {
	

	    var filename = JSON.stringify(response.pram);
	    var filens = String(filename).replace(/;/g, '').replace(/"/g, '');
	    $("#thumbnil").attr('src', path + "" + filens);
	    $("#id_upload_success").val(filens);

	},
		'onStart': function() {
			var fileSize = this.get(0).files[0].size;
			if(fileSize>1024*500){
         		showdialog('dialogmanual', 0, 'This image is out of accepted size. Select another !', '', '');
         		$('#message2').html("<font color='red'>" + "Accepted size : 500KB !" + " </font>");
         		$("#id_uploadsuccess").val('');
         		$('#message2').css('display','block');
         		$('#message').css('display','none');
         		return false;
         	}
         	else{
         		$('#message2').css('display','none');
         		$('#message').html("<font color='green'>" + "Your file is uploaded !" + " </font>");
         		$('#message').css('display','block');
         	}
		}
	});
	 $("#btnvisible").click(function() {
	        var str = get_munti_Selected();
	        if (str.length == 0) {
	            showdialog('dialogmanual', 0, 'Select Image Visible', '', '');
	            return;
	        }
	        var pdata = {
	        	'type':'1',
	            'str': str
	        };
	        blockbg();
	        Return_get("SlideController", "visible_slide", pdata, "GET", function(data) {
	            if (data != null) {
	                unblockbg();
	                var error = parseInt(data.result);
	                if (error == 0) {
	                	get_list_slider();
	                    showdialog('dialogmanual', 0, 'Visible success', '', '');
	                } else {

	                    showdialog('dialogmanual', 0, data.message, '', '');
	                   
	                }
	            } else {
	                showdialog('dialogmanual', 0, 'Visible fail', '', '');
	               
	            }
	        });
	    });
	 $("#btnunvisible").click(function() {
	        var str = get_munti_Selected();
	        if (str.length == 0) {
	            showdialog('dialogmanual', 0, 'Select Image Visible', '', '');
	            return;
	        }
	        var pdata = {
	        	'type':'0',
	            'str': str
	        };
	        blockbg();
	        Return_get("SlideController", "visible_slide", pdata, "GET", function(data) {
	            if (data != null) {
	                unblockbg();
	                var error = parseInt(data.result);
	                if (error == 0) {
	                	get_list_slider();
	                    showdialog('dialogmanual', 0, 'UnVisible success', '', '');
	                } else {

	                    showdialog('dialogmanual', 0, data.message, '', '');
	                   
	                }
	            } else {
	                showdialog('dialogmanual', 0, 'UnVisible', '', '');
	               
	            }
	        });
	    });
}); //end document
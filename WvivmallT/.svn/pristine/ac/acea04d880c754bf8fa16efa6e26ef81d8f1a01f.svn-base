var p_idemail = '';
var p_fullname_main = '';
var p_iduploadimg = '';
var p_product = '';
var p_shortfullname = '';
var str_text_item_comment = '';
var str_text_item_comment_sub = '';
var str_box = '';
var str_box_sub = '';
var p_id_comment = '';
var p_box_current = '';
$(function(){
	
	p_product =  getUrlParameter('id');
	
	if(p_product==null || p_product==undefined){
		return;
	}
	load_box_comment();
	function load_box_comment(){
		var url = ReturnHosing_apache()+'txt/box_comment.txt';
		$.get(url,function(data){
			str_box = data;
			var str_main = data;
			str_main = str_main.replace(/\@id/g,'1991');			
			str_main = str_main.replace(/\@valuecomment/g,"");
			$("#boxcomment").append(str_main);
			load_text_item_comment();
		});
	}	
	
	function load_text_item_comment(){
		var url = ReturnHosing_apache()+'txt/item_comment.txt';
		$.get(url,function(data){
			str_text_item_comment = data;
			load_text_item_comment_sub();
		});
	}	
	function load_text_item_comment_sub(){
		var url = ReturnHosing_apache()+'txt/item_comment_sub.txt';
		$.get(url,function(data){
			str_text_item_comment_sub = data;
			load_text_box_comment_sub();
			load_info_customer_aft_login(function(){
				load_comment_message();	
			});
			
		});
	}	
	function load_text_box_comment_sub(){
		var url = ReturnHosing_apache()+'txt/box_comment_sub.txt';
		$.get(url,function(data){
			str_box_sub = data;			
		});
	}	
	function load_info_customer_aft_login(callback){
		var pdata = {
				'subid':'1991'
		}
		Return_get('MemberController','get_info_log_aft',pdata,'GET',function(data){		
			
			if(data!=null){					
				var result = data.result;
				if(result==1){		
					$("#infologincomment_"+data.subid).css('display','none');
					p_idemail = data.id;
					p_fullname_main  = data.fullname;
					p_shortfullname = data.shortname;
					$("#infosend_"+data.subid).css('display','block');
					$("#infologincomment_"+data.subid).css('display','none');
					$("#txtemail").val('');
					$("#txtpassword").val('');
					callback();
				}
				else{
					callback();	
				}
			}
			else{
				callback();
			}
		});
	}
	function load_comment_message(){
		
		var pdata = {
				'product':p_product
		}
		Return_get("CommentController","get_list_comment",pdata,"GET",function(data){
			if(data!=null){
				
				for(var i=0;i<data.length;i++){										
					var html = str_text_item_comment;
					html = html.replace('@shortname',data[i].shortname);
					html = html.replace('@fullname',data[i].name_customer);
					html = html.replace('@content_comment',data[i].message);
					html = html.replace('@idparent',data[i].id); //id message
					html = html.replace('@timesend',data[i].timesend);
					html = html.replace(/\@id/g,data[i].id);					
					$("#content_comment").append(html);
					if(data[i].list_comment_sub.length >0){
						for(var j=0;j<data[i].list_comment_sub.length;j++){										
							var html1 = str_text_item_comment_sub;
							html1 = html1.replace('@shortname',data[i].list_comment_sub[j].shortname);
							html1 = html1.replace('@fullname',data[i].list_comment_sub[j].name_customer);
							html1 = html1.replace('@content_comment',data[i].list_comment_sub[j].message);
							html1 = html1.replace('@parentid',data[i].list_comment_sub[j].id); //id message
							html1 = html1.replace('@subid',data[i].list_comment_sub[j].id_sub); //id message
							html1 = html1.replace('@timesend',data[i].list_comment_sub[j].timesend);
							html1 = html1.replace(/\@id/g,data[i].list_comment_sub[j].id_sub);
							if(data[i].list_comment_sub[j].role=='0'){ //nhan vien
								html1 = html1.replace('@display','block');
							}
							else{
								html1 = html1.replace('@display','none'); // thuong
							}
							$("#content_comment").append(html1);					
						}
					}
										
				}				
			}
			else{
				show_message_basic('dialogmanual1','Lỗi');
			}
		});
	}
	
	
	
	
	
	var urlimg = ReturnHosing_tomcat()+"CommentController/upload_image_comment.htm";;
    $('input[name="imgcomment"]').ajaxfileupload({
        'action': urlimg,
        'onComplete': function(response) {            
            var statusVal = JSON.stringify(response.status);

            if (statusVal == "false") {
                $("#messageupload").html("<font color='red'>" + JSON.stringify(response.message) + " </font>");
            }
            if (statusVal == "true") {
                $("#messageupload").html("<font color='green'>" + JSON.stringify(response.message) + " </font>");
            }
            var pram = JSON.stringify(response.pram);
            var first = pram.indexOf('"');
            var last = pram.lastIndexOf('"');
            pram = pram.substring(first + 1, last);
            p_iduploadimg= pram;            

        },
        'onStart': function() {
            $('#upload').show();
            $('#messageupload').hide();
        }
    });
});
function fnregister(boxid){	
	var p_fullname = $("#txtfullname_"+boxid).val();
	var p_email = $("#txtemail_"+boxid).val();
	if(p_fullname==null || p_fullname==''){
		show_message_basic('dialogmanual1','Vui lòng nhập họ tên');
		return;
	}
	if(p_email==null || p_email==''){
		show_message_basic('dialogmanual1','Vui lòng nhập email');
		return;
	}	
	var obj  = {};
	obj.email = p_email;
	obj.fullname = p_fullname;
	obj.boxid = boxid;
	var strdata = JSON.stringify(obj);
	var pdata="{'str':'"+strdata+"'}";
	
	blockbg();
	Return_get("CommentController","register_customer",pdata,"POST",function(data){
		if(data!=null){
			unblockbg();
			var error = parseInt(data.result);	
			email = data.id;
			if(error == 0 ){
				p_idemail= data.id;
				p_fullname_main = data.fullname;
				p_shortfullname = data.shortfullname;
				clear_form(data.boxid);
				show_message_basic('dialogmanual1','Vui lòng đăng nhập email để lấy mật khẩu.');					
			}					
			else{
				show_message_basic('dialogmanual1','Đăng ký không thành công');					
			
			}
		}
		else{
			show_message_basic('dialogmanual1','Đăng ký không thành công');
		}
	});
}
function fnsendcomment(boxid){
	
	if(p_idemail=='' || p_idemail == undefined){
		$("#infosend_"+boxid).css('display','none');
		$("#infologincomment_"+boxid).css('display','block');
		return;
	}
	var contentcomment = $("#txtcontentcomment_"+boxid).val();
	if(contentcomment=='' || contentcomment == undefined){
		show_message_basic('dialogmanual1','Vui lòng nhập nội dụng');
		return;
	}
	
	var obj  = {};
	obj.id_customer = p_idemail;
	obj.id =boxid;
	obj.id_sub ='';		
	obj.product = p_product;
	obj.message = contentcomment;		
	obj.boxid = boxid;
	var strdata = JSON.stringify(obj);		
	var pdata="{'str':'"+strdata+"'}";
	send(pdata);
}
function fnsendcommentsub(parentid,boxid){
	if(p_idemail=='' || p_idemail == undefined){
		$("#infosend_"+boxid).css('display','none');
		$("#infologincomment_"+boxid).css('display','block');
		return;
	}
	var contentcomment = $("#txtcontentcomment_"+boxid).val();
	if(contentcomment=='' || contentcomment == undefined){
		show_message_basic('dialogmanual1','Vui lòng nhập nội dụng');
		return;
	}
	
	var obj  = {};
	obj.id_customer = p_idemail;
	obj.id =parentid;
	obj.id_sub ='';		
	obj.product = p_product;
	obj.message = contentcomment;		
	obj.boxid = boxid;
	var strdata = JSON.stringify(obj);		
	var pdata="{'str':'"+strdata+"'}";
	send(pdata);
}
function send(pdata){
	blockbg();
	Return_get("CommentController","insert_comment",pdata,"POST",function(data){
		if(data!=null){
			unblockbg();
			var error = parseInt(data.result);	
			email = data.id;
			if(error == 0 ){	
				$("#txtcontentcomment_"+data.boxid).val('');
				if(data.boxid=='1991'){
					var html = str_text_item_comment;
					html = html.replace('@shortname',p_shortfullname);
					html = html.replace('@fullname',p_fullname_main);
					html = html.replace('@content_comment',data.message);
					html = html.replace('@idparent',data.id);
					html = html.replace('@timesend',data.timesend);
					html = html.replace(/\@id/g,data.id);
					html = html.replace('@display','none');
					var str_old = $("#content_comment").html();					
					$("#content_comment").text('');
					$("#content_comment").append(html);
					$("#content_comment").append(str_old);
				}
				else{
					var html = str_text_item_comment_sub;
					html = html.replace('@shortname',p_shortfullname);
					html = html.replace('@fullname',p_fullname_main);
					html = html.replace('@content_comment',data.message);					
					html = html.replace('@parentid',p_box_current); 
					html = html.replace('@subid',data.id); 
					html = html.replace('@timesend',data.timesend);
					html = html.replace(/\@id/g,data.id);
					html = html.replace('@display','none');
					$("#itembox_"+data.boxid).after(html);		
					//remove box message
					$("#box_"+p_box_current).remove();
					p_box_current = '';
				}							
			}					
			else{
				show_message_basic('dialogmanual1','Lỗi');				
			
			}
		}
		else{
			show_message_basic('dialogmanual1','Lỗi');
		}
	});
}
function clear_form(boxid){
	$("#txtfullname_"+boxid).val('');
	$("#txtemail_"+boxid).val('');
	$("#infosend_"+boxid).css('display','block');
	$("#infologincomment_"+boxid).css('display','none');
}
function close_box(boxid){
	$("#infosend_"+boxid).css('display','block');
	$("#infologincomment_"+boxid).css('display','none');
}

function replay_comment_parent(itemid){
	
	var str_main = str_box;
	str_main = str_main.replace(/\@id/g,itemid);
	var fullname_reply = $("#fullname_"+itemid).text();
	str_main = str_main.replace(/\@valuecomment/g,'@'+fullname_reply);
	if(p_box_current==''){
		p_box_current = itemid;		
		$("#itembox_"+itemid).append(str_main);	
	}
	else{		
		if(itemid!=p_box_current){			
			$("#box_"+p_box_current).remove();
			p_box_current = itemid;		
			$("#itembox_"+itemid).append(str_main);	
		}	
		
	}	
}
function replay_comment_parent_sub(idparent,idsub){
	var str_main = str_box_sub;
	str_main = str_main.replace(/\@id/g,idsub);
	str_main = str_main.replace(/\@parentid/g,idparent);
	str_main = str_main.replace(/\@subid/g,idsub);
	var fullname_reply = $("#fullname_"+idsub).text();
	str_main = str_main.replace(/\@valuecomment/g,'@'+fullname_reply);
	if(p_box_current==''){				
		p_box_current = idsub;		
		$("#itembox_"+idsub).append(str_main);	
	}
	else{
		if(p_box_current!=idsub){			
			
			$("#box_"+p_box_current).remove();
			p_box_current = idsub;		
			$("#itembox_"+idsub).append(str_main);
		}
	}	
}
function Login(subid){
	$("#dialoglogin").dialog({
		 height: 390,
		 width: 650,
		 modal: true ,
		 autoOpen: true ,
		 title:'Đăng nhập VMALL',
		 resizable: false ,
		 buttons: {	   			
			"Đăng nhập": function() {
				  var p_email = $("#txtemail").val();
				  var p_pass = $("#txtpassword").val();
				  blockbg();
					var email = $("#txtemail").val();
					var pass = $("#txtpassword").val();
					var pdata = {
						'email':email,
						'pass':pass,
						'subid':subid
					};
					exec_check_login(pdata);
			},
			"Đóng": function() {
			   $(this).dialog("close");
		   }
		
		}
	});
}
function exec_check_login(pdata){
	
	Return_get('MemberController','check_login_comment',pdata,'GET',function(data){		
		unblockbg();
		if(data!=null){					
			var error = data.error;
			if(error==1){		
				$("#infologincomment_"+data.subid).css('display','none');
				p_idemail = data.id;
				p_fullname_main  = data.fullname;
				p_shortfullname = data.shortname;
				$("#infosend_"+data.subid).css('display','block');
				$("#infologincomment_"+data.subid).css('display','none');
				$("#txtemail").val('');
				$("#txtpassword").val('');
				showdialogfunctionok('dialogmanual1', 'Đăng nhập thành công.', function(out){
					 $(this).dialog("close");
					 $("#dialoglogin").dialog('close');
				});
			}
			else if(error==2){								
				show_message_basic('dialogmanual1','Tài khoản chưa xác nhận, vui lòng xác nhận!.');
			}
			else{
				show_message_basic('dialogmanual1','Đăng nhập không thành công.');
			}
		}
	});
}

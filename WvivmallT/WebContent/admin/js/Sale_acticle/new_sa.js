$(function(){
 
    var lang=get_lang_current();
    var type="A";
    var sale_acticle_id='0';
    var sessionad= $.session.get("profile_session_admin");
	exe_load_header(function(output) {		   
		if(output==true){
                	load_data();			  
			  CKEDITOR.replace( 'idcontent',
			  {
				filebrowserImageUploadUrl: ReturnHosing()+"UploadImageController/upload_image.htm"
			  } );
		}
    });
   
	function load_data(){
		var p_id=getUrlParameter("sale_acticle_id");
		if(p_id==null||p_id==""){
			return;
		}
		sale_acticle_id=p_id;
		var pdata = {
		  'p_id':p_id
		};
		Return_get("Sale_acticleController","get_Sale_acticle",pdata,"GET",function(data){
			if(data!=null){
				$("#idtitle").val(data.title);
                $("#idcontent").text(data.content);
                type="E";
			}
			
		});
	}
	 function htmlEntities(str) {
	    return String(str).replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;').replace(/'/g, '&blink;');
	}
	function get_json_sale_acticle(){
		var obj={};
		var str1= CKEDITOR.instances.idcontent.getData();		
		str1 = htmlEntities(str1);
		obj.id=sale_acticle_id;
		obj.title=$("#idtitle").val();
		obj.content=str1;
		obj.creator=sessionad;
		obj.modifier=sessionad;
		obj.description=$("#iddescription").val();
		obj.image=$("#id_upload_success").val();
	    var str= JSON.stringify(obj);		
		return str;
    
 }
	function check_befor_submit(){
		var title=$("#idtitle").val();
		var content=CKEDITOR.instances.idcontent.getData();	
		var description=$("#iddescription").val();
		var image=$("#id_upload_success").val();
		if(title==""){
			showdialog('dialogmanual',0,'Xin nhập `Tiêu đề`','','');
			return false;
		}
		if(content==""){
			showdialog('dialogmanual',0,'Xin nhập `Nội dung`','','');
			return false;
		}
		if(description==""){
			showdialog('dialogmanual',0,'Xin nhập `Mô tả`','','');
			return false;
		}
		if(image==""){
			showdialog('dialogmanual',0,'Xin chọn `Ảnh minh họa`','','');
			return false;
		}
		return true;
	}
	$("#btn_submit").click(function(){
		if(!check_befor_submit()){
		
			return;
		}
		 var json_acticle=get_json_sale_acticle();
         var pdata="{'str':'"+json_acticle+"','type':'"+type+"'}"; 
		 blockbg();
		 Return_get("Sale_acticleController","save_Sale_acticle",pdata,"POST",function(data){
			if(data!=null){
				unblockbg();
				var error = parseInt(data.result);				
				if(error == 0 ){
					showdialog('dialogmanual',0,'Lưu thành công','','');
					
				}					
				else{
					showdialog('dialogmanual',0,'Lưu thất bại','','');					
				
				}
			}
			else{
				showdialog('dialogmanual',0,'Lưu thất bại','','');
			}
		});
	});	
	
	
	  var src_feature_image=ReturnHosing()+"upload/sale_acticle/";
	  var url_upload = ReturnHosing()+"Sale_acticleController/upload_feature_image.htm";    
	    $('input[id="input_upload_feature"]').ajaxfileupload({
			'action' : url_upload,		
			'onComplete' : function(response) {
			//alert(JSON.stringify(response));
			 // showMyImage( $('input[id="input_upload_feature"]'));
				var filename = JSON.stringify(response.pram);
	             //alert(filename); 
	            var filens=String(filename).replace(/;/g, '').replace(/"/g, '');
	            $("#thumbnil").attr('src',src_feature_image+""+filens);
	           // alert(filens);
				$("#id_upload_success").val(filens);
			},
			'onStart' : function() {
				var fileSize = this.get(0).files[0].size;
	         	if(fileSize>1024*30){
	         		showdialog('dialogmanual', 0, 'Hình ảnh có kích thước lớn hơn mức cho phép. Xin chọn hình ảnh khác !', '', '');
	         		$('#message').html("<font color='red'>" + "Kích cỡ cho phép : 30KB !" + " </font>");
	         		$("#id_uploadsuccess").val('');
	         		$('#message').css('display','block');
	         		return false;
	         	}
	         	else{
	         		$('#message').html("<font color='green'>" + "Ảnh đã được gửi lên !" + " </font>");
	         		$('#message').css('display','block');
	         	}
			}
		});   
	
	
	
	$("#btn-cancel").click(function(){
		window.location.href="admin/index.html";
		
		
	});
	
});
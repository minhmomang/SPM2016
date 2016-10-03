$(function(){
	var id = '';
    var lang= '';
	var type = 'A';
	exe_load_header(function(output) {		   
		if(output==true){
			
			load_data();
		}
    });
	function load_data(){
		id = getUrlParameter('id');	
		blockbg();		
		if(id!=null && id!='' && id!='undefined')
		{
			var arr_id = id.split('_');
			var action = arr_id[0];			
			id = arr_id[1];			
			if(action=="UPDATE"){	
				type = 'E';
			 	load_rec(function(output){
					if(output==true){
						unblockbg();
						load_ckeditor();
					}
				},id)
			}
			else if(action=="VIEW"){		 	
			  load_rec(function(output){
					if(output==true){
						unblockbg();
						load_ckeditor();
					}
				},id)
				$("#btn_submit").css('display','none');
                $("#btn_submit").attr('disabled','disabled');
			} 
		}
		else{
			unblockbg();
			load_ckeditor();
		}
	}
	function load_rec(callback,id){
			var pdata = {'id':id};
			Return_get("RecController","get_rec_id",pdata,"GET",function(data){
				if(data!=null){
					
					$("#idpos").val(data.pos);
					$("#idqt").val(data.qt);
					$("#idlocation").val(data.location);
					$("#idmissionmain").val(data.main);
					$("#idrequired").val(data.requi);
					$("#idpolicy").val(data.poli);
					$("#idcontact").val(data.contact);		
					callback(true);
				}			
			});
	}
	function load_ckeditor(){
		CKEDITOR.replace( 'idmissionmain' );			
		CKEDITOR.replace( 'idrequired' );
		CKEDITOR.replace( 'idpolicy' );
		CKEDITOR.replace( 'idcontact' );
	}
	 function htmlEntities(str) {
	    return String(str).replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;').replace(/'/g, '&blink;');
	}
	function get_json(){
		var obj={};
		obj.id =id;
		obj.pos = $("#idpos").val();
		obj.qt = $("#idqt").val();
		obj.location = $("#idlocation").val();
		obj.main = htmlEntities(CKEDITOR.instances.idmissionmain.getData());
		obj.requi = htmlEntities(CKEDITOR.instances.idrequired.getData());
		obj.poli = htmlEntities(CKEDITOR.instances.idpolicy.getData());
		obj.contact = htmlEntities(CKEDITOR.instances.idcontact.getData());
		obj.type = type;
	    var str= JSON.stringify(obj);		
		return str;    
    } 
	$("#btn_submit").click(function(){
		 var _json=get_json();
         var pdata="{'str':'"+_json+"'}"; 
		 blockbg();
		 Return_get("RecController","insert_rec",pdata,"POST",function(data){
			 unblockbg();
			if(data!=null){
				if(type=='A'){
					showdialog('dialogmanual', 0, 'Nhập thành công!', '', '');
					clearcontent();	
				}
				else{
					showdialog('dialogmanual', 0, 'Cập nhật thành công!', '', '');
					window.location.href=ReturnHosing_apache()+'admin/recruitment/recruitment.html';
				}
				
                
			}
			else{
				showdialog('dialogmanual',0,'Fail','','');									
			}
		});
	});
	function clearcontent(){
		id='';
		$("#idpos").val('');
		$("#idqt").val('');
		$("#idlocation").val('');
		htmlEntities(CKEDITOR.instances.idmissionmain.setData(''));
		htmlEntities(CKEDITOR.instances.idrequired.setData(''));
		htmlEntities(CKEDITOR.instances.idpolicy.setData(''));
		htmlEntities(CKEDITOR.instances.idcontact.setData(''));
		type ='A';
	}
});
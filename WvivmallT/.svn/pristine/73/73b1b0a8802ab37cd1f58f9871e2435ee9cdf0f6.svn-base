
$(function() {
	var list_writer=[];
	var source = null;
     var lang=get_lang_current();
	 exe_load_header(function(output) {
	   if(output==true){	      
		  
		   load_form_grid();	   
		   exec_load_data();
		  
	   }
       
	 });
 
  function exec_load_data(){
		blockbg();
		var pdata = {'lang':lang};	
		load_data(function(output){
			if(output==true){
				unblockbg();
				source.localdata=list_writer;        	
                $("#idwriter").jqxGrid('updatebounddata');
         	    $('#idwriter').jqxGrid('clearselection');
			}
		},pdata);
	}
	function load_data(callback,pdata) {		
		list_writer= [];		 
		Return_get("WriterController", "get_list_writer", pdata, 'GET', function(data) {		
			if (data != null) {
				list_writer = data;
				callback(true);
			}else
            {
                	unblockbg();
            }
		});		       
	}
    
    
     
     function load_form_grid() {
        source = {
            localdata: list_writer,
            datatype: "array",
            datafields: [{
                name: 'writer_id',
                type: 'string'
            }, {
                name: 'title',
                type: 'string'
            }, {
                name: 'cate',
                type: 'string'
            }, {
                name: 'description',
                type: 'string'
            }, {
                name: 'visible',
                type: 'string'
            },{
                name: 'create_date',
                type: 'string'
            }, {
                name: 'creator',
                type: 'string'
            }, {
                name: 'modify_date',
                type: 'string'
            }, {
                name: 'modifier',
                type: 'string'
            }]

        };
        ///end source
        var dataAdapter = new $.jqx.dataAdapter(source);
        $("#idwriter").jqxGrid({
            width: get_width(),
            source: dataAdapter,
            columnsresize: true,
            pageable: true,
            height: get_height(),            
            selectionmode: 'checkbox',
            columns: [{
                text: 'Loại tin tức',
                datafield: 'cate',
                cellsalign: 'center',
                align: 'center',
                width: 150
            },,{
                text: 'Mã tin',
                datafield: 'writer_id',
                cellsalign: 'center',
                align: 'center',
                width: 250
            }, {
                text: 'Tiêu đề',
                datafield: 'title',
                cellsalign: 'left',
                align: 'center',
                width: 350
            }, {
                text: 'Hiển thị',
                datafield: 'visible',
                cellsalign: 'center',
                align: 'center',
                width: 150
            },{
                text: 'Ngày tạo',
                datafield: 'create_date',
                cellsalign: 'center',
                align: 'center',
                width: 150
            }, {
                text: 'Người tạo',
                datafield: 'creator',
                cellsalign: 'center',
                align: 'center',
                width: 100
            }, {
                text: 'Ngày chỉnh sửa',
                datafield: 'modify_date',
                cellsalign: 'center',
                align: 'center',
                width: 150
            }, {
                text: 'Người chỉnh sửa',
                datafield: 'modifier',
                cellsalign: 'center',
                align: 'center',
                width: 150
            }
            ]
        });  
        
    } //end 
     function get_writer() {
		var getselectedrowindexes = $("#idwriter").jqxGrid('getselectedrowindexes');
		var writer_id = '';
		if (getselectedrowindexes.length > 0) {
			var selectedRowData = $("#idwriter").jqxGrid('getrowdata',
					getselectedrowindexes[0]);
			writer_id = selectedRowData["writer_id"];
		}
		return writer_id;
	}
      $("#btnexport").click(function() {
        $("#idwriter").jqxGrid('exportdata', 'xls', 'Writer');
    });
    
   	$("#btnload").click(function() {
		var writer_id = get_writer();
		if (writer_id == null || writer_id == '') {		  
            showdialog("dialogmanual",2,"Xin chọn dòng","","");			
			return;
		}
		writer_id = 'VIEW_' + writer_id;
		window.location.href = "admin/Writer/Writernew.html?writer_id=" + writer_id;
	});
   $("#btncreate").click(function() {    	
	   	var url = "admin/Writer/Writernew.html";		
    	window.location.href=url;
   });  	
	$("#btnupdate").click(function() {
		var writer_id = get_writer();
		if (writer_id == null || writer_id == '') {		  
			showdialog('dialogmanual',0,'Xin chọn dòng','','');			
			return;
		}
		writer_id = 'UPDATE_' + writer_id;
		window.location.href = "admin/Writer/Writernew.html?writer_id=" + writer_id;
	});  
	$("#btnremove").click(function() {
   	    var c = get_munti_selected();
		if (c == null || c == '') {
			showdialog('dialogmanual',0,'Xin chọn dòng','','');
		   return;
		}
		showdialogconfirmfunc2("dialogmanual","Bạn có muốn xóa ?",function(){
			 var pdata = {'str_writer' : c};
			 Return_get("WriterController","delete_writer",pdata,"GET",function(data) {
					if (data != null) {	
						unblockbg();
						var error = parseInt(data.result);	
						if (error == 0) {
							exec_load_data();
							showdialog('dialogmanual',0,'Xóa thành công','','');
						} else {									   
							showdialog('dialogmanual',0,'Xóa thất bại','','');
										
						}
					}
					else{
						
						showdialog('dialogmanual',0,'Xóa thành công','','');
					}
            });
		},function(){
			$("#dialogmanual").dialog('close');
		});
	});
   
    $("#btnpublish").click(function() {
    	var c = get_munti_selected();
		if (c == null || c == '') {
			showdialog('dialogmanual',0,'Xin chọn dòng','','');		
			return;
		}
        
		if (confirm('Bạn có muốn đăng lên')){
	       publish_or_unpublish(c,'yes');
	    }	
});  
 $("#btnunpublish").click(function() {
	 
	 var c = get_munti_selected();
		if (c == null || c == '') {
			showdialog('dialogmanual',0,'Xin chọn dòng','','');		
			return;
		}
     
		if (confirm('Bạn có muốn gỡ xuống')){
	       publish_or_unpublish(c,'no');
	    }	
	
});  
       
function publish_or_unpublish(str_selected,ispublish){
	
     if (str_selected != null) {
	           blockbg();
	           var pdata = {'str_writer' : str_selected, 'publish':ispublish,'lang':lang};
	           Return_get("WriterController","publish_writer",pdata,"GET",function(data) {
								if (data != null) {	
									unblockbg();
									var error = parseInt(data.result);
									
									if (error == 0) {
									   exec_load_data();
									   showdialog('dialogmanual',0,'Đăng lên/Gỡ xuống thành công','','');
                                       
										
									} else {
										showdialog('dialogmanual',0,'Đăng lên/Gỡ xuống thất bại','','');						
    								}
								} 
								else {								    	
									showdialog('dialogmanual',0,'Đăng lên/Gỡ xuống thất bại','','');										
								}
	                       });
					}
}         
function get_munti_selected(){
        var c = '';
		var rows = $("#idwriter").jqxGrid('selectedrowindexes');
        if(rows.length>0){
    		for ( var m = 0; m < rows.length; m++) {
    			var row = $("#idwriter").jqxGrid('getrowdata', rows[m]);
    			c += "'" + row["writer_id"] + "'" + ",";
    		}
		c = c.substring(0, c.length - 1);
        }else{
        	showdialog('dialogmanual',0,'Xin chọn dòng','','');
            return;
        }
		return c;
  }  
});
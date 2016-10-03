
$(function() {
var list_contact=[];
	var source = null;

	 exe_load_header(function(output) {
	   if(output==true){
	      
	       
			load_form_grid();
			exec_load_data();
	   }
       
	 });
 
  function exec_load_data(){
		blockbg();
		var pdata = {'option':'','value':''};	
		load_data(function(output){
			if(output==true){
				unblockbg();
				source.localdata=list_contact;
        	
                $("#idcontact").jqxGrid('updatebounddata');
         	      $('#idcontact').jqxGrid('clearselection');
			}
		},pdata);
	}
	function load_data(callback,pdata) {		
		list_contact= [];		 
		Return_get("ContactController", "get_list_contact", pdata, 'GET', function(data) {		
			if (data != null) {
				list_contact = data;
				callback(true);
			}else
            {
                	unblockbg();
            }
		});		       
	}
    
     
     function load_form_grid() {
        source = {
            localdata: list_contact,
            datatype: "array",

            datafields: [{
                name: 'contactid',
                type: 'string'
            }, {
                name: 'title',
                type: 'string'
            }, {
                name: 'email',
                type: 'string'
            }, {
                name: 'fullname',
                type: 'string'
            },{
                name: 'phone',
                type: 'string'
            }, {
                name: 'status',
                type: 'string'
            }]

        };
        ///end source
        var dataAdapter = new $.jqx.dataAdapter(source);
        $("#idcontact").jqxGrid({
            width: get_width(),
            source: dataAdapter,
            columnsresize: true,
            pageable: true,
            height: get_height(),            
            selectionmode: 'checkbox',
            columns: [{
                text: 'Mã',
                datafield: 'contactid',
                cellsalign: 'center',
                align: 'center',
                width: 150
            }, {
                text: 'Title',
                datafield: 'title',
                cellsalign: 'left',
                align: 'center',
                width: 150
            }, {
                text: 'Họ tên',
                datafield: 'fullname',
                cellsalign: 'left',
                align: 'center',
                width: 157
            },{
                text: 'Điện thoại',
                datafield: 'phone',
                cellsalign: 'center',
                align: 'center',
                width: 150
            }, {
                text: 'Trạng thái',
                datafield: 'status',
                cellsalign: 'center',
                align: 'center',
                width: 100
            }
            ]
        });
       
    } //end 
	function get_contact() {
		var getselectedrowindexes = $("#idcontact").jqxGrid('getselectedrowindexes');
		var contact_id = '';
		if (getselectedrowindexes.length > 0) {
			var selectedRowData = $("#idcontact").jqxGrid('getrowdata',
					getselectedrowindexes[0]);
			contact_id = selectedRowData["contactid"];
		}
		return contact_id;
	}
    
    
   	$("#btnprocessing").click(function() {
		var contact_id = get_contact();
		if (contact_id == null || contact_id == '') {		  
			showdialog("dialogmanual",0, "Xin chọn dòng!",'','');
			return;
		}
		window.location.href = ReturnHostingCMS()+"Contact/sendcontact.html?contactid=" + contact_id;
	});
     $("#btncancel").click(function(){
		 var contact_id = get_munti_selected();
		 if (contact_id == null || contact_id == '') {		  
			showdialog("dialogmanual", "Chọn liên lạc muốn xóa!");
			return;
		}
		showdialogconfirmfunc('dialogmanual','Bạn có muốn xóa?',function(){			
	       if (contact_id != null) {
	          
	           blockbg();
	           var pdata = {'str_contact' : contact_id};
	           Return_get("ContactController","delete_contact",pdata,"GET",function(data) {
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
									
									showdialog('dialogmanual',0,'Xóa thất bại','','');
								}
	                       });
						}
		});
	 })
	function get_munti_selected(){
			var c = '';
			var rows = $("#idcontact").jqxGrid('selectedrowindexes');
			if(rows.length>0){
				for ( var m = 0; m < rows.length; m++) {
					var row = $("#idcontact").jqxGrid('getrowdata', rows[m]);
					c += "'" + row["contactid"] + "'" + ",";
				}
			c = c.substring(0, c.length - 1);
			}else{
			   showdialog("dialogmanual",1,"","ad_dialog","r10" );
				return;
			}
			return c;
	  }  
});
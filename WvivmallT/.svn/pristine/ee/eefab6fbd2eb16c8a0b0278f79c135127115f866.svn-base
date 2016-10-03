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
		Return_get("UserController", "get_list_user", pdata, 'GET', function(
				data) {
			if (data != null) {
				list_category = data;
				callback(true);
			} else {
				unblockbg();
			}
		});
	}

	function load_form_grid() {
		source = {
			localdata : list_category,
			datatype : "array",
			datafields : [ {
				name : 'user_id',
				type : 'string'
			}, {
				name : 'user_name',
				type : 'string'
			}, {
				name : 'creator',
				type : 'string'
			}, {
				name : 'create_date',
				type : 'string'
			}, {
				name : 'cate_type',
				type : 'string'
			} ]

		};

		// /end source
		var dataAdapter = new $.jqx.dataAdapter(source);
		$("#idcategory").jqxGrid({
			width : get_width(),
			source : dataAdapter,
			columnsresize : true,
			pageable : true,
			height : get_height(),
			selectionmode : 'checkbox',
			columns : [ {
				text : 'Mã',
				datafield : 'user_id',
				cellsalign : 'center',
				align : 'center',
				width : 250
			}, {
				text : 'Tên tài khoản',
				datafield : 'user_name',
				cellsalign : 'left',
				align : 'center',
				width : 350
			}, {
				text : 'Người tạo',
				datafield : 'creator',
				cellsalign : 'left',
				align : 'center',
				width : 150
			}, {
				text : 'Ngày tạo',
				datafield : 'create_date',
				cellsalign : 'left',
				align : 'center',
				width : 250
			}, {
				text : 'Danh mục',
				datafield : 'cate_type',
				cellsalign : 'left',
				align : 'center',
				width : 260
			} ]
		});

	}

	$("#btnload").click(function() {
		exec_load_data();
	});
	$("#btncreate").click(function() {
		type = 'A';
		// set value
		$("#iduser").prop('disabled', false);
		$("#iduser").val('');
		$("#idname").val('');
		//
		$("#dlgcreate").css('display', 'block');
		load_cate_type_id();
		$('#dlgcreate').dialog({
			autoOpen : false,
			title : 'Người dùng mới',
			width : 400,
			height : 325,
			buttons : [ {
				text : 'OK',
				iconCls : 'icon-ok',
				handler : function() {
					exec_cate(function(out) {
						if (out) {
							exec_load_data();
							$(dlgcreate).dialog('close');
						}

					});
				}
			}, {
				text : 'Cancel',
				iconCls : 'icon-cancel',
				handler : function() {
					$("#dlgcreate").dialog('close');
				}
			} ]
		});
		$(dlgcreate).dialog('open');

	});

	function exec_cate(callback) {
		var user = $("#iduser").val();
		var pass = $("#idpass").val();
		var str_pro = get_cate_type_insert();

		if (user == null || user == '') {
			showdialog('dialogmanual', 0, 'Nhập tên tài khoản', '', '');
			callback(false);
			return;
		}

		if (pass == null || pass == '') {
			showdialog('dialogmanual', 0, 'Nhập mật khẩu', '', '');
			callback(false);
			return;
		}
		blockbg();
		var pdata = {
			'type' : type,
			'user' : user,
			'pass' : pass,
			'cate_type' : str_pro,
		};
		Return_get("UserController","insert_user",pdata,"GET",function(data) {
					if (data != null) {
						unblockbg();
						var error = parseInt(data._error);

						if (error == 0) {
							$("#iduser").val('');
							$("#idpass").val('');
							showdialog('dialogmanual', 0, 'Nhập thành công', '',
									'');
							callback(true);
						} else if (error == 2) {
							showdialog('dialogmanual', 0, 'Danh mục '
									+ data.mes + ' đã tồn tại', '', '');
							callback(false);
						} else if (error == -2) {
							showdialog('dialogmanual', 0, 'Trùng tên đăng nhập', '', '');
							callback(false);
						} else if (error == -3) {
							showdialog('dialogmanual', 0, 'Lấy mã người dùng thất bại', '', '');
							callback(false);
						} else {
							showdialog('dialogmanual', 0, 'Nhập thất bại', '', '');
							callback(false);
						}

					} else {
						showdialog('dialogmanual', 0, 'Nhập thất bại', '', '');
						callback(true);
					}
				});

	}
	var idcurrent = '';
	function get_user() {
		var getselectedrowindexes = $("#idcategory").jqxGrid(
				'getselectedrowindexes');
		var user = '';
		if (getselectedrowindexes.length > 0) {
			var selectedRowData = $("#idcategory").jqxGrid('getrowdata',
					getselectedrowindexes[0]);
			user = selectedRowData["user_id"];
		}
		return user;
	}
	function load_info_user(id, callback) {
		var pdata = {
			'userid' : id
		};
		Return_get("UserController", "get_info_user", pdata, 'GET', function(
				data) {
			if (data != null) {
				$("#iduser").prop('disabled', true);
				$("#iduser").val(data.user_name);
				$("#idname").val('');
				callback(true);
			}
		});
	}
	$("#btnupdate").click(function() {
		var id = get_user();

		if (id == null || id == '') {
			showdialog('dialogmanual', 0, 'Xin chọn dòng', '', '');
			return;
		}
		type = 'E';
		idcurrent = id;
		load_info_user(id, function(out) {
			$("#dlgcreate").css('display', 'block');
			load_cate_type_id();
			$('#dlgcreate').dialog({
				autoOpen : false,
				title : 'Update User',
				width : 400,
				height : 325,
				buttons : [ {
					text : 'OK',
					iconCls : 'icon-ok',
					handler : function() {
						exec_update_cate(function(out) {
							if (out) {
								exec_load_data();
								$("#idtype").prop('disabled', false);
								$(dlgcreate).dialog('close');
							}

						});
					}
				}, {
					text : 'Hủy',
					iconCls : 'icon-cancel',
					handler : function() {
						$("#dlgcreate").dialog('close');
					}
				} ]
			});
			$(dlgcreate).dialog('open');

		});
	});

	function exec_update_cate(callback) {
		var user = $("#iduser").val();
		var pass = $("#idpass").val();
		var str_pro_type = get_cate_type_insert();

		if (pass == null || pass == '') {
			showdialog('dialogmanual', 0, 'Nhập mật khẩu', '', '');
			callback(false);
			return;
		}
		blockbg();
		var pdata = {
			'type' : type,
			'user' : user,
			'pass' : pass,
			'cate_type' : str_pro_type
		};
		Return_get("UserController","insert_user",pdata,"GET",function(data) {
					if (data != null) {
						unblockbg();
						var error = parseInt(data._error);

						if (error == 0) {
							$("#iduser").val('');
							$("#idpass").val('');
							showdialog('dialogmanual', 0, 'Cập nhật thành công', '',
									'');
							callback(true);
						} else if (error == 2) {
							showdialog('dialogmanual', 0, 'Danh mục '+ data.mes + ' đã tồn tại', '', '');
							callback(false);
						} else if (error == -2) {
							showdialog('dialogmanual', 0, 'Người dùng không tồn tại','', '');
							callback(false);
						} else {
							showdialog('dialogmanual', 0, 'Cập nhật thất bại', '', '');
							callback(false);
						}

					} else {
						showdialog('dialogmanual', 0, 'Cập nhật thất bại', '', '');
						callback(true);
					}
				});

	}
	$("#btnremove")
			.click(
					function() {

						var id = get_munti_selected();

						if (id == null || id == '') {
							showdialog('dialogmanual', 0, 'Xin chọn dòng', '', '');
							return;
						}
						showdialogconfirmfunc(
								'dialogmanual',
								'Bạn có muốn xóa ?',
								function() {
									if (id != null) {

										blockbg();
										var pdata = {
											'str' : id
										};
										Return_get("UserController","remove_user",pdata,"GET",function(data) {
													if (data != null) {
														unblockbg();
														var error = parseInt(data.result);
														if (error == 0) {
															exec_load_data();
															showdialog('dialogmanual',0,'Xóa thành công','', '');
														} else {
															showdialog('dialogmanual',0,'Xóa thất bại','', '');
														}
													} else {
														showdialog('dialogmanual',0,'Xóa thất bại','', '');
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
				c += "'" + row["user_id"] + "'" + ",";
			}
			c = c.substring(0, c.length - 1);
		}
		return c;
	}

	function get_cate_type_insert() {

		var str_product_tp = '';
		$('input[name="producttype"]:checked').each(function() {
			str_product_tp += $(this).val() + ',';
		});
		str_product_tp = str_product_tp.substring(0, str_product_tp.length - 1);
		return str_product_tp;
	}

	function load_cate_type_id() {
		$("#cate_type").html("");
		var user_id = get_user();
		
		
		
		Return_get("ProductController","get_list_category","","GET",function(data) {
					if (data != null) {
							for (var i = 0; i < data.length; i++) {
								var obj = data[i];
								var input = '<input type="checkbox" name="producttype" id="chkproducttype_'+obj.producttype+'" value="'
										+ obj.producttype
										+ '"> &nbsp'
										+ obj.producttypename + '</br>';
								$("#cate_type").append(input);
							}
							if(user_id!=null && user_id!=''){
								get_user_cate_status(function(output,data1){
									if(output=true){
										var arr_cate_type = data1.cate_type.split(",");
										for(var j=0;j<arr_cate_type.length;j++){
											$("#chkproducttype_"+arr_cate_type[j]).prop("checked",true);
										}
										callback(true);		
									}
								},user_id);
							}	
						}
				})
	}

	function get_user_cate_status(callback,user_id) {
		
		pdata = {
			'userid' : user_id,
		}
		Return_get("UserController", "get_info_user", pdata, "GET", function(data) {
			if (data != null) {
				callback(true,data);
			}
			else
				callback(false,"");
		})
		
	}
	
});
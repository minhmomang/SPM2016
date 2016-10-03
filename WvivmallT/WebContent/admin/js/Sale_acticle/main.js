$(function() {
    var type = 'A';
    var list_saleacticle = [];
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
                source.localdata = list_saleacticle;
                $("#idsaleacticle").jqxGrid('updatebounddata');
                $('#idsaleacticle').jqxGrid('clearselection');

            }
        }, '');
    }

    function load_data(callback, pdata) {
        list_saleacticle = [];
        Return_get("Sale_acticleController", "get_list_saleacticle", pdata, 'GET', function(data) {
            if (data != null) {
                list_saleacticle = data;
                callback(true);
            } else {
                unblockbg();
            }
        });
    }



    function load_form_grid() {
        source = {
            localdata: list_saleacticle,
            datatype: "array",
            datafields: [{
                name: 'id',
                type: 'string'
            }, {
                name: 'title',
                type: 'string'
            }, {
                name: 'sale_acticle_status',
                type: 'string'
            }, {
                name: 'creator',
                type: 'string'
            }, {
                name: 'create_date',
                type: 'string'
            }, {
                name: 'modifier',
                type: 'string'
            }, {
                name: 'modify_date',
                type: 'string'
            }]

        };
        ///end source
        var dataAdapter = new $.jqx.dataAdapter(source);
        $("#idsaleacticle").jqxGrid({
            width: get_width(),
            source: dataAdapter,
            columnsresize: true,
            pageable: true,
            height: get_height(),
            selectionmode: 'checkbox',
            columns: [{
                text: 'Mã',
                datafield: 'id',
                cellsalign: 'center',
                align: 'center',
                width: 100
            }, {
                text: 'Tiêu đề',
                datafield: 'title',
                cellsalign: 'left',
                align: 'center',
                width: 300
            },{
                text: 'Trạng thái',
                datafield: 'sale_acticle_status',
                cellsalign: 'left',
                align: 'center',
                width: 150
            }, {
                text: 'Người tạo',
                datafield: 'creator',
                cellsalign: 'left',
                align: 'center',
                width: 70
            },, {
                text: 'Ngày tạo',
                datafield: 'create_date',
                cellsalign: 'left',
                align: 'center',
                width: 100
            },, {
                text: 'Người chỉnh sửa',
                datafield: 'modifier',
                cellsalign: 'left',
                align: 'center',
                width: 120
            },, {
                text: 'Ngày chỉnh sửa',
                datafield: 'modify_date',
                cellsalign: 'left',
                align: 'center',
                width: 120
            }]
        });

    }

    function get_cate_product() {
        var getselectedrowindexes = $("#idsaleacticle").jqxGrid('getselectedrowindexes');
        var product_id = '';
        if (getselectedrowindexes.length > 0) {
            var selectedRowData = $("#idsaleacticle").jqxGrid('getrowdata',
                getselectedrowindexes[0]);
            product_id = selectedRowData["id"];
        }
        return product_id;
    }


    $("#btnload").click(function() {
		exec_load_data();
    });

    $("#btncreate").click(function() {
    		window.location.href="admin/Sale_acticle/saleaticle_new.html";
       
    });

    var idcurrent = '';

    $("#btnupdate").click(function() {
        var id = get_cate_product();
       
        if (id == null || id == '') {
            showdialog('dialogmanual', 0, 'Xin chọn dòng', '', '');
            return;
        }
        window.location.href="admin/Sale_acticle/saleaticle_new.html?sale_acticle_id="+id;
    });

   
    $("#btnremove").click(function() {
    	
        var id = get_munti_selected();
      
        if (id == null || id == '') {
            showdialog('dialogmanual', 0, 'Xin chọn dòng', '', '');
            return;
        }
        showdialogconfirmfunc('dialogmanual', 'Bạn có muốn xóa?', function() {
            if (id != null) {

                blockbg();
                var pdata = {
                    'str': id
                };
                Return_get("Sale_acticleController", "delete_sale", pdata, "GET", function(data) {
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
    	
        var id = get_munti_selected();
      
        if (id == null || id == '') {
            showdialog('dialogmanual', 0, 'Xin chọn dòng', '', '');
            return;
        }
        showdialogconfirmfunc('dialogmanual', 'Bạn có muốn đăng lên ?', function() {
            if (id != null) {

                blockbg();
                var pdata = {
                    'str': id
                };
                Return_get("Sale_acticleController", "visible1_sale", pdata, "GET", function(data) {
                    if (data != null) {
                        unblockbg();
                        var error = parseInt(data.result);
                        if (error == 0) {
                            exec_load_data();
                            showdialog('dialogmanual', 0, 'Đăng thành công', '', '');
                        } else {
                            showdialog('dialogmanual', 0, 'Đăng thất bại', '', '');

                        }
                    } else {

                        showdialog('dialogmanual', 0, 'Đăng thất bại', '', '');
                    }
                });
            }
        });
    });
    
    $("#btnunvisible").click(function() {
    	
        var id = get_munti_selected();
      
        if (id == null || id == '') {
            showdialog('dialogmanual', 0, 'Xin chọn dòng', '', '');
            return;
        }
        showdialogconfirmfunc('dialogmanual', 'Bạn có muốn gỡ xuống?', function() {
            if (id != null) {

                blockbg();
                var pdata = {
                    'str': id
                };
                Return_get("Sale_acticleController", "visible0_sale", pdata, "GET", function(data) {
                    if (data != null) {
                        unblockbg();
                        var error = parseInt(data.result);
                        if (error == 0) {
                            exec_load_data();
                            showdialog('dialogmanual', 0, 'Gỡ thành công', '', '');
                        } else {
                            showdialog('dialogmanual', 0, 'Gỡ thất bại', '', '');

                        }
                    } else {

                        showdialog('dialogmanual', 0, 'Gỡ thất bại', '', '');
                    }
                });
            }
        });
    });
    
    
    
    
    function get_munti_selected() {
        var c = '';
        var rows = $("#idsaleacticle").jqxGrid('selectedrowindexes');
        if (rows.length > 0) {
            for (var m = 0; m < rows.length; m++) {
                var row = $("#idsaleacticle").jqxGrid('getrowdata', rows[m]);
                c += "'" + row["id"] + "'" + ",";
            }
            c = c.substring(0, c.length - 1);
        }
        return c;
    }
    
  


});
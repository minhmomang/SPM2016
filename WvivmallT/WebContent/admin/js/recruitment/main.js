$(function() {
    var list = [];
    var source = null;

    exe_load_header(function(output) {
        if (output == true) {
           
            load_form_grid();
            exec_load_data();
        }

    });

    function exec_load_data() {
        blockbg();
        var pdata = {
            'option': '',
            'value': ''
        };
        load_data(function(output) {
            if (output == true) {
                unblockbg();
                source.localdata = list;

                $("#idrec").jqxGrid('updatebounddata');
                $('#idrec').jqxGrid('clearselection');
            }
        }, pdata);
    }

    function load_data(callback, pdata) {
        list = [];
        Return_get("RecController", "get_list_", pdata, 'GET', function(data) {
            if (data != null) {
                list = data;
                callback(true);
            }
        });
    }

   

    var self = this;
    var pagerrenderer = function() {
        var element = $("<div style='margin-left:0px; margin-top: 5px; '></div>");
        var datainfo = $("#idrec").jqxGrid('getdatainformation');
        var paginginfo = datainfo.paginginformation;

        var leftButton = $("<div style='padding: 0px; float: left;'><div style='margin-left: 9px; width: 16px; height: 16px;'></div></div>");
        leftButton.find('div').addClass('jqx-icon-arrow-left');
        leftButton.width(36);
        leftButton.jqxButton({
            theme: theme
        });

        var rightButton = $("<div style='padding: 0px; margin: 0px 3px; float: left;'><div style='margin-left: 9px; width: 16px; height: 16px;'></div></div>");
        rightButton.find('div').addClass('jqx-icon-arrow-right');
        rightButton.width(36);
        rightButton.jqxButton({
            theme: theme
        });

        leftButton.appendTo(element);
        rightButton.appendTo(element);

        var label = $("<div style='font-size: 11px; margin: 2px 3px; font-weight: bold; float: left;'></div>");
        label.text("1-" + paginginfo.pagesize + ' of ' + datainfo.rowscount);
        label.appendTo(element);
        self.label = label;
        // update buttons states.
        var handleStates = function(event, button, className, add) {
            button.on(event, function() {
                if (add == true) {
                    button.find('div').addClass(className);
                } else
                    button.find('div').removeClass(className);
            });
        };

        if (theme != '') {
            handleStates('mousedown', rightButton,
                'jqx-icon-arrow-right-selected-' + theme, true);
            handleStates('mouseup', rightButton,
                'jqx-icon-arrow-right-selected-' + theme, false);
            handleStates('mousedown', leftButton,
                'jqx-icon-arrow-left-selected-' + theme, true);
            handleStates('mouseup', leftButton, 'jqx-icon-arrow-left-selected-' + theme, false);

            handleStates('mouseenter', rightButton,
                'jqx-icon-arrow-right-hover-' + theme, true);
            handleStates('mouseleave', rightButton,
                'jqx-icon-arrow-right-hover-' + theme, false);
            handleStates('mouseenter', leftButton, 'jqx-icon-arrow-left-hover-' + theme, true);
            handleStates('mouseleave', leftButton, 'jqx-icon-arrow-left-hover-' + theme, false);
        }

        rightButton.click(function() {
            $("#idrec").jqxGrid('gotonextpage');
        });

        leftButton.click(function() {
            $("#idrec").jqxGrid('gotoprevpage');
        });

        return element;
    }; //end parender
    function load_form_grid() {
        source = {
            localdata: list,
            datatype: "array",

            datafields: [{
                name: 'id',
                type: 'string'
            }, {
                name: 'pos',
                type: 'string'
            }, {
                name: 'qt',
                type: 'string'
            }, {
                name: 'location',
                type: 'string'
            }]

        };
        ///end source
        var dataAdapter = new $.jqx.dataAdapter(source);
        $("#idrec").jqxGrid({
            width: get_width(),
            source: dataAdapter,
            columnsresize: true,
            pageable: true,
            height: get_height(),
            pagerrenderer: pagerrenderer,
            selectionmode: 'checkbox',
            columns: [{
                text: 'Mã',
                datafield: 'id',
                width: 150
            }, {
                text: 'Vị trí',
                datafield: 'pos',
                width: 500
            }, {
                text: 'Số lượng',
                datafield: 'qt',
                width: 157
            }, {
                text: 'Nơi làm việc',
                datafield: 'location',
                width: 157
            }]
        });
 
    } //end 
    function get_id() {
        var getselectedrowindexes = $("#idrec").jqxGrid('getselectedrowindexes');
        var id = '';
        if (getselectedrowindexes.length > 0) {
            var selectedRowData = $("#idrec").jqxGrid('getrowdata',
                getselectedrowindexes[0]);
            id = selectedRowData["id"];
        }
        return id;
    }
    $("#btnexport").click(function() {
        $("#idrec").jqxGrid('exportdata', 'xls', 'Recruitment');
    });

    $("#btnload").click(function() {
        var id = get_id();
        if (id == null || id == '') {
            $opso_get(function(output,text){
                if(output==true){showdialog("dialogmanual", text);}},"ad_dialog","r30");
            return;
        }
        id = 'VIEW_' + id;
        window.location.href = ReturnHosing_apache()+"admin/recruitment/recruitmentnew.html?id=" + id;
    });
    $("#btncreate").click(function() {

        var url = window.location.href = ReturnHosing_apache()+"admin/recruitment/recruitmentnew.html";
        window.location.href=url;

    });
    $("#btnupdate").click(function() {

        var id = get_id();
        if (id == null || id == '') {
                        $opso_get(function(output,text){
                if(output==true){showdialog("dialogmanual", text);}},"ad_dialog","r31");
            return;
        }
        id = 'UPDATE_' + id;
        window.location.href =  window.location.href = ReturnHosing_apache()+"admin/recruitment/recruitmentnew.html?id=" + id;
    });
    $("#btnremove").click(function() {
        var id = get_id();
        if (id == null || id == '') {
        	showdialog('dialogmanual', 0, 'Xin chọn dòng', '', '');
        	return;
        }
        var pdata = {
                'id': id
            };
            Return_get("RecController", "delete_rec", pdata, "GET", function(data) {
            	 unblockbg();
                if (data != null) {               
                    var error = parseInt(data.result);
                    if (error == 0) {
                        exec_load_data();
                        showdialog('dialogmanual', 0, 'Xóa thành công', '', '');    
                    }
                } else {
                	showdialog('dialogmanual', 0, 'Xóa thất bại', '', '');
                	return;
                }
            });
    });
   
});
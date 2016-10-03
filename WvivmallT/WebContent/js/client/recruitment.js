$(function(){
	exe_load_header(function(output){
		if(output==true){
			get_list();
		}	
	});
    function get_list(){
		Return_get("RecController","get_list",'',"GET",function(data){
			if(data!=null){
					load_str(function(output,str){
						if(output==true){
							$.map(data,function(item){
								var html = str;
								html = html.replace('@class',item.order%2==0?'success':'info');
								html = html.replace('@qt',item.order);
								html = html.replace('@pos',item.pos);
								html = html.replace('@num',item.qt);
								html = html.replace('@id',item.id);
								$("#content").append(html).show();
							});
						}
					});
					
			}			
		});
	}
   function load_str(callback){
		var url = ReturnHosing_apache()+'txt/rec.txt';
		$.get(url,function(data){
			callback(true,data);
		});
   }
});//end document
function viewpage(src){
	var id = $(src).attr('id');
	var arr = id.split('_');
	id = arr[1];
	PopupCenter('job.html?id='+id,'xtf','900','800'); 
}
function PopupCenter(url, title, w, h) {
    // Fixes dual-screen position                         Most browsers      Firefox
    var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
    var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;

    width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
    height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

    var left = ((width / 2) - (w / 2)) + dualScreenLeft;
    var top = ((height / 2) - (h / 2)) + dualScreenTop;
    var newWindow = window.open(url, title, 'scrollbars=yes, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);

    // Puts focus on the newWindow
    if (window.focus) {
        newWindow.focus();
    }
}
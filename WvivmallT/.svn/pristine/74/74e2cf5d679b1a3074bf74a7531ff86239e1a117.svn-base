$(function(){
	exe_load_header_news(function(out){
		load_social();
		load_writer();
	});
	function load_writer(){
		var writerid= getUrlParameter('id');
		var lang = get_lang_current();
		var pdata = {
			'Writerid':writerid,
			'lang':lang
		};
		
		Return_get("WriterController", "get_writer_by_id",pdata, 'GET', function(data) {
			if(data!=null){
				$("#title").html(data.title);
				$("#content").html(data.content);
			}
		});
	}
	
});
$(function(){
	
	exe_load_header_news(function(out){		
		load_social();
		load_read_more();
		
	});
	function load_read_more(){
		var lang = get_lang_current();
		var cate = getUrlParameter('cate');
		var pdata = {
		  		'lang':lang,
		  		'cate':cate
		};
		Return_get("WriterController","get_read_more_category",pdata, 'GET', function(data) {
			
			if(data!=null){
				load_str_more_writer(function(out,str){
					$.map(data,function(item){
						var html = str;
						html = html.replace('@title',item.title);							
						var urlimg = ReturnHosing_tomcat()+'upload/Writer/'+item.image;							
						html = html.replace('@hrefimg',urlimg);						
						html = html.replace('@desc',item.description);
						var urlhref = 'writerdtl.html?id='+item.writer_id;
						html = html.replace('@idhref',urlhref).replace('@idhref',urlhref).replace('@idhref',urlhref);						
						$("#content_more").append(html).show();								
					});
				});	
			}
		});
	}
	function load_str_more_writer(callback){
		var url = ReturnHosing_apache()+'txt/box_more_writer.txt';
		$.get(url,function(data){
			callback(true,data);
		});
    }
});
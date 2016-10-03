$(function(){
	exe_load_header_news(function(output){
		if(output==true){	
			var id = getUrlParameter('id');
			if(id==null || id== ''|| id==undefined){
				load_news();	
			}
			else{
				get_news_via_cate(id);
			}						
		}
	});
	function load_news(){
		
		var lang = get_lang_current();
		
		var pdata = {
		  		'lang':lang
		};
		Return_get("WriterController","get_news",pdata, 'GET', function(data) {
			if(data!=null){				
				load_txt_news(function(out,str1){
					$.map(data,function(item){
						var html = str1;	
						
						if (item.image!='') {
							var urlimg = ReturnHosing_tomcat()+'upload/Writer/'+item.image;
							html = html.replace('@img',urlimg);
						}
						else{
							var urlimg = ReturnHosing_tomcat()+'upload/Writer/noimage.jpg';
							html = html.replace('@img',urlimg);
						}
						html = html.replace('@title',item.title);	
						html = html.replace('@create_date',item.create_date);
						html = html.replace('@description',item.description);
						html = html.replace('@writer_id',item.writer_id);
						$("#loadnews").append(html).show();								
					});
				});			
				
			}
		});
	}
	function get_news_via_cate(id){
		
		var lang = get_lang_current();
		var pdata = {
		  		'lang':lang,
		  		'cate':id
		};
		Return_get("WriterController","get_news_via_cate",pdata, 'GET', function(data) {
			if(data!=null){				
				load_txt_news(function(out,str1){
					$.map(data,function(item){
						var html = str1;	
						
						if (item.image!='') {
							var urlimg = ReturnHosing_tomcat()+'upload/Writer/'+item.image;
							html = html.replace('@img',urlimg);
						}
						else{
							var urlimg = ReturnHosing_tomcat()+'upload/Writer/noimage.jpg';
							html = html.replace('@img',urlimg);
						}
						html = html.replace('@title',item.title);	
						html = html.replace('@create_date',item.create_date);
						html = html.replace('@description',item.description);
						html = html.replace('@writer_id',item.writer_id);
						$("#loadnews").append(html).show();								
					});
				});			
				
			}
		});
	}
	function load_txt_news(callback){
		var url = ReturnHosing_apache()+'txt/news.txt';
		$.get(url,function(data){
			callback(true,data);
		});
    }
});
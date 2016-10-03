$(function(){
	var _ismobile = ismobile();
	exe_load_header_news(function(output){
		if(output==true){	
			if(_ismobile){
				$("#leftnews").css('display','none');
			}
				var id = getUrlParameter('id');
				load_content_news(id);
								
		}
	});
	function load_content_news(id){
		
		var lang = get_lang_current();
		
		var pdata = {
		  		'lang':lang,
		  		'id':id
		};
		Return_get("WriterController","get_news_id",pdata, 'GET', function(data) {
			if(data!=null){				
				load_txt_newsdetail(function(out,str1){
					
						var html = str1;	
						
						if (data.image!='') {
							var urlimg = ReturnHosing_tomcat()+'upload/Writer/'+data.image;
							html = html.replace('@img',urlimg);
						}
						else{
							var urlimg = ReturnHosing_tomcat()+'upload/Writer/noimage.jpg';
							html = html.replace('@img',urlimg);
						}
						html = html.replace('@title',data.title);	
						html = html.replace('@create_date',data.create_date);
						html = html.replace('@content',data.content);
						html = html.replace('@description',data.description);
					
						$("#contentnews").append(html).show();								
					
				});	
							
			}
		});
	}
	function load_txt_newsdetail(callback){
		var url = ReturnHosing_apache()+'txt/contentOfnews.txt';
		$.get(url,function(data){
			callback(true,data);
		});
    }
	
});
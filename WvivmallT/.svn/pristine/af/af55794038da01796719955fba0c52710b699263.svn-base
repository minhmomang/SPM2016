$(function(){
	exe_load_header_news(function(out){
		
		if(out==true){
			load_social();
			load_writer();	
		}		
	});
	function load_writer(){
	
		var lang = get_lang_current();
		
		var pdata = {
		  		'lang':lang
		};
		Return_get("WriterController","get_list_top_writer",pdata, 'GET', function(data) {
			if(data!=null){				
				load_str_writer(function(out,str){
					if(out==true){
						load_str_sub_writer(function(out,str1){
							$.map(data,function(item){
								var html = str;
								html = html.replace('@titlecate',item.categoryName);							
								var urlimg = ReturnHosing_tomcat()+'upload/Writer/'+item.list[0].image;
								html = html.replace('@idcate',item.categoryId).replace('@idcate',item.categoryId);
								html = html.replace('@hrefimg',urlimg);
								html = html.replace('@titlemain',item.list[0].title);	
								html = html.replace('@desc',item.list[0].description);
								var urlhref = 'writerdtl.html?id='+item.list[0].writer_id;
								html = html.replace('@idhref',urlhref).replace('@idhref',urlhref).replace('@idhref',urlhref);
								var content_sub = '';
								for(var i=1;i<item.list.length;i++){
									var html1 = str1;																	
									var urlimg = ReturnHosing_tomcat()+'upload/Writer/'+item.list[i].image;							
									html1 = html1.replace('@hrefimg',urlimg);
									html1 = html1.replace('@title',item.list[i].title);
									var urlhrefsub = 'writerdtl.html?id='+item.list[i].writer_id;
									html1 = html1.replace('@idhref',urlhrefsub).replace('@idhref',urlhrefsub);
									content_sub+=html1;
									
								}
								html = html.replace('@contentsub',content_sub);
								$("#content").append(html).show();								
							});
						});						
					}
				});
			}
		});
	}
	function load_str_writer(callback){
		var url = ReturnHosing_apache()+'txt/box_writer.txt';
		$.get(url,function(data){
			callback(true,data);
		});
    }
	function load_str_sub_writer(callback){
		var url = ReturnHosing_apache()+'txt/box_sub_writer.txt';
		$.get(url,function(data){
			callback(true,data);
		});
    }
	
});
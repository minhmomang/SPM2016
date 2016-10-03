$(document).ready(function(){
	
	load_writer_category(function(checkcall){
		
	});
	if(!ismobile()){ 
		$("#titlerelated").css('display','block');
		load_related_news();
	}
	
	function load_writer_category(callback){
		$("#category_leftnews").html("");
		Return_get("WriterCategoryController","get_list_product_category_manager","","GET",function(data){
			if(data!=null){
				for(var i=0;i<data.length;i++){
					var obj=data[i];
					var li = '<li><span class="glyphicon glyphicon-triangle-right" id='+obj.id+'></span><a href="news.html?id='+obj.id+'">'+ obj.name +'</a></li>';
				$("#category_leftnews").append(li);
				}
				callback(true);
			}
		})
	};
	
	function load_related_news(){
		
		Return_get("WriterCategoryController","get_related_news","","GET",function(data){
			load_text(function(out_put,str){
				if(out_put){
					if(data.length>0){
						for(var i=0;i<data.length;i++){
							var obj=data[i];
							
							// cut description by 9 words
							var desc = obj.description.split(" ");
							if(desc.length > 9){
								obj.description="";
								for(var j=0;j<9;j++){
									obj.description+= desc[j];
									obj.description+=" ";
								}
								obj.description+=" ..."
							}
							//
							var html=str;
							html=html.replace('@title',obj.title);
							html=html.replace('@description',obj.description);
							
							var urlimg ='';
							if(obj.image==null || obj.image==""){
								urlimg= ReturnHosing_tomcat()+'upload/Writer/noimage.jpg';
							}
							else{
								urlimg= ReturnHosing_tomcat()+'upload/Writer/'+obj.image;
							}
							html=html.replace('@urlimg',urlimg);
							html=html.replace('@href','contentOfnews.html?id='+obj.writer_id);
							$("#leftnews").append(html);
						}
					}
				}
			});
		})
		
	};
	function load_text(callback){
		var url = ReturnHosing_apache()+'txt/related_news.txt';
		$.get(url,function(data){
			callback(true,data);
		});
	}
	
});


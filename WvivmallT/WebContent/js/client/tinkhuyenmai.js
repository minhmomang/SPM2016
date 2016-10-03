/**
 * http://usejsdoc.org/
 */
$(function(){
	var _ismobile= ismobile();
	var url_image_feature=ReturnHosing_tomcat()+"upload/sale_acticle/";
    
	var homenews_li_hero='<li class="hero">'
               + '<a href="@hrefkhuyenmai">'
               +'<img class="lazy" width="575" height="325" src="@featureimage" alt="@desimage">'
               +'<h3>@title</h3>'
              +' <figure>@description</figure>   '
              + ' </a>'
           + '</li>';
           
     var  homenews_li= '<li>'
                +'<a href="@hrefkhuyenmai">'
                   + '<img class="lazy" width="230" height="130" src="@featureimage" alt="@desimage">'
                  + ' <h3>@title</h3>'
                  + ' <figure style="height: 38px;">@description</figure>'
                +'</a>'
            +'</li>';   
	exe_load_header(function(out){	
		if(_ismobile){
			$("#sliderelate").css('display','none');
			$(".wrap_cate").css('width','100%');
		}
		load_tinkhuyenmai();
	});
	
    
    function load_tinkhuyenmai(){
        Return_get("Sale_acticleController", "get_list_saleacticle_client", "", "GET", function(data) {
            if (data != null) {		
            	        append_li_herro_into_homenews(data[0]);
                             append_li_into_homenews(data);
            }
        });
    }
    
    
    function append_li_herro_into_homenews(dataelem){
        	var html = homenews_li_hero;
        	html = html.replace('@hrefkhuyenmai',ReturnHosing_apache()+"khuyenmai.html?khuyenmaiid="+dataelem.id);
            	html = html.replace('@featureimage',url_image_feature+""+dataelem.image);
                html = html.replace('@title',dataelem.title);
                html = html.replace('@desimage',dataelem.title);
                html = html.replace('@description',dataelem.description);
              	$(".homenews").append(html);  
    }
    
    function append_li_into_homenews(data){
        	for(var i=1;i<data.length;i++){
            				var html = homenews_li;
            				html = html.replace('@hrefkhuyenmai',ReturnHosing_apache()+"khuyenmai.html?khuyenmaiid="+data[i].id);
                        	html = html.replace('@featureimage',url_image_feature+""+data[i].image);
                            html = html.replace('@title',data[i].title);
                            html = html.replace('@desimage',data[i].title);
                            html = html.replace('@description',data[i].description);
                          	$(".homenews").append(html);                 
                    }         
    }
    
});
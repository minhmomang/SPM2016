/**
 * http://usejsdoc.org/
 */
$(function(){
	var _ismobile = ismobile();
    
	var article_content='<h1>@title</h1>'
		+	'<h2 style="text-align: left;">@description &nbsp;</h2>'
			+'<div>@content</div>';
           
    
	exe_load_header(function(out){		
		if(_ismobile){
			$("#sliderelate").css('display','none');
		}
		load_khuyenmai();
	});
	
    
    function load_khuyenmai(){
    	var id=getUrlParameter("khuyenmaiid");
        if(id==null||id==""){
            return;
        }
    	
        Return_get("Sale_acticleController", "get_saleacticle_clientbyid", {"khuyenmaiid":id}, "GET", function(data) {
      
            if (data!=null&&data!="") {		
            	        append_article_content(data);             
            }
        });
    }
    
    
    function append_article_content(dataelem){
        	var html = article_content;
 
                html = html.replace('@title',dataelem.title);
                html = html.replace('@description',dataelem.description);
                     html = html.replace('@content',dataelem.content);
              	$(".article").append(html);  
    }
    
    
});
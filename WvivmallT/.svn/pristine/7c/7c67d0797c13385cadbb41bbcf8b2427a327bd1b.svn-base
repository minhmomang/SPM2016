$(function(){
	var _ismobile = ismobile();
	exe_load_header_intro(function(out){
		if(out==true){
			if(_ismobile==false){				
							
			}
			load_social();	
			load_intro();
		}
	});
	function load_intro()
	{
		var lang = get_lang_current();
		var pdata = {
		  'lang':lang
		};
		Return_get("AboutController","get_about",pdata,"GET",function(data){
			if(data!=null){						
		         $("#info").html(data.desc).show();
			}
					
		});		
	}
		
});
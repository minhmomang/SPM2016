$(function(){
	
	exe_load_header(function(out){		
	
		load_about();
		
	});
	function load_about(){
		var pdata={"lang":"VN"};
		Return_get("AboutController","get_about",pdata, 'GET', function(data) {
			
			if(data!=null){
				$("#loadabout").html(data.desc);
			}
		});
	}
	
});
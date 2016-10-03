$(function(){	
	get_name_user();
	function get_name_user(){
    	Return_get('MemberController','get_fullname','','GET',function(data){				
			if(data!=null){		
				if(data.result!=''){
					$("#txtuser_fullname").text(data.result);						
				}					
			}
		});
    }
});
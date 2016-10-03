$(function(){
	
	var id = getUrlParameter('id');
	var url_image = ReturnHosing()+'/ProductController/upload_image_product_sub.htm?id='+id;
	$('#file_upload').uploadify({
			'swf'      : '../../admin/uploadify/uploadify.swf',
			'uploader' : url_image,			
			'method': 'Post',
			'fileExt': '*.gif;*.jpg;*.jpeg,*.png',
			'auto': false,
			'multi': true,
			'onCancel' : function(file) {
	            alert('The file ' + file.name + ' was cancelled.');
	        },
	        'onUploadSuccess':function(file,data,response){	  
	        	$('#file_upload').uploadify('upload');
	        }		
	 }); 
	$("#btn-startupload").click(function(){
		$('#file_upload').uploadify('upload');		
	});
	$("#btnclose").click(function(){
		self.close();
	});
});
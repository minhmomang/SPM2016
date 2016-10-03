function Url_tomcat_vivmall(){
	var url ='http://'+window.location.hostname+':8080/Nvmall/';
	return url;
}
function Url_sell_vivmall(){
	var url ='http://'+window.location.hostname+':8080/Svmall/';
	return url;
}
function ReturnHosing(){
	var url ='http://'+window.location.hostname+':8080/WvivmallT/';
	return url;
}
function ReturnHostingCMS(){
	var url ='http://'+window.location.hostname+':8080/WvivmallT/admin/';
	return url;
}
function ReturnHosing_apache(){
	var url ='http://'+window.location.hostname+':8080/WvivmallT/';
	return url;
}
function ReturnHosing_service_w3pl(){
	var url ='http://'+window.location.hostname+':8080/WS3PL/';
	return url;
}
function Return_get(pcontroller,pmethod,pdata,ptype,callback){
	switch(ptype.toUpperCase()){
		case 'GET':
			_get(pcontroller,pmethod,pdata,'GET',callback);
			break;
		case 'POST':
			_post(pcontroller,pmethod,pdata,'POST',callback);
			break;
	}
}
function _get(pcontroller,pmethod,pdata,ptype,callback){
	purl = ReturnHosing()+pcontroller+"/"+pmethod+".htm";
	
	$.ajax({
		url : purl,
		headers : {
			'Accept' : 'application/json',
			'Content-Type': 'application/json,charset=UTF-8'				
		},
		type:ptype,
		dataType : 'json',
		data:pdata,			
		success : function(data) {	
			
			callback(data);
		}
	});
}
function _post(pcontroller,pmethod,pdata,ptype,callback){
	var purl = ReturnHosing()+pcontroller+"/"+pmethod+".htm";	
	$.ajax({
		url: purl, 
	    type: ptype, 
	    dataType: 'json', 
	    data: pdata, 
	    contentType: 'application/json',
	    mimeType: 'application/json',
	    success: function(data) { 
	       callback(data);
	    },
	    error:function(data,status,er) { 
	        alert("Exception:"+er);
	    }
	});
}
function getUrlParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) 
        {
            return sParameterName[1];
        }
    }
}
var check_menu_load  = true;
function get_check_load(){
	return check_menu_load;
}
function exe_load_header(callback){
	var urlhead=ReturnHostingCMS()+"Header.html";
	var urlmenu=ReturnHostingCMS()+"Menu.html";
	 $("#header").load(urlhead, function (responseText, textStatus, XMLHttpRequest) {	
			$("#menu_f").load(urlmenu, function (responseText, textStatus, XMLHttpRequest) {			
				callback(true);		
			});		
	  });
	 return;
	
	Return_get("Admin_loginController", "getSession_admin", "", 'GET', function(data) {
          if (data != null) {
              if(data.isSessionAdmin=='error_getsession_profile_admin'){            	  
            	  window.location.href=Url_sell_vivmall();
              }
              else{
            	  $("#header").load(urlhead, function (responseText, textStatus, XMLHttpRequest) {	
            			$("#menu_f").load(urlmenu, function (responseText, textStatus, XMLHttpRequest) {			
            				callback(true);		
            			});		
            	  });	        	  
              }
          }
    }); //end ajax post	 
	
}
function get_height(){
	var height = $(window).height();
	height =height-300;
	return height;
}
function get_width(){
	var width = $(window).width();
	width = (width/12)*9;
	width = Math.ceil(width) - 20;
	return width;
}
function myformatter(date){
	
	try{
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);	
	}
	catch(ex){
		return date;
	}
	
}
function myparser(s){
	if (!s) return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		return new Date(y,m-1,d);
	} else {
		return new Date();
	}
}
function get_lang_current(){
	//alert('EN');
	var lang =  $.session.get("curlange");
	if(lang==null||lang==undefined||lang==''){
	   return "EN";
	}
    else
        return lang;
}
function change_lang_vn(){	
    $.session.set("curlange","VN");
    location.reload();
}
function change_lang_en(){	

                        $.session.set("curlange","EN");
                        location.reload();           	 
}
function loadScript(callback,url) {
	 
    var script = document.createElement("script");
    script.type = "text/javascript";

    if (script.readyState) { //IE
        script.onreadystatechange = function () {
            if (script.readyState == "loaded" || script.readyState == "complete") {
                script.onreadystatechange = null;
               callback(true);
            }
        };
    } else { //Others
        script.onload = function () {
        	callback(true);
        };
    }	 
    script.src = url;
    document.getElementsByTagName("body")[0].appendChild(script);
}
function formatcurrency(num){	
    var str = num.toString().replace("$", ""), parts = false, output = [], i = 1, formatted = null;
    if(str.indexOf(".") > 0) {
        parts = str.split(".");
        str = parts[0];
    }
    str = str.split("").reverse();
    for(var j = 0, len = str.length; j < len; j++) {
        if(str[j] != ",") {
            output.push(str[j]);
            if(i%3 == 0 && j < (len - 1)) {
                output.push(",");
            }
            i++;
        }
    }
    formatted = output.reverse().join("");
    return(formatted + ((parts) ? "." + parts[1].substr(0, 2) : ""));
};
function PopupCenter(url, title, w, h) {
    // Fixes dual-screen position                         Most browsers      Firefox
    var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
    var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;

    width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
    height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

    var left = ((width / 2) - (w / 2)) + dualScreenLeft;
    var top = ((height / 2) - (h / 2)) + dualScreenTop;
    var newWindow = window.open(url, title, 'scrollbars=yes, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);

    // Puts focus on the newWindow
    if (window.focus) {
        newWindow.focus();
    }
}
function formatedate(dateObject) {
    var d = new Date(dateObject);
    var day = d.getDate();
    var month = d.getMonth() + 1;
    var year = d.getFullYear();
    if (day < 10) {
        day = "0" + day;
    }
    if (month < 10) {
        month = "0" + month;
    }
    var date = day + "/" + month + "/" + year;

    return date;
};
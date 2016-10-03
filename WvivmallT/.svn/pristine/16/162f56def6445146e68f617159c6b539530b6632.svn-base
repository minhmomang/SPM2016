$(function(){
	var path = ReturnHosing_tomcat() + "upload/slide/";
	get_list_slider();
	 function get_list_slider() {
	        Return_get("SlideController", "get_list_slide", '', 'GET', function(data) {
	        	 $(".carousel-indicators").html('');
	        	 $(".carousel-inner").html('');
	            if (data != null) {                
	            	//alert(data[0].name);
	                for(var i=0;i<data.length;i++){
	                	var active="";
	                	if(i==0){
	                		active="active";
	                	}
	                	var li='<li data-target="#myCarousel" data-slide-to="'+i+'" class="'+active+'"></li>';
	                	var devv='<div class="item '+active+'"><img src="'+path+''+data[i].name+'" style="height:406px;width:100%"></div>';
	                	 $(".carousel-indicators").append(li);
	                	 $(".carousel-inner").append(devv);
	                }

	            }
	        });
	   }
//	load_slide();
	function load_slide(){
		var path=ReturnHosing_tomcat()+'upload/slide';
		 var _wc = window.screen.width;
				 
		 var _w =0;
		 if(_wc<500){
			 _w = 0;
		 }
		 else if(_wc>=700 && _wc <=1200){
			 _w = 400;
		 }
		 else{
			 _w= _wc/2.8;
			 _w = Math.floor(_w) + 1;
		}
		var lang = get_lang_current();
		var pdata = {
			'lang':lang	
		};
		Return_get("SlideController","get_list_slide",pdata,"GET",function(data){
			if(data!=null && data.length>0){	
				load_text_slide(function(str1){
					for(var i=0;i<data.length;i++){
						var imgpath = path+'/'+data[i].name;
						var caption = htmlUnescape(data[i].caption);
						var name = data[i].name;
						var html = str1;
						html = html.replace('@imageurl',imgpath);
						html = html.replace('@contain_desc',caption);
						$("#contain-slide-main").append(html);				
					}
					loadScript(function(out){
						if(out){
							loadScript(function(out1){
								if(out1){
									// run jquery
									 var setREVStartSize = function() {
						                  	var	tpopt = new Object();
						                  		tpopt.startwidth = 1040;
						                  		tpopt.startheight = 470;
						                  		tpopt.container = jQuery('#rev_slider_1_1');
						                  		tpopt.fullScreen = "off";
						                  		tpopt.forceFullWidth="off";
						                  
						                  	tpopt.container.closest(".rev_slider_wrapper").css({height:tpopt.container.height()});tpopt.width=parseInt(tpopt.container.width(),0);tpopt.height=parseInt(tpopt.container.height(),0);tpopt.bw=tpopt.width/tpopt.startwidth;tpopt.bh=tpopt.height/tpopt.startheight;if(tpopt.bh>tpopt.bw)tpopt.bh=tpopt.bw;if(tpopt.bh<tpopt.bw)tpopt.bw=tpopt.bh;if(tpopt.bw<tpopt.bh)tpopt.bh=tpopt.bw;if(tpopt.bh>1){tpopt.bw=1;tpopt.bh=1}if(tpopt.bw>1){tpopt.bw=1;tpopt.bh=1}tpopt.height=Math.round(tpopt.startheight*(tpopt.width/tpopt.startwidth));if(tpopt.height>tpopt.startheight&&tpopt.autoHeight!="on")tpopt.height=tpopt.startheight;if(tpopt.fullScreen=="on"){tpopt.height=tpopt.bw*tpopt.startheight;var cow=tpopt.container.parent().width();var coh=jQuery(window).height();if(tpopt.fullScreenOffsetContainer!=undefined){try{var offcontainers=tpopt.fullScreenOffsetContainer.split(",");jQuery.each(offcontainers,function(e,t){coh=coh-jQuery(t).outerHeight(true);if(coh<tpopt.minFullScreenHeight)coh=tpopt.minFullScreenHeight})}catch(e){}}tpopt.container.parent().height(coh);tpopt.container.height(coh);tpopt.container.closest(".rev_slider_wrapper").height(coh);tpopt.container.closest(".forcefullwidth_wrapper_tp_banner").find(".tp-fullwidth-forcer").height(coh);tpopt.container.css({height:"100%"});tpopt.height=coh;}else{tpopt.container.height(tpopt.height);tpopt.container.closest(".rev_slider_wrapper").height(tpopt.height);tpopt.container.closest(".forcefullwidth_wrapper_tp_banner").find(".tp-fullwidth-forcer").height(tpopt.height);}
						            };
						                  
						            /* CALL PLACEHOLDER */
						            setREVStartSize();
						            $('#rev_slider_1_1').show().revolution(
						            {	
						                  		dottedOverlay:"none",
						                  		delay:9000,
						                  		startwidth:1040,
						                  		startheight:470,
						                  		hideThumbs:200,
						                  
						                  		thumbWidth:100,
						                  		thumbHeight:50,
						                  		thumbAmount:2,
						                  		
						                  								
						                  		simplifyAll:"off",
						                  
						                  		navigationType:"none",
						                  		navigationArrows:"solo",
						                  		navigationStyle:"preview1",
						                  
						                  		touchenabled:"on",
						                  		onHoverStop:"on",
						                  		nextSlideOnWindowFocus:"off",
						                  
						                  		swipe_threshold: 75,
						                  		swipe_min_touches: 1,
						                  		drag_block_vertical: false,
						                  		
						                  								
						                  								
						                  		keyboardNavigation:"off",
						                  
						                  		navigationHAlign:"center",
						                  		navigationVAlign:"bottom",
						                  		navigationHOffset:0,
						                  		navigationVOffset:20,
						                  
						                  		soloArrowLeftHalign:"left",
						                  		soloArrowLeftValign:"center",
						                  		soloArrowLeftHOffset:20,
						                  		soloArrowLeftVOffset:0,
						                  
						                  		soloArrowRightHalign:"right",
						                  		soloArrowRightValign:"center",
						                  		soloArrowRightHOffset:20,
						                  		soloArrowRightVOffset:0,
						                  
						                  		shadow:0,
						                  		fullWidth:"on",
						                  		fullScreen:"off",
						                  
						                  								spinner:"spinner0",
						                  								
						                  		stopLoop:"off",
						                  		stopAfterLoops:-1,
						                  		stopAtSlide:-1,
						                  
						                  		shuffle:"off",
						                  
						                  		autoHeight:"off",
						                  		forceFullWidth:"off",
						                  		
						                  		
						                  		
						                  		hideThumbsOnMobile:"off",
						                  		hideNavDelayOnMobile:1500,
						                  		hideBulletsOnMobile:"off",
						                  		hideArrowsOnMobile:"off",
						                  		hideThumbsUnderResolution:0,
						                  
						                  								hideSliderAtLimit:0,
						                  		hideCaptionAtLimit:0,
						                  		hideAllCaptionAtLilmit:0,
						                  		startWithSlide:0				
						                  });
								}
							},'js/jquery.themepunch.revolution.min.js');							
						}
					},'js/jquery.themepunch.tools.min.js');					
				});	
			}
			else{
				$("#contain-slide").css('display','none');
			}
					
		});
	}	
	function load_text_slide(callback){
		var url = ReturnHosing_apache()+'txt/slide3.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
});
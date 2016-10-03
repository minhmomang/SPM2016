
$(function(){
	var total_income = 0;
	var totalquantity=0;
	var columns_data = [];
	var item_income_report = "<tr><td>@id</td><td>@quantity</td><td style='float:right;margin-right:25px;'>@income</td></tr>";
	var item_total="<tr class='item_total_income'><td>Tổng số</td><td>@totalquantity</td><td style='float:right;margin-right:25px;'>@totalincome</td></tr>"

		exe_load_header(function(output){
			if(output==true){
				load_date_Box(function(sucs){
					if(sucs==true){
						var nowdate=getnowdate_mysql();
						$('#seldate').datebox('setValue',nowdate);
						load_char_bydate(nowdate);
						
					
						
						
						
					}
				});
			}
		});
	
	function load_date_Box(callback){
		$('#seldate').datebox({
		    required:true,
		    formatter:myformatter,
		    parser:myparser ,
		    onSelect: function(date){
				var pdate=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
				load_char_bydate(pdate);
			}
		});
		callback(true);
	}
	
	function getnowdate_mysql(){
		var nowdate=new Date();
		return nowdate.getFullYear()+"-"+(nowdate.getMonth()+1)+"-"+nowdate.getDate();
	}
	function load_char_bydate(customdate){
		//alert(customdate);
		load_count_order(customdate);
		load_data_report(function(out){
			var chart = c3.generate({
				bindto:"#chart",
				data: {
					// iris data from R
					columns:columns_data ,
					type : 'pie',
					onclick: function (d, i) { alert(d.id + " : " + d.value); },
					onmouseover: function (d, i) { },
					onmouseout: function (d, i) { }
				}
			});
			
		},customdate);
	}
	
	function load_data_report(callback,customdate){
		//alert(customdate);
		columns_data=[];
		total_income=0;
		totalquantity=0;
		Return_get("OrderController","get_info_report_product",{"pdate":customdate},"GET",function(data){
			if(data!=null){
				load_text(function(output,string){
				if(output==true){
				var item=string;
				var html="";
				for(var i=0;i<data.length;i++){
					var str= item_income_report;
					columns_data.push([data[i].productName,parseFloat(data[i].productquantity)*data[i].productPrice]);
					str=str.replace('@id',(data[i].productName));
					str=str.replace('@quantity',data[i].productquantity);
					str=str.replace('@income',formatcurrency(parseFloat(data[i].productquantity)*data[i].productPrice));
					html=html+str;
					totalquantity = totalquantity+parseInt(data[i].productquantity); 
					total_income = total_income+ parseFloat(data[i].productquantity)*data[i].productPrice;
				}
			}
			var temp_total = item_total.replace('@totalincome',formatcurrency(total_income));
			temp_total =temp_total.replace('@totalquantity',totalquantity);
			html=html+temp_total;
			item = item.replace('@item',html);
			$("#info_income").html('');
			$("#info_income").append(item);
			callback(true);
			})
		}
		});
	}
	
	
	function load_count_order(customdate){
		var total_order=0;
		$("#success").html("0");
		$("#fail").html("0");
		Return_get("OrderController","count_order",{"pdate":customdate},"GET",function(data){
			if(data!=null){
				for(var i=0;i<data.length;i++){
					switch (data[i].order_status){
					case '3':
						$("#success").html(data[i].countorder);
						total_order+= parseInt(data[i].countorder);
						break;
					case '-1':
						$("#fail").html(data[i].countorder);
						total_order+= parseInt(data[i].countorder);
						break;
					default: 
						break;
					}
				}
				$("#total").text(total_order);
			}
		})
	}
	
	function load_text(callback){
		var url=ReturnHostingCMS()+'js/report/byproduct/admin_report.txt';
		$.get(url,function(data){
			callback(true,data);
		})
	};
});
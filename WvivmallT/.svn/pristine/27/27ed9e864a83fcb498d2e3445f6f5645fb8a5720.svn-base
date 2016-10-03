
$(function(){
	var arr_date=[];
	var columns_data = [];
	var item_income_report = 
			"<tr><td>@date</td><td style='text-align:right;padding-right:70px;'>@income</td>" +
			"<td style='text-align:right;padding-right:70px;'>@fromnl</td><td style='text-align:right;padding-right:70px;'>@fromrest</td>"+
			"<td style='padding-left:10px;'>@success</td><td style='padding-left:10px;'>@fail</td></tr>";
	var item_total=
			"<tr class='item_total_income'><td style='padding-left:10px;'>Tổng số</td><td style='text-align:right;padding-right:70px;'>@total</td>" +
			"<td style='text-align:right;padding-right:70px;'>@totalnl</td><td style='text-align:right;padding-right:70px;'>@totalrest</td>"+
			"<td style='padding-left:10px;'>@totalsuccess</td><td style='padding-left:10px;'>@totalfail</td></tr>"

		exe_load_header(function(output){
			if(output==true){
				load_date_Box();
				load_default();
			}
		});
	
	$("#btnview").click(function(){
		check_date(function(out){
			if(out==true){
				$("#chart").html('');
				var total=0;
				var arr_date_d =[];
				arr_date_d.push('x');
				var arr_date_v =[];
				arr_date_v.push('Doanh thu');
				var totalsuccess=0;
				var totalfail=0;
				var stritem="";
				var totalnl=0;
				var totalrest=0;
				$("#table_content").html('');
				var fdate =$('#fromdate').datebox("getValue");
				var tdate =$('#todate').datebox("getValue");
				pdata={'fdate':fdate,
						'tdate':tdate};
				Return_get("OrderController","get_report_period",pdata,"GET",function(data){
					if(data.length>0){
						for(var i=0;i<arr_date.length;i++){
							// start get date+value
							var flag=0;
							var flagss=0;
							var flagf=0;
							var flagnl=0;
							var flagrest=0;
							arr_date_d.push(arr_date[i]);
							
							for(var j=0;j<data.length;j++){
								if(data[j].order_date==arr_date[i]){
									flag=data[j].total_amount;
									total+=data[j].total_amount;
									flagss=data[j].SS;
									totalsuccess+=flagss;
									flagf=data[j].FAIL;
									totalfail+=flagf;
									flagnl=data[j].amount_nl;
									totalnl+=flagnl;
									flagrest=data[j].amount_kh;
									totalrest+=flagrest;
									break;
								}
							}
							arr_date_v.push(flag);
							gen_table_content(arr_date[i],flag,flagss,flagf,flagnl,flagrest,function(item){
								stritem+=item;
							});
						}//end get date+value  end for(i)
						gen_table_total(total,totalsuccess,totalfail,totalnl,totalrest,function(strtotal){
							gen_table(stritem,strtotal);
						})
						var chart = c3.generate({
							data:{
								x:'x',
								columns: [
								      arr_date_d,
								      arr_date_v,
									]
							},
							axis:{
								x:
									{	type: 'timeseries',
										tick:{
											format :  '%Y-%m-%d'
										}
									}
							}
						});//end gene chart
					}else{
						load_default();
					}
				});
				
			}
			else{
				return;
			}
		});
	})
	
	function get_arr_date(fromdate,todate){
		arr_date.splice(0, arr_date.length);
		var nowdate=fromdate;
		do{
		var temp_date = new Date(nowdate).toISOString().slice(0,10).replace(/-/g,"-");
		arr_date.push(temp_date);
		nowdate+=1000*60*60*24;
		}while(nowdate<=todate);
	}
	function load_date_Box(){
		$('#fromdate').datebox({
		    required:true,
		    formatter:myformatter,
		    parser:myparser,
		   
		});
		$('#todate').datebox({
				    required:true,
				    formatter:myformatter,
				    parser:myparser,
				   
		});
	}

	function load_text(callback){
		var url=ReturnHostingCMS()+'js/report/byperiod/admin_report.txt';
		$.get(url,function(data){
			callback(true,data);
		})
	};
	function check_date(callback){
		 var long_fdate = Date.parse($('#fromdate').datebox("getValue"));
		 var long_tdate = Date.parse($('#todate').datebox("getValue"));
		 if(long_fdate==null || long_fdate=="" || long_tdate==null || long_tdate=="" ||long_tdate-long_fdate<0 || isNaN(long_fdate) || isNaN(long_tdate) ){			 
			 alert("Ngày tháng không hợp lệ !");
			 callback(false);
		 } 
		 else{
			get_arr_date(long_fdate,long_tdate);
			callback(true);
		 }
	}
	function gen_table_total(total,tsuccess,tfail,tnl,trest,callback){
		var strtotal=item_total;
		strtotal=strtotal.replace('@total',formatcurrency(total));
		strtotal=strtotal.replace('@totalsuccess',tsuccess);
		strtotal=strtotal.replace('@totalfail',tfail);
		strtotal=strtotal.replace('@totalnl',formatcurrency(tnl));
		strtotal=strtotal.replace('@totalrest',formatcurrency(trest));
		callback(strtotal);
	}
	function gen_table_content(date,income,isuccess,ifail,inl,irest,callback){
		var stritem=item_income_report;
		stritem=stritem.replace('@date',date);
		stritem=stritem.replace('@income',formatcurrency(income));
		stritem=stritem.replace('@success',isuccess);
		stritem=stritem.replace('@fail',ifail);
		stritem=stritem.replace('@fromnl',formatcurrency(inl));
		stritem=stritem.replace('@fromrest',formatcurrency(irest));
		callback(stritem);
	}
	function gen_table(stritem,strtotal){
		load_text(function(out,str){
			str=str.replace('@item',stritem+strtotal);
			$("#table_content").append(str);
		});
	}
	function load_default(){
		gen_table_total(0,0,0,0,0,function(str){
			gen_table("",str);
		});
	};
});
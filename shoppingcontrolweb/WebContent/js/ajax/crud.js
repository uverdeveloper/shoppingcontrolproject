/**
 * C.R.U.D with restful protocol
 */

var dateToday = new Date();

function consultaPorAno() {
	
	var ano = $("#ano").val();
	
	$("#tabelaDeGastos").empty();

	$.ajax(
			{
			type : "GET",
			url : "http://localhost:8083/shoppingcontrolweb/rest/search1/" + ano,
			contentType : "application/json; charset=UTF-8",
			success : function(data){
				tabela();
				$.each(data, function(index, value) {
					$("tbody").append(
									"<tr>" 
										+ "<td>" + data[index].id	+ "</td>" 
										+ "<td>" + data[index].application	+ "</td>" 
										+ "<td>" + parseFloat(data[index].value).toFixed(2)	+ "</td>" 
										+ "<td>" + data[index].buy_date + "</td>"
										+ "<td>" + data[index].due_date + "</td>"
										+ "<td>" + data[index].description + "</td>"
									+ "</tr>"
									)
								}
							)
						},
				error : function() {
					Materialize.toast('Item não encontrado', 4000)
				}
			});
}

function consultaPorMes() {	
		
	var mes = $("#mes").val();
	var ano = $("#ano").val();	

	if((mes != "--------" && mes != null) && (ano != "--------" && ano != null)){
	
	$("#tabelaDeGastos").empty();
	
	var rest = "";
	var sum;	
				
	$.ajax(
			{
			type : "GET",
			url : "http://localhost:8083/shoppingcontrolweb/rest/search2/" + mes + "/" + ano,
			contentType : "application/json; charset=UTF-8",
			success : function(data) {
				tabela();
				$.each(data, function(index, value) {
					$("tbody").append(
									"<tr>" 
										+ "<td>" + data[index].id	+ "</td>"
										+ "<td>" + data[index].application + "</td>" 
										+ "<td>" + parseFloat(data[index].value).toFixed(2)	+ "</td>"
										+ "<td>" + data[index].buy_date + "</td>"
										+ "<td>" + data[index].due_date + "</td>"
										+ "<td>" + data[index].description + "</td>"										
									+ "</tr>"
									)										
								}
							)
							
							$.ajax(
									{
									type : "GET",
									url : "http://localhost:8083/shoppingcontrolweb/rest/sumValues/" + mes + "/" + ano,
									contentType : "application/json; charset=UTF-8",
									success : function(result) {				
																	
										sum = result;
											
										$("tbody").append(	
														"<tr>"
															+"<td>Total: "+ sum + "</td>"
														+"</tr>"															
														)
													},
									error : function() {
											Materialize.toast('Não há valores', 5000)
									}
								}
							);
							},
							error : function() {
								Materialize.toast('Item não encontrado', 5000)
										}
							}
						);	
					}
				}		
	

function cadastrarGastos() {

	var ultimoId;
	
	var monthNumber = 1;
	var day = dateToday.getDate();
	monthNumber += dateToday.getMonth();
	var year = dateToday.getFullYear().toString();
	year = year.substring(2);
	
	if(day < 10)
		day = '0'+day;
	
	if(monthNumber < 10)
		monthNumber = '0'+monthNumber;
	
		$.ajax(
				{
				type: "GET",
				url: "http://localhost:8083/shoppingcontrolweb/rest/organizingId/lastId",
				success: function(result){
				
				ultimoId = result;				
				
				var data = {
						"id" : ultimoId,
						"application" : $("#aplicacao").val(),
						"value" : $("#valor").val().replace(',','.'),
						"buy_date" : day + '/' + monthNumber + '/' + year,
						"due_date" : $("#vencimento").val(),
						"description" : $("#observacao").val()
					};
				
					json = JSON.stringify(data);
					
					$.ajax(
							{
							type : "POST",
							url : "http://localhost:8083/shoppingcontrolweb/rest/notes/",
							contentType : "application/json; charset=utf-8",
							dataType : "text",
							data : json,
							success : function(result) {
								Materialize.toast('Item cadastrado!', 4000)
								},
							error : function(erro) {
								Materialize.toast('Item não cadastrado!', 5000)
								},
								
							}
						);
				
			},error : function(){
				Materialize.toast('Cadastre um item', 4000)
			} 
			
		});		
	}

function apagarItem(paramId) {
		
	var data = {
		"id" : paramId.value
	}

	json = JSON.stringify(data);

		$.ajax(
				{
				type : "DELETE",
				url : "http://localhost:8083/shoppingcontrolweb/rest/notes/",
				contentType : "application/json; charset=UTF-8",
				dataType : "text",
				data : json,
				success : function(result){
					init('historico');
				},
				error : function(request, status, erro) {
					Materialize.toast('Esse item não foi apagado', 5000)
				}
			}
		);
}

function updateItem() {
		
	var data = {
			"id" : $("#id").val(),
			"application" : $("#aplicacao").val(),
			"value" : $("#valor").val().replace(',','.'),
			"buy_date" : $("#dataDaCompra").val(),
			"due_date" : $("#vencimento").val(),
			"description" : $("#observacao").val()!= '' ? $("#observacao").val() : 'N/A' 
		};
	
	json = JSON.stringify(data);
	/*alert(json)*/
	
	$.ajax(
			{
			type : "PUT",
			url : "http://localhost:8083/shoppingcontrolweb/rest/notes/",
			dataType : "text",
			contentType : "application/json; charset=utf-8",
			data : json,
			success : function(result) {
				Materialize.toast('Item atualizado!', 4000)
			},
			error : function(erro) {
				Materialize.toast('Item não atualizado!', 4000)
			}
		}
	);
}

function chartInit(){
    
	var months = ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'];
	
	var monthNumber = 1;
	var yearNumber = dateToday.getFullYear().toString().substring(2);
	
	$.ajax(
			{
			type : "GET",
			url : "http://localhost:8083/shoppingcontrolweb/rest/monthlyTotal/" + 19,
			contentType : "application/json; charset=UTF-8",
			success : function(data) {
				var i = 0;
				var n = new Float64Array(12);
				for(i = 0; i<= 11; i++){
					if(data[i]>0){
					
					n[i] = parseFloat(data[i]);
					}
				}
				
				var dt = new google.visualization.DataTable();
			      dt.addColumn('string', 'X');
			      dt.addColumn('number', 'gastos');
			      // EM 2019 ALTERAR A ORDEM DOS N n[0],n[1],n[2] ... 
			      dt.addRows(
			    [[months[0],n[0]],[months[1],n[1]],[months[2],n[2]],[months[3],n[3]],[months[4],n[4]],[months[5],n[5]],
			    [months[6],n[6]],[months[7],n[7]],[months[8],n[8]],[months[9],n[9]],[months[10],n[10]],[months[11],n[11]]]);
			      
			      var options = {
			        hAxis: {
			          title: 'Meses'
			        },
			        vAxis: {
			          title: 'Gastos'
			        },
			        backgroundColor: '#f1f8e9'
			      };
			      var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
			      chart.draw(dt, options);
			},
			error : function() {
					Materialize.toast('Gráfico indisponível', 4000)
			}
		}
	);
	
}
/**
 * C.R.U.D with restful protocol
 */

var dateToday = new Date();

function consultaPorAno() {
	
	var ano = $("#ano").val();
	
	$("#tabelaDeGastos").empty();

	$.ajax({
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
									+ "</tr>")
					})
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
				
				$.ajax({
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
											+ "</tr>")										
							})
							
							$.ajax({
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
										});
							},
							error : function() {
								Materialize.toast('Item não encontrado', 5000)
										}
							});	
	}
}		
	

function cadastrarGastos() {

	var ultimoId;
	
	var monthNumber = 1;
	var dayNumber = dateToday.getDate();
	monthNumber += dateToday.getMonth();
	
	if(dayNumber<10)
		dayNumber = '0'+dayNumber;
	
	if(monthNumber <10)
		monthNumber = '0'+monthNumber;
		
		$.ajax({
			type: "GET",
			url: "http://localhost:8083/shoppingcontrolweb/rest/organizingId/lastId",
			success: function(result){
				
				ultimoId = result;				
				
				var data = {
						"id" : ultimoId,
						"application" : $("#aplicacao").val(),
						"value" : $("#valor").val(),
						"buy_date" : dayNumber + '/' + monthNumber + '/' + dateToday.getFullYear().toString().substring(2),
						"due_date" : $("#vencimento").val(),
						"description" : $("#observacao").val()
					};
				
					json = JSON.stringify(data);
					
					$.ajax({
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
								
							});
				
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

		$.ajax({
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
			});
}

function updateItem() {
		
	var data = {
			"id" : $("#id").val(),
			"application" : $("#aplicacao").val(),
			"value" : $("#valor").val(),
			"buy_date" : $("#dataDaCompra").val(),
			"due_date" : $("#vencimento").val(),
			"description" : $("#observacao").val()!= '' ? $("#observacao").val() : 'N/A' 
		};
	
	
	
	json = JSON.stringify(data);
	alert(json)
	
	$.ajax({
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
		});
}

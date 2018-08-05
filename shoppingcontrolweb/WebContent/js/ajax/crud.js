/**
 * C.R.U.D with restful protocol
 */


function consultaPorAno() {
	
	var ano = $("#ano").val();
	
	$("#tabelaDeGastos").empty();

	$.ajax({
				type : "GET",
				url : "http://localhost:8083/sistemacontroledegastosweb/rest/sistemacontroledegastos/" + ano,
				contentType : "application/json; charset=UTF-8",
				success : function(data){
					tabela();
					$.each(data, function(index, value) {
						$("tbody").append(
									"<tr>" 
										+ "<td>" + data[index].id	+ "</td>" 
										+ "<td>" + data[index].aplicacao	+ "</td>" 
										+ "<td>" + parseFloat(data[index].valor).toFixed(2)	+ "</td>" 
										+ "<td>" + data[index].mes + "</td>" 
										+ "<td>" + data[index].ano + "</td>" 
										+ "<td>" + data[index].observacao + "</td>"
									+ "</tr>")
					})
				},
				error : function() {
					$("body").append(
						"<div id=\"noData\">"
						+ "<p>"
						+ "<label for=\"databaseEmpty\" id=\"mensagem\">Aplicação não encontrada.</label>"
						+ "</p>"
						+ "</div>")
				}
			});
}

function consultaPorMes() {	
		
	var valor = $("#valueDisponible").val();
	var mes = $("#mes").val();
	var ano = $("#ano").val();	
	
	if((mes != "--------" && mes != null) && (ano != "--------" && ano != null)){
	
	$("#tabelaDeGastos").empty();
	
	var rest = "";
	var sum;	
	
	$.ajax({
			type : "GET",
			url : "http://localhost:8083/sistemacontroledegastosweb/rest/sistemacontroledegastos/soma/" + mes + "/" + ano + "/" + valor,
			dataType : "text",
			success : function(result) {				
				rest = result;
				
				$("#valueDisponible").attr("required","required").css("border-bottom-color","d3d3d3");
				
				$.ajax({
					type : "GET",
					url : "http://localhost:8083/sistemacontroledegastosweb/rest/sistemacontroledegastos/" + mes + "/" + ano,
					contentType : "application/json; charset=UTF-8",
					success : function(data) {
						tabela();
							$.each(data, function(index, value) {
								$("tbody").append(
											"<tr>" 
												+ "<td>" + data[index].id	+ "</td>"
												+ "<td>" + data[index].aplicacao + "</td>" 
												+ "<td>" + parseFloat(data[index].valor).toFixed(2)	+ "</td>" 
												+ "<td>" + data[index].mes + "</td>" 
												+ "<td>" + data[index].ano + "</td>" 
												+ "<td>" + data[index].observacao + "</td>"										
											+ "</tr>")										
							})									
									$.ajax({
										type : "GET",
										url : "http://localhost:8083/sistemacontroledegastosweb/rest/sistemacontroledegastos/soma/" + mes + "/" + ano,
										contentType : "application/json; charset=UTF-8",
										success : function(result) {				
																			
													sum = result;
													
													if(rest != "" && rest != null){
														if(!rest.match(/-/)){
																$("tbody").append(	
																		"<tr>"
																		+"<td>Total do mês: "+ sum + "</td>"
																		+"</tr>"
																		+"<tr>"
																		+"<td>Restante: " + rest + "</td>"	
																		+"</tr>"																		
																		)
														}else{		
															
															var convertString = parseFloat(rest);
															
															$("tbody").append(
																	"<tr>"
																	+"<td>Total: "+ sum + "</td>"
																	+"</tr>"
																	+"<tr>"
																	+"<td style=\"color:red;\">Valor restante: " + convertString.toFixed(2) + "</td>"	
																	+"</tr>"
																)
															}
														}
												},
												error : function() {
													$("body").append(
															"<div id=\"noData\">"
															+ "<p>"
															+ "<label for=\"databaseEmpty\" id=\"mensagem\">Não Há valores a serem somados.</label>"
															+ "</p>"
															+ "</div>")
															}
												});									
							
							},
							error : function() {
								$("body").append(
										"<div id=\"noData\">"
										+ "<p>"
										+ "<label for=\"databaseEmpty\" id=\"mensagem\">Aplicação não encontrada.</label>"
										+ "</p>"
										+ "</div>")
										}
							});
				
				},
				error : function() {
					$("#valueDisponible").attr("required","required").css("border-bottom-color","#f00");
					}
			});	
		}
	}

function cadastrarGastos() {

	var ultimoId;
	
		$.ajax({
			type: "GET",
			url: "http://localhost:8083/sistemacontroledegastosweb/rest/sistemacontroledegastos/lastId",
			success: function(result){
				
				ultimoId = result;
				
				var data = {
						"id" : ultimoId,
						"aplicacao" : $("#aplicacao").val(),
						"valor" : $("#valor").val(),
						"mes" : $("#mes option:selected").val(),
						"ano" : $("#ano option:selected").val(),
						"observacao" : $("#observacao").val()
					};

					json = JSON.stringify(data);

					$.ajax({
							type : "POST",
							url : "http://localhost:8083/sistemacontroledegastosweb/rest/sistemacontroledegastos/",
							dataType : "text",
							contentType : "application/json; charset=utf-8",
							data : json,
							success : function(result) {								
								Materialize.toast('Cadastramento com sucesso!', 4000)
								},
							error : function(erro) {
								$("body").append(
										"<div id=\"noData\">"
										+ "<p>"
										+ "<label for=\"databaseEmpty\" id=\"mensagem\">Aplicação não cadastrada.</label>"
										+ "</p>"
										+ "</div>")
								}
							});
				
			},error : function(){
				$("body").append(
						"<div id=\"noData\">"
						+ "<p>"
						+ "<label for=\"databaseEmpty\" id=\"mensagem\">Cadastre uma aplicação.</label>"
						+ "</p>"
						+ "</div>")
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
				url : "http://localhost:8083/sistemacontroledegastosweb/rest/sistemacontroledegastos/",
				contentType : "application/json; charset=UTF-8",
				dataType : "text",
				data : json,
				success : function(result){
					init('historico');
				},
				error : function(request, status, erro) {
					alert('Dados não deletados. ')
				}
			});
}

function updateItem() {
		
	var data = {
			"id" : $("#id").val(),
			"aplicacao" : $("#aplicacao").val(),
			"valor" : $("#valor").val(),
			"mes" : $("#mes option:selected").val(),
			"ano" : $("#ano option:selected").val(),
			"observacao" : $("#observacao").val()!= '' ? $("#observacao").val() : 'N/A' 
		};
	
	json = JSON.stringify(data);
	
	$.ajax({
		type : "PUT",
		url : "http://localhost:8083/sistemacontroledegastosweb/rest/sistemacontroledegastos/",
		dataType : "text",
		contentType : "application/json; charset=utf-8",
		data : json,
		success : function(result) {
			alert('')
			},
		error : function(erro) {
				alert('Dados não inseridos: ' + erro)
			}
		});
}

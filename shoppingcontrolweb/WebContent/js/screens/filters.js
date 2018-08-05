/**
 * Filtro do menu principal
 */

function init(val) {
		
	var filtro = val;
	
	$("#tabelaDeGastos").empty();
	$("#Consultas").empty();

	if (filtro == "historico") {		

		$.ajax({
					type : "GET",
					url : "http://localhost:8083/sistemacontroledegastosweb/rest/sistemacontroledegastos/all",
					contentType : "application/json; charset=UTF-8",
					success : function(data) {
						tabela();
						$.each(data, function(index, value) {
							
							$("tbody").append(
											"<tr>"
												+ "<td>" + data[index].id + "</td>"
												+ "<td>" + data[index].aplicacao + "</td>" 
												+ "<td>" + parseFloat(data[index].valor).toFixed(2) + "</td>"
												+ "<td>" + data[index].mes + "</td>" 
												+ "<td>" + data[index].ano + "</td>"
												+ "<td>" + data[index].observacao + "</td>"
												+ "<td><button class=\"waves-effect waves-light btn\" onclick=\"updateScreenItem(this);\" value=\""+data[index].id+"\">Alterar</button></td>"
												+ "<td><button class=\"waves-effect waves-light btn\" onclick=\"apagarItem(this);\" value=\""+data[index].id+"\">Apagar</button></td>"
											+ "</tr>")
						})
					},
					error : function() {
						$("body").append(
								"<div id=\"noData\">"
										+ "<p>"
										+ "<label for=\"databaseEmpty\" id=\"mensagem\">Cadastre uma aplicação.</label>"
										+ "</p>"
										+ "</div>")
					}
				});

	}
	if (filtro == "anual") {

		$("#Consultas").append(
						"<div id=\"row\" style=\"width:300px;\">"
						+ "Consulta por ano: " 
						+ "<select id=\"ano\" onchange=\"consultaPorAno()\">"
						+ "<option value=\"Escolher\" disabled selected>Escolher</option>"
						+ "<option value=\"2017\">2017</option>"
						+ "<option value=\"2018\">2018</option>"
						+ "<option value=\"2019\">2019</option>"
						+ "</select>"
						+ "</div>"
						)
	}

	if (filtro == "mensal") {
		$("#Consultas").empty();
		$("#Consultas").append(
						"<div id=\"row\" style=\"width:300px;\">"
								+"Insira o valor disponível: "
								+"<input id=\"valueDisponible\"\ placeholder=\"Digite aqui\" />"
								+"Consulta por mes/ano: "
								+ "<select  id=\"mes\">"
								+ "<option value=\"--------\" selected>--------</option>"
								+ "<option value=\"Janeiro\">Janeiro</option>"
								+ "<option value=\"Fevereiro\">Fevereiro</option>"
								+ "<option value=\"Marco\">Março</option>"
								+ "<option value=\"Abril\">Abril</option>"
								+ "<option value=\"Maio\">Maio</option>"
								+ "<option value=\"Junho\">Junho</option>"
								+ "<option value=\"Julho\">Julho</option>"
								+ "<option value=\"Agosto\">Agosto</option>"
								+ "<option value=\"Setembro\">Setembro</option>"
								+ "<option value=\"Outubro\">Outubro</option>"
								+ "<option value=\"Novembro\">Novembro</option>"
								+ "<option value=\"Dezembro\">Dezembro</option>"
								+ "</select>"
								+ "<select id=\"ano\">"
								+ "<option value=\"--------\"selected>--------</option>"
								+ "<option value=\"2017\">2017</option>"
								+ "<option value=\"2018\">2018</option>"
								+ "<option value=\"2019\">2019</option>"
								+ "</select>"
								+"<button id=\"btnSearchMonth\" onclick=\"consultaPorMes()\" class=\"waves-effect waves-light btn-floating\"><i class=\"material-icons\">search</i></button>"
								+"</div>"
								)
	}

	if (filtro == "cadastrar") {

		$("#Consultas").append(
								"<div id=\"row\" style=\"width:300px;\">"
									+ " Aplicacao: <input type=\"text\" id=\"aplicacao\" />"
									+ " Valor: <input type=\"text\" id=\"valor\" />"
									+ " Mes: <select id=\"mes\">"
									+ "<option value=\"Escolher\" disabled selected>Escolher</option>"
									+ "<option value=\"Janeiro\">Janeiro</option>"
									+ "<option value=\"Fevereiro\">Fevereiro</option>"
									+ "<option value=\"Marco\">Março</option>"
									+ "<option value=\"Abril\">Abril</option>"
									+ "<option value=\"Maio\">Maio</option>"
									+ "<option value=\"Junho\">Junho</option>"
									+ "<option value=\"Julho\">Julho</option>"
									+ "<option value=\"Agosto\">Agosto</option>"
									+ "<option value=\"Setembro\">Setembro</option>"
									+ "<option value=\"Outubro\">Outubro</option>"
									+ "<option value=\"Novembro\">Novembro</option>"
									+ "<option value=\"Dezembro\">Dezembro</option>"
									+ "</select>"
									+ " Ano: <select id=\"ano\">"
									+ "<option value=\"Escolher\" disabled selected>Escolher</option>"
									+ "<option value=\"2017\">2017</option>"
									+ "<option value=\"2018\">2018</option>"
									+ "<option value=\"2019\">2019</option>"
									+ "</select>"
									+ " Observacao: <input type=\"text\" id=\"observacao\" />"
									+ "<button class=\"waves-effect waves-light btn\" onclick=\"cadastrarGastos();\">" +
											"<i class=\"material-icons right\">mode_edit</i>Cadastrar</button>"
								+ "</div>"
							+"<div id=\"cadastrado\">"+"</div>")
	}
	
	// Executa o select do html com JQuery
	$("select").material_select();
}
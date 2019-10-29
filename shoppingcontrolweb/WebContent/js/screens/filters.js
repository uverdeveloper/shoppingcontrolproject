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
					url : "http://localhost:8083/shoppingcontrolweb/rest/notes/all",
					contentType : "application/json; charset=UTF-8",
					success : function(data) {
						tabela();
						$.each(data, function(index, value) {
							
							$("tbody").append(
											"<tr>"
												+ "<td>" + data[index].id + "</td>"
												+ "<td>" + data[index].application + "</td>" 
												+ "<td>" + parseFloat(data[index].value).toFixed(2) + "</td>"
												+ "<td>" + data[index].buy_date + "</td>"
												+ "<td>" + data[index].due_date + "</td>"
												+ "<td>" + data[index].description + "</td>"
												+ "<td><button class=\"waves-effect waves-light btn\" onclick=\"updateScreenItem(this);\" value=\""+data[index].id+"\">Alterar</button></td>"
												+ "<td><button class=\"waves-effect waves-light btn\" onclick=\"apagarItem(this);\" value=\""+data[index].id+"\">Apagar</button></td>"
											+ "</tr>")
						})
					},
					error : function() {
						Materialize.toast('Cadastre um item', 4000)
					}
				});

	}
	if (filtro == "anual") {

		$("#Consultas").append(
						"<div id=\"row\" style=\"width:300px;\">"
						+ "Consulta por ano: " 
						+ "<select id=\"ano\" onchange=\"consultaPorAno()\">"
						+ "<option value=\"Escolher\" disabled selected>Escolher</option>"
						+ "<option value=\"17\">2017</option>"
						+ "<option value=\"18\">2018</option>"
						+ "<option value=\"19\">2019</option>"
						+ "<option value=\"20\">2020</option>"
						+ "</select>"
						+ "</div>"
						)
	}

	if (filtro == "mensal") {
		$("#Consultas").empty();
		$("#Consultas").append(
						"<div id=\"row\" style=\"width:300px;\">"
								+"Consulta por mes/ano: "
								+ "<select  id=\"mes\">"
								+ "<option value=\"--------\" selected>--------</option>"
								+ "<option value=\"01\">Janeiro</option>"
								+ "<option value=\"02\">Fevereiro</option>"
								+ "<option value=\"03\">Março</option>"
								+ "<option value=\"04\">Abril</option>"
								+ "<option value=\"05\">Maio</option>"
								+ "<option value=\"06\">Junho</option>"
								+ "<option value=\"07\">Julho</option>"
								+ "<option value=\"08\">Agosto</option>"
								+ "<option value=\"09\">Setembro</option>"
								+ "<option value=\"10\">Outubro</option>"
								+ "<option value=\"11\">Novembro</option>"
								+ "<option value=\"12\">Dezembro</option>"
								+ "</select>"
								+ "<select id=\"ano\">"
								+ "<option value=\"--------\"selected>--------</option>"
								+ "<option value=\"17\">2017</option>"
								+ "<option value=\"18\">2018</option>"
								+ "<option value=\"19\">2019</option>"
								+ "<option value=\"20\">2020</option>"
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
									+ " Vencimento: <input type=\"text\" id=\"vencimento\" />"
									+ " Observacao: <input type=\"text\" id=\"observacao\" />"
									+ "<button class=\"waves-effect waves-light btn\" onclick=\"cadastrarGastos();\">" +
											"<i class=\"material-icons right\">mode_edit</i>Cadastrar</button>"
								+ "</div>"
							+"<div id=\"cadastrado\">"+"</div>")
	}
	
	// Executa o select do html com JQuery
	$("select").material_select();
}
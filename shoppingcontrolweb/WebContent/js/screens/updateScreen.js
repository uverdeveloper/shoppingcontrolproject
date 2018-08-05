/**
 * Atualiza aplicação cadastrada 
 */

function updateScreenItem(id){
	$("#Consultas").empty();
	$("tbody").empty();	
					$("tbody").append(
							"<tr>"
								+"<td><input style=\"text-align:center;\" type=\"text\" id=\"id\" value=\""+id.value+"\"/></td>"
								+"<td><input type=\"text\" id=\"aplicacao\" /></td>"
								+"<td><input type=\"text\" id=\"valor\" /></td>"
								+ "<td><select id=\"mes\" class=\"browser-default\" style=\"width:100px;\" >"
								+ "<option disabled selected>-------</option>"
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
								+ "</select></td>"
								+ "<td><select id=\"ano\" class=\"browser-default\" style=\"width:80px;\" >"
								+ "<option disabled selected>-------</option>"
								+ "<option value=\"2017\">2017</option>"
								+ "<option value=\"2018\">2018</option>"
								+ "<option value=\"2019\">2019</option>"
								+ "</select></td>"
								+ "<td><input type=\"text\" id=\"observacao\" /></td>"
								+ "<td><button class=\"waves-effect waves-light btn\" id=\"btnUpdateItem\" onclick=\"updateItem()\">Inserir</button></td>"
								+ "</tr>"
								)	
					
				
			$("select").material_select();
	
}

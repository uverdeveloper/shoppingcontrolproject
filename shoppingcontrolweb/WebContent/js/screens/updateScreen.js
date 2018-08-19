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
								+"<td><input type=\"text\" id=\"dataDaCompra\" /></td>"
								+"<td><input type=\"text\" id=\"vencimento\" /></td>"
								+"<td><input type=\"text\" id=\"observacao\" /></td>"
								+"<td><button class=\"waves-effect waves-light btn\" id=\"btnUpdateItem\" onclick=\"updateItem()\">Inserir</button></td>"
								+"</tr>"
								)	
					
				
			$("select").material_select();
	
}

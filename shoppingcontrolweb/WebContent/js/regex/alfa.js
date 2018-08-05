/**
 * Inputs Rules 
 */

function regexAlfa() {

	var campo = $("#aplicacao").val();

	var regular_expression = /([a-z]|[0-9])/;

	var retorno = regular_expression.exec(campo);
	if (retorno != null) {
		return true;
	}

	return false;
}
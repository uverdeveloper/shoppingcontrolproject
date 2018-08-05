/**
 * Login screen
 */


function checkLogin() {

	var user = $("#user").val();
	var pwd = $("#pwd").val();
	
	$("#messageLogin").empty();
	
		$.ajax({
				type : "GET",
				url : "http://localhost:8083/sistemacontroledegastosweb/rest/sistemacontroledegastoslogin/" + user + "/" + pwd,
				contentType : "application/json; charset=utf-8",
				dataType : "text",
				success : function(result) {
					
					if (result == "OK") {
						
						window.location.assign("http://localhost:8083/sistemacontroledegastosweb/views/gastos.html");
						
					} else {
						$("#messageLogin").append(
										"<div id=\"loginInvalido\">"
												+ "<label for=\"mensagem\" id=\"mensagem\">Usuário ou Senha inválido.</label>"
												+ "</div>")
					}					
				},
				error : function(erro) {
					
					$("#messageLogin").append(
									"<div id=\"loginInvalido\">"
											+ "<label for=\"mensagem\" id=\"mensagem\">Preencha os campos: Usuário e Senha.</label>"
											+ "</div>")
				}
			});
}

function alterarSenha() {

	var senhaAtual = $("#campoSenhaAtual").val();
	var novaSenha = $("#campoNovaSenha").val();
	var confirmarNovaSenha = $("#campoConfirmarNovaSenha").val();

	$("#mensagemSenhaNaoAlterada").empty();
	
		$.ajax({
				type : "GET",
				url : "http://localhost:8083/sistemacontroledegastosweb/rest/sistemacontroledegastoslogin/" + senhaAtual + "/" + novaSenha + "/" + confirmarNovaSenha,
				contentType : "application/json; charset=UTF-8",
				dataType : "text",
				success : function(result) {

					$("#alterarSenha").empty();

					if (result == "OK") {
						window.location.assign("http://localhost:8083/sistemacontroledegastosweb/views/login.html");
					} else {
						$("#mensagemSenhaNaoAlterada").append(
										"<div id=\"alterarSenha\">"
												+"<p>"
												+ "<label for=\"mensagemSenhaNaoAlterada\" id=\"mensagem\">Usuário ou Senha inválido.</label>"
												+ "</p>"
												+ "</div>")
					}
				},
				error : function(erro) {
					$("#mensagemSenhaNaoAlterada").append(
							"<div id=\"alterarSenha\">"
									+ "<p>"
									+ "<label for=\"mensagemSenhaNaoAlterada\" id=\"mensagem\">Há algum campo em branco.</label>"
									+ "</p>"
									+ "</div>")
				}
			});
}

function cadastrarUsuario() {

	var novoUsuario = $("#novoUsuario").val();
	var cadastrarSenha = $("#cadastrarSenha").val();
	var confirmarNovaSenha = $("#campoConfirmarNovaSenha").val();

	$("#mensagemUsuarioNaoCadastrado").empty();
	
		$.ajax({
				type : "GET",
				url : "http://localhost:8083/sistemacontroledegastosweb/rest/sistemacontroledegastoscadastro/" + novoUsuario + "/" + cadastrarSenha + "/" + confirmarNovaSenha,
				contentType : "application/json; charset=UTF-8",
				dataType : "text",
				success : function(result) {

					$("#mensagemUsuarioNaoCadastrado").empty();

					if (result == "OK") {
						window.location.assign("http://localhost:8083/sistemacontroledegastosweb/views/login.html");
					} else {
						$("#mensagemUsuarioNaoCadastrado").append(
										"<div>"
												+"<p>"
												+ "<label for=\"mensagemSenhaNaoAlterada\" id=\"mensagem\">Não foi possível fazer o cadastrado</label>"
												+ "</p>"
												+ "</div>")
					}
				},
				error : function(erro) {
					$("#mensagemUsuarioNaoCadastrado").append(
							"<div>"
									+ "<p>"
									+ "<label for=\"mensagemSenhaNaoAlterada\" id=\"mensagem\">Não foi possível cadastrar</label>"
									+ "</p>"
									+ "</div>")
				}
			});
}


$(document).ready(function() {
	
	dontBack();
	
	$('.carousel.carousel-slider').carousel({fullWidth: true});
	autoplay();
	function autoplay(){
		$('.carousel.carousel-slider').carousel('next');
		setTimeout(autoplay, 12000);
	}
	$(function(){
	    $('#itemDropDown li').click(function(){
	        var val = $(this).attr('id');     
	     init(val);
	    });
	});
});


function tabela(){
	$("#tabelaDeGastos").empty();
	$("#tabelaDeGastos").append(
			"<table class=\"responsive-table highlight centered z-depth-2\" cellspacing=0 cellpadding=2>"
			+"<thead>"
				+"<tr>"
					+"<th id=\"idGastos\">ID</th>"
					+"<th id=\"aplicacaoGastos\">APLICACAO</th>"
					+"<th id=\"valorGastos\">VALOR</th>"
					+"<th id=\"mesGastos\">MES</th>"
					+"<th id=\"anoGastos\">ANO</th>"
					+"<th id=\"observacaoGastos\">OBSERVACAO</th>"
					+"<th id=\"btnAlterar\"></th>"
				+"</tr>"
			+"</thead>"
			+"<tbody>"
			+"</tbody>"
		+"</table>"
	)
}

function limparCampos(){
	$("#aplicacao").val("");
	$("#valor").val("");
	$("#mes").val("");
	$("#ano").val("");	
	$("#observacao").val("");	
}


function dontBack(){
			(function(window) { 
	  'use strict'; 
	 
	var noback = { 
		 
		//globals 
		version: '0.0.1', 
		history_api : typeof history.pushState !== 'undefined', 
		 
		init:function(){ 
			window.location.hash = '#no-back'; 
			noback.configure(); 
		}, 
		 
		hasChanged:function(){ 
			if (window.location.hash == '#no-back' ){ 
				window.location.hash = '#bloqued';
				//mostra mensagem que não pode usar o btn volta do browser
				if($( "#msgAviso" ).css('display') =='none'){
					$( "#msgAviso" ).slideToggle("slow");
				}
			} 
		}, 
		 
		checkCompat: function(){ 
			if(window.addEventListener) { 
				window.addEventListener("hashchange", noback.hasChanged, false); 
			}else if (window.attachEvent) { 
				window.attachEvent("onhashchange", noback.hasChanged); 
			}else{ 
				window.onhashchange = noback.hasChanged; 
			} 
		}, 
		 
		configure: function(){ 
			if ( window.location.hash == '#no-back' ) { 
				if ( this.history_api ){ 
					history.pushState(null, '', '#bloqued'); 
				}else{  
					window.location.hash = '#bloqued';
					//mostra mensagem que não pode usar o btn volta do browser
					if($( "#msgAviso" ).css('display') =='none'){
						$( "#msgAviso" ).slideToggle("slow");
					}
				} 
			} 
			noback.checkCompat(); 
			noback.hasChanged(); 
		} 
		 
		}; 
		 
		// AMD support 
		if (typeof define === 'function' && define.amd) { 
			define( function() { return noback; } ); 
		}  
		// For CommonJS and CommonJS-like 
		else if (typeof module === 'object' && module.exports) { 
			module.exports = noback; 
		}  
		else { 
			window.noback = noback; 
		} 
		noback.init();
	}(window)); 
}


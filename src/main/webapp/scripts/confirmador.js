/**
 *  Confirmação de exclusão de um contato
 */
 
 function confirmar(idCon) {
	let resposta = confirm("Confirma a exclusão deste contato ?")
	if (resposta === true) {
		window.location.href="delete?idCon=" + idCon
	}
}
 

$('email').change(function(){
	verif('#email', '#emailDiv');	
});

$( "#email" ).focusout(function() {
	verif('#email', '#emailDiv');
});

$('#nom').change(function(){
	verif('#nom', '#nomDiv');	
});

$( "#nom" ).focusout(function() {
	verif('#nom', '#nomDiv');
});

$('#prenom').change(function(){
	verif('#prenom', '#prenomDiv');	
});

$( "#prenom" ).focusout(function() {
	verif('#prenom', '#prenomDiv');
});

$('#telephone').change(function(){
	verif('#telephone', '#telephoneDiv');	
});

$( "#telephone" ).focusout(function() {
	verif('#telephone', '#telephoneDiv');
});

$('#role').change(function(){
	verif('#role', '#roleDiv');	
});

$( "#role" ).focusout(function() {
	verif('#role', '#roleDiv');
});

$('#password').change(function(){
	verif('#password', '#passwordDiv');	
});

$( "#password" ).focusout(function() {
	verif('#password', '#passwordDiv');
});

$('#confirmation').change(function(){
	verif('#confirmation', '#confirmationDiv');	
});

$( "#confirmation" ).focusout(function() {
	verif('#confirmation', '#confirmationDiv');
});

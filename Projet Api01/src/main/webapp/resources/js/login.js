
$('#email').change(function(){
	verif('#email', '#emailDiv');	
});

$( "#email" ).focusout(function() {
	verif('#email', '#emailDiv');
});

$('#password').change(function(){
	verif('#password', '#passwordDiv');	
});

$( "#password" ).focusout(function() {
	verif('#password', '#passwordDiv');
});

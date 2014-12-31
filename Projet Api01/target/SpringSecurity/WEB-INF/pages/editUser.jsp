<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<LINK rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap/css/bootstrap.min.css">

<html>
<head>
	<title>Gestion des utilisateurs</title>
</head>
<body>


<h1>Modification d'un utilisateur</h1>
    	<form:form commandName="user" class="form-horizontal" role="form" action='${pageContext.request.contextPath}/saveEdit'>
    	  <form:hidden path="idUtilisateur"/>
    	  <div class="form-group">
		    <label for="labelNom" class="col-sm-2 control-label">Nom</label>
		    <div class="col-sm-10">
		      <form:input path="nom" type="text" class="form-control" id="inputNom" placeholder="Nom"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="labelPrenom" class="col-sm-2 control-label">Prénom</label>
		    <div class="col-sm-10">
		      <form:input path="prenom" type="text" class="form-control" id="inputPrenom" placeholder="Prenom"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
		    <div class="col-sm-10">
		      <form:input path="email" type="email" class="form-control" id="inputEmail3" placeholder="Email"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="labelAdresse" class="col-sm-2 control-label">Adresse</label>
		    <div class="col-sm-10">
		      <form:input path="adresse" type="text" class="form-control" id="inputAdresse" placeholder="Adresse"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="labelPass" class="col-sm-2 control-label">Mot de passe</label>
		    <div class="col-sm-10">
		      <form:input path="password" type="password" class="form-control" id="inputPass" placeholder="Mot de passe" />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="labelConfirm" class="col-sm-2 control-label">Confirmation</label>
		    <div class="col-sm-10">
		      <input type="password" class="form-control" id="inputConfirm" placeholder="Confirmation">
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">Sauvegarder</button>
		    </div>
		  </div>
		</form:form>
</body>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.min.js">
	
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/css/bootstrap/js/bootstrap.min.js">
	
</script>
</html>

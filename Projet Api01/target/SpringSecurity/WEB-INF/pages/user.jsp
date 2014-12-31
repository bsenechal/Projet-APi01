<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<LINK rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.min.css">

<html>
<head>
	<title>Gestion des utilisateurs</title>
</head>
<body>


<h1>Gestion des utilisateurs</h1>


<div role="tabpanel">

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Liste des utilisateurs</a></li>
    <li role="presentation"><a href="#ajout" aria-controls="ajout" role="tab" data-toggle="tab">Ajout d'un utilisateur</a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="home">
    	<c:if test="${!empty listUsers}">
			<div class="table-responsive">
			  <table class="table">
			    <tr>
					<th>ID Utilisateur</th>
					<th>Nom</th>
					<th>Prenom</th>
					<th>Adresse</th>
					<th>Email</th>
					<th>Telephone</th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach items="${listUsers}" var="user">
					<tr>
						<td>${user.username}</td>
						<td>${user.firstname}</td>
						<td>${user.lastname}</td>
						<td>${user.email}</td>
						<td><a href="<c:url value='/edit/${user.username}' />" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
						<td><a href="<c:url value='/remove/${user.username}' />" ><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></a></td>
					</tr>
				</c:forEach>
			  </table>
			</div>
		</c:if>    
    </div>
    <div role="tabpanel" class="tab-pane" id="ajout">
    	<form:form  commandName="user" id="user-form" class="form-horizontal" role="form" action='${pageContext.request.contextPath}/admin/user/add'>
    	  <div class="form-group">
		    <label for="labelLogin" class="col-sm-2 control-label">Login</label>
		    <div class="col-sm-10">
		      <form:input path="username" type="text" class="form-control" id="inputLogin" placeholder="Login"/>
		    </div>
		  </div>
    	  <div class="form-group">
		    <label for="labelNom" class="col-sm-2 control-label">Nom</label>
		    <div class="col-sm-10">
		      <form:input path="lastname" type="text" class="form-control" id="inputNom" placeholder="Nom"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="labelPrenom" class="col-sm-2 control-label">Prénom</label>
		    <div class="col-sm-10">
		      <form:input path="firstname" type="text" class="form-control" id="inputPrenom" placeholder="Prenom"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
		    <div class="col-sm-10">
		      <form:input path="email" type="email" class="form-control" id="inputEmail3" placeholder="Email"/>
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
		      <button type="submit" class="btn btn-default">Ajout</button>
		    </div>
		  </div>
		</form:form>
    
    </div>
  </div>

</div>

</body>





<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.min.js">
</script>

</script>
</html>

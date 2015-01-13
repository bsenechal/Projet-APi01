<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<jsp:include page="header.jsp"/>

<div class="content">
	<div class="wrap">
		<div class="single-page">
			<jsp:include page="messages.jsp"/>
			
			<div class="page-header">
				<h1>
					<c:choose>
						<c:when test="${user.idUser == 0}">Ajout d'un utilisateur</c:when>
						<c:otherwise>Modification d'un utilisateur</c:otherwise>
					</c:choose>
				</h1>
			</div>	
			
   			<form:form commandName="user" class="form-horizontal" role="form" action="${url}">
		   		<form:input path="idUser" type="hidden" name="idUser" value="${user.idUser}" />
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-10">
							<form:input path="email" type="email" class="form-control" id="inputEmail3" placeholder="Email"/>
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
							<form:input path="firstname" type="text" class="form-control" id="inputPrenom" placeholder="Prénom"/>
						</div>
					</div>
					<div class="form-group">
						<label for="labelTelephone" class="col-sm-2 control-label">Téléphone</label>
						<div class="col-sm-10">
							<form:input path="telephone" type="text" class="form-control" id="inputTelephone" placeholder="Téléphone"/>
						</div>
					</div>
					<div class="form-group">
						<label for="labelRole" class="col-sm-2 control-label">Role</label>
						<div class="col-sm-10">
							<form:input path="role" type="text" class="form-control" id="inputRole" placeholder="Role"/>
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
							<form:input path="confirmation" type="password" class="form-control" id="inputConfirm" placeholder="Confirmation"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary">
							<c:choose>
								<c:when test="${user.idUser == 0}">Ajouter</c:when>
								<c:otherwise>Sauvegarder</c:otherwise>
							</c:choose>
							</button>
						</div>
					</div>
			</form:form>
		</div>
	</div>
</div>
</body>
</html>

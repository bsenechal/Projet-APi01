<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>


<jsp:include page="header.jsp"/>

<section class="main clearfix">	

	<section class="top">	
			<div class="wrapper content_header clearfix">
				<div class="work_nav">
							
					<ul class="btn clearfix">
						<li><a href="#" class="previous" data-title="Previous"></a></li>
						<li><a href="index.html" class="grid" data-title="Portfolio"></a></li>
						<li><a href="#" class="next" data-title="Next"></a></li>
					</ul>							
					
				</div><!-- end work_nav -->
				<h1 class="title">Gestion des utilisateurs</h1>
			</div>		
		</section><!-- end top -->
	<section class="wrapper">
		<div class="content">
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
								<th>Email</th>
								<th>Nom</th>
								<th>Prénom</th>
								<th>Téléphone</th>
								<th>Date de création</th>
								<th>Rôle</th>
								<th></th>
								<th></th>
							</tr>
							<c:forEach items="${listUsers}" var="user">
								<tr>
									<td>${user.email}</td>
									<td>${user.firstname}</td>
									<td>${user.lastname}</td>
									<td>${user.telephone}</td>
									<td>${user.creationDate}</td>
									<td>${user.role}</td>
									<td><a href="<c:url value='/admin/edit/${user.idUser}' />" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
									<td><a href="<c:url value='/admin/remove/${user.idUser}' />" ><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></a></td>
								</tr>
							</c:forEach>
						  </table>
						</div>
					</c:if>    
			    </div>
			    <div role="tabpanel" class="tab-pane" id="ajout">
			    	<br />
			    	<div class="container-fluid">
				    	<form:form  commandName="user" id="user-form" class="form-horizontal" role="form" action='${pageContext.request.contextPath}/admin/user/add'>
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
						      <button type="submit" class="btn btn-primary">Ajout</button>
						    </div>
						  </div>
						</form:form>
			    	</div>
			    </div>
			  </div>
			</div>
		</div>
	</section>
</section>
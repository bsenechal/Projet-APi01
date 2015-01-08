<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
				<h1 class="title">Modification de l'utilisateur "${user.firstname} ${user.lastname}"</h1>
			</div>		
		</section><!-- end top -->
	<section class="wrapper">
		<div class="content">
    	<form:form commandName="user" class="form-horizontal" role="form" action='${pageContext.request.contextPath}/admin/saveEdit'>
		    <input type="hidden" name="idUser" value="${user.idUser}" />
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
						      <button type="submit" class="btn btn-primary">Sauvegarder</button>
						    </div>
						  </div>
		</form:form>
		</div>
		</section>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<jsp:include page="header.jsp"/>

<div class="content">
	<div class="wrap">
		<div class="single-page">
		
			<jsp:include page="messages.jsp"/>
			
			<div class="page-header">
				<h1>Connexion</h1>
			</div>	
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-6 col-md-offset-3">.
						<div class="jumbotron">
							<div class="container-fluid">
								<form name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								  <div class="form-group">
								    <label for="inputEmail">Email</label>
								    <input type="email" name="username" class="form-control" id="inputEmail" placeholder="Email">
								  </div>
								  <div class="form-group">
								    <label for="exampleInputPassword1">Mot de passe</label>
								    <input type="password" name="password" class="form-control" id="inputPassword" placeholder="Mot de passe">
								  </div>
								  <button type="submit" name="submit" class="btn btn-primary">Connexion</button>
								</form>	 
							</div> 
						</div>
					</div>
				</div>
			</div>
		</div>	
	</div>
</div>
</body>

</html>
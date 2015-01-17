<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>


<jsp:include page="header.jsp"/>

<div class="content">
	<div class="wrap">
		<div class="single-page">
			
			<jsp:include page="messages.jsp"/>
			
			<div class="page-header">
				<h1>Gestion des evaluations</h1>
			</div>	
							
			<c:if test="${!empty listEvals}">
				<div class="table-responsive">
				  <table class="table">
				    <tr>
						<th><b>Status</b></th>
						<th><b>Livre</b></th>
						<th><b>Utilisateur</b></th>
						<th></th>
						<th></th>
					</tr>
					<c:forEach items="${listEvals}" var="eval">
						<tr>
							<td>${eval.status}</td>
							<td>${eval.book.title}</td>
							<td>${eval.user.email}</td>
							<td><a href="<c:url value='/admin/evaluation/edit/${eval.idEval}' />" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
							<td><a href="<c:url value='/admin/evaluation/remove/${eval.idEval}' />" ><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></a></td>
						</tr>
					</c:forEach>
				  </table>
				</div>
			</c:if>    
    	</div>
    	<jsp:include page="footer.jsp"/>
  	</div>
</div>
	
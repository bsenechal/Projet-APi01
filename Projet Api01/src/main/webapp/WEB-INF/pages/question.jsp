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
						
			<ul class="list-group">
			  <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/questions/addQuestion"><span class="glyphicon glyphicon-plus"></span> Ajouter une Question</a></li>
			</ul>	
			
			<c:if test="${!empty listQuestions}">
				<div class="table-responsive">
				  <table class="table">
				    <tr>
						<th><b>Titre</b></th>
						<th><b>Note maximale</b></th>
						<th><b>Importance</b></th>
						<th></th>
						<th></th>
					</tr>
					<c:forEach items="${listQuestions}" var="quest">
						<tr>
							<td>${quest.libelle}</td>
							<td>${quest.valMax}</td>
							<td>${quest.ponderation}</td>
							<td><a href="<c:url value='/admin/questions/edit/${quest.idQuestions}' />" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
							<td><a href="<c:url value='/admin/questions/remove/${quest.idQuestions}' />" ><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></a></td>
						</tr>
					</c:forEach>
				  </table>
				</div>
			</c:if>    
    	</div>
    	<jsp:include page="footer.jsp"/>
  	</div>
</div>
	
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
				<h1>Ajout d'une Evaluation</h1>
			</div>	
			<h2>${book.title}</h2>
			<p>${book.description}</p>
			<div class="container-fluid">
					<form:form id="question-form" class="form-horizontal" action="${pageContext.request.contextPath}/evaluation/save/${book.idBook}" method="POST" modelAttribute="questionWrapper">

					    <c:forEach items="${questionWrapper.questionList}" var="quest" varStatus="i">
					       <div class="form-group">        
                               <label  for="libelle" class="col-sm-2 control-label">${quest.libelle}</label>
					           <div class="col-sm-1">
						           <form:input class="form-control" path="questionList[${i.index}].note" type="number"/>
						           <form:hidden path="questionList[${i.index}].idQuestions"/>
					           </div>
					       </div>
					    </c:forEach>
					
					
					    <div class="col-sm-offset-2 col-sm-10">
	                        <button type="submit" class="btn btn-primary">Sauvegarder</button>
                        </div>
					</form:form>
				</div>
    	</div>
    	<jsp:include page="footer.jsp"/>
  	</div>
 </div>	
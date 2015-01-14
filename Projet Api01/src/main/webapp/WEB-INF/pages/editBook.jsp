<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page session="true"%>

<jsp:include page="header.jsp"/>

<div class="content">
	<div class="wrap">
		<div class="single-page">
			<jsp:include page="messages.jsp"/>
			
			<div class="page-header">
				<h1>
					<c:choose>
						<c:when test="${book.idBook == 0}">Ajout d'un livre</c:when>
						<c:otherwise>Modification d'un livre</c:otherwise>
					</c:choose>
				</h1>	
			</div>	

			<div class="container-fluid">
				<form:form commandName="book" id="book-form" class="form-horizontal" role="form" action="${url}">
					<div id="titleDiv" class="form-group">
						<label for="title" class="col-sm-2 control-label">Title</label>
						<div class="col-sm-10">
							<form:input path="title" type="text" class="form-control" id="title" placeholder="${book.title}"/>
						</div>
					</div>
					<div id="autorDiv" class="form-group">			    
						<label  for="autor" class="col-sm-2 control-label">Author</label>
						<div class="col-sm-10">
						<form:input path="autor" type="text" class="form-control" id="autor" placeholder="${book.autor}"/>
						</div>
					</div>
					<div id="typeDiv" class="form-group">			    
						<label for="type" class="col-sm-2 control-label">Type</label>
						<div class="col-sm-10">
						<form:input path="type" type="text" class="form-control" id="type" placeholder="${book.type}"/>
						</div>
					</div>
					<div id="imageDiv" class="form-group">			    
						<label for="image" class="col-sm-2 control-label">Image</label>
						<div class="col-sm-10">
						<form:input path="image" type="file" class="form-control" id="image" placeholder="${book.image}"/>
						</div>
					</div>
					<div id="descriptionDiv" class="form-group">			    
						<label for="description" class="col-sm-2 control-label">Description</label>
						<div class="col-sm-10">
						<form:input path="description" type="textarea" class="form-control" id="description" placeholder="${book.type}"/>
						</div>
					</div>
					
					<form:hidden path="idBook"/>
										
					
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">
							<c:choose>
								<c:when test="${book.idBook == 0}">Ajouter</c:when>
								<c:otherwise>Sauvegarder</c:otherwise>
							</c:choose>
						</button>
					</div>
				</form:form>
			</div>
			<nav>
			  <ul class="pager">
			    <li><a href="${pageContext.request.contextPath}/book/listing">Précédent</a></li>
			  </ul>
			</nav>
		</div>
		<jsp:include page="footer.jsp"/>
	</div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/editBook.js"></script>
</html>
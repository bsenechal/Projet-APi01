<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
			<!-- Tab panes -->
			  <div class="tab-content">
			    <div role="tabpanel" class="tab-pane active" id="home">
			    	<c:if test="${!empty listBooks}">
						<div class="table-responsive">
							<table class="table">
								<tr>
<!-- 									<th>Id</th> -->
									<th>Title</th>
									<th>Name Author</th>
									<th>Name Type</th>
									</tr>
								<c:forEach items="${listBooks}" var="boook">
								<tr>
<%-- 									<td>${boook.idBook}</td> --%>
									<td>${boook.title}</td>
									<td>${boook.autor}</td>
									<td>${boook.type}</td>
									<td><a href="<c:url value='/book/edit/${boook.idBook}' />" >editer</a></td>
								</tr>
								</c:forEach>
							</table>
						</div>
					</c:if>    
			    </div>
			    <div role="tabpanel" class="tab-pane" id="ajout">
			    	<br />
			    	<div class="container-fluid">
				    	<form:form  commandName="book" id="book-form" class="form-horizontal" role="form" action='${pageContext.request.contextPath}/book/add'>
				    	   <div class="form-group">
						    <label for="title" class="col-sm-2 control-label">Title</label>
						    <div class="col-sm-10">
						      <form:input path="title" type="text" class="form-control" id="title" placeholder="title"/>
						    </div>
						    
						    <label for="autor" class="col-sm-2 control-label">Author</label>
						    <div class="col-sm-10">
						      <form:input path="autor" type="text" class="form-control" id="autor" placeholder="author"/>
						    </div>
						    
						    <label for="type" class="col-sm-2 control-label">Type</label>
						    <div class="col-sm-10">
						      <form:input path="type" type="text" class="form-control" id="type" placeholder="type"/>
						    </div>
							
				            </div>
						    <div class="col-sm-offset-2 col-sm-10">
						      <button type="submit" class="btn btn-primary">Ajout</button>
						    </div>
						  </div>
						</form:form>
			    	</div>
			    </div>
			  </div>
			</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>Insert title here</title>
</head>
<body>

<div class="container-fluid">
	<form:form  commandName="book" id="book-form" class="form-horizontal" role="form" action='${pageContext.request.contextPath}/book/update'>
	  <div class="form-group">
	   <label for="title" class="col-sm-2 control-label">Title</label>
	   <div class="col-sm-10">
	     <form:input path="title" type="text" class="form-control" id="title" placeholder="${book.title}"/>
	   </div>
						    
			<label for="autor" class="col-sm-2 control-label">Author</label>
			<div class="col-sm-10">
			  <form:input path="autor" type="text" class="form-control" id="autor" placeholder="${book.autor}"/>
			</div>
						    
			<label for="type" class="col-sm-2 control-label">Type</label>
			<div class="col-sm-10">
			  <form:input path="type" type="text" class="form-control" id="type" placeholder="${book.type}"/>
			</div>
			
			<form:hidden path="idBook"/>
							
		</div>
		
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-primary">UPDATE</button>
		</div>
	</form:form>
</div>

</body>
</html>
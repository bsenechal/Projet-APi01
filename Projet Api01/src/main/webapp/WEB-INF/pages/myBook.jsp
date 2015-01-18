<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

    <c:if test="${!empty notes}">
       <div class="table-responsive">
           <table class="table">
               <tr>
                   <th><b>Titre</b></th>
                   <th><b>Auteur</b></th>
                   <th><b>Type</b></th>
                   <th></th>
                   <sec:authorize access="hasRole('ROLE_ADMIN')"><th></th></sec:authorize>
               </tr>
               <c:forEach items="${notes}" var="note">
                   <tr>
                       <td>${note.evaluation.book.title}</td>
                       <td>${note.evaluation.book.autor}</td>
                       <td>${note.evaluation.book.type}</td>
                   </tr>
               </c:forEach>
           </table>
       </div>
   </c:if>

</body>
</html>
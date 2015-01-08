<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>

<jsp:include page="header.jsp"/>

<body>
<section class="main clearfix">	
	<div class="content">
		<h2>Page d'administration</h2>
		
		<ul class="list-group">
		  <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/users">Gestion des utilisateurs</a></li>
		</ul>
	</div>
</section>
</body>

</html>
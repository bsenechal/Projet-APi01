<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>

<jsp:include page="header.jsp"/>

<body>
<div class="content">
	<div class="wrap">
		<div class="single-page">
		
			<jsp:include page="messages.jsp"/>
			
			<div class="page-header">
				<h1>Page d'administration</h1>
			</div>
			
			<ul class="list-group">
			  <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/users">Gestion des utilisateurs</a></li>
			</ul>
		</div>
	</div>
</div>
</body>

</html>
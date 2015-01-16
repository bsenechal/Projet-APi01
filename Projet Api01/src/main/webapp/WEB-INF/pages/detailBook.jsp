<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page session="true"%>

<jsp:include page="header.jsp"/>
		<!---start-content---->
		<div class="content">
			<div class="wrap">
			<div class="single-page">
				<jsp:include page="messages.jsp"/>
							<div class="single-page-artical">
								<div class="artical-content">
									<img src="${pageContext.request.contextPath}/imageDisplay/${book.idBook}">
									<h3><a href="#">${book.title}</a></h3>
									<p>${book.description}</p>
									</div>
		  						 <div class="clear"> </div>
							</div>
						</div>
						 </div>
		</div>
		<jsp:include page="footer.jsp"/>
		<!---//End-wrap---->
	</body>
</html>


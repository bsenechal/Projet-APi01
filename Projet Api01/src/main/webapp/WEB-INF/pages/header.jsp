<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
	<title>Magnetic - Stunning Responsive HTML5/CSS3 Photography Template</title>
	<meta charset="utf-8">
	<meta name="author" content="pixelhint.com">
	<meta name="description" content="Magnetic is a stunning responsive HTML5/CSS3 photography/portfolio website  template"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/reset.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
    <LINK rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/css/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

	<header>
		<div class="logo">
			<a href="index.html"><img src="${pageContext.request.contextPath}/resources/img/logo.png" title="Magnetic" alt="Magnetic"/></a>
		</div><!-- end logo -->

		<div id="menu_icon"></div>
		<nav>
			<ul>
				<li><a href="index.html">Home</a></li>
				<li><a href="#">About</a></li>
				<li><a href="#">The Team</a></li>
				<li><a href="#">Journal</a></li>
				<li><a href="contact.html">Contact Us</a></li>
				<li><c:url value="/j_spring_security_logout" var="logoutUrl" /></li>
			</ul>
		</nav><!-- end navigation menu -->

		<div class="footer clearfix">
			<ul class="social clearfix">
				<li><a href="#" class="fb" data-title="Facebook"></a></li>
				<li><a href="#" class="google" data-title="Google +"></a></li>
				<li><a href="#" class="behance" data-title="Behance"></a></li>
				<!--<li><a href="#" class="twitter" data-title="Twitter"></a></li>
				<li><a href="#" class="dribble" data-title="Dribble"></a></li>-->
				<li><a href="#" class="rss" data-title="RSS"></a></li>
			</ul><!-- end social -->

			<div class="rights">
				<p>Copyright © 2014 magnetic.</p>
				<p>Template by <a href="">Pixelhint.com</a></p>
			</div><!-- end rights -->
		</div ><!-- end footer -->
	</header><!-- end header -->

	
</body>
</html>
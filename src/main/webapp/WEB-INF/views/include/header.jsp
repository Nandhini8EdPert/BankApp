<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

.topnav {
	position: relative;
	overflow: hidden;
	background-color: #333;
}

.topnav a {
	float: left;
	display: block;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.topnav a:hover {
	background-color: #ddd;
	color: black;
}

.topnav a.active {
	background-color: #04AA6D;
	color: white;
}

.topnav-centered a {
	float: none;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	
}

.topnav-right {
	float: right;
}

/* Responsive navigation menu (for mobile devices) */
@media screen and (max-width: 600px) {
	.topnav a, .topnav-right {
		float: none;
		display: block;
	}
	.topnav-centered a {
		position: relative;
		top: 0;
		left: 0;
		transform: none;
	}
}
</style>
</head>
<body>
	<s:url var="url_logout" value="/logout" />
	<s:url var="url_user_dashboard" value="/user/dashboard" />
	<s:url var="url_admin_dashboard" value="/admin/dashboard" />
	<s:url var="url_login" value="/index" />
	<s:url var="url_reg_form" value="/reg_form" />
	<s:url var="url_about" value="/aboutLink" />
	
	<!-- Top navigation -->
	<div class="topnav">
		<!-- Centered link -->
		<div class="topnav-centered">
			<a href="#home" class="active">Bliss Bank</a>
		</div>
		<c:if test="${sessionScope.userId==null}">
			<%-- Guest User no one is logged in --%>

			<!-- Left-aligned links (default) -->
			<a href="${url_about }">About</a>


			<!-- Right-aligned links -->
			<div class="topnav-right">
				<a href="${url_login}">Login</a>
			</div>
		</c:if>

		<c:if
			test="${sessionScope.userId!=null && sessionScope.role eq 'MANAGER'}">
			<%-- Admin is logged in --%>

			<!-- Left-aligned links (default) -->
			<a href="${url_admin_dashboard}">Home</a>
			

			<!-- Right-aligned links -->
			<div class="topnav-right">
				<a href="${url_about }">About</a>
				<a href="#contact">Welcome Manager</a>
				 <a href="${url_logout}">Logout</a>
			</div>

		</c:if>
		<c:if
			test="${sessionScope.userId!=null && sessionScope.role eq 'CUSTOMER'}">
			<%-- General User is logged in --%>
			
		
		<!-- Left-aligned links (default) -->
		<a href="${url_user_dashboard}">Home</a>

		<!-- Right-aligned links -->
		<div class="topnav-right">
			<a href="${url_about }">About</a> <a href="${url_logout}">Logout</a>
		</div>
</c:if>
	</div>
</body>
</html>

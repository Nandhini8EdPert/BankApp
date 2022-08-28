<%@ page contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>About</title>
<s:url var="css" value="/resource/css/style.css" />
<link rel="stylesheet" href="${css}">
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>
	<h2 align="center">About</h2>



	<div class="box">
		<h2 align="center">Bliss Bank</h2>
		<p align="center">A good bank is not only the financial heart of
			the community, but also one with an obligation of helping in every
			possible manner to improve the economic conditions of the common
			people</p>

		<h4 align="center">Contact</h4>
		<p align="center">customercare@blissbank.com</p>
		<h2 align="center">Thank You</h2>
	</div>

</body>
<jsp:include page="include/footer.jsp"></jsp:include>
</html>
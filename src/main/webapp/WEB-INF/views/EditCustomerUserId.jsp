<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Customer</title>

<s:url var="css" value="/resource/css/style.css" />
<link rel="stylesheet" href="${css}">
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>
	<c:url var="url_editCustomer" value="/editCustomer"></c:url>
<h1 align="center">Edit customer</h1>
	<f:form method="post" action='${url_editCustomer}'
		modelAttribute="userId">
		<c:if test="${errorMsg!=null}">
				<p align="center" class="error">${errorMsg}</p>
			</c:if>
		<div class="box">
			<table class="box">

				<tr>
					<td>User Id</td>
					<td><f:input path="userId" required="true"/></td>
				</tr>

				<tr>
					<td><button>Edit Customer</button></td>
					<td><button type="reset">Reset</button></td>
				</tr>
			</table>
		</div>
	</f:form>
</body>
<jsp:include page="include/footer.jsp"></jsp:include>
</html>
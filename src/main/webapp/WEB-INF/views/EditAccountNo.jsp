<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Account</title>

<s:url var="css" value="/resource/css/style.css" />
<link rel="stylesheet" href="${css}">
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>
	<c:url var="url_editAccount" value="/editAccount"></c:url>
<h1 align="center">Edit Account</h1>
	<f:form method="post" action='${url_editAccount}'
		modelAttribute="accountNo">
		<c:if test="${errorMsg!=null}">
				<p align="center" class="error">${errorMsg}</p>
			</c:if>
		<div class="box">
			<table class="box">

				<tr>
					<td>Account_Number</td>
					<td><f:input path="accountNo" required="true"/></td>
				</tr>

				<tr>
					<td><button >submit</button></td>
					<td><button type="reset">Reset</button></td>
				</tr>
			</table>
		</div>
	</f:form>
</body>
<jsp:include page="include/footer.jsp"></jsp:include>
</html>
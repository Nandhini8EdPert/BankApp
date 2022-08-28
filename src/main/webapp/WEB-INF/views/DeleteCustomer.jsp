<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Customer</title>

<s:url var="css" value="/resource/css/style.css" />
<link rel="stylesheet" href="${css}">
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>
	<c:url var="url_deleteCustomer" value="/deleteCustomer"></c:url>
	<h1 align="center">Delete Customer</h1>
	<f:form method="post" action='${url_deleteCustomer}'
		modelAttribute="userId" onsubmit="deleteAccount()">
		<c:if test="${errorMsg!=null}">
			<p align="center" class="error">${errorMsg}</p>
		</c:if>
		<c:if test="${successMsg!=null}">
			<p align="center" class="success">${successMsg}</p>
		</c:if>
		<div class="box">
			<table class="box">

				<tr>
					<td>Customer_Id</td>
					<td><f:input path="userId" required="true"/></td>
				</tr>

				<tr>
					<td><button>submit</button></td>
					<td><button type="reset">Reset</button></td>
				</tr>
			</table>
		</div>
	</f:form>
	<script type="text/javascript">
		function deleteAccount() {
			alert("Are you sure, You want to delete?")
		}
	</script>
</body>
<jsp:include page="include/footer.jsp"></jsp:include>
</html>
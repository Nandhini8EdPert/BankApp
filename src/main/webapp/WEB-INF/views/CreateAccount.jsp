<%@ page contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Account Registration Form</title>

<s:url var="css" value="/resource/css/style.css" />
<link rel="stylesheet" href="${css}">
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>

	<h2 align="center">Account creation</h2>
	<c:if test="${errorMsg!=null}">
		<p align="center" class="error">${errorMsg}</p>
	</c:if>
	<c:if test="${successMsg!=null}">
		<p align="center" class="success">${successMsg}</p>
	</c:if>


	<c:url var="url_create" value="/createAccount"></c:url>

	<f:form method="post" action='${url_create}'
		modelAttribute="accountCommand">
		<div class="box">
			<table class="box">
				<tbody>
					<tr>
						<td>Customer_Id</td>
						<td><f:input path="customerId" required="true"/></td>
					</tr>
					<tr>
					<td>Account_Type</td>
						<td><f:select path="accountType" required="true">
						<f:option value="SAVINGS" label="Savings Account">Savings Account</f:option>
						 <f:option value="CURRENT" label="Current Account">Current Account</f:option>
							</f:select></td>
					</tr>
					<tr>
						<td>Initial_Deposit</td>
						<td><f:input path="initialDeposit" required="true"/></td>
					</tr>

					<tr>
						<td colspan="2"><button>Create Account</button></td>
						<td><button type="reset">Reset</button></td>
					</tr>
				</tbody>
			</table>
		</div>
	</f:form>
</body>
<jsp:include page="include/footer.jsp"></jsp:include>
</html>
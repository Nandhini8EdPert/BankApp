<%@ page contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Edit account</title>

<s:url var="css" value="/resource/css/style.css" />
<link rel="stylesheet" href="${css}">
<link rel="stylesheet" href="/bankingApp/resource/css/style.css">
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>

	<h2 align="center">Edit Account</h2>
	<c:if test="${errorMsg!=null}">
		<p align="center" class="error">${errorMsg}</p>
	</c:if>
	<c:if test="${successMsg!=null}">
		<p align="center" class="success">${successMsg}</p>
	</c:if>


	<c:url var="url_updateAccount" value="/updateAccount"></c:url>

	<f:form method="post" action='${url_updateAccount}'
		modelAttribute="command">
		<div class="box">
			<table class="box">
				<tbody>
					<tr>
						<td>Account Number</td>
						<td><f:input path="accountNo" readonly="true" value="${accountNumber.accountNo }"/></td>
					</tr>
					<tr>
						<td>Customer_Id</td>
						<td><f:input path="customerId" readonly="true" value="${userId }"/></td>
					</tr>
					<tr>
					<td>Account_Type</td>
						<td><f:select path="accountType" value="${ account.accountType}">
						<f:option value="SAVINGS" label="Savings Account">Savings Account</f:option>
						 <f:option value="CURRENT" label="Current Account">Current Account</f:option>
							</f:select></td>
					</tr>
					<tr>
						<td>Balance</td>
						<td><f:input path="initialDeposit" readonly="true" value="${account.balanceAmount}"/></td>
					</tr>

					<tr>
						<td colspan="2"><button>Update</button></td>
						<td><button type="reset">Reset</button></td>
					</tr>
				</tbody>
			</table>
		</div>
	</f:form>
</body>
<jsp:include page="include/footer.jsp"></jsp:include>
</html>
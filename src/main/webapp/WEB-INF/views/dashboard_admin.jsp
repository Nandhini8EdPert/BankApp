<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin DashboardConatct</title>

<s:url var="css" value="/resource/css/style.css" />
<link rel="stylesheet" href="${css}">
</head>
<body>
	<s:url var="url_editCustomer" value="/editCustomerUserLink" />
	<s:url var="url_newCustomer" value="/reg_form"/>
	<s:url var="url_editpassword" value="/editPasswordLink"/>
	<s:url var="url_newAccount" value="/newAccountLink"/>
	<s:url var="url_editAccount" value="/editAccountLink"/>
	<s:url var="url_deleteAccount" value="/deleteAccountLink"/>
	<s:url var="url_deleteCustomer" value="/deleteCustomerLink"/>
	<s:url var="url_depositLink" value="/depositLink"/>
	<s:url var="url_withdrawLink" value="/withdrawLink"/>
	<s:url var="url_transferLink" value="/fundTransferLink"/>
	<s:url var="url_balanceLink" value="/balanceLink"/>
	<s:url var="url_miniStatementLink" value="/miniStatementLinkManager"/>
	<s:url var="url_customStatementLink" value="/customizedStatementLink"/>

	<jsp:include page="include/header.jsp"></jsp:include>
	<h2 align="center">Admin Dashboard</h2>
	<table class="box">
		<tr>
			<td><a href="${url_newCustomer}" type="button">New Customer</a></td>
			<td><a href="${url_newAccount}" type="button">New Account</a>
		</td></tr>
		<tr>
			<td><a href="${url_editCustomer}" type="button">Edit Customer</a></td>
			<td><a href="${url_editAccount}" type="button">Edit Account</a></td>
		</tr>
		<tr>
			<td><a href="${url_deleteCustomer}" type="button">Delete Customer</a></td>
			<td><a href="${url_deleteAccount }" type="button">Delete Account</a></td>
		</tr>
		<tr>
			<td><a href="${url_depositLink}" type="button">Deposit</a></td>
			<td><a href="${url_withdrawLink}" type="button">Withdrawal</a></td>
		</tr>
		<tr>
			<td><a href="${url_transferLink}" type="button">Fund Transfer</a></td>
			<td><a href="${url_editpassword}" type="button">Change_Password</a></td>
		</tr>
		<tr>
			<td><a href="${url_balanceLink}" type="button">Balance Enquiry</a></td>
			<td><a href="${url_miniStatementLink}" type="button">Mini Statement</a></td>
		</tr>
		<tr>
			<td><a href="${url_customStatementLink }" type="button">Customized_Statement</a></td>
		</tr>

	</table>
	<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Dashboard Conatct</title>

<s:url var="css" value="/resource/css/style.css" />
<link rel="stylesheet" href="${css}">
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>
	<s:url var="url_editpassword" value="/editUserPasswordLink"/>
	<s:url var="url_fundTransfer" value="/fundTransferLinkUser"/>
	<s:url var="url_balanceLink" value="/balanceUserLink"/>
	<s:url var="url_miniStatementLink" value="/miniStatementLinkUser"/>
	<s:url var="url_customStatementLink" value="/customizedStatementUserLink"/>
	
	<h2 align="center">User Dashboard</h2>
	<table class="box">
		<tr>
			<td><a href="${url_balanceLink }" type="button">Balance enquiry</a></td>
		</tr>
		<tr>
			<td><a href="${url_fundTransfer}" type="button">Fund Transfer</a></td>
		</tr>
		<tr>
			<td><a href="${url_miniStatementLink}" type="button">Mini Statement</a></td>
		</tr>
		<tr>
			<td><a href="${url_customStatementLink}" type="button">Customized Statement</a></td>
		</tr>
		<tr>
			<td><a href="${url_editpassword}" type="button">Change Password</a></td>
		</tr>
	</table>
	<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>
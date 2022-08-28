<%@ page contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Fund Transfer</title>

<s:url var="css" value="/resource/css/style.css" />
<link rel="stylesheet" href="${css}">
<link rel="stylesheet" href="/bankingApp/resource/css/style.css">
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>

	<h2 align="center">Fund Transfer</h2>
	<c:if test="${errorMsg!=null}">
		<p align="center" class="error">${errorMsg}</p>
	</c:if>
	<c:if test="${successMsg!=null}">
		<p align="center" class="success">${successMsg}</p>
	</c:if>


	<c:url var="url_fundtransfer" value="/fundTransfer"></c:url>

	<f:form method="post" action='${url_fundtransfer}'
		modelAttribute="Command" onsubmit="onTransfer()">
		<div class="box">
			<table class="box">
				<tbody>
					<tr>
						<td>From account</td>
						<td><f:input path="fromAccount" required="true"/></td>
					</tr>
					<tr>
						<td>To account</td>
						<td><f:input path="toAccount" required="true"/></td>
					</tr>

					<tr>
						<td>Balance</td>
						<td><f:input path="amount" id="amount" required="true"/></td>
					</tr>

					<tr>
						<td>Description</td>
						<td><f:input path="description" /></td>
					</tr>

					<tr>
						<td colspan="2"><button>Fund_transfer</button></td>
						<td><button type="reset">Reset</button></td>
					</tr>
				</tbody>
			</table>
		</div>
	</f:form>
	<script type="text/javascript">
		function onTransfer() {
			var amount = document.getElementById("amount").value;
			alert(amount + " is going to be transferred");
		}
	</script>
</body>
<jsp:include page="include/footer.jsp"></jsp:include>
</html>
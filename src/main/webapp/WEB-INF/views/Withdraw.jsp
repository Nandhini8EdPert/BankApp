<%@ page contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Withdraw</title>

<s:url var="css" value="/resource/css/style.css" />
<link rel="stylesheet" href="${css}">
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>

	<h2 align="center">Withdraw</h2>
	<c:if test="${errorMsg!=null}">
		<p align="center" class="error">${errorMsg}</p>
	</c:if>
	<c:if test="${successMsg!=null}">
		<p align="center" class="success">${successMsg}</p>
	</c:if>


	<c:url var="url_withdraw" value="/withdraw"></c:url>

	<f:form method="post" action='${url_withdraw}'
		modelAttribute="Command" onsubmit="onWithdraw()">
		<div class="box">
			<table class="box">
				<tbody>
					<tr>
						<td>Account Number</td>
						<td><f:input path="accountNo" required="true"/></td>
					</tr>

					<tr>
						<td>Balance</td>
						<td><f:input path="amount" id="amount" required="true"/></td>
					</tr>

					<tr>
						<td>Description</td>
						<td><f:input path="description"  /></td>
					</tr>
					
					<tr>
						<td colspan="2"><button>Withdraw</button></td>
						<td><button type="reset">Reset</button></td>
					</tr>
				</tbody>
			</table>
		</div>
	</f:form>
	<script type="text/javascript">
		function onWithdraw() {
			var amount = document.getElementById("amount").value;
			alert(amount + " is going to be debited");
		}
	</script>
</body>
<jsp:include page="include/footer.jsp"></jsp:include>
</html>
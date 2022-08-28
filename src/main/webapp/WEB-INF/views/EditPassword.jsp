<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change password</title>

<s:url var="css" value="/resource/css/style.css" />
<link rel="stylesheet" href="${css}">
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>
<h1 align="center">Change password</h1>
	<c:url var="url_updatePassword" value="/updatePassword"></c:url>

	<f:form method="post" action='${url_updatePassword}'
		modelAttribute="passwordCommand" onsubmit="checkPassword()">
		<c:if test="${errorMsg!=null}">
			<p align="center" class="error">${errorMsg}</p>
		</c:if>
		<c:if test="${successMsg!=null}">
			<p align="center" class="success">${successMsg}</p>
		</c:if>
		<div class="box">
			<table class="box">
				<tr>
					<td>Account Number</td>
					<td><f:input path="accountNo" readonly="true"
							value="${accountNo.accountNo}" /></td>
				</tr>
				<tr>
					<td>Old Password</td>
					<td><f:password path="oldPassword" required="true"/>
				</tr>

				<tr>
					<td>New Password</td>
					<td><f:password id="np" name="np" path="newPassword" required="true" />
				</tr>

				<tr>
					<td>Confirm Password</td>
					<td><f:password id="cp" name="cp" path="confirmPassword"
							onchange="checkPassword()" required="true"/>
				</tr>


				<tr>
					<td colspan="2"><button>Update</button></td>
					<td><button type="reset">Reset</button>
				</tr>
			</table>

		</div>
	</f:form>
	<jsp:include page="include/footer.jsp"></jsp:include>
	<script type="text/javascript">
		function checkPassword() {
			var confirmPassword = document.getElementById("cp").value;
			var newPassword = document.getElementById("np").value;
			console.log(newPassword);
			console.log(confirmPassword);
			console.log(confirmPassword==newPassword);
			if (confirmPassword==newPassword) {

			} else {
				alert("Confirm Password differs from new password!");
			}

		}
	</script>
</body>
</html>
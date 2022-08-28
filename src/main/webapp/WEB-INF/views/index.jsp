<%@ page contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

<s:url var="css" value="/resource/css/style.css" />
<link rel="stylesheet" href="${css}">
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>
	<h2 align="center">User Login</h2>
		<c:if test="${err!=null}">
			<p class="error" align="center">${err}</p>
		</c:if>
		<c:if test="${param.act eq 'lo'}">
			<p class="success" align="center">Logout Successfully !</p>
		</c:if>

		<c:url var="user_url" value="/login"></c:url>


		<f:form action='${user_url}' modelAttribute="command">
			<div class="box">
				<table class="box">
					<tbody>
						<tr>
							<td>User id</td>
							<td><f:input path="userId" required="true"/></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><f:password path="password" required="true"/></td>
						</tr>
						<tr>
							<td colspan="25"><button>Login</button></td>
							<td>
								<button type="reset">Reset</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</f:form>

</body>
<jsp:include page="include/footer.jsp"></jsp:include>
</html>
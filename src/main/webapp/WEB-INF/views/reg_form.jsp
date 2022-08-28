<%@ page contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>User Registration Form</title>

<s:url var="css" value="/resource/css/style.css" />
<link rel="stylesheet" href="${css}">
<link rel="stylesheet" href="/bankingApp/resource/css/style.css">
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>

	<h2 align="center">User Registration</h2>
		<c:if test="${errorMsg!=null}">
			<p align="center" class="error">${errorMsg}</p>
		</c:if>
		<c:if test="${successMsg!=null}">
			<p align="center" class="success">${successMsg}</p>
			<p align="center" class="success">Your User Id:${userId }</p>
		</c:if>


		<c:url var="user_reg" value="/register"></c:url>

		<f:form method="post" action='${user_reg}' modelAttribute="user">
			<div class="box">
				<table class="box">
					<tbody>
						<tr>
							<td>User_Name</td>
							<td><f:input path="name" required="true"/></td>
						</tr>
						<tr>
							<td><f:label path="gender">Gender</f:label></td>
							<td><f:radiobutton path="gender" value="Male" label="Male" />
								<f:radiobutton path="gender" value="Female" label="Female" /></td>
						</tr>
						<tr>
							<td>Date_Of_Birth (yyyy-MM-dd)</td>
							<td><f:input path="dateOfBirth" required="true"/></td>
						</tr>
						<tr>
							<td>Phone_Number</td>
							<td><f:input path="telephoneNumber" required="true"/></td>
						</tr>
						<tr>
							<td>E-mail</td>
							<td><f:input path="emailId" required="true"/></td>
						</tr>
						<tr>
							<td>House_number</td>
							<td><f:input path="houseNo" required="true"/></td>
						</tr>
						<tr>
							<td>City</td>
							<td><f:input path="city" required="true"/></td>
						</tr>
						<tr>
							<td>State</td>
							<td><f:input path="state" required="true" /></td>
						</tr>
						<tr>
							<td>Pincode</td>
							<td><f:input path="pincode" required="true"/></td>
						</tr>
						<tr>
							<td colspan="2"><button>Register</button></td>
							<td><button type="reset">Reset</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</f:form>
</body>
<jsp:include page="include/footer.jsp"></jsp:include>
</html>
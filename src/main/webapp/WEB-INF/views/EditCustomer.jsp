<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Customer</title>

<s:url var="css" value="/resource/css/style.css" />
<link rel="stylesheet" href="${css}">
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>

	<c:url var="url_update" value="/updateCustomer"></c:url>
<h1 align="center">Edit Customer</h1>
	<f:form method="post" action='${url_update}'
		modelAttribute="editCustomer">
		<c:if test="${errorMsg!=null}">
				<p align="center" class="error">${errorMsg}</p>
			</c:if>
			<c:if test="${successMsg!=null}">
				<p align="center" class="success">${successMsg}</p>
			</c:if>
		<div class="box">
			<table class="box">
				<tr>
					<td>User Id</td>
					<td><f:input path="userId" readonly="true"
							value="${customer.userId }" /></td>
				</tr>
				<tr>
					<td>Customer Name</td>
					<td><f:input path="name" value="${customer.name}" />
				</tr>

				<tr>
					<td><f:label path="gender">Gender</f:label></td>
					<td><f:radiobutton path="gender" value="Male" label="Male" />
						<f:radiobutton path="gender" value="Female" label="Female" /></td>
				</tr>

				<tr>
					<td>Date of Birth(yyyy-MM-dd)</td>
					<td><f:input path="dateOfBirth"
							value="${customer.dateOfBirth}" />
				</tr>

				<tr>
					<td>House Number</td>
					<td><f:input path="houseNo" value="${customer.houseNo}" required="true"/>
				</tr>

				<tr>
					<td>City</td>
					<td><f:input path="city" value="${customer.city}" required="true"/>
				</tr>

				<tr>
					<td>State</td>
					<td><f:input path="state" value="${customer.state}" required="true"/>
				</tr>

				<tr>
					<td>Pincode</td>
					<td><f:input path="pincode" value="${customer.pincode}" required="true" />
				</tr>

				<tr>
					<td>Telephone Number</td>
					<td><f:input path="telephoneNumber"
							value="${customer.telephoneNumber}" required="true"/>
				</tr>

				<tr>
					<td>Email-Id</td>
					<td><f:input path="emailId" value="${customer.emailId}" required="true"/>
				</tr>
				<tr>
					<td colspan="2"><button>Update</button></td>
				</tr>
			</table>

		</div>
	</f:form>
	<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>
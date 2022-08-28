<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customized statement</title>

<s:url var="css" value="/resource/css/style.css" />
<link rel="stylesheet" href="${css}">
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>
	<c:url var="url_customStatement" value="/customizedStamentCallService"></c:url>
<h1 align="center">Customized Statement</h1>
	<f:form method="post" action='${url_customStatement}'
		modelAttribute="command">
		<c:if test="${transactions!=null}">
				<table class="box">
				<thead>
						<tr>
							<th scope="col">Deposit/Transaction</th>
							<th scope="col">Date_and_Time</th>
							<th scope="col">Amount</th>
							<th scope="col">Description</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="transaction" items="${transactions}">
							<tr>
								<td>${transaction.transactionType}</td>
								<td>${transaction.dateTime}</td>
								<td>${transaction.amount}</td>
								<td>${transaction.description }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			<c:if test="${msg!=null}">
				<p align="center" class="error">${msg}</p>
			</c:if>
			<c:if test="${errorMsg!=null}">
				<p align="center" class="error">${errorMsg}</p>
			</c:if>
			<c:if test="${transactions ==null}">
		<div class="box">
			<table class="box">

				<tr>
					<td>Account_Number</td>
					<td><f:input path="accountNo" required="true"/></td>
				</tr>
				<tr>
					<td>From_Date(yyyy-MM-dd)</td>
					<td><f:input path="fromDate" required="true"/></td>
				</tr>
				<tr>
					<td>To_Date(yyyy-MM-dd)</td>
					<td><f:input path="toDate" required="true"/></td>
				</tr>

				<tr>
					<td><button >submit</button></td>
					<td><button type="reset">Reset</button></td>
				</tr>
			</table>
		</div>
		</c:if>
	</f:form>
</body>
<jsp:include page="include/footer.jsp"></jsp:include>
</html>
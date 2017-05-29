<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="local"%>
<%@ page session="false"%>
<html>
<head>
<title>Person Page</title>
<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	border-color: #ccc;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #fff;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #f0f0f0;
}

.tg .tg-4eph {
	background-color: #f9f9f9
}
</style>
</head>
<body>
	<h1><local:message code="label.Add_Person" /></h1>

	<c:url var="addAction" value="/person/add"></c:url>
	<div class="form-group ${error != null ? 'has-error' : ''}">
		<form:form action="${addAction}" commandName="person">
			<table>
				<tr>
					<td><form:label path="name">
							<local:message code="label.Name" />
						</form:label></td>
					<td><spring:bind path="name">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<form:input type="text" path="name" class="form-control"
									 autofocus="true"></form:input>
								<form:errors path="name"></form:errors>
							</div>
						</spring:bind>
					</td>
				</tr>
				<tr>
					<td><form:label path="surname">
							<local:message code="label.Surname" />
						</form:label></td>
					<td><spring:bind path="surname">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<form:input type="text" path="surname" class="form-control"
									 autofocus="true"></form:input>
								<form:errors path="surname"></form:errors>
							</div>
						</spring:bind></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit"
						value="<local:message code="label.Add_Person" />" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>

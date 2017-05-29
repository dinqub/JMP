<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Error page</title>
</head>
<body>
	<div style="margin-top: 25%;
  margin-left: 35%;">
		<h1>Error page:</h1>		
	<c:if test="${not empty message}">
		<h1>${message}</h1>
	</c:if>
	
	<c:if test="${empty message}">
		<h1>Page not found</h1>
	</c:if>

	</div>
</body>
</html>

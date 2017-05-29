<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="local"%>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.4.min.js" />"></script>
<%@ page session="false" %>
<html>
<head>
	<title>Person Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>

<%request.setAttribute("isAdmin", request.isUserInRole("ROLE_ADMIN"));%>
<%request.setAttribute("isUser", request.isUserInRole("ROLE_USER"));%>

<br>
<c:if test="${requestScope.isUser || requestScope.isAdmin}">
	<a href="addperson"><button><local:message code="label.Addperson" /></button></a>
</c:if>

<br>
<h3><local:message code="label.PersonsList" /></h3>
<c:if test="${!empty listPersons}">
	<table class="tg">
	<tr>	
		<th width="110"><local:message code="label.PersonsPhoto" /></th>
		<th width="80"><local:message code="label.PersonsId" /></th>
		<th width="120"><local:message code="label.PersonsName" /></th>
		<th width="120"><local:message code="label.PersonsSurName" /></th>
		<c:if test="${requestScope.isAdmin}">
			<th width="60"><local:message code="label.Edit" /></th>
			<th width="60"><local:message code="label.Delete" /></th>
			<th width="60"><local:message code="label.Upload" /></th>
		</c:if>
		<c:if test="${requestScope.isUser || requestScope.isAdmin}">
			<th width="60"><local:message code="label.Download" /></th>
		</c:if>
	</tr>
	<c:forEach items="${listPersons}" var="person">
		<tr>
			<td><img src="${pageContext.request.contextPath}${person.photo}" height="110" width="110"></img></td>
			<td>${person.id}</td>
			<td>${person.name}</td>
			<td>${person.surname}</td>
			<c:if test="${requestScope.isAdmin}">
				<td><a href="<c:url value='/edit/${person.id}' />" ><local:message code="label.Edit" /></a></td>
				<td><a href="<c:url value='/remove/${person.id}' />" ><local:message code="label.Delete" /></a></td>
				<td><a href="<c:url value='/upload/${person.id}'/>" ><local:message code="label.Upload" /></a></td>
			</c:if>
			<c:if test="${requestScope.isUser || requestScope.isAdmin}">
				<td><a href="<c:url value='/download/${person.id}'/>"><local:message code="label.Download" /></a></td>	
			</c:if>			
		</tr>
	</c:forEach>
	</table>
      	<c:forEach begin="1" end="${pageCount}" var="i">

			<c:if test="${pageId==i}">
				<a href="listpersons?pageId=${i}" class="pagination">
					<button style="color: red">${i}</button>
				</a>
			</c:if>
			<c:if test="${pageId!=i}">
				<a href="listpersons?pageId=${i}" class="pagination">
					<button>${i}</button>
				</a>
			</c:if>

		</c:forEach>
</c:if>
</body>
</html>

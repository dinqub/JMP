
<%@taglib uri="http://www.springframework.org/tags" prefix="local"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h1 align="left">
	<local:message code="label.NewsPortal" />
</h1>
<c:url value="/logout" var="logoutUrl" />
<div class="container" align="right" style=" margin-right: 10;">
	<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<c:if test="${pageContext.request.userPrincipal.name != null}">
			
				<%request.setAttribute("isAdmin", request.isUserInRole("ROLE_ADMIN"));%>
				<%request.setAttribute("isUser", request.isUserInRole("ROLE_USER"));%>
				<c:if test="${requestScope.isAdmin}">
					<h2>
						<local:message code="label.Hello" /> ${pageContext.request.userPrincipal.name}
						 <local:message code="label.Admin_Page" />
					</h2>
				</c:if>
				<c:if test="${requestScope.isUser}">
					<h2>
					<local:message code="label.Hello" /> ${pageContext.request.userPrincipal.name}
						 <local:message code="label.User_Page" />
					</h2>
				</c:if>
				
				<input type="submit" name="submit"
					style="padding-left: 25; padding-right: 25; padding-bottom: 5; padding-top: 5;"
					value="<local:message code="label.Logout" />" />
			</c:if>
	</form>
	<c:if test="${pageContext.request.userPrincipal.name == null}">
		<a href="login" class="pagination">
			<button><local:message
			code="label.Login" /></button>
		</a>
	</c:if>
</div>

<div align="right">
	<a href="?lang=en&<%=request.getQueryString()%>"><local:message
			code="label.en" /></a> <a href="?lang=ru&<%=request.getQueryString()%>"><local:message
			code="label.ru" /></a>
</div>


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
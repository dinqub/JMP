<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<link href="<s:url value="/resources/css/tablec.css"/>" rel="stylesheet" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript"	src="http://scriptjava.net/source/scriptjava/scriptjava.js"></script>
<title><tiles:getAsString name="title" /></title>
</head>
<body style="  height: 93%;" id="bodyId">
<body style="margin: 0; padding: 0;">

 		<div class="header">
			<tiles:insertAttribute name="header" />
		</div> 
		<div class="content">
			<tiles:insertAttribute name="content" />
		</div>
		<div class="footer">
			<tiles:insertAttribute name="footer" />
		</div>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Upload File Request Page</title>
</head>
<body>
	<form method="POST" action="upload?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
		File to upload: <input type="file" name="file"> 
		<input type="hidden" id="id" name="id" value="${id}">
 		<input type="submit" value="Upload"> Press here to upload the file!
	</form>	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>SpringMVC Test</title>
</head>
<body>
	<p>${greeting }</p>
	<c:forEach items="${listString }" var="str">
		<p>${str }</p>
	</c:forEach>
	
</body>
<script type="text/javascript">
	
</script>
</html>
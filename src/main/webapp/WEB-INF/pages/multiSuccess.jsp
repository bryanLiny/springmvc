<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="basePath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>File Upload Success</title>
<link href="${basePath }/static/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
<link href="${basePath }/static/css/app.css" rel="stylesheet" />
</head>
<body>
	<div class="success">
			<c:forEach var="fileName" items="${fileNames}">
				File  <strong>${fileName}</strong> uploaded successfully<br/>
			</c:forEach>
			<br/>
		<a href="<c:url value='/fileupload/welcome' />">Home</a>
	</div>
</body>
</html>
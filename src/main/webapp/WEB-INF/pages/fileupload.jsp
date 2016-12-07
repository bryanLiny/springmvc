<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="basePath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>文件下载</title>
	<link href="${basePath }/static/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
	<link href="${basePath }/static/css/app.css" rel="stylesheet" />
</head>

<body>
	<div class="form-container">
		<h1>Welcome to FileUploader Example</h1>
		
		Click on below links to see FileUpload in action.<br/><br/>
		
		<a href="<c:url value='/fileupload/singleUpload' />">Single File Upload</a>  OR  
		<a href="<c:url value='/fileupload/multiUpload' />">Multi File Upload</a>
	</div> 
</body>
</html>
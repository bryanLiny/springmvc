<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册成功确认</title>
<link href="${basePath }/static/lib/bootstrap/css/bootstrap.min.css"
	rel="stylesheet"></link>
<link href="${basePath }/static/css/app.css" rel="stylesheet" />
</head>
<body>
	<div class="generic-container">
		<div class="alert alert-success lead">${success}</div>

		<span class="well floatRight">
			<a href="<c:url value='/user/list' />">用户列表</a>
		</span>
	</div>
</body>
</html>
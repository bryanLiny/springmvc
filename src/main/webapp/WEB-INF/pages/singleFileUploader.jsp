<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>单文件上传</title>
	<link href="${basePath }/static/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
	<link href="${basePath }/static/css/app.css" rel="stylesheet" />
</head>
<body> 

	<div class="form-container">
		<h1>Spring 4 MVC File Upload Example </h1>
		<form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data" class="form-horizontal">
		
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="file">Upload a file</label>
					<div class="col-md-7">
						<form:input type="file" path="file" id="file" class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="file" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit" value="Upload" class="btn btn-primary btn-sm">
				</div>
			</div>
		</form:form>
		<a href="<c:url value='/fileupload/welcome' />">Home</a>
	</div>
</body>
</html>
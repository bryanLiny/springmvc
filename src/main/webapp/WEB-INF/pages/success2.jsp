<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Registration Confirmation Page</title>
</head>
<body>
	message : ${success}
	<br />
	<br /> Go back to
	<a href="<c:url value='/employees/list' />">List of All Employees</a>

</body>
</html>
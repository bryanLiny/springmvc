<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="basePath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>WebSocket Demo</title>

<script src="${basePath }/static/lib/websocket/sockjs-1.1.1.js"></script>

<script type="text/javascript">
	
	/* var sock = new SockJS('http://127.0.0.1:8080/SpringMVC/websocket');
	sock.onopen = function() {
		console.log('open');
		sock.send('你好，我是客户端，需要请求数据！');
		setTimeout(function() {
			sock.send('3秒后再重新请求！');
		}, 3000);
	};
	sock.onmessage = function(e) {
		console.log('message：', e.data);
	};
	sock.onclose = function() {
		console.log('close');
	}; */
	
	var path = 'localhost:8080/SpringMVC/';  
    var userId = 'lys';  
    
	var jspCode = userId + "_AAA";
	var websocket;
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://" + path + "wsMy?jspCode=" + jspCode);
	} else if ('MozWebSocket' in window) {
		websocket = new MozWebSocket("ws://" + path + "wsMy?jspCode=" + jspCode);
	} else {
		websocket = new SockJS("http://" + path + "wsMy/sockjs?jspCode="
				+ jspCode);
	}
	websocket.onopen = function(event) {
		console.log("WebSocket:已连接");
		console.log(event);
	};
	websocket.onmessage = function(event) {
		var data = JSON.parse(event.data);
		console.log("WebSocket:收到一条消息-norm", data);
		alert("WebSocket:收到一条消息");
	};
	websocket.onerror = function(event) {
		console.log("WebSocket:发生错误 ");
		console.log(event);
	};
	websocket.onclose = function(event) {
		console.log("WebSocket:已关闭");
		console.log(event);
	}
</script>
</head>

<body>

</body>
</html>
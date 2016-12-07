package com.yiibai.springmvc.interceptor;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * @author Administrator 
 * websocket握手拦截器， 用于拦截websocket初始化连接的请求
 *
 */
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler,
			Map<String, Object> attributes) throws Exception {

		String jspCode = ((ServletServerHttpRequest) request).getServletRequest().getParameter("jspCode");
		// 标记用户
		// String userId = (String) session.getAttribute("userId");
		if (jspCode != null) {
			attributes.put("jspCode", jspCode);
		} else {
			return false;
		}
		return true;
		
		//super.beforeHandshake(request, response, handler, attributes);
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {

		System.out.println("After Handshake");

		super.afterHandshake(request, response, wsHandler, ex);
	}

}

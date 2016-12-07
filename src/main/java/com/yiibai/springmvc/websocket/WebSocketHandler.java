package com.yiibai.springmvc.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author Administrator 
 * 用来与websocket客户端来进行交互的接口， 
 * Spring WebSocket提供了一些实现类，可以根据自己的需求进行选择与重写
 * 通过配置spring-mvc-servlet.xml实现
 *
 */
public class WebSocketHandler extends TextWebSocketHandler {

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		// 接收到客户端消息时调用
		System.out.println("text message: " + session.getId() + "-" + message.getPayload());

		try {
			super.handleTextMessage(session, message);
			TextMessage returnMessage = new TextMessage("请求消息：‘"+message.getPayload() + "’ 已收到");
			session.sendMessage(returnMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 与客户端完成连接后调用
		System.out.println("afterConnectionEstablished");
		System.out.println("getId:" + session.getId());
		System.out.println("getLocalAddress:" + session.getLocalAddress().toString());
		System.out.println("getTextMessageSizeLimit:" + session.getTextMessageSizeLimit());
		System.out.println("getUri:" + session.getUri().toString());
		System.out.println("getPrincipal:" + session.getPrincipal());
		session.sendMessage(new TextMessage("WebSocket connect success,Hello Client!".getBytes()));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// 消息传输出错时调用
		System.out.println("handleTransportError");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// 一个客户端连接断开时关闭
		System.out.println("afterConnectionClosed");
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

}

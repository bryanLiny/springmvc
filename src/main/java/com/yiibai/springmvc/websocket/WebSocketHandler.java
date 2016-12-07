package com.yiibai.springmvc.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author Administrator 
 * ������websocket�ͻ��������н����Ľӿڣ� 
 * Spring WebSocket�ṩ��һЩʵ���࣬���Ը����Լ����������ѡ������д
 * ͨ������spring-mvc-servlet.xmlʵ��
 *
 */
public class WebSocketHandler extends TextWebSocketHandler {

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		// ���յ��ͻ�����Ϣʱ����
		System.out.println("text message: " + session.getId() + "-" + message.getPayload());

		try {
			super.handleTextMessage(session, message);
			TextMessage returnMessage = new TextMessage("������Ϣ����"+message.getPayload() + "�� ���յ�");
			session.sendMessage(returnMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// ��ͻ���������Ӻ����
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
		// ��Ϣ�������ʱ����
		System.out.println("handleTransportError");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// һ���ͻ������ӶϿ�ʱ�ر�
		System.out.println("afterConnectionClosed");
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

}

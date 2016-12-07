package com.yiibai.springmvc.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.GsonBuilder;

/**
 * @author Administrator
 * 
 * ͨ��componentע��ʵ��
 *
 */
@Component
public class MyWebSocketHandler implements WebSocketHandler {

	public static final Map<String, WebSocketSession> userSocketSessionMap;

	static {
		userSocketSessionMap = new HashMap<String, WebSocketSession>();
	}

	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		String jspCode = (String) session.getAttributes().get("jspCode");
		if (userSocketSessionMap.get(jspCode) == null) {
			userSocketSessionMap.put(jspCode, session);
		}
		for (int i = 0; i < 3; i++) {
			// broadcast(new TextMessage(new
			// GsonBuilder().create().toJson("\"number\":\""+i+"\"")));
			session.sendMessage(new TextMessage(new GsonBuilder().create().toJson("\"number\":\""+i+"\"")));
		}
	}

	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		// Message msg=new
		// Gson().fromJson(message.getPayload().toString(),Message.class);
		// msg.setDate(new Date());
		// sendMessageToUser(msg.getTo(), new TextMessage(new
		// GsonBuilder().setDateFormat("yyyy-MM-dd
		// HH:mm:ss").create().toJson(msg)));

		session.sendMessage(message);
	}

	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		if (session.isOpen()) {
			session.close();
		}
		Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
		// �Ƴ�Socket�Ự
		while (it.hasNext()) {
			Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.out.println("Socket�Ự�Ѿ��Ƴ�:�û�ID" + entry.getKey());
				break;
			}
		}
	}

	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Websocket:" + session.getId() + "�Ѿ��ر�");
		Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
		// �Ƴ�Socket�Ự
		while (it.hasNext()) {
			Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.out.println("Socket�Ự�Ѿ��Ƴ�:�û�ID" + entry.getKey());
				break;
			}
		}
	}

	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Ⱥ�� 
	 * @Title: broadcast 
	 * @Description: TODO
	 * @param message
	 * @throws IOException 
	 * @return: void 
	 * @author: ��ԣ
	 * @Date: 2016��11��26��
	 * @throws
	 */
	public void broadcast(final TextMessage message) throws IOException {
		Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();

		// ���߳�Ⱥ��
		while (it.hasNext()) {

			final Entry<String, WebSocketSession> entry = it.next();

			if (entry.getValue().isOpen()) {
				new Thread(new Runnable() {

					public void run() {
						try {
							if (entry.getValue().isOpen()) {
								entry.getValue().sendMessage(message);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}).start();
			}

		}
	}

	/**
	 * �����������û���ʵʱ���̼��ҳ�淢����Ϣ
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void sendMessageToJsp(final TextMessage message, String type) throws IOException {
		Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();

		// ���߳�Ⱥ��
		while (it.hasNext()) {

			final Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().isOpen() && entry.getKey().contains(type)) {
				new Thread(new Runnable() {

					public void run() {
						try {
							if (entry.getValue().isOpen()) {
								entry.getValue().sendMessage(message);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}).start();
			}

		}
	}
}

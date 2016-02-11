package com.supsms.webSocket;

import com.supsms.entity.Conversation;
import com.supsms.entity.User;
import com.supsms.service.ConversationService;
import com.supsms.service.SmsService;

import javax.ejb.EJB;
import javax.inject.Singleton;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.List;

@ServerEndpoint(value = "/user/chat/{conversationId}",
        configurator = GetHttpSessionConfigurator.class)
@Singleton
public class ChatServerEndPoint {
    private static final String ATT_SESSION = "user";
    @EJB
    private SmsService smsService;
    @EJB
    private ConversationService conversationService;
    private HttpSession httpSession;
    private Conversation conversation;

    @OnOpen
    public void onOpen(@PathParam("conversationId") String conversationId, Session userSession, EndpointConfig config) {
        conversation = conversationService.getConversationById(conversationId);
        userSession.getUserProperties().put("conversationId", conversation.getId());
        this.httpSession = (HttpSession) config.getUserProperties.get(HttpSession.class.getName());
    }

    @OnClose
    public void onClose(Session userSession, EndpointConfig config) {
    }

    @OnMessage
    public void onMessage(String message, Session userSession) {
        //On recuperer le numero de l'utilisateur qui envoit :
        User user;
        user = (User) httpSession.getAttribute(ATT_SESSION);
        String Sender = user.getPhoneNumber();
        if (Sender == null) return;
        List<String> phoneNumbersConversation = conversation.getPhoneNumber();
        String destinationPhoneNumber = "";
        for (String phoneNumber : phoneNumbersConversation)
            if (!phoneNumber.equals(Sender)) destinationPhoneNumber = phoneNumber;
        for (Session session : userSession.getOpenSessions()) {
            if (session.isOpen() && conversation.getId().equals(session.getUserProperties().get("conversationId"))) {
                String json = "{"
                        + "\"phone\":" + "\"" + user.getPhoneNumber() + "\","
                        + "\"sms\":" + "\"" + message + "\""
                        + "}";
                session.getAsyncRemote().sendText(json);
            }
        }
        smsService.sendMessage(message, destinationPhoneNumber, user.getPhoneNumber());
    }
}

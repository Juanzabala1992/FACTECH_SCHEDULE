package com.login.authentication.security;

import com.login.authentication.model.Notifications;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

  @Autowired
  private NotificationService notificationService;

  private final SimpMessagingTemplate simpMessagingTemplate;
  @MessageMapping("/application")
  @SendTo("/common/messages")
  public Notifications send(final Notifications message) {
    return message;
  }

  @MessageMapping("/private")
  public void sendToSpecificUser(@Payload Notifications message,
                                  SimpMessageHeaderAccessor headerAccessor
  ) {
    System.out.println("Message "+ message);
    simpMessagingTemplate.convertAndSendToUser(message.getDestination(), "/specific", message);
  }
}

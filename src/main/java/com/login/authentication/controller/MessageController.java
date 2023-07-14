package com.login.authentication.controller;

import com.login.authentication.exceptions.ApiRequestExceptionValid;
import com.login.authentication.model.Notifications;
import com.login.authentication.model.Profile;
import com.login.authentication.repository.ProfileRepository;
import com.login.authentication.security.NotificationService;
import com.login.authentication.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MessageController {

  @Autowired
  private NotificationService notificationService;

  @Autowired
  private final ProfileService profileService;
  private final ProfileRepository profileRepository;

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
    System.out.println("Message "+ message.getDestination());
    Optional<Profile> data = profileRepository.findByEmail(message.getDestination());
    if(data.isEmpty()){
      throw new ApiRequestExceptionValid("Perfil no existe");
    }else{
      message.setIdUser(data.get().getIdUser());
      notificationService.sendPrivateNotification(message);
      simpMessagingTemplate.convertAndSendToUser(message.getDestination(), "/specific", message);
    }
  }
}

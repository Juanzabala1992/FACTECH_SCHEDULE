package com.login.authentication.security;


import com.login.authentication.exceptions.ApiRequestException;
import com.login.authentication.exceptions.ApiRequestExceptionValid;
import com.login.authentication.model.Notifications;
import com.login.authentication.model.Profile;
import com.login.authentication.repository.NotificationsRepository;
import com.login.authentication.repository.ProfileRepository;
import com.login.authentication.service.ProfileService;
import com.login.authentication.user.Rol;
import com.login.authentication.user.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@AllArgsConstructor
@Service
public class NotificationService {
    //private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    private final NotificationsRepository repositorio;

    @Autowired
    private final ProfileRepository profileRepository;
   /* @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendGlobalNotification() {
        ResponseMessage message = new ResponseMessage("Global Notification");

        messagingTemplate.convertAndSend("/topic/global-notifications", message);
    }*/
   public ResponseEntity<List<Notifications>> getByUser(List<Notifications> data){
       if(data.isEmpty() || data == null) {
           throw new ApiRequestException("No hay datos para el usuario");
       }else {
           return ResponseEntity.status(HttpStatus.OK).body(data);
       }
   }
    public void sendPrivateNotification(Notifications notifications) {

            String email = notifications.getDestination();
            Optional<Profile> data = profileRepository.findByEmail(email);
            System.out.println(" vacio ? "+ data);
            if(!data.isEmpty()){
                String id = CodigoAleatorioGenerator.generateRandomCode();
                notifications.setMessage_id(id);
                notifications.setEmail(email);
                repositorio.save(notifications);
            }else{
                throw new ApiRequestExceptionValid("Destino no existe");
            }
    }
    public class CodigoAleatorioGenerator {
        private static final String PREFIX = "ntcn-";
        private static final int DIGIT_COUNT = 5;
        private static final int LETTER_COUNT = 2;

        public static String generateRandomCode() {
            Random random = new Random();
            StringBuilder codeBuilder = new StringBuilder(PREFIX);

            // Generar dígitos aleatorios
            for (int i = 0; i < DIGIT_COUNT; i++) {
                int digit = random.nextInt(10); // Números del 0 al 9
                codeBuilder.append(digit);
            }

            // Generar letras aleatorias
            for (int i = 0; i < LETTER_COUNT; i++) {
                char letter = (char) (random.nextInt(26) + 'a'); // Letras minúsculas
                codeBuilder.append(letter);
            }

            return codeBuilder.toString();
        }

        // Ejemplo de uso
        public void main(String[] args) {
            CodigoAleatorioGenerator generator = new CodigoAleatorioGenerator();
            String randomCode = generator.generateRandomCode();
            System.out.println("Código aleatorio: " + randomCode);
        }
    }
}
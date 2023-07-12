package com.login.authentication.service;

import com.login.authentication.exceptions.ApiRequestException;
import com.login.authentication.exceptions.ApiRequestExceptionValid;
import com.login.authentication.model.Profile;
import com.login.authentication.model.ScheduleModel;
import com.login.authentication.repository.ProfileRepository;
import com.login.authentication.repository.ScheduleRepository;
import com.login.authentication.repository.UserRepository;
import com.login.authentication.user.Rol;
import com.login.authentication.user.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class ProfileService {

    @Autowired
    private ProfileRepository repositorio;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final EmailSenderService emailSenderService;

    public ResponseEntity<List<Profile>> getAllData(List<Profile> lista){
        if(lista.isEmpty() || lista == null) {
            throw new ApiRequestException("No hay usuarios en la base");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(lista);
        }
    }

    public ResponseEntity<Optional<Profile>> getByUserId(Optional<Profile> data){
        if(data.isEmpty() || data == null) {
            throw new ApiRequestException("No hay datos para el usuario");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }
    }
      public ResponseEntity<Profile> setData(Profile profileData, BindingResult result){
        if(result.hasErrors()) {
            if(!result.getFieldError().getDefaultMessage().isEmpty()) {
                throw new ApiRequestExceptionValid(result.getFieldError().getDefaultMessage());

            }else {
                throw new ApiRequestExceptionValid("Datos no validos");
            }

        }else {

            String id_profile = profileData.getEmail();
            Optional<Profile> data = repositorio.findByEmail(id_profile);

            String password = CodigoAleatorioGenerator.generarCodigoAleatorio();

            System.out.println("Password generada: "+ password);

            if(data.isEmpty()) {
                var user = User.builder()
                        .firstname(profileData.getNombre())
                        .lastName(profileData.getApellido())
                        .email(profileData.getEmail())
                        .idUser(profileData.getIdUser())
                        .password((passwordEncoder.encode(password)))
                        .role(Rol.USER)
                        .build();
                    repository.save(user);
                    repositorio.save(profileData);
                 emailSenderService.sendEmail(profileData.getEmail(), "Bienvenido a Factech!",
                        password);

                return ResponseEntity.status(HttpStatus.OK).body(profileData);



            }else {
                throw new ApiRequestExceptionValid("Perfil ya existe");
            }
        }
    }


    public class CodigoAleatorioGenerator {

        private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static final String NUMBERS = "0123456789";
        private static final String SPECIAL_CHARACTERS = "$%#*/";
        private static final int MIN_LENGTH = 8;

        public static String generarCodigoAleatorio() {
            StringBuilder codigo = new StringBuilder();
            Random random = new SecureRandom();

            // Agregar una letra mayúscula
            codigo.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));

            // Agregar dos números
            for (int i = 0; i < 2; i++) {
                codigo.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
            }

            // Agregar un carácter especial
            codigo.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

            // Generar los caracteres restantes
            int remainingLength = MIN_LENGTH - codigo.length();
            for (int i = 0; i < remainingLength; i++) {
                int type = random.nextInt(3); // 0: letra mayúscula, 1: número, 2: carácter especial

                if (type == 0) {
                    codigo.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
                } else if (type == 1) {
                    codigo.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
                } else {
                    codigo.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));
                }
            }

            return codigo.toString();
        }
    }
}

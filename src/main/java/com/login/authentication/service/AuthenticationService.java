package com.login.authentication.service;

import com.login.authentication.exceptions.ApiRequestException;
import com.login.authentication.model.*;
import com.login.authentication.exceptions.ApiRequestExceptionValid;
import com.login.authentication.exceptions.EmailValidator;
import com.login.authentication.user.Rol;
import com.login.authentication.user.User;
import com.login.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request, BindingResult result) {
        if(!EmailValidator.validateEmail(request.getEmail())){
            throw new ApiRequestExceptionValid("Formato correo incorrecto");
        }
        else{
            if (result.hasErrors()) {
                if (!result.getFieldError().getDefaultMessage().isEmpty()) {
                    throw new ApiRequestExceptionValid(result.getFieldError().getDefaultMessage());
                } else {
                    throw new ApiRequestExceptionValid("Datos no validos");
                }

            } else {
                String email = request.getEmail();
                Optional<User> person = repository.findByEmail(email);

                if (!person.isPresent()){
                    var user = User.builder()
                            .firstname(request.getFirstname())
                            .lastName(request.getLastName())
                            .email(request.getEmail())
                            .idUser(request.getIdUser())
                            .password((passwordEncoder.encode(request.getPassword())))
                            .role(Rol.USER)
                            .build();
                    repository.save(user);
                    var jwtToken = jwtService.generateToken(user);


                    return AuthenticationResponse.builder()
                            .token(jwtToken)
                            .build();
                } else {
                    throw new ApiRequestExceptionValid("Usuario ya existe");
                }
            }
        }

    }
    public AuthenticationResponse authenticate(AuthenticatonRequest request, BindingResult result) {
            if (result.hasErrors()) {
               if (!result.getFieldError().getDefaultMessage().isEmpty()) {
                   throw new ApiRequestExceptionValid(result.getFieldError().getDefaultMessage());
               } else {
                   throw new ApiRequestExceptionValid("Datos no validos");
               }

           } else {
               String email = request.getEmail();
               Optional<User> person = repository.findByEmail(email);

               if (person.isPresent()){
                   authenticationManager.authenticate(
                           new UsernamePasswordAuthenticationToken(
                                   request.getEmail(),
                                   request.getPassword()
                           )
                   );
                   var user = repository.findByEmail(request.getEmail())
                           .orElseThrow();
                   var jwtToken = jwtService.generateToken(user);
                   return AuthenticationResponse.builder()
                           .token(jwtToken)
                           .idUser(user.getIdUser())
                           .email(user.getUsername())
                           .role(String.valueOf(user.getRole()))
                           .build();

               } else {
                   throw new ApiRequestExceptionValid("Usuario no registrado");
               }
           }

    }

    public ResponseEntity<String> updatePassword(PasswordModel data, BindingResult result) {

            if (result.hasErrors()) {
                if (!result.getFieldError().getDefaultMessage().isEmpty()) {
                    throw new ApiRequestExceptionValid(result.getFieldError().getDefaultMessage());
                } else {
                    throw new ApiRequestExceptionValid("Datos no validos");
                }

            } else {
                Optional<User> person = repository.findByEmail(data.getEmail());

                if (person.isPresent()){

                    String oldPassword = person.get().getPassword();
                    String newPassword = data.getNewPassword();

                    boolean passwordsMatch = passwordEncoder.matches(newPassword,oldPassword);
                    String encodedPassword = passwordEncoder.encode(newPassword);

                    if(passwordsMatch){
                        throw new ApiRequestExceptionValid("La contraseña es igual a la anterior ");
                    }else{
                        person.get().setPassword(encodedPassword);
                        repository.save(person.get());
                        return ResponseEntity.ok().body("La contraseña se actualizo correctamente");
                    }
                } else {
                    throw new ApiRequestExceptionValid("Usuario ya existe");
                }
            }
    }

}

package com.login.authentication.security;

import com.login.authentication.exceptions.ApiRequestException;
import com.login.authentication.model.Profile;
import com.login.authentication.repository.ProfileRepository;
import com.sun.security.auth.UserPrincipal;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;


@AllArgsConstructor
public class UserHandshakeHandler extends DefaultHandshakeHandler {
    private final Logger LOG = LoggerFactory.getLogger(UserHandshakeHandler.class);

    @Autowired
    private final ProfileRepository profileRepository;

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        MultiValueMap<String, String> queryParams = UriComponentsBuilder.fromUri(request.getURI()).build().getQueryParams();
        String queryParamValue = queryParams.getFirst("user");

        System.out.println("queryParamValue---> 1"+ queryParamValue);
        Optional<Profile> data = profileRepository.findByEmail(queryParamValue);
            if(data.isEmpty() || data== null){
                throw new ApiRequestException("No hay datos para el usuario");
            }
            else{
                System.out.println("queryParamValue--->"+ queryParamValue);
                return new UserPrincipal(queryParamValue);
            }
    }
}
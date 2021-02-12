package com.auth.gateway.service;

import com.auth.gateway.common.constants.ErrorEnum;
import com.auth.gateway.common.exception.CustomException;
import com.auth.gateway.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final RestTemplate restTemplate;
    @Value("${security.oauth2.client.client-id}")
    private String CLIENT_ID;
    @Value("${security.oauth2.client.client-secret}")
    private String CLIENT_SECRET;
    @Value("${server.port}")
    private String port;

    public String getJwtByUserInfo(LoginRequest loginRequest){
        final String GRANT_TYPE = "password";
        final String SERVER_URL = "http://localhost:" + port;
        final String API_OAUTH_TOKEN = "/oauth/token";
        final String SCOPE = "read";

        String clientCredentials = CLIENT_ID+":"+ CLIENT_SECRET;
        String base64ClientCredentials = new String(Base64.encodeBase64(clientCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + base64ClientCredentials);

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("grant_type", GRANT_TYPE);
        parameters.add("username", loginRequest.getEmail());
        parameters.add("password", loginRequest.getPassword());
        parameters.add("scope", SCOPE);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, headers);
        URI uri = URI.create(SERVER_URL+API_OAUTH_TOKEN);
        ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);

        if(response.getStatusCode() != HttpStatus.OK){
            throw new CustomException(ErrorEnum.LOG_NOT_FOUND);
        }

        return response.getBody();
    }

    public String getJwtByRefreshToken(String refreshToken){
        final String GRANT_TYPE = "refresh_token";
        final String SERVER_URL = "http://localhost:" + port;
        final String API_OAUTH_TOKEN = "/oauth/token";
        final String SCOPE = "read";

        String clientCredentials = CLIENT_ID+":"+ CLIENT_SECRET;
        String base64ClientCredentials = new String(Base64.encodeBase64(clientCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + base64ClientCredentials);

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("grant_type", GRANT_TYPE);
        parameters.add("refresh_token", refreshToken);
        parameters.add("scope", SCOPE);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, headers);
        URI uri = URI.create(SERVER_URL+API_OAUTH_TOKEN);
        ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);

        if(response.getStatusCode() != HttpStatus.OK){
            throw new CustomException(ErrorEnum.LOG_NOT_FOUND);
        }

        return response.getBody();
    }
}

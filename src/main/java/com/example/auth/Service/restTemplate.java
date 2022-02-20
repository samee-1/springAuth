package com.example.auth.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
public class restTemplate {

//    private static final String GET_ALL_USERS ="http://localhost:8080/api/v1";
//    static restTemplate restTemplate = new restTemplate();
//    static String baseUrl =  "https://ums.bytetale.com/umservices/api/auth/signin";

//    public RestTemplate restTemplate = new RestTemplate();

//    public static void main(String[] args) {
//        callGetAllUsers();
//    }
    public String callGetAllUsers() throws JsonProcessingException {

//        String baseUrl =  "https://ums.bytetale.com/umservices/api/auth/signin";
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
//
//        ResponseEntity<String> result = restTemplate.exchange(baseUrl, HttpMethod.POST, entity, String.class);
//
//        System.out.println(result);
        RestTemplate restTemplate = new RestTemplate();
        String base_uri = "https://ums.bytetale.com/umservices/api/auth/validate";
//        String token = String.valueOf(personController.getAuthToken());
        HttpServletRequest curr_request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String token = curr_request.getHeader("Authorization");
        if(token == null){
            System.out.println("no token");
//            return ResponseEntity.internalServerError().body(new TokenInvalid("No token provided"));
        }
        System.out.println(token);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", token);
        HttpEntity<String> request =
                new HttpEntity<String>("", headers);
        String UmsRes = restTemplate.exchange(
                base_uri, HttpMethod.POST, request, String.class).getBody();
//        System.out.println(UmsRes);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode js = objectMapper.readTree(UmsRes);

        String success = js.get("success").toString();
        System.out.println(success);
        return success;
//        if( success == "true"){
//            return "Success!";
//        }
//        else{
//            return "Validation failed";
//        }
    }


}

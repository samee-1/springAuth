package com.example.auth.Controller;

import com.example.auth.Exception.ResourceNotFoundException;
import com.example.auth.User.user;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.auth.Payloads.successResponse;

import java.util.List;
import com.example.auth.User.user;

@RestController
@RequestMapping("/api/v1")
public class controller {

    @Autowired
    private com.example.auth.Repository.userRepository userRepository;
    @Autowired
    private com.example.auth.Service.restTemplate restTemplate;



//    @GetMapping
//    public List <user> getAllUsers() throws JsonProcessingException {
//        this.restTemplate.callGetAllUsers();
//        return this.userRepository.findAll();
//    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() throws JsonProcessingException {
        String validation = this.restTemplate.callGetAllUsers();
        System.out.println(validation);
        if ("true".equals(validation)){
            System.out.println(validation);
            String result = "Success!";
            successResponse s = new successResponse(result, this.userRepository.findAll());
            return ResponseEntity.ok().body(s);
        }
        else{
            return ResponseEntity.internalServerError().body("Validation failed");
        }
//        successResponse s = new successResponse(validation.toString(), this.userRepository.findAll());
    }


    @GetMapping("/{id}")
    public user getUserById(@PathVariable(value = "id") long userId) throws ResourceNotFoundException {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
    }

    @PostMapping
    public user createUser(@RequestBody user user) {
        return this.userRepository.save(user);
    }

    @PutMapping("/{id}")
    public user updateUser(@RequestBody user user, @PathVariable("id") long userId) throws ResourceNotFoundException {
        user existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return this.userRepository.save(existingUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity < user > deleteUser(@PathVariable("id") long userId) throws ResourceNotFoundException {
        user existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
        this.userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }
}
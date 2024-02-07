package com.andrusevich.restapp.controller;

import com.andrusevich.restapp.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final ObjectMapper objectMapper;

    @GetMapping("/api/main")
    public String mainListener() {
        return "Rest App is running";
    }

    @GetMapping("/api/user")
    public String getUser() {
        User user = new User("Bob", 35, "UK");
        String jsonData = null;
        try {
             jsonData = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
        return jsonData;
    }

    @PostMapping("/api/special")
    public String setSpecialUser(@RequestParam String name) {
        User user = new User(name, 35, "UK");
        String jsonData = null;
        try {
            jsonData = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
        return jsonData;
    }
}

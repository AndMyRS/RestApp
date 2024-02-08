package com.andrusevich.restapp.controller;

import com.andrusevich.restapp.entity.User;
import com.andrusevich.restapp.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    @PostMapping("/api/add")
    public void addUser(@RequestBody User user) {
        log.info("New database entry:" + userRepository.save(user));
    }

    @SneakyThrows
    @GetMapping("/api/all")
    public String getAllUsers() {
        List<User> users = userRepository.findAll();
        return objectMapper.writeValueAsString(users);
    }

    @GetMapping("/api/{id}")
    public User getUserById(@PathVariable String id) {
        return userRepository.findById(Long.valueOf(id)).orElseThrow();
    }

    @DeleteMapping("/api")
    public void deleteUserById(@RequestParam long id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/api/update")
    public String updateUser(@RequestBody User user) {
        if (!userRepository.existsById(user.getId())) {
            return "No such user found in database";
        } else return userRepository.save(user).toString();
    }
}

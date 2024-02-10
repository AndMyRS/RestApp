package com.andrusevich.restapp.controller;

import com.andrusevich.restapp.dto.UserDto;
import com.andrusevich.restapp.entity.User;
import com.andrusevich.restapp.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "main_user_controller")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final UserRepository userRepository;


    @Operation(
            summary = "Insert new user to db",
            description = "Gets an userDto object"
    )
    @PostMapping("/api/add")
    public void addUser(@RequestBody UserDto userDto) {
        log.info(
                "New database entry: " + userRepository.save(
                        User.builder()
                            .name(userDto.getName())
                            .age(userDto.getAge())
                            .country(userDto.getCountry())
                            .build())
        );
    }

    @Operation(
            summary = "Get all users",
            description = "Returns a list of all users from db"
    )
    @SneakyThrows
    @GetMapping("/api/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
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

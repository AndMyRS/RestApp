package com.andrusevich.restapp.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;

    private int age;

    private String country;
}

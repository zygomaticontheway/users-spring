package de.ait.users.dto;

import de.ait.users.entity.User;

public class UserRequestDto {
    private String name;
    private String email;
    private String password;

    public UserRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //пустой конструктор нужен для библиотеки mapper
    public UserRequestDto() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static User toEntity (UserRequestDto request){
        return new User(null, request.getName(), request.getEmail(), request.getPassword());
    }
}

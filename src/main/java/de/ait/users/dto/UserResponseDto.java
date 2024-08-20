package de.ait.users.dto;

import de.ait.users.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserResponseDto {
    private Long id;
    private String name;
    private String email;

    public UserResponseDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    User -> UserResponseDto
    public static UserResponseDto of (User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail());
    }

    //   List<User> -> List<UserResponseDto>
    public static List<UserResponseDto> of (List<User> users) {

        return users.stream()
                .map(UserResponseDto::of)
                .collect(Collectors.toList());
    }
}

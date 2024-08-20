package de.ait.users.controller;

import de.ait.users.dto.UserRequestDto;
import de.ait.users.dto.UserResponseDto;
import de.ait.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //тоже аннотация Spring, но превращает класс в rest api
@RequestMapping("/api") //добавляет /api к началу пути, т.е. общий префикс. Аннотация ставится перед классом
public class UserController {

    private final IUserService service;

    @Autowired
    public UserController(IUserService service) {
        this.service = service;
    }

    //GET /users
    //GET /users?n=jack

    @GetMapping("/users") //аннотация, которая генерит endpoint, метод будет выполняться по вводу в браузер адреса localhost:8080/users
    public List<UserResponseDto> getUsers(@RequestParam (name = "n", required = false, defaultValue = "") String name,
                                          @RequestParam (name = "email", required = false, defaultValue = "") String email){

        return service.getUsers(name, email);
    }

    @PostMapping("/users")
    public UserResponseDto createNewUser (@RequestBody UserRequestDto request){
        return service.createNewUser(request);
    }
    @GetMapping("/users/{id}")
    public UserResponseDto getUserById(@PathVariable (name = "id") Long id){

        return service.findById(id);
    }
    @PutMapping("/users/{id}")
    public UserResponseDto updateUser (@PathVariable (name = "id") Long id, @RequestBody UserRequestDto request){
        return service.updateUser(id, request);
    }

//    @GetMapping("/users/{id}/accounts/{accountId}")
//    public User getUserById(@PathVariable (name = "id") Long id,
//                            @PathVariable (name = "accountId") Long accountId){
//
//        return service.findById(id);
//    }

}

package com.eguerra.agenda.controllers;

import com.eguerra.agenda.dtos.UserDto;
import com.eguerra.agenda.model.User;
import com.eguerra.agenda.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agenda")
public class UserControllers {
    private final UserService userService;

    public UserControllers(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/user")
    public ResponseEntity<Object>saveUser(@RequestBody @Valid UserDto userDto){
        try{
            User saverUser = userService.create(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario criado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar usuario");
        }
    }
}

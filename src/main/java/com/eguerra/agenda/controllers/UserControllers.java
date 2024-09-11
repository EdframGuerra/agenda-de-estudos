package com.eguerra.agenda.controllers;

import com.eguerra.agenda.dtos.UserDto;
import com.eguerra.agenda.model.User;
import com.eguerra.agenda.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

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

    @GetMapping("/userList")
    public ResponseEntity<List<User>>listUser(){
        try {
            List<User>userList=userService.getAllusers();
            return ResponseEntity.ok(userList);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/userList/{id}")
    public ResponseEntity<Object> listOneUser(@PathVariable(value = "id") Long id) {
        try {

            Optional<User> user = Optional.ofNullable(userService.getUserById(id));

            if (user.isPresent()) {
                return ResponseEntity.ok(user.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }

    @GetMapping("/userName")
    public ResponseEntity<List<User>> findUsers(@RequestParam String name){
        List<User> users = userService.findUserByName(name);
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return  new ResponseEntity<>(users,HttpStatus.OK);
        }
    }

    @PutMapping("/userUpdate/{id}")
    public ResponseEntity<Object>updateUser(@PathVariable(value = "id")Long id, @RequestBody @Valid UserDto userDto){
        try {
            Optional<User>optionalUser = Optional.ofNullable(userService.updateUser(id, userDto));
            return ResponseEntity.ok("User updated successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to update user");
        }
    }

    @DeleteMapping("/userDelete/{id}")
    public ResponseEntity<Object>deleteUser(@PathVariable(value = "id") Long id){
        try {
            Optional<User>optionalUser=userService.deleteUser(id);
            return ResponseEntity.ok("User successfully deleted");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error when deleting the person");
        }
    }
}

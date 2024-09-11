package com.eguerra.agenda.service;

import com.eguerra.agenda.dtos.UserDto;
import com.eguerra.agenda.model.User;
import com.eguerra.agenda.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private  final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create (UserDto userDto){
        User user = convertUser(userDto);
        return userRepository.save(user);
    }

    public List<User> getAllusers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new EntityNotFoundException("User not found");
        }
        return  optionalUser.get();
    }

    public  List<User>findUserByName(String name){
        List<User> userExact = userRepository.findByName(name);

        return !userExact.isEmpty()?userExact:userRepository.findByNameContainingIgnoreCase(name);
    }

    public User updateUser(Long id, UserDto userDto){
        Optional<User>optionalUser=userRepository.findById(id);

        if(optionalUser.isEmpty()){
            throw new EntityNotFoundException("User not found");
        }
        User user = optionalUser.get();

        user.setName(userDto.getName());

        return userRepository.save(user);
    }

    public Optional<User>deleteUser(Long id){
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent()){
            userRepository.delete(user.get());
        }else{
            throw new EntityNotFoundException("User not found");
        }
        return user;
    }




    private User convertUser(UserDto userDto) {
        User user = new User();

        user.setName(userDto.getName());
        return  user;
    }
}

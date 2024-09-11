package com.eguerra.agenda.service;

import com.eguerra.agenda.dtos.UserDto;
import com.eguerra.agenda.model.User;
import com.eguerra.agenda.repository.UserRepository;
import org.springframework.stereotype.Service;

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

    private User convertUser(UserDto userDto) {
        User user = new User();

        user.setName(userDto.getName());
        return  user;
    }
}

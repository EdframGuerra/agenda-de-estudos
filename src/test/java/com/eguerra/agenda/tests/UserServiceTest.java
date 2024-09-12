package com.eguerra.agenda.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import com.eguerra.agenda.dtos.UserDto;
import com.eguerra.agenda.model.User;
import com.eguerra.agenda.repository.UserRepository;
import com.eguerra.agenda.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreate() {
        UserDto userDto = new UserDto();
        userDto.setName("João");

        User user = new User();
        user.setName(userDto.getName());

        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.create(userDto);

        assertEquals("João", createdUser.getName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllusers();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById_Success() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(userId);

        assertNotNull(foundUser);
        assertEquals(userId, foundUser.getId());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testGetUserById_NotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            userService.getUserById(userId);
        });

        assertEquals("User not found", thrown.getMessage());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testFindUserByName_ExactMatch() {
        String name = "João";
        List<User> users = Arrays.asList(new User(), new User());

        when(userRepository.findByName(name)).thenReturn(users);

        List<User> foundUsers = userService.findUserByName(name);

        assertEquals(2, foundUsers.size());
        verify(userRepository, times(1)).findByName(name);
    }

    @Test
    public void testFindUserByName_Containing() {
        String name = "Jo";
        List<User> users = Arrays.asList(new User(), new User());

        when(userRepository.findByName(name)).thenReturn(Collections.emptyList());
        when(userRepository.findByNameContainingIgnoreCase(name)).thenReturn(users);

        List<User> foundUsers = userService.findUserByName(name);

        assertEquals(2, foundUsers.size());
        verify(userRepository, times(1)).findByName(name);
        verify(userRepository, times(1)).findByNameContainingIgnoreCase(name);
    }

    @Test
    public void testUpdateUser_Success() {
        Long userId = 1L;
        UserDto userDto = new UserDto();
        userDto.setName("Novo Nome");

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setName("Nome Antigo");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User updatedUser = userService.updateUser(userId, userDto);

        assertEquals("Novo Nome", updatedUser.getName());
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    public void testUpdateUser_NotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            userService.updateUser(userId, new UserDto());
        });

        assertEquals("User not found", thrown.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testDeleteUser_Success() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.deleteUser(userId);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void testDeleteUser_NotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            userService.deleteUser(userId);
        });

        assertEquals("User not found", thrown.getMessage());
        verify(userRepository, never()).delete(any(User.class));
    }
}

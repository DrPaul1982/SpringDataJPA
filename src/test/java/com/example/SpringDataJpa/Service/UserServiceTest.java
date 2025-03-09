package com.example.SpringDataJpa.Service;

import com.example.SpringDataJpa.model.Post;
import com.example.SpringDataJpa.model.User;
import com.example.SpringDataJpa.repository.PostRepository;
import com.example.SpringDataJpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private UserService userService;


    @Test
    void addUser() {
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(new User());
        assertEquals(new User().getId(), userService.addUser(new User()).getId());
    }

    @Test
    void getUserById() {
        Mockito.when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(new User(1L, "Name1", "Email", Collections.emptyList())));
        assertEquals(1L, userService.getUserById(1L).getId());
    }

    @Test
    void getUserById_withEmptyShouldThrowException() {
        Mockito.when(userRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.getUserById(1L));
    }

    @Test
    void getAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(java.util.List.of(new User(), new User()));
        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    void getUserByName() {
        Mockito.when(userRepository.findByName("Alex")).thenReturn(java.util.List.of(new User()));
        assertEquals(1, userService.getUserByName("Alex").size());
    }

    @Test
    void findUserByEmail() {
        Mockito.when(userRepository.findByEmail("f@hk")).thenReturn(java.util.List.of(new User()));
        assertEquals(1, userService.findUserByEmail("f@hk").size());
    }

    @Test
    void getUsersByEmailDomain() {
        Mockito.when(userRepository.findByEmailEndingWith("gmail.com")).thenReturn(java.util.List.of(new User()));
        assertEquals(1, userService.getUsersByEmailDomain("gmail.com").size());
    }

    @Test
    void deleteAllUsers() {
        Mockito.doNothing().when(userRepository).deleteAll();
        userService.deleteAllUsers();
    }

    @Test
    void deleteUserById() {
        Mockito.doNothing().when(userRepository).deleteById(1L);
        userService.deleteUserById(1L);
    }

    @Test
    void updateUser() {
        Mockito.when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(new User(1L, "Name1", "Email", Collections.emptyList())));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(new User(1L, "Name2", "Email", Collections.emptyList()));
        assertEquals("Name2", userService.updateUser(1L, new User()).getName());
    }

    @Test
    void addPost() {
        Mockito.when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(new User(1L, "Name1", "Email", Collections.emptyList())));
        Mockito.when(postRepository.save(Mockito.any(Post.class))).thenReturn(new Post());
        assertEquals(new Post().getId(), userService.addPost(1L, "Title", "Content").getId());
    }

    @Test
    void getPostsByUserId() {
        Mockito.when(postRepository.findByUserId(1L)).thenReturn(java.util.List.of(new Post()));
        assertEquals(1, userService.getPostsByUserId(1L).size());
    }
}
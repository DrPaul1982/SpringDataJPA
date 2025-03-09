package com.example.SpringDataJpa.controller;

import com.example.SpringDataJpa.Service.PostService;
import com.example.SpringDataJpa.Service.UserService;
import com.example.SpringDataJpa.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;


    @PostMapping
    private ResponseEntity<Post> addPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.savePost(post));
    }

    @GetMapping("/{id}/posts")
    private ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getPostsByUserId(id));
    }

    @GetMapping("/posts/{id}")
    private ResponseEntity<Post> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping
    private ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @DeleteMapping("/posts/{id}")
    private void deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
    }
}



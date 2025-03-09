package com.example.SpringDataJpa.Service;

import com.example.SpringDataJpa.model.Post;
import com.example.SpringDataJpa.model.User;
import com.example.SpringDataJpa.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;


    @Test
    void savePost() {
        Mockito.when(postRepository.save(Mockito.any(Post.class))).thenReturn(new Post());
        assertEquals(new Post().getId(), postService.savePost(new Post()).getId());
    }

    @Test
    void deletePostById() {
        postService.deletePostById(1L);
        Mockito.verify(postRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void getPostById() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Title");
        post.setContent("Content");
        post.setUser(new User());

        Mockito.when(postRepository.findById(1L)).thenReturn(java.util.Optional.of(post));
        assertEquals(1L, postService.getPostById(1L).getId());
    }

    @Test
    void getAllPosts() {
        Mockito.when(postRepository.findAll()).thenReturn(java.util.List.of(new Post(), new Post()));
        assertEquals(2, postService.getAllPosts().size());
    }

    @Test
    void updatePost() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Title");
        post.setContent("Content");
        Mockito.when(postRepository.findById(1L)).thenReturn(java.util.Optional.of(post));
        Post newPost = new Post();
        newPost.setId(1L);
        newPost.setTitle("Title1");
        newPost.setContent("Content1");
        Mockito.when(postRepository.save(newPost)).thenReturn(newPost);
        assertEquals("Content1", postService.updatePost(1L, newPost).getContent());
    }
}
package com.example.SpringDataJpa.Service;

import com.example.SpringDataJpa.model.Post;
import com.example.SpringDataJpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post updatePost(Long id, Post newPost) {
        Post post = getPostById(id);
        post.setTitle(newPost.getTitle());
        post.setContent(newPost.getContent());
        return postRepository.save(post);
    }

}

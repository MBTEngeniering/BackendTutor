package de.fairstudy.backend.controller;
import de.fairstudy.backend.dto.PostDto;
import de.fairstudy.backend.entity.Post;
import de.fairstudy.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post/save")
    public Post createPost(@RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }

    @GetMapping("/post")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/post/{id}")
    public Post getPostById(@PathVariable UUID id) {
        return postService.getPostById(id)
                .orElseThrow(() -> new NoSuchElementException("Post nicht gefunden: " + id));
    }
    @GetMapping("/post/sender/{senderId}")
    public List<Post> getPostsBySender(@PathVariable UUID senderId) {
        return postService.getPostsBySender(senderId);
    }

    @GetMapping("/post/empfaenger/{empfaengerId}")
    public List<Post> getPostsByEmpfaenger(@PathVariable UUID empfaengerId) {
        return postService.getPostsByEmpfaenger(empfaengerId);
    }
    @DeleteMapping("/post/delete/{id}")
    public void deletePost(@PathVariable UUID id) {
        postService.deletePost(id);
    }

    @PutMapping("/post/update/{id}")
    public Post updatePost(@PathVariable UUID id, @RequestBody PostDto postDto) {
        return postService.updatePost(id, postDto);
    }


}

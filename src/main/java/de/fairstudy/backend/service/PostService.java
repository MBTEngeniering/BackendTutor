package de.fairstudy.backend.service;
import de.fairstudy.backend.entity.Post;
import de.fairstudy.backend.dto.PostDto;
import de.fairstudy.backend.repository.PostDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostDao postDao;

    public Post createPost(PostDto postDto) {
        List<Post.Nachricht> nachrichten = postDto.getNachrichten().stream()
                .map(dto -> Post.Nachricht.builder()
                        .text(dto.getText())
                        .timestamp(dto.getTimestamp())
                        .build())
                .collect(Collectors.toList());

        Post post = Post.builder()
                .sender(postDto.getSender())
                .empfaenger(postDto.getEmpfaenger())
                .nachrichten(nachrichten)
                .build();

        return postDao.save(post);
    }

    public List<Post> getAllPosts() {
        return postDao.findAll();
    }

    public Optional<Post> getPostById(UUID id) {
        return postDao.findById(id);
    }
    public List<Post> getPostsBySender(UUID senderId) {
        return postDao.findBySender(senderId);
    }

    public List<Post> getPostsByEmpfaenger(UUID empfaengerId) {
        return postDao.findByEmpfaenger(empfaengerId);
    }
    public void deletePost(UUID id) {
        if (!postDao.existsById(id)) {
            throw new NoSuchElementException("Post nicht gefunden: " + id);
        }
        postDao.deleteById(id);
    }

    public Post updatePost(UUID id, PostDto postDto) {
        Post existing = postDao.findById(id).orElseThrow(() ->
                new NoSuchElementException("Post nicht gefunden: " + id));

        existing.setSender(postDto.getSender());
        existing.setEmpfaenger(postDto.getEmpfaenger());

        List<Post.Nachricht> nachrichten = postDto.getNachrichten().stream()
                .map(dto -> Post.Nachricht.builder()
                        .text(dto.getText())
                        .timestamp(dto.getTimestamp())
                        .build())
                .toList();

        existing.setNachrichten(nachrichten);

        return postDao.save(existing);
    }

}

package de.fairstudy.backend.service;
import de.fairstudy.backend.dto.AllUserDto;
import de.fairstudy.backend.entity.AllUser;
import de.fairstudy.backend.entity.Post;
import de.fairstudy.backend.dto.PostDto;
import de.fairstudy.backend.repository.AllUserDao;
import de.fairstudy.backend.repository.PostDao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostDao postDao;
    private final AllUserService alluserService;

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
Post savePost= postDao.save(post);

System.out.println("Die erstelle Nachricht ahst : "+savePost.getId());
        Optional<AllUserDto> sender= alluserService.getUsersById(savePost.getSender());
        Optional<AllUserDto> emphaenger= alluserService.getUsersById(savePost.getEmpfaenger());
        if(sender.isPresent()){
            AllUserDto senderToupdate= sender.get();
            senderToupdate.getMessage().add(savePost.getId());
            alluserService.updateUsers(senderToupdate.getId(),senderToupdate);
        }
        if(emphaenger.isPresent()){
            AllUserDto emphaengerToupdate= emphaenger.get();
            emphaengerToupdate.getMessage().add(savePost.getId());
            alluserService.updateUsers(emphaengerToupdate.getId(),emphaengerToupdate);
        }
        return postDao.save(savePost);
    }

    public List<Post> getAllPosts() {
        return postDao.findAll();
    }

    public Optional<Post> getPostById(UUID id) {
        return postDao.findById(id);
    }
    @Transactional
    public List<Post> getPostsBySender(UUID senderId) {
        List<Post> posts = postDao.findBySender(senderId);
        posts.forEach(post -> Hibernate.initialize(post.getNachrichten())); // Lazy-Loading umgehen
        return posts;
    }
    @Transactional
    public List<Post> getPostsByEmpfaenger(UUID empfaengerId) {
        List<Post> posts = postDao.findByEmpfaenger(empfaengerId);
        posts.forEach(post -> Hibernate.initialize(post.getNachrichten())); // Lazy-Loading umgehen
        return posts;
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

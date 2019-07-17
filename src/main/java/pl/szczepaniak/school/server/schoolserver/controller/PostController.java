package pl.szczepaniak.school.server.schoolserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szczepaniak.school.server.schoolserver.domain.PostDto;
import pl.szczepaniak.school.server.schoolserver.model.Post;
import pl.szczepaniak.school.server.schoolserver.model.User;
import pl.szczepaniak.school.server.schoolserver.repository.PostRepositiry;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
public class PostController extends AbstractController {

    @Autowired
    private PostRepositiry postRepository;

//    @GetMapping("/posts")
//    public Page<PostDto> getPosts(Pageable pageable) {
//        User user = getCurrentUser();
//        return postRepository.findUsersPost(user.getId(),pageable).map(this::convert);
//    }


    @GetMapping("/posts")
    public Page<PostDto> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable).map(this::convert);
    }


    @PostMapping("/posts")
    public PostDto createQuestion(@Valid @RequestBody Post post) {
        post.setUser(getCurrentUser());
        post.setUserID(getCurrentUser().getId());
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = Calendar.getInstance();
        post.setDateTime("test");
        return convert(postRepository.save(post));
    }

    @PutMapping("/posts/{id}")
    public PostDto updatePost(@PathVariable Long postId,
                                        @Valid @RequestBody Post post) {
        return postRepository.findById(postId)
                .map(question -> {
                    question.setContent(post.getContent());
                    question.setPermission(post.getPermission());
                    question.setDateTime(post.getDateTime());
                    question.setUserID(post.getUserID());
                    question.setUser(post.getUser());
                    return convert(postRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + postId));
    }



    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long postId) {
        return postRepository.findById(postId)
                .map(question -> {
                    postRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + postId));
    }

    private PostDto convert(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setDateTime(post.getDateTime());
        dto.setContent(post.getContent());
        dto.setPermission(post.getPermission());
        dto.setUserID(post.getUserID());
        //dto.setUser(post.getUser());
        return dto;
    }
}

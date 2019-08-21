package pl.szczepaniak.school.server.schoolserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szczepaniak.school.server.schoolserver.domain.PostDto;
import pl.szczepaniak.school.server.schoolserver.domain.PostReactionDto;
import pl.szczepaniak.school.server.schoolserver.domain.UserDto;
import pl.szczepaniak.school.server.schoolserver.model.Post;
import pl.szczepaniak.school.server.schoolserver.model.PostReaction;
import pl.szczepaniak.school.server.schoolserver.model.User;
import pl.szczepaniak.school.server.schoolserver.repository.PostRepository;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
public class PostController extends AbstractController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    public Page<PostDto> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable).map(this::convert);
    }

    @GetMapping("/posts/{id}")
    public PostDto getPostByID(@PathVariable Long id){
        Post post = getPostById(id);
        return convert(post);
    }

    @PostMapping("/posts")
    public PostDto createQuestion(@Valid @RequestBody Post post) {
        post.setUser(getCurrentUser());
        post.setUserID(getCurrentUser().getId());
        post.setPostReactions(post.getPostReactions());
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = Calendar.getInstance();
        String time = dateFormat.format(cal.getTime());
        post.setDateTime(time);
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
                    question.setPhotos(post.getPhotos());
                    question.setPostReactions(post.getPostReactions());
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
        dto.setPhotos(post.getPhotos());
        UserDto userDto = new UserDto();
        User user = post.getUser();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSuname(user.getSurname());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setPhoto(user.getPhoto());
        userDto.setPermission(user.getPermissions());
        dto.setUser(userDto);
        List<PostReactionDto> reactionDtoList = new ArrayList<>();
        List<PostReaction> reactions = post.getPostReactions();

        if(reactions != null) {
            for (PostReaction r : reactions) {
                PostReactionDto d = new PostReactionDto();
                d.setId(r.getId());
                d.setUserID(r.getUserID());
                d.setReaction(r.getReaction());
                reactionDtoList.add(d);

            }
        }

        dto.setPostReactions(reactionDtoList);

        return dto;
    }
}

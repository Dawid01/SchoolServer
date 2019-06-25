package pl.szczepaniak.school.server.schoolserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szczepaniak.school.server.schoolserver.domain.PostDto;
import pl.szczepaniak.school.server.schoolserver.model.Post;
import pl.szczepaniak.school.server.schoolserver.model.User;
import pl.szczepaniak.school.server.schoolserver.repository.PostRepositiry;

import javax.validation.Valid;

public class PostController extends AbstractController {

    @Autowired
    private PostRepositiry postRepository;

    @GetMapping("/posts")
    public Page<PostDto> getFlashcards(Pageable pageable) {
        User user = getCurrentUser();
        return postRepository.findUsersPost(user.getId(),pageable).map(this::convert);
    }


    @PostMapping("/posts")
    public PostDto createQuestion(@Valid @RequestBody Post post) {
        post.setUser(getCurrentUser());
        return convert(postRepository.save(post));
    }

    @PutMapping("/posts/{id}")
    public PostDto updateFlashcard(@PathVariable Long flashcardId,
                                        @Valid @RequestBody Post post) {
        return postRepository.findById(flashcardId)
                .map(question -> {
                    question.setContent(post.getContent());
                    question.setPermission(post.getPermission());
                    question.setDateTime(post.getDateTime());
                    return convert(postRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + flashcardId));
    }



    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long flashcardId) {
        return postRepository.findById(flashcardId)
                .map(question -> {
                    postRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + flashcardId));
    }

    private PostDto convert(Post flashcard) {
        PostDto dto = new PostDto();
        dto.setId(flashcard.getId());
        dto.setContent(flashcard.getContent());
        dto.setDateTime(flashcard.getDateTime());
        dto.setContent(flashcard.getContent());
        dto.setPermission(flashcard.getPermission());
        return dto;
    }
}

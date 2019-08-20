package pl.szczepaniak.school.server.schoolserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szczepaniak.school.server.schoolserver.domain.PostReactionDto;
import pl.szczepaniak.school.server.schoolserver.model.PostReaction;
import pl.szczepaniak.school.server.schoolserver.repository.PostReactionRepository;
import javax.validation.Valid;

@RestController
public class PostReactionController extends AbstractController {

    @Autowired
    private PostReactionRepository reactionRepository;

    @GetMapping("/reactions")
    public Page<PostReactionDto> getAllReactions(Pageable pageable) {
        return reactionRepository.findAll(pageable).map(this::convert);
    }

//    @GetMapping("/reactions/{id}")
//    public Page<PostReactionDto> gettByPost(@PathVariable Long id) {
//        return reactionRepository.findByPost(id).map(this::convert);


    @PostMapping("/reactions")
    public PostReactionDto createQuestion(@Valid @RequestBody PostReaction reaction) {
        reaction.setReaction(reaction.getReaction());
        reaction.setUserID(getCurrentUser().getId());
        reaction.setPost(reaction.getPost());
        return convert(reactionRepository.save(reaction));
    }

    @PutMapping("/reactions/{id}")
    public PostReactionDto updatePost(@PathVariable Long reactionId,
            @Valid @RequestBody PostReaction reaction) {
        return reactionRepository.findById(reactionId)
                .map(question -> {
                   question.setId(reaction.getId());
                   question.setReaction(reaction.getReaction());
                   question.setUserID(reaction.getUserID());
                   question.setPost(reaction.getPost());
                    return convert(reactionRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("Reaction not found with id " + reactionId));
    }



    @DeleteMapping("/reactions/{reactionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long reactionId) {
        return reactionRepository.findById(reactionId)
                .map(question -> {
                    reactionRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Reaction not found with id " + reactionId));
    }

    private PostReactionDto convert(PostReaction reaction) {
        PostReactionDto dto = new PostReactionDto();
        dto.setId(reaction.getId());
        dto.setReaction(reaction.getReaction());
        dto.setUserID(reaction.getUserID());
        return dto;
    }
}


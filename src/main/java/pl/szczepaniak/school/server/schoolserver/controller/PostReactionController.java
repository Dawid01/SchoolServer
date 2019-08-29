package pl.szczepaniak.school.server.schoolserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szczepaniak.school.server.schoolserver.domain.PostReactionDto;
import pl.szczepaniak.school.server.schoolserver.model.Post;
import pl.szczepaniak.school.server.schoolserver.model.PostReaction;
import pl.szczepaniak.school.server.schoolserver.model.User;
import pl.szczepaniak.school.server.schoolserver.repository.PostReactionRepository;
import pl.szczepaniak.school.server.schoolserver.repository.PostRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PostReactionController extends AbstractController {

    @Autowired
    private PostReactionRepository reactionRepository;

    @GetMapping("/reactions")
    public Page<PostReactionDto> getAllReactions(Pageable pageable) {
        return reactionRepository.findAll(pageable).map(this::convert);
    }

    @PostMapping("/reactions/post/{id}")
    public PostReactionDto setPostReaction(@Valid @RequestBody PostReaction reaction, @PathVariable Long id){

        Long userId = getCurrentUser().getId();
        Post post = getPostById(id);
        List<PostReaction> reactions = post.getPostReactions();
        PostReaction postReaction = new PostReaction();

        if(reactions != null && reactions.size() != 0) {
            for (PostReaction r : reactions) {

                if (r.getUserID() == userId) {
                    if (r.getReaction() == reaction.getReaction()) {
                        deleteQuestion(r.getId());
                        return convert(reactionRepository.save(postReaction));
                    } else {
                        r.setReaction(reaction.getReaction());
                        postReaction = r;
                        deleteQuestion(r.getId());
                        return convert(reactionRepository.save(postReaction));
                    }
                }
            }
        }
        reaction.setPost(post);
        reaction.setUserID(userId);
        return convert(reactionRepository.save(reaction));
    }


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


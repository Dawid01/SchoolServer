package pl.szczepaniak.school.server.schoolserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szczepaniak.school.server.schoolserver.domain.CommentDto;
import pl.szczepaniak.school.server.schoolserver.model.Comment;
import pl.szczepaniak.school.server.schoolserver.repository.CommentRepository;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
public class CommentController extends  AbstractController{

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/comments")
    public Page<CommentDto> getAllReactions(Pageable pageable) {
        return commentRepository.findAll(pageable).map(this::convert);
    }

    @GetMapping("/comments/post/{id}")
    public Page<CommentDto> getCommentsByPost(Pageable pageable, @PathVariable Long id) {
        return commentRepository.findByPostId(id,pageable).map(this::convert);
    }

    @PostMapping("/comments")
    public CommentDto createQuestion(@Valid @RequestBody Comment comment) {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Calendar cal = Calendar.getInstance();
            String time = dateFormat.format(cal.getTime());
            comment.setDateTime(time);
            comment.setUserID(getCurrentUser().getId());
        return convert(commentRepository.save(comment));
    }

    @PutMapping("/comments/{id}")
    public CommentDto updatePost(@PathVariable Long commentId,
                                      @Valid @RequestBody CommentDto comment) {
        return commentRepository.findById(commentId)
                .map(question -> {
                    question.setId(comment.getId());
                    question.setUserID(comment.getUserID());
                    question.setDateTime(comment.getDateTime());
                    question.setContent(comment.getContent());
                    return convert(commentRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId));
    }



    @DeleteMapping("/comments/{reactionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long commentId) {
        return commentRepository.findById(commentId)
                .map(question -> {
                    commentRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId));
    }

    private CommentDto convert(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setUserID(comment.getUserID());
        dto.setDateTime(comment.getDateTime());
        dto.setContent(comment.getContent());
        return dto;
    }

}

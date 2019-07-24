package pl.szczepaniak.school.server.schoolserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szczepaniak.school.server.schoolserver.domain.LessonDto;
import pl.szczepaniak.school.server.schoolserver.model.Lesson;
import pl.szczepaniak.school.server.schoolserver.repository.LessonRepositiry;

import javax.validation.Valid;

@RestController
public class LessonController {

    @Autowired
    private LessonRepositiry lessonRepositiry;

    @GetMapping("/lessons")
    public Page<LessonDto> getAllLessons(Pageable pageable) {
        return lessonRepositiry.findAll(pageable).map(this::convert);
    }

    @PostMapping("/lessons")
    public LessonDto createQuestion(@Valid @RequestBody Lesson lesson) {

        return convert(lessonRepositiry.save(lesson));
    }

//    @PutMapping("/lessons/{id}")
//    public LessonDto updatePost(@PathVariable Long lessonID,
//                                     @Valid @RequestBody Lesson lesson) {
//        return lessonRepositiry.findById(lessonID)
//                .map(question -> {
//                    question.setId(lesson.getId());
//                    return convert(postRepository.save(question));
//                }).orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + postId));
//    }



    @DeleteMapping("/lessons/{lessonID}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long lessonID) {
        return lessonRepositiry.findById(lessonID)
                .map(question -> {
                    lessonRepositiry.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id " + lessonID));
    }

    private LessonDto convert(Lesson lesson) {
        LessonDto dto = new LessonDto();
        dto.setId(lesson.getId());
        dto.setRoom(lesson.getRoom());
        dto.setInfo(lesson.getInfo());
        dto.setLessonNumber(lesson.getLessonNumber());
        dto.setLessonPlan(lesson.getLessonPlan());
        dto.setSubjectName(lesson.getSubjectName());
        dto.setTime(lesson.getTime());
        return dto;
    }
}
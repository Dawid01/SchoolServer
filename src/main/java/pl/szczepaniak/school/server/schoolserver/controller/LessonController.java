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

//        switch (lesson.getLessonNumber()){
//            case 0:
//                lesson.setTime("7:10 - 7:55");
//                break;
//            case 1:
//                lesson.setTime("8:00 - 8:45");
//                break;
//            case 2:
//                lesson.setTime("8:55 - 9:40");
//                break;
//            case 3:
//                lesson.setTime("9:50 - 10:35");
//                break;
//            case 4:
//                lesson.setTime("10:45 - 11:30");
//                break;
//            case 5:
//                lesson.setTime("11:40 - 12:25");
//                break;
//            case 6:
//                lesson.setTime("12:40 - 13:25");
//                break;
//            case 7:
//                lesson.setTime("13:35 - 14:20");
//                break;
//            case 8:
//                lesson.setTime("14:30 - 15:15");
//                break;
//            case 9:
//                lesson.setTime("15:25 - 16:10");
//                break;
//            case 10:
//                lesson.setTime("16:20 - 17:05");
//                break;
//            case 11:
//                lesson.setTime("17:10 - 17:55");
//                break;
//        }

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
        dto.setSubjectName(lesson.getSubjectName());
        dto.setWeekDay(lesson.getWeekDay());
        dto.setTime(lesson.getTime());
        return dto;
    }
}
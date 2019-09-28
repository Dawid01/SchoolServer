package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class LessonController {

    @Autowired
    private LessonRepository lessonRepository;

    @GetMapping("/lessons")
    public Page<LessonDto> getAllLessons(Pageable pageable) {
        return lessonRepository.findAll(pageable).map(this::convert);
    }

    @GetMapping("/lessons/{externalID}")
    public LessonDto getByExternalID(@PathVariable String externalID){
        return  convert(lessonRepository.findByexternalID(externalID));
    }

    @PostMapping("/lessons")
    public LessonDto createQuestion(@Valid @RequestBody Lesson lesson) {
        return convert(lessonRepository.save(lesson));
    }

    @PutMapping("/lessons/{id}")
    public LessonDto updateLesson(@PathVariable Long lesonId,
                              @Valid @RequestBody Lesson lesson) {
        return lessonRepository.findById(lesonId)
                .map(question -> {
                    question.setId(lesson.getId());
                    question.setExternalID(lesson.getExternalID());
                    question.setClassId(lesson.getClassId());
                    question.setDayId(lesson.getDayId());
                    question.setGroupId(lesson.getGroupId());
                    question.setPeroidId(lesson.getPeroidId());
                    question.setSubjectId(lesson.getSubjectId());
                    question.setTeacherId(lesson.getTeacherId());
                    question.setWeekId(lesson.getWeekId());
                    return convert(lessonRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("Lesson room not found with id " + lesonId));
    }



    @DeleteMapping("/lessons/{groupId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long lesonId) {
        return lessonRepository.findById(lesonId)
                .map(question -> {
                    lessonRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Lesson room not found with id " + lesonId));
    }

    private LessonDto convert(Lesson lesson) {
        LessonDto dto = new LessonDto();
        dto.setId(lesson.getId());
        dto.setExternalID(lesson.getExternalID());
        dto.setClassId(lesson.getClassId());
        dto.setDayId(lesson.getDayId());
        dto.setGroupId(lesson.getGroupId());
        dto.setPeroidId(lesson.getPeroidId());
        dto.setSubjectId(lesson.getSubjectId());
        dto.setTeacherId(lesson.getTeacherId());
        dto.setWeekId(lesson.getWeekId());
        return dto;
    }


}

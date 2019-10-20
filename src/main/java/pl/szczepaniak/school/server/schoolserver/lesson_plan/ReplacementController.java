package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ReplacementController {

    @Autowired
    private ReplacementRepository replacementRepository;

    @GetMapping("/replacements")
    public Page<ReplacementDto> getAllReplacement(Pageable pageable) {
        return replacementRepository.findAll(pageable).map(this::convert);
    }

    @GetMapping("/replacements/{week}/{day}/{className}")
    public Page<ReplacementDto> getReplacementByClass(@PathVariable String week, @PathVariable String day, @PathVariable String className,Pageable pageable) {
        return replacementRepository.findReplacementByClass(week, day, className, pageable).map(this::convert);
    }

    @GetMapping("/replacements/teachers/{week}/{day}/{teacher}")
    public Page<ReplacementDto> getReplacementByTeacher(@PathVariable String week, @PathVariable String day, @PathVariable String teacher,Pageable pageable) {
        return replacementRepository.findReplacementByClass(week, day, teacher, pageable).map(this::convert);
    }


    @PostMapping("/replacements")
    public ReplacementDto createQuestion(@Valid @RequestBody Replacement replacement) {
        return convert(replacementRepository.save(replacement));
    }

    @PutMapping("/replacements/{id}")
    public ReplacementDto updateReplacement(@PathVariable Long id,
                               @Valid @RequestBody Replacement r) {
        return replacementRepository.findById(id)
                .map(question -> {
                    question.setId(r.getId());
                    question.setDay(r.getDay());
                    question.setLessonNumber(r.getLessonNumber());
                    question.setWeek(r.getWeek());
                    question.setClassName(r.getClassName());
                    question.setGroupName(r.getGroupName());
                    question.setSubject(r.getSubject());
                    question.setStartTime(r.getStartTime());
                    question.setEndTime(r.getEndTime());
                    question.setRoom(r.getRoom());
                    question.setTeacher(r.getTeacher());
                    question.setStatus(r.getStatus());
                    return convert(replacementRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("Replacement room not found with id " + id));
    }


    @DeleteMapping("/replacements/{Id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
        return replacementRepository.findById(id)
                .map(question -> {
                    replacementRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Replacement room not found with id " + id));
    }

    private ReplacementDto convert(Replacement r) {
        ReplacementDto dto = new ReplacementDto();
        dto.setId(r.getId());
        dto.setClassName(r.getClassName());
        dto.setDay(r.getDay());
        dto.setGroupName(r.getGroupName());
        dto.setLessonNumber(r.getLessonNumber());
        dto.setWeek(r.getWeek());
        dto.setSubject(r.getSubject());
        dto.setStartTime(r.getStartTime());
        dto.setEndTime(r.getEndTime());
        dto.setRoom(r.getRoom());
        dto.setTeacher(r.getTeacher());
        dto.setStatus(r.getStatus());
        return dto;
    }

}
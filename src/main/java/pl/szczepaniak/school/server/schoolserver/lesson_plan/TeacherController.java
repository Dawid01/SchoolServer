package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/teachers")
    public Page<TeacherDto> getAllTeachers(Pageable pageable) {
        return teacherRepository.findAll(pageable).map(this::convert);
    }

    @PostMapping("/teachers")
    public TeacherDto createQuestion(@Valid @RequestBody Teacher teacher) {
        return convert(teacherRepository.save(teacher));
    }

    @PutMapping("/teachers/{id}")
    public TeacherDto updateTeacher(@PathVariable Long teacherId,
                                   @Valid @RequestBody Teacher teacher) {
        return teacherRepository.findById(teacherId)
                .map(question -> {
                    question.setId(teacher.getId());
                    question.setName(teacher.getName());
                    question.setExternalID(teacher.getExternalID());
                    return convert(teacherRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id " + teacherId));
    }



    @DeleteMapping("/teachers/{teacherId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long teacherId) {
        return teacherRepository.findById(teacherId)
                .map(question -> {
                    teacherRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id " + teacherId));
    }

    private TeacherDto convert(Teacher teacher) {
        TeacherDto dto = new TeacherDto();
        dto.setId(teacher.getId());
        dto.setExternalID(teacher.getExternalID());
        dto.setName(teacher.getName());
        return dto;
    }
}

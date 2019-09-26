package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ClassController {

    @Autowired
    private ClassRepository classRepository;

    @GetMapping("/classes")
    public Page<ClassDto> getAllclasses(Pageable pageable) {
        return classRepository.findAll(pageable).map(this::convert);
    }

    @GetMapping("/classes/{externalID}")
    public ClassDto getByExternalID(@PathVariable String externalID){
        return  convert(classRepository.findByexternalID(externalID));
    }


    @PostMapping("/classes")
    public ClassDto createQuestion(@Valid @RequestBody Class c) {
        return convert(classRepository.save(c));
    }

    @PutMapping("/classes/{id}")
    public ClassDto updateClass(@PathVariable Long classId,
                                  @Valid @RequestBody Class c) {
        return classRepository.findById(classId)
                .map(question -> {
                    question.setId(c.getId());
                    question.setExternalID(c.getExternalID());
                    question.setName(c.getName());
                    return convert(classRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("Class not found with id " + classId));
    }


    @DeleteMapping("/classes/{classId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long classId) {
        return classRepository.findById(classId)
                .map(question -> {
                    classRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Class not found with id " + classId));
    }

    private ClassDto convert(Class c) {
        ClassDto dto = new ClassDto();
        dto.setId(c.getId());
        dto.setExternalID(c.getExternalID());
        dto.setName(c.getName());
        return dto;
    }
}


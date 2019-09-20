package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/subjects")
    public Page<SubjectDto> getAllSubjects(Pageable pageable) {
        return subjectRepository.findAll(pageable).map(this::convert);
    }

    @PostMapping("/subjects")
    public SubjectDto createQuestion(@Valid @RequestBody Subject subject) {
        return convert(subjectRepository.save(subject));
    }

    @PutMapping("/subjects/{id}")
    public SubjectDto updateSubject(@PathVariable Long subjectId,
                                  @Valid @RequestBody Subject subject) {
        return subjectRepository.findById(subjectId)
                .map(question -> {
                    question.setId(subject.getId());
                    question.setName(subject.getName());
                    question.setExternalID(subject.getExternalID());
                    return convert(subjectRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("Subject not found with id " + subjectId));
    }



    @DeleteMapping("/subjects/{subjectId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long subjectId) {
        return subjectRepository.findById(subjectId)
                .map(question -> {
                    subjectRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Subject not found with id " + subjectId));
    }

    private SubjectDto convert(Subject subject) {
        SubjectDto dto = new SubjectDto();
        dto.setId(subject.getId());
        dto.setExternalID(subject.getExternalID());
        dto.setName(subject.getName());
        return dto;
    }
}

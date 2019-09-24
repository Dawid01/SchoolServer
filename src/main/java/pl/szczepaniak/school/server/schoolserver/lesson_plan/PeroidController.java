package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PeroidController {

    @Autowired
    private PeroidRepository peroidRepository;

    @GetMapping("/peroids")
    public Page<PeroidDto> getAllPeroids(Pageable pageable) {
        return peroidRepository.findAll(pageable).map(this::convert);
    }


    @PostMapping("/peroids")
    public PeroidDto createQuestion(@Valid @RequestBody Period post) {
        return convert(peroidRepository.save(post));
    }

    @PutMapping("/peroids/{id}")
    public PeroidDto updatePeroid(@PathVariable Long peroidId,
                              @Valid @RequestBody Period period) {
        return peroidRepository.findById(peroidId)
                .map(question -> {
                    question.setId(period.getId());
                    return convert(peroidRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("Peroid not found with id " + peroidId));
    }



    @DeleteMapping("/peroid/{peroidId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long peroidId) {
        return peroidRepository.findById(peroidId)
                .map(question -> {
                    peroidRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Peroid not found with id " + peroidId));
    }

    private PeroidDto convert(Period period) {
        PeroidDto dto = new PeroidDto();
        dto.setId(period.getId());
        dto.setExternalID(period.getExternalID());
        dto.setStartTime(period.getStartTime());
        dto.setEndTime(period.getEndTime());
        dto.setPeriod(period.getPeriod());
        return dto;
    }
}

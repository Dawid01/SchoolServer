package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PeriodController {

    @Autowired
    private PeriodRepository peroidRepository;

    @GetMapping("/peroids")
    public Page<PeriodDto> getAllPeroids(Pageable pageable) {
        return peroidRepository.findAll(pageable).map(this::convert);
    }


    @GetMapping("/peroids/{externalID}")
    public PeriodDto getByExternalID(@PathVariable String externalID){
        return  convert(peroidRepository.findByexternalID(externalID));
    }


    @PostMapping("/peroids")
    public PeriodDto createQuestion(@Valid @RequestBody Period post) {
        return convert(peroidRepository.save(post));
    }

    @PutMapping("/peroids/{id}")
    public PeriodDto updatePeroid(@PathVariable Long peroidId,
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

    private PeriodDto convert(Period period) {
        PeriodDto dto = new PeriodDto();
        dto.setId(period.getId());
        dto.setExternalID(period.getExternalID());
        dto.setStartTime(period.getStartTime());
        dto.setEndTime(period.getEndTime());
        dto.setPeriod(period.getPeriod());
        return dto;
    }
}

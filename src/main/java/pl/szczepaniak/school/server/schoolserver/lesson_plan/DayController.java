package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DayController {

    @Autowired
    private DayRepository dayRepository;

    @GetMapping("/days")
    public Page<DayDto> getAllDays(Pageable pageable) {
        return dayRepository.findAll(pageable).map(this::convert);
    }

    @GetMapping("/days/{externalID}")
    public DayDto getByExternalID(@PathVariable String externalID){
        return  convert(dayRepository.findByexternalID(externalID));
    }



    @PostMapping("/days")
    public DayDto createQuestion(@Valid @RequestBody Day day) {
        return convert(dayRepository.save(day));
    }

    @PutMapping("/days/{id}")
    public DayDto updateClass(@PathVariable Long dayId,
                                @Valid @RequestBody Day day) {
        return dayRepository.findById(dayId)
                .map(question -> {
                    question.setId(day.getId());
                    question.setExternalID(day.getExternalID());
                    question.setName(day.getName());
                    question.setDay(day.getDay());
                    return convert(dayRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("Day not found with id " + dayId));
    }


    @DeleteMapping("/days/{dayId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long dayId) {
        return dayRepository.findById(dayId)
                .map(question -> {
                    dayRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Day not found with id " + dayId));
    }

    private DayDto convert(Day day) {
        DayDto dto = new DayDto();
        dto.setId(day.getId());
        dto.setExternalID(day.getExternalID());
        dto.setName(day.getName());
        return dto;
    }
}

package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class WeekController {

    @Autowired
    private WeekRepository weekRoomRepository;

    @GetMapping("/weeks")
    public Page<WeekDto> getAllWeeks(Pageable pageable) {
        return weekRoomRepository.findAll(pageable).map(this::convert);
    }

    @GetMapping("/weeks/{externalID}")
    public WeekDto getByExternalID(@PathVariable String externalID){
        return  convert(weekRoomRepository.findByexternalID(externalID));
    }

    @PostMapping("/weeks")
    public WeekDto createQuestion(@Valid @RequestBody Week room) {
        return convert(weekRoomRepository.save(room));
    }

    @PutMapping("/weeks/{id}")
    public WeekDto updateWeek(@PathVariable Long weekId,
                                        @Valid @RequestBody Week week) {
        return weekRoomRepository.findById(weekId)
                .map(question -> {
                    question.setId(week.getId());
                    question.setName(week.getName());
                    question.setExternalID(week.getExternalID());
                    return convert(weekRoomRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("Week room not found with id " + weekId));
    }



    @DeleteMapping("/weeks/{groupId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long weekId) {
        return weekRoomRepository.findById(weekId)
                .map(question -> {
                    weekRoomRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Week room not found with id " + weekId));
    }

    private WeekDto convert(Week week) {
        WeekDto dto = new WeekDto();
        dto.setId(week.getId());
        dto.setExternalID(week.getExternalID());
        dto.setName(week.getName());
        dto.setWeek(week.getWeek());
        return dto;
    }


}


package pl.szczepaniak.school.server.schoolserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szczepaniak.school.server.schoolserver.domain.LessonPlanDto;
import pl.szczepaniak.school.server.schoolserver.model.LessonPlan;
import pl.szczepaniak.school.server.schoolserver.repository.LessonPlanRepository;

import javax.validation.Valid;

@RestController
public class LessonPlanController extends AbstractController{

    @Autowired
    private LessonPlanRepository lessonPlanRepository;

    @GetMapping("/lessonPlans")
    public Page<LessonPlanDto> getLessonPlans(Pageable pageable) {
        return lessonPlanRepository.findAll(pageable).map(this::convert);
    }

    @GetMapping("/lessonPlans/{id}")
    public LessonPlanDto getLessonPlanByID(@PathVariable Long id) {
            return lessonPlanRepository.findById(id).map(this::convert).orElseThrow(() -> new ResourceNotFoundException("Lesson plan not found with id " + id));
    }


    @PostMapping("/lessonPlans")
    public LessonPlanDto createQuestion(@Valid @RequestBody LessonPlan lessonPlan) {
        return convert(lessonPlanRepository.save(lessonPlan));
    }


    @DeleteMapping("/lessonPlans/{lessonPlanID}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long lessonPlanID) {
        return lessonPlanRepository.findById(lessonPlanID)
                .map(question -> {
                    lessonPlanRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Lesson plan not found with id " + lessonPlanID));
    }

    private LessonPlanDto convert(LessonPlan lessonPlan) {
        LessonPlanDto dto = new LessonPlanDto();
        dto.setId(lessonPlan.getId());
        dto.setName(lessonPlan.getName());
        dto.setMondaylessonList(lessonPlan.getMondaylessonList());
        dto.setTuesdaylessonList(lessonPlan.getTuesdaylessonList());
        dto.setWednesdaylessonList(lessonPlan.getWednesdaylessonList());
        dto.setFridaylessonList(lessonPlan.getFridaylessonList());
        return dto;
    }
}

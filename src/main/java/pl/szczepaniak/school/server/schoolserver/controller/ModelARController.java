package pl.szczepaniak.school.server.schoolserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szczepaniak.school.server.schoolserver.domain.ModelARDto;
import pl.szczepaniak.school.server.schoolserver.model.ModelAR;
import pl.szczepaniak.school.server.schoolserver.repository.ModelARRepository;

import javax.validation.Valid;

@RestController
public class ModelARController {

    @Autowired
    private ModelARRepository modelARRepository;

    @GetMapping("/models3d")
    public Page<ModelARDto> getAllGames(Pageable pageable) {
        return modelARRepository.findAll(pageable).map(this::convert);
    }

    @GetMapping("/models3d/{id}")
    public ModelARDto getGameById(@PathVariable Long id) {
        return modelARRepository.findById(id).map(this::convert).orElseThrow(() -> new ResourceNotFoundException("Model not found with id " + id));
    }

    @PostMapping("/models3d")
    public ModelARDto createQuestion(@Valid @RequestBody ModelAR modelAR) {
        return convert(modelARRepository.save(modelAR));
    }

    @PutMapping("/models3d/{id}")
    public ModelARDto updateGame(@PathVariable Long modelId,
                              @Valid @RequestBody ModelARDto modelARDto) {
        return modelARRepository.findById(modelId)
                .map(question -> {
                    question.setId(modelARDto.getId());
                    question.setName(modelARDto.getName());
                    question.setImageURL(modelARDto.getImageURL());
                    question.setModelURL(modelARDto.getModelURL());
                    return convert(modelARRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("Model not found with id " + modelId));
    }



    @DeleteMapping("/models3d/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long modelId) {
        return modelARRepository.findById(modelId)
                .map(question -> {
                    modelARRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Model not found with id " + modelId));
    }

    private ModelARDto convert(ModelAR modelAR) {
        ModelARDto dto = new ModelARDto();
        dto.setId(modelAR.getId());
        dto.setName(modelAR.getName());
        dto.setImageURL(modelAR.getImageURL());
        dto.setModelURL(modelAR.getModelURL());
        return dto;
    }

}

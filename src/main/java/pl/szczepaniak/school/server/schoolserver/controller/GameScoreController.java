package pl.szczepaniak.school.server.schoolserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szczepaniak.school.server.schoolserver.domain.GameDto;
import pl.szczepaniak.school.server.schoolserver.domain.GameScoreDto;
import pl.szczepaniak.school.server.schoolserver.model.Game;
import pl.szczepaniak.school.server.schoolserver.model.GameScore;
import pl.szczepaniak.school.server.schoolserver.repository.GameScoreRepository;

import javax.validation.Valid;

@RestController
public class GameScoreController {

    @Autowired
    private GameScoreRepository gameScoreRepository;

    @GetMapping("/scores")
    public Page<GameScoreDto> getAllScores(Pageable pageable) {
        return gameScoreRepository.findAll(pageable).map(this::convert);
    }

    @GetMapping("/scores/{id}")
    public GameScoreDto getScoreById(@PathVariable Long id) {
        return gameScoreRepository.findById(id).map(this::convert).orElseThrow(() -> new ResourceNotFoundException("Game not found with id " + id));
    }

    @PostMapping("/scores")
    public GameScoreDto createQuestion(@Valid @RequestBody GameScore score) {
        return convert(gameScoreRepository.save(score));
    }

    @PutMapping("/scores/{id}")
    public GameScoreDto updateGame(@PathVariable Long commentId,
                              @Valid @RequestBody GameScoreDto scoreDto) {
        return gameScoreRepository.findById(commentId)
                .map(question -> {
                    question.setId(scoreDto.getId());
                    question.setScore(scoreDto.getScore());
                    question.setUserID(scoreDto.getUserID());
                    return convert(gameScoreRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("Game not found with id " + commentId));
    }



    @DeleteMapping("/scores/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long gameId) {
        return gameScoreRepository.findById(gameId)
                .map(question -> {
                    gameScoreRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Game not found with id " + gameId));
    }

    private GameScoreDto convert(GameScore score) {
        GameScoreDto dto = new GameScoreDto();
        dto.setId(score.getId());
        dto.setScore(score.getScore());
        dto.setUserID(score.getUserID());
        return dto;
    }

}

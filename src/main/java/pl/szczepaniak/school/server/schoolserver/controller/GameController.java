package pl.szczepaniak.school.server.schoolserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szczepaniak.school.server.schoolserver.domain.GameDto;
import pl.szczepaniak.school.server.schoolserver.model.Game;
import pl.szczepaniak.school.server.schoolserver.repository.GameRepository;

import javax.validation.Valid;

@RestController
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/games")
    public Page<GameDto> getAllGames(Pageable pageable) {
        return gameRepository.findAll(pageable).map(this::convert);
    }

    @GetMapping("/games/{id}")
    public GameDto getGameById(@PathVariable Long id) {
        return gameRepository.findById(id).map(this::convert).orElseThrow(() -> new ResourceNotFoundException("Game not found with id " + id));
    }

    @PostMapping("/games")
    public GameDto createQuestion(@Valid @RequestBody Game game) {
        return convert(gameRepository.save(game));
    }

    @PutMapping("/games/{id}")
    public GameDto updateGame(@PathVariable Long commentId,
                                 @Valid @RequestBody GameDto gameDto) {
        return gameRepository.findById(commentId)
                .map(question -> {
                    question.setId(gameDto.getId());
                    question.setName(gameDto.getName());
                    question.setGameScores(gameDto.getGameScores());
                    return convert(gameRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("Game not found with id " + commentId));
    }



    @DeleteMapping("/games/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long gameId) {
        return gameRepository.findById(gameId)
                .map(question -> {
                    gameRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Game not found with id " + gameId));
    }

    private GameDto convert(Game game) {
        GameDto dto = new GameDto();
        dto.setId(game.getId());
        dto.setName(game.getName());
        dto.setGameScores(game.getGameScores());
        return dto;
    }

}

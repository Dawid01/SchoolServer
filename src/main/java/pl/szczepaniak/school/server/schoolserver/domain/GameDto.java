package pl.szczepaniak.school.server.schoolserver.domain;

import pl.szczepaniak.school.server.schoolserver.model.GameScore;

import java.util.List;

public class GameDto {

    private Long id;

    private String name;

    private List<GameScore> gameScores;

    public GameDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GameScore> getGameScores() {
        return gameScores;
    }

    public void setGameScores(List<GameScore> gameScores) {
        this.gameScores = gameScores;
    }
}

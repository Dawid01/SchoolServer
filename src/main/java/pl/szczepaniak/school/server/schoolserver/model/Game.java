package pl.szczepaniak.school.server.schoolserver.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    private List<GameScore> gameScores;

    public Game() {
    }

    public Game(String name, List<GameScore> gameScores) {
        this.name = name;
        this.gameScores = gameScores;
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

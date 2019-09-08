package pl.szczepaniak.school.server.schoolserver.model;

import javax.persistence.*;

@Entity
public class GameScore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int score;

    private Long userID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Game game;

    public GameScore() {
    }


    public GameScore(int score, Long userID, Game game) {
        this.score = score;
        this.userID = userID;
        this.game = game;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}

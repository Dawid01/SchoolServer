package pl.szczepaniak.school.server.schoolserver.domain;

import pl.szczepaniak.school.server.schoolserver.model.Post;
import pl.szczepaniak.school.server.schoolserver.model.PostReaction;
import pl.szczepaniak.school.server.schoolserver.model.User;

import java.util.List;

public class PostReactionDto {

    private Long id;

    private int reaction;

    private long userID;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getReaction() {
        return reaction;
    }

    public void setReaction(int reaction) {
        this.reaction = reaction;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

}

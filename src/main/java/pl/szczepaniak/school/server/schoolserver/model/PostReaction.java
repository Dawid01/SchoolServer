package pl.szczepaniak.school.server.schoolserver.model;

import javax.persistence.*;

@Entity
public class PostReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int reaction;

    private Long userID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Post post;


    public PostReaction(int reaction, Long user, Post post) {
        this.reaction = reaction;
        this.userID = user;
        this.post = post;
    }

    public PostReaction() {
    }

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

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

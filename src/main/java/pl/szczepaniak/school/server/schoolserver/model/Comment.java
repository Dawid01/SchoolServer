package pl.szczepaniak.school.server.schoolserver.model;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(name="content", length=512)
    private String content;

    private Long userID;

    private String dateTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Post post;

    public Comment() {
    }

    public Comment(String content, Long userID, String dateTime, Post post) {
        this.content = content;
        this.userID = userID;
        this.dateTime = dateTime;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

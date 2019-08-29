package pl.szczepaniak.school.server.schoolserver.domain;

import pl.szczepaniak.school.server.schoolserver.model.Post;

import javax.persistence.*;

public class CommentDto {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(name="content", length=512)
    private String content;

    private Long userID;

    @Lob
    @Column(name="date", length=512)
    private String dateTime;


    public CommentDto() {
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
}

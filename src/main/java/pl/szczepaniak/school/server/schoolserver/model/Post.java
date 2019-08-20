package pl.szczepaniak.school.server.schoolserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(name="content", length=512)
    private String content;

    @Lob
    @Column(name="date", length=512)
    private String dateTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    private int permission;

    private Long userID;

    @Value("${photos}")
    String[] photos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<PostReaction> postReactions;


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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    public List<PostReaction> getPostReactions() {
        return postReactions;
    }

    public void setPostReactions(List<PostReaction> postReactions) {
        this.postReactions = postReactions;
    }
}

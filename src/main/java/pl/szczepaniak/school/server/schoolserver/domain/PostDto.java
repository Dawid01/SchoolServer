package pl.szczepaniak.school.server.schoolserver.domain;

import pl.szczepaniak.school.server.schoolserver.model.PostReaction;

import java.util.List;

public class PostDto {

    private Long id;
    private String content;
    private String dateTime;
    private UserDto user;
    private int permission;
    private Long userID;
    String[] photos;
    private List<PostReactionDto> postReactions;


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

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
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

    public List<PostReactionDto> getPostReactions() {
        return postReactions;
    }

    public void setPostReactions(List<PostReactionDto> postReactions) {
        this.postReactions = postReactions;
    }
}

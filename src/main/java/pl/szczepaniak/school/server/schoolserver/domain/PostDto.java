package pl.sykisoft.flashcards.server.flashcardsserver.domain;

import pl.sykisoft.flashcards.server.flashcardsserver.model.User;

import java.time.LocalDateTime;

public class PostDTO {

    private Long id;
    private String content;
    private LocalDateTime lastUpdate;
    private User user;
    private int permission;

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

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
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
}

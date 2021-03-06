package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import javax.persistence.*;
import java.util.List;

@Entity
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String externalID;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aClass")
    private List<GroupClass> groups;

    public Class() {
    }

    public Class(String externalID, String name) {
        this.externalID = externalID;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExternalID() {
        return externalID;
    }

    public void setExternalID(String externalID) {
        this.externalID = externalID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GroupClass> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupClass> groups) {
        this.groups = groups;
    }
}

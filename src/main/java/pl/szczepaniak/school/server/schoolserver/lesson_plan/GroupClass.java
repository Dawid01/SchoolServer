package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import javax.persistence.*;

@Entity
public class GroupClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String externalID;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Class aClass;

    public GroupClass() {
    }

    public GroupClass(String externalID, String name) {
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

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }
}

package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DayDef {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String externalID;

    private String name;

    private String days;

    public DayDef() {
    }

    public DayDef(String externalID, String name, String days) {
        this.externalID = externalID;
        this.name = name;
        this.days = days;
    }

    public Long getId() {
        return id;
    }

    public String getExternalID() {
        return externalID;
    }

    public String getName() {
        return name;
    }

    public String getDays() {
        return days;
    }
}

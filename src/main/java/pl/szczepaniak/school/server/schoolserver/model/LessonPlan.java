package pl.szczepaniak.school.server.schoolserver.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class LessonPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Lesson> mondaylessonList;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Lesson> tuesdaylessonList;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Lesson> wednesdaylessonList;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Lesson> thursdaylessonList;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Lesson> fridaylessonList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lesson> getMondaylessonList() {
        return mondaylessonList;
    }

    public void setMondaylessonList(List<Lesson> mondaylessonList) {
        this.mondaylessonList = mondaylessonList;
    }

    public List<Lesson> getTuesdaylessonList() {
        return tuesdaylessonList;
    }

    public void setTuesdaylessonList(List<Lesson> tuesdaylessonList) {
        this.tuesdaylessonList = tuesdaylessonList;
    }

    public List<Lesson> getWednesdaylessonList() {
        return wednesdaylessonList;
    }

    public void setWednesdaylessonList(List<Lesson> wednesdaylessonList) {
        this.wednesdaylessonList = wednesdaylessonList;
    }

    public List<Lesson> getThursdaylessonList() {
        return thursdaylessonList;
    }

    public void setThursdaylessonList(List<Lesson> thursdaylessonList) {
        this.thursdaylessonList = thursdaylessonList;
    }

    public List<Lesson> getFridaylessonList() {
        return fridaylessonList;
    }

    public void setFridaylessonList(List<Lesson> fridaylessonList) {
        this.fridaylessonList = fridaylessonList;
    }
}

package pl.szczepaniak.school.server.schoolserver.domain;


import pl.szczepaniak.school.server.schoolserver.model.Lesson;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

public class LessonPlanDto {

    private Long id;
    private String name;
   // private List<Lesson> lessons;

    public LessonPlanDto() {
    }

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

//    public List<Lesson> getLessons() {
//        return lessons;
//    }
//
//    public void setLessons(List<Lesson> lessons) {
//        this.lessons = lessons;
//    }
}

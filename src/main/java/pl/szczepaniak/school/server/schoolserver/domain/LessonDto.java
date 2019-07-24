package pl.szczepaniak.school.server.schoolserver.domain;

import pl.szczepaniak.school.server.schoolserver.model.LessonPlan;

public class LessonDto {

    private Long id;
    private int lessonNumber;
    private String subjectName;
    private String room;
    private String time;
    private String info;
    private LessonPlan LessonPlan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public pl.szczepaniak.school.server.schoolserver.model.LessonPlan getLessonPlan() {
        return LessonPlan;
    }

    public void setLessonPlan(pl.szczepaniak.school.server.schoolserver.model.LessonPlan lessonPlan) {
        LessonPlan = lessonPlan;
    }
}

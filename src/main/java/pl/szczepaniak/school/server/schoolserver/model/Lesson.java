package pl.szczepaniak.school.server.schoolserver.model;

import javax.persistence.*;

@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int lessonNumber;

    private String subjectName;

    private String room;

    private String time;

    private String info;

    private int weekDay;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private LessonPlan lessonPlan;

    public Lesson() {
    }

    public Lesson(int lessonNumber, String subjectName, String room, String info, int weekDay, LessonPlan lessonPlan) {
        this.lessonNumber = lessonNumber;
        this.subjectName = subjectName;
        this.room = room;
        this.info = info;
        this.weekDay = weekDay;
        this.lessonPlan = lessonPlan;
        this.time = setLesonTime(lessonNumber);

    }

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

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public LessonPlan getLessonPlan() {
        return lessonPlan;
    }

    public void setLessonPlan(LessonPlan lessonPlan) {
        this.lessonPlan = lessonPlan;
    }

    private String setLesonTime(int lessonNumber){

        String time = "";

        switch (lessonNumber){
            case 0:
                time = "7:10 - 7:55";
                break;
            case 1:
                time = "8:00 - 8:45";
                break;
            case 2:
                time = "8:55 - 9:40";
                break;
            case 3:
                time = "9:50 - 10:35";
                break;
            case 4:
                time = "10:45 - 11:30";
                break;
            case 5:
                time = "11:40 - 12:25";
                break;
            case 6:
                time = "12:40 - 13:25";
                break;
            case 7:
                time = "13:35 - 14:20";
                break;
            case 8:
                time = "14:30 - 15:15";
                break;
            case 9:
                time = "15:25 - 16:10";
                break;
            case 10:
                time = "16:20 - 17:05";
                break;
            case 11:
                time = "17:10 - 17:55";
                break;
        }
        return time;
    }
}

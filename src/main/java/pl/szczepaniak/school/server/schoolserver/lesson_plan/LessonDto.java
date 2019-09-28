package pl.szczepaniak.school.server.schoolserver.lesson_plan;

public class LessonDto {

    private Long id;

    private String externalID;

    private String classId;

    private String subjectId;

    private String groupId;

    private String weekId;

    private String dayId;

    private String peroidId;

    private String teacherId;

    public LessonDto() {
    }

    public LessonDto(String externalID, String classId, String subjectId, String groupId, String weekId, String dayId, String peroidId, String teacherId) {
        this.externalID = externalID;
        this.classId = classId;
        this.subjectId = subjectId;
        this.groupId = groupId;
        this.weekId = weekId;
        this.dayId = dayId;
        this.peroidId = peroidId;
        this.teacherId = teacherId;
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

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getWeekId() {
        return weekId;
    }

    public void setWeekId(String weekId) {
        this.weekId = weekId;
    }

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

    public String getPeroidId() {
        return peroidId;
    }

    public void setPeroidId(String peroidId) {
        this.peroidId = peroidId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}

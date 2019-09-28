package pl.szczepaniak.school.server.schoolserver.lesson_plan;

public class WeekDto {

    private Long id;

    private String externalID;

    private String name;

    private String week;

    public WeekDto() {
    }

    public WeekDto(String externalID, String name, String week) {
        this.externalID = externalID;
        this.name = name;
        this.week = week;
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

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}

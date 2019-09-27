package pl.szczepaniak.school.server.schoolserver.lesson_plan;

public class DayDto {

    private Long id;

    private String externalID;

    private String name;

    private String day;

    public DayDto() {
    }

    public DayDto(String externalID, String name, String day) {
        this.externalID = externalID;
        this.name = name;
        this.day = day;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}

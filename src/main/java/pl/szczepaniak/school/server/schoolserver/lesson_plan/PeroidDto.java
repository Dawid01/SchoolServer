package pl.szczepaniak.school.server.schoolserver.lesson_plan;

public class PeroidDto {

    private Long id;

    private String externalID;

    private int period;

    private String startTime;

    private String endTime;

    public PeroidDto() {
    }

    public PeroidDto(String externalID, int peroid, String startTime, String endTime) {
        this.externalID = externalID;
        this.period = peroid;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}

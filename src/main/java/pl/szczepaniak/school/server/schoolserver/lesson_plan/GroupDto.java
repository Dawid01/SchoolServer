package pl.szczepaniak.school.server.schoolserver.lesson_plan;

public class GroupDto {

    private Long id;

    private String externalID;

    private String name;

    private ClassDto classDto;

    public GroupDto() {
    }

    public GroupDto(String externalID, String name) {
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

    public ClassDto getClassDto() {
        return classDto;
    }

    public void setClassDto(ClassDto classDto) {
        this.classDto = classDto;
    }
}

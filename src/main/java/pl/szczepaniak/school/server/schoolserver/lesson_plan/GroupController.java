package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/groups")
    public Page<GroupDto> getAllGroups(Pageable pageable) {
        return groupRepository.findAll(pageable).map(this::convert);
    }

    @GetMapping("/groups/{externalID}")
    public GroupDto getByExternalID(@PathVariable String externalID){
        return  convert(groupRepository.findByexternalID(externalID));
    }

    @PostMapping("/groups")
    public GroupDto createQuestion(@Valid @RequestBody GroupClass group) {
        return convert(groupRepository.save(group));
    }

    @PutMapping("/groups/{id}")
    public GroupDto updateGroup(@PathVariable Long groupId,
                                    @Valid @RequestBody GroupClass group) {
        return groupRepository.findById(groupId)
                .map(question -> {
                    question.setId(group.getId());
                    question.setName(group.getName());
                    question.setExternalID(group.getExternalID());
                    return convert(groupRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("Group not found with id " + groupId));
    }



    @DeleteMapping("/groups/{groupId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long groupId) {
        return groupRepository.findById(groupId)
                .map(question -> {
                    groupRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Group not found with id " + groupId));
    }

    private GroupDto convert(GroupClass group) {
        GroupDto dto = new GroupDto();
        dto.setId(group.getId());
        dto.setExternalID(group.getExternalID());
        dto.setName(group.getName());
        dto.setClassDto(convert(group.getaClass()));
        return dto;
    }

    private ClassDto convert(Class c) {
        ClassDto dto = new ClassDto();
        dto.setId(c.getId());
        dto.setExternalID(c.getExternalID());
        dto.setName(c.getName());
        return dto;
    }
}


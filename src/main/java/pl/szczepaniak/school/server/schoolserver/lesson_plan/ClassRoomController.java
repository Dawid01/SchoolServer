package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ClassRoomController {

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @GetMapping("/classrooms")
    public Page<ClassRoomDto> getAllClassRooms(Pageable pageable) {
        return classRoomRepository.findAll(pageable).map(this::convert);
    }

    @GetMapping("/classrooms/{externalID}")
    public ClassRoomDto getByExternalID(@PathVariable String externalID){
        return  convert(classRoomRepository.findByexternalID(externalID));
    }

    @PostMapping("/classrooms")
    public ClassRoomDto createQuestion(@Valid @RequestBody ClassRoom room) {
        return convert(classRoomRepository.save(room));
    }

    @PutMapping("/classrooms/{id}")
    public ClassRoomDto updateClassRoom(@PathVariable Long roomId,
                                @Valid @RequestBody ClassRoom room) {
        return classRoomRepository.findById(roomId)
                .map(question -> {
                    question.setId(room.getId());
                    question.setName(room.getName());
                    question.setExternalID(room.getExternalID());
                    return convert(classRoomRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("Class room not found with id " + roomId));
    }



    @DeleteMapping("/classrooms/{groupId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long roomId) {
        return classRoomRepository.findById(roomId)
                .map(question -> {
                    classRoomRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Class room not found with id " + roomId));
    }

    private ClassRoomDto convert(ClassRoom classRoom) {
        ClassRoomDto dto = new ClassRoomDto();
        dto.setId(classRoom.getId());
        dto.setExternalID(classRoom.getExternalID());
        dto.setName(classRoom.getName());
        return dto;
    }

}

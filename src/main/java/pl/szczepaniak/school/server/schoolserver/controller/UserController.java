package pl.szczepaniak.school.server.schoolserver.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szczepaniak.school.server.schoolserver.domain.UserDto;
import pl.szczepaniak.school.server.schoolserver.model.User;

import javax.validation.Valid;

@RestController
public class UserController extends AbstractController {

    @GetMapping("/users")
    public Page<UserDto> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(this::convert);
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userRepository.findById(id).map(this::convert).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @GetMapping("/users/current/")
    public UserDto getLoggedUser() {
        User user = getCurrentUser();
        UserDto dto = convert(user);
        return dto;
    }


    @PostMapping("/users")
    public UserDto createQuestion(@Valid @RequestBody User user) {
        return convert(userRepository.save(user));
    }

    @PutMapping("/users/{userId}")
    public UserDto updateUsers(@PathVariable Long userId,
                               @Valid @RequestBody User user) {
        return userRepository.findById(userId)
                .map(question -> {
                    question.setName(user.getName());
                    question.setEmail(user.getEmail());
                    question.setSurname(user.getSurname());
                    question.setPermissions(user.getPermissions());
                    question.setPassword(user.getPassword());
                    question.setFlashcards(user.getFlashcards());
                    return convert(userRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }


    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long userID) {
        return userRepository.findById(userID)
                .map(question -> {
                    userRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userID));
    }

    private UserDto convert(User user) {
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSuname(user.getSurname());
        dto.setPermission(user.getPermissions());
        dto.setPassword(user.getPassword());
        return dto;
    }
}

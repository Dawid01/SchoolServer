package pl.szczepaniak.school.server.schoolserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import pl.szczepaniak.school.server.schoolserver.PasswordGenerator;
import pl.szczepaniak.school.server.schoolserver.domain.UserDto;
import pl.szczepaniak.school.server.schoolserver.model.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class UserController extends AbstractController {

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/users")
    public Page<UserDto> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(this::convert);
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userRepository.findById(id).map(this::convert).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @GetMapping("/users/name/{name}")
    public UserDto getUserByName(@PathVariable String name) {
        User user = userRepository.findByName(name);
        UserDto dto = convert(user);
        return dto;
    }

    @GetMapping("/users/current/")
    public UserDto getLoggedUser() {
        User user = getCurrentUser();
        UserDto dto = convert(user);
        return dto;
    }

//    @GetMapping("/users/{post}")
//    public UserDto getPostUser() {
//        User user = getCurrentUser();
//        UserDto dto = convert(user);
//        return dto;
//    }


    @PostMapping("/users")
    public UserDto createQuestion(@Valid @RequestBody User user) {
        return convert(userRepository.save(user));
    }

    @PostMapping("/users/confirm/{name}/{surname}/{email}")
    public String createUser(@PathVariable String name, @PathVariable String surname, @PathVariable String email) {

        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(email);
            helper.setSubject("Witaj " + name);

            User user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setSurname(surname);
            user.setPassword(new PasswordGenerator().generateRandomPassword(12));

            helper.setText("Twoje konto zostało utworzone! <br>" +
                    "Tymczasowe hasło: <b>" + user.getPassword() + "</b>", true);
            javaMailSender.send(msg);
            convert(userRepository.save(user));
            return "Dodano nowego użytkownika";
        }catch (MessagingException e){
            return "ERROR";
        }
    }

    @GetMapping("/user/create/{className}/{name}/{surname}/{email}")
    public String createUserAccount(@PathVariable String className, @PathVariable String name, @PathVariable String surname, @PathVariable String email) {

        try {

            //String ip = "35.232.24.163";
            String ip = "192.168.0.110";

            String link = "http://" + ip +":8080/" + "users/confirm/" + name + "/" + surname + "/" + email;

            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo("dawidszczepaniak55@gmail.com");
            helper.setSubject("Nowy użytkownik: " + name + " " + surname);
            helper.setText("<b>Imie: </b>\n" + name +
                    "<br>\n" +
                    "<br>\n" +
                    "<b>Nazwisko: </b>\n" + surname +
                    "<br>\n" +
                    "<br>\n" +
                    "<b>Email: </b>\n" + email +
                    "<br>\n" +
                    "<br>\n" +
                    "<b>Klasa: </b>\n" + className +
                    "<br>\n" +
                    "<br>\n" +
                    "<form action=\"" + link +"\" method=\"post\">" +
                    "<button style=\"background-color: #14b1ae;\n" +
                    "  border: none;\n" +
                    "  color: white;\n" +
                    "  padding: 15px 32px;\n" +
                    "  text-align: center;\n" +
                    "  text-decoration: none;\n" +
                    "  display: inline-block;\n" +
                    "  font-size: 16px;\n" +
                    "  cursor: pointer;\n" +
                    "  margin: auto\"><b>Potwierdz</b></button>\n" +
                    "</form>", true);

            javaMailSender.send(msg);

            return "OK";
        }catch (MessagingException e){
            return ":(";
        }

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
                    question.setPosts(user.getPosts());
                    question.setPhoto(user.getPhoto());
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
        dto.setSurname(user.getSurname());
        dto.setPermission(user.getPermissions());
        dto.setPassword(user.getPassword());
        dto.setPhoto(user.getPhoto());
        return dto;
    }
}

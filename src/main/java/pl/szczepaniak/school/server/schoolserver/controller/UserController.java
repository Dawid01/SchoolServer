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
import org.springframework.web.servlet.ModelAndView;
import pl.szczepaniak.school.server.schoolserver.PasswordGenerator;
import pl.szczepaniak.school.server.schoolserver.domain.UserDto;
import pl.szczepaniak.school.server.schoolserver.model.ConfirmationToken;
import pl.szczepaniak.school.server.schoolserver.model.User;
import pl.szczepaniak.school.server.schoolserver.repository.ConfirmationTokenRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class UserController extends AbstractController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;


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


    @PostMapping("/register/{className}")
    public String registerUser(@Valid @RequestBody UserDto userDto, @PathVariable String className)
    {

        String message = "Error";
        User existingUser = userRepository.findByEmail(userDto.getEmail());
        if(existingUser != null)
        {
            message = "This email already exists!";
        }
        else
        {
            User user = new User();
            user.setName(userDto.getName());
            user.setSurname(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPermissions(userDto.getPermission());
            userRepository.save(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);
            //String link = "http://192.168.0.110:8080/confirm-account?token="+confirmationToken.getConfirmationToken();
            String link = "http://35.232.24.163:8080/confirm-account?token="+confirmationToken.getConfirmationToken();

            try {
                MimeMessage msg = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(msg, true);
                helper.setTo("dawidszczepaniak55@gmail.com");
                helper.setSubject("Nowy użytkownik!");
                helper.setText("<b>Imie: </b>\n" + user.getName()  +
                        "<br>\n" +
                        "<br>\n" +
                        "<b>Nazwisko: </b>\n" + user.getSurname()  +
                        "<br>\n" +
                        "<br>\n" +
                        "<b>Email: </b>\n" + user.getEmail() +
                        "<br>\n" +
                        "<br>\n" +
                        "<b>Klasa: </b>\n" +
                        className +
                        "<br>\n" +
                        "<br>\n" +
                        "<button  style=\"background-color: #14b1ae;\n" +
                        "  border: none;\n" +
                        "  color: white;\n" +
                        "  padding: 15px 32px;\n" +
                        "  text-align: center;\n" +
                        "  text-decoration: none;\n" +
                        "  display: inline-block;\n" +
                        "  font-size: 16px;\n" +
                        "  cursor: pointer;\n" +
                        "  margin: auto\"><b><a href = \" + link + \">Potwierdz</a></b></button>\n  <br> " +
                        "" + link, true);

                javaMailSender.send(msg);
                message = "successful Registeration";
            }catch (MessagingException e){

            }
        }

        return message;
    }


    // @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    @GetMapping("/confirm-account")
    public ModelAndView confirmUserAccount(@RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        ModelAndView modelAndView = new ModelAndView();
        System.out.format("Confirm...");

        if(token != null)
        {
            System.out.format("token != null");
            User user = userRepository.findByEmail(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            modelAndView.setViewName("accountVerified");
            try {
                System.out.format("send email to " + user.getEmail());
                MimeMessage msg = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(msg, true);
                helper.setTo(user.getEmail());
                user.setPassword(new PasswordGenerator().generateRandomPassword(12));
                updateUsers(user.getId(), user);
                helper.setSubject("Witaj " + user.getName() + "!");
                helper.setText("Twoje konto zostało utworzone! <br>" +
                        "Tymczasowe hasło: <b>" + user.getPassword() + "</b>", true);
                javaMailSender.send(msg);
            }catch (MessagingException e){

            }
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
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

package pl.szczepaniak.school.server.schoolserver;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.szczepaniak.school.server.schoolserver.model.Post;
import pl.szczepaniak.school.server.schoolserver.model.User;
import pl.szczepaniak.school.server.schoolserver.repository.PostRepositiry;
import pl.szczepaniak.school.server.schoolserver.repository.UserRepository;

@SpringBootApplication
public class SchoolServerApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepositiry postRepositiry;



    public static void main(String[] args) {
        SpringApplication.run(SchoolServerApplication.class, args);
    }


    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            initUser();
            initFlashCards();
        };
    }

    private void initUser() {
        User user = new User();
        user.setEmail("johnrambo@gmail.com");
        user.setName("John");
        user.setSurname("Rambo");
        user.setPassword("12345678");
        User user2 = new User();
        user2.setEmail("example@gmail.com");
        user2.setName("name");
        user2.setSurname("surname");
        user2.setPassword("12345678");

        user = userRepository.save(user);
        Post post1 =  new Post();
        post1.setUser(user);
        post1.setContent("Test Content 1");
        post1.setPermission(0);
        post1 = postRepositiry.save(post1);

        user2 = userRepository.save(user2);
        Post post2 =  new Post();
        post2.setUser(user2);
        post2.setContent("Test Content 2");
        post2.setPermission(0);
        post2 = postRepositiry.save(post2);

    }

    private void initFlashCards()
    {

    }

}


package pl.sykisoft.flashcards.server.flashcardsserver;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.sykisoft.flashcards.server.flashcardsserver.model.User;
import pl.sykisoft.flashcards.server.flashcardsserver.repository.FlashCardRepository;
import pl.sykisoft.flashcards.server.flashcardsserver.repository.FlashcardItemRepository;
import pl.sykisoft.flashcards.server.flashcardsserver.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SchoolServerApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlashCardRepository flashCardRepository;

    @Autowired
    private FlashcardItemRepository flashcardItemRepository;


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
        user.setName("John Rambo");
        user.setPassword("12345678");
        User user2 = new User();
        user2.setEmail("example@gmail.com");
        user2.setName("example");
        user2.setPassword("12345678");

        user = userRepository.save(user);
        user2 = userRepository.save(user2);


    }

    private void initFlashCards()
    {

    }

}


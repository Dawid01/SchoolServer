package pl.szczepaniak.school.server.schoolserver;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.szczepaniak.school.server.schoolserver.model.Post;
import pl.szczepaniak.school.server.schoolserver.model.User;
import pl.szczepaniak.school.server.schoolserver.repository.PostRepository;
import pl.szczepaniak.school.server.schoolserver.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SchoolServerApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepositiry;

    public static void main(String[] args) {
        SpringApplication.run(SchoolServerApplication.class, args);
    }


    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            initUser();
        };
    }

    private void initUser() {
        User user = new User();
        user.setEmail("johnrambo@gmail.com");
        user.setName("John");
        user.setSurname("Rambo");
        user.setPassword("12345678");
        user.setPhoto("https://vignette.wikia.nocookie.net/deadliestfiction/images/c/c5/Rambo.jpg/revision/latest?cb=20141007212329");
        User user2 = new User();
        user2.setEmail("example@gmail.com");
        user2.setName("name");
        user2.setSurname("surname");
        user2.setPassword("12345678");
        user2.setPhoto("https://www.w3schools.com/howto/img_avatar.png");
        User user3 = new User();
        user3.setEmail("user3@gmail.com");
        user3.setName("User");
        user3.setSurname("3");
        user3.setPassword("12345678");
        user3.setPhoto("http://giphygifs.s3.amazonaws.com/media/7kn27lnYSAE9O/giphy.gif");


        user = userRepository.save(user);
        Post post1 =  new Post();
        post1.setUser(user);
        post1.setContent("Lorem Ipsum jest tekstem stosowanym jako przykładowy wypełniacz w przemyśle poligraficznym. Został po raz pierwszy użyty w XV w. przez nieznanego drukarza do wypełnienia tekstem próbnej książki. Pięć wieków później zaczął być używany przemyśle elektronicznym, pozostając praktycznie niezmienionym. Spopularyzował się w latach 60. XX w. wraz z publikacją arkuszy Letrasetu, zawierających fragmenty Lorem Ipsum, a ostatnio z zawierającym różne wersje Lorem Ipsum oprogramowaniem przeznaczonym do realizacji druków na komputerach osobistych, jak Aldus PageMaker. Ogólnie znana teza głosi, iż użytkownika może rozpraszać zrozumiała zawartość strony, kiedy ten chce zobaczyć sam jej wygląd. Jedną z mocnych stron używania Lorem Ipsum jest to, że ma wiele różnych „kombinacji” zdań, słów i akapitów, w przeciwieństwie do zwykłego: „tekst, tekst, tekst”, sprawiającego, że wygląda to „zbyt czytelnie” po polsku. Wielu webmasterów i designerów używa Lorem Ipsum jako domyślnego modelu tekstu i wpisanie w internetowej wyszukiwarce ‘lorem ipsum’ spowoduje znalezienie bardzo wielu stron, które wciąż są w budowie. Wiele wersji tekstu ewoluowało i zmieniało się przez lata, czasem przez przypadek, czasem specjalnie (humorystyczne wstawki itd).");
        post1.setPermission(0);
        post1.setDateTime("01.07.2019");
        post1.setUserID(user.getId());
        postRepositiry.save(post1);

        user2 = userRepository.save(user2);
        Post post2 =  new Post();
        post2.setUser(user2);
        post2.setContent("Test Content 2");
        post2.setPermission(0);
        post2.setDateTime("01.07.2019");
        post2.setUserID(user2.getId());
        postRepositiry.save(post2);

        user3 = userRepository.save(user3);
        Post post3 =  new Post();
        post3.setUser(user3);
        post3.setContent("Lorem Ipsum jest tekstem stosowanym jako przykładowy wypełniacz w przemyśle poligraficznym. Został po raz pierwszy użyty w XV w. przez nieznanego drukarza do wypełnienia tekstem próbnej książki. Pięć wieków później zaczął być używany przemyśle elektronicznym, pozostając praktycznie niezmienionym. Spopularyzował się w latach 60. XX w. wraz z publikacją arkuszy Letrasetu, zawierających fragmenty Lorem Ipsum, a ostatnio z zawierającym różne wersje Lorem Ipsum oprogramowaniem przeznaczonym do realizacji druków na komputerach osobistych, jak Aldus PageMaker. Ogólnie znana teza głosi, iż użytkownika może rozpraszać zrozumiała zawartość strony, kiedy ten chce zobaczyć sam jej wygląd. Jedną z mocnych stron używania Lorem Ipsum jest to, że ma wiele różnych „kombinacji” zdań, słów i akapitów, w przeciwieństwie do zwykłego: „tekst, tekst, tekst”, sprawiającego, że wygląda to „zbyt czytelnie” po polsku. Wielu webmasterów i designerów używa Lorem Ipsum jako domyślnego modelu tekstu i wpisanie w internetowej wyszukiwarce ‘lorem ipsum’ spowoduje znalezienie bardzo wielu stron, które wciąż są w budowie. Wiele wersji tekstu ewoluowało i zmieniało się przez lata, czasem przez przypadek, czasem specjalnie (humorystyczne wstawki itd).");
        post3.setPermission(0);
        post3.setDateTime("01.07.2019");
        post3.setUserID(user3.getId());
        List<String> images = new ArrayList<>();
        images.add("http://hdwpro.com/wp-content/uploads/2018/12/Top-Bugatti-Chiron.jpg");
        images.add("https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwi9hcft4MHjAhXlx6YKHZ_GBjcQjRx6BAgBEAU&url=http%3A%2F%2Fimaganationface.org%2Fhd-images-of-super-cars-and-bikes%2F&psig=AOvVaw3fZfZ3zcCwXe5X5fX4uLmk&ust=1563652094072695");
        images.add("https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwi33pKp4cHjAhVIzqYKHbAyA1kQjRx6BAgBEAU&url=https%3A%2F%2Fautoekspress.al%2Fford-mustang-shelby-gt500-e-2020-es-pershendet-hellcats-dhe-camaros-me-te-pakten-700-kuaj-fuqi%2F&psig=AOvVaw3GnQPAS67JXOYYAEl-a0p-&ust=1563652329325094");
        images.add("https://cnet4.cbsistatic.com/img/QNyEq1zWTqUwGACe8fTKLv4K1us=/980x551/2019/01/13/53d3ba47-59df-4f54-bcc4-3af9f228f578/2020-ford-mustang-shelby-gt500-detroit-auto-show-46.jpg");
        postRepositiry.save(post3);

    }

}


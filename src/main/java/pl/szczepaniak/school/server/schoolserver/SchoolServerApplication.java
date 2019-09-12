package pl.szczepaniak.school.server.schoolserver;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import pl.szczepaniak.school.server.schoolserver.files.FileStorageProperties;
import pl.szczepaniak.school.server.schoolserver.model.*;
import pl.szczepaniak.school.server.schoolserver.repository.*;

import java.util.ArrayList;
import java.util.List;


@EnableConfigurationProperties({
        FileStorageProperties.class
})

@SpringBootApplication
public class SchoolServerApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepositiry;

    @Autowired
    private PostReactionRepository reactionRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameScoreRepository gameScoreRepository;

    @Autowired
    private LessonPlanRepository lessonPlanRepository;

    @Autowired
    private  LessonRepositiry lessonRepositiry;

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

        User guest = new User();
        guest.setEmail("guest@ezn.pl");
        guest.setName("guest");
        guest.setPassword("guest");
        guest.setPhoto("https://www.wykop.pl/cdn/c3397992/anonimowy-anonim_HKw17T8zG1,q250.jpg");
        guest.setPermissions(100);
        guest.setSurname("");
        userRepository.save(guest);

        User user1 = new User();
        user1.setEmail("johnrambo@gmail.com");
        user1.setName("John");
        user1.setSurname("Rambo");
        user1.setPassword("12345678");
        user1.setPhoto("https://vignette.wikia.nocookie.net/deadliestfiction/images/c/c5/Rambo.jpg/revision/latest?cb=20141007212329");
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


        user1 = userRepository.save(user1);
        Post post1 =  new Post();
        post1.setUser(user1);
        post1.setContent("Lorem Ipsum jest tekstem stosowanym jako przykładowy wypełniacz w przemyśle poligraficznym. Został po raz pierwszy użyty w XV w. przez nieznanego drukarza do wypełnienia tekstem próbnej książki. Pięć wieków później zaczął być używany przemyśle elektronicznym, pozostając praktycznie niezmienionym. Spopularyzował się w latach 60. XX w. wraz z publikacją arkuszy Letrasetu, zawierających fragmenty Lorem Ipsum, a ostatnio z zawierającym różne wersje Lorem Ipsum oprogramowaniem przeznaczonym do realizacji druków na komputerach osobistych, jak Aldus PageMaker. Ogólnie znana teza głosi, iż użytkownika może rozpraszać zrozumiała zawartość strony, kiedy ten chce zobaczyć sam jej wygląd. Jedną z mocnych stron używania Lorem Ipsum jest to, że ma wiele różnych „kombinacji” zdań, słów i akapitów, w przeciwieństwie do zwykłego: „tekst, tekst, tekst”, sprawiającego, że wygląda to „zbyt czytelnie” po polsku. Wielu webmasterów i designerów używa Lorem Ipsum jako domyślnego modelu tekstu i wpisanie w internetowej wyszukiwarce ‘lorem ipsum’ spowoduje znalezienie bardzo wielu stron, które wciąż są w budowie. Wiele wersji tekstu ewoluowało i zmieniało się przez lata, czasem przez przypadek, czasem specjalnie (humorystyczne wstawki itd).");
        post1.setPermission(0);
        post1.setDateTime("01.07.2019");
        post1.setUserID(user1.getId());
        String[] photos1 = new String[1];
        photos1[0] = "https://images.unsplash.com/photo-1534067783941-51c9c23ecefd?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80";
        post1.setPhotos(photos1);
        postRepositiry.save(post1);

        user2 = userRepository.save(user2);
        Post post2 =  new Post();
        post2.setUser(user2);
        post2.setContent("Test Content 2");
        post2.setPermission(0);
        post2.setDateTime("01.07.2019");
        post2.setUserID(user2.getId());
        String[] photos2 = new String[9];
        photos2[0] = "https://images.unsplash.com/photo-1531804055935-76f44d7c3621?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80";
        photos2[1] = "https://cdn.pixabay.com/photo/2016/11/14/04/45/elephant-1822636_960_720.jpg";
        photos2[2] = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dw-burnett-2020-gt500-1547418557.jpg?resize=768:*";
        photos2[3] = "https://images.unsplash.com/photo-1534067783941-51c9c23ecefd?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80";
        photos2[4] = "https://www.designboom.com/wp-content/uploads/2018/09/photos-burning-man-2018-designboom-1.jpg";
        photos2[5] = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4R7pF91TTryDvBS00aHt9nYWQVybgKCDj-0rdky6buOpFufwbmQ";
        photos2[6] = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dw-burnett-2020-gt500-1547418557.jpg?resize=768:*";
        photos2[7] = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dw-burnett-2020-gt500-1547418557.jpg?resize=768:*";
        photos2[8] = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dw-burnett-2020-gt500-1547418557.jpg?resize=768:*";
        post2.setPhotos(photos2);
        postRepositiry.save(post2);


        user3 = userRepository.save(user3);
        Post post3 =  new Post();
        post3.setUser(user3);
        post3.setContent("Lorem Ipsum jest tekstem stosowanym jako przykładowy wypełniacz w przemyśle poligraficznym. Został po raz pierwszy użyty w XV w. przez nieznanego drukarza do wypełnienia tekstem próbnej książki. Pięć wieków później zaczął być używany przemyśle elektronicznym, pozostając praktycznie niezmienionym. Spopularyzował się w latach 60. XX w. wraz z publikacją arkuszy Letrasetu, zawierających fragmenty Lorem Ipsum, a ostatnio z zawierającym różne wersje Lorem Ipsum oprogramowaniem przeznaczonym do realizacji druków na komputerach osobistych, jak Aldus PageMaker. Ogólnie znana teza głosi, iż użytkownika może rozpraszać zrozumiała zawartość strony, kiedy ten chce zobaczyć sam jej wygląd. Jedną z mocnych stron używania Lorem Ipsum jest to, że ma wiele różnych „kombinacji” zdań, słów i akapitów, w przeciwieństwie do zwykłego: „tekst, tekst, tekst”, sprawiającego, że wygląda to „zbyt czytelnie” po polsku. Wielu webmasterów i designerów używa Lorem Ipsum jako domyślnego modelu tekstu i wpisanie w internetowej wyszukiwarce ‘lorem ipsum’ spowoduje znalezienie bardzo wielu stron, które wciąż są w budowie. Wiele wersji tekstu ewoluowało i zmieniało się przez lata, czasem przez przypadek, czasem specjalnie (humorystyczne wstawki itd).");
        post3.setPermission(0);
        post3.setDateTime("01.07.2019");
        String[] photos3 = new String[4];
        photos3[0] = "http://hdwpro.com/wp-content/uploads/2018/12/Top-Bugatti-Chiron.jpg";
        photos3[1] = "https://cnet4.cbsistatic.com/img/QNyEq1zWTqUwGACe8fTKLv4K1us=/980x551/2019/01/13/53d3ba47-59df-4f54-bcc4-3af9f228f578/2020-ford-mustang-shelby-gt500-detroit-auto-show-46.jpg";
        photos3[2] = "https://images.unsplash.com/photo-1531804055935-76f44d7c3621?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80";
        photos3[3] = "https://cdn.pixabay.com/photo/2016/11/14/04/45/elephant-1822636_960_720.jpg";
        post3.setPhotos(photos3);
        post3.setUserID(user3.getId());
        postRepositiry.save(post3);

        reactionRepository.save(new PostReaction(1,user1.getId(),post3));
        reactionRepository.save(new PostReaction(1,user2.getId(),post3));
        reactionRepository.save(new PostReaction(0,user3.getId(),post3));

        commentRepository.save(new Comment("Cool", user1.getId(), "01.07.2019", post3));
        commentRepository.save(new Comment("Nice, Lorem Ipsum jest tekstem stosowanym jako przykładowy wypełniacz w przemyśle poligraficznym. Został po raz pierwszy użyty w XV w. przez nieznanego drukarza do wypełnienia tekstem próbnej książki. Pięć wieków później zaczął być używany przemyśle elektronicznym, pozostając praktycznie niezmienionym.", user2.getId(), "01.07.2019", post3));
        commentRepository.save(new Comment("Good Luck", user3.getId(), "01.07.2019", post3));

        Game binaryGame = new Game("Binary Game", new ArrayList<>());
        gameRepository.save(binaryGame);
        gameScoreRepository.save(new GameScore(84, user2.getId(), binaryGame));
        gameScoreRepository.save(new GameScore(14, user3.getId(), binaryGame));

        LessonPlan lessonPlan1 = new LessonPlan();
        lessonPlan1.setName("IIIG");
        lessonPlanRepository.save(lessonPlan1);

        lessonRepositiry.save(new Lesson(4, "Matematyka", "203", "GD", 1, lessonPlan1));
        lessonRepositiry.save(new Lesson(5, "Religia / Etyka", "08 / 03", "OJ / JK", 1, lessonPlan1));
        lessonRepositiry.save(new Lesson(6, "Historia", "101", "TW", 1, lessonPlan1));
        lessonRepositiry.save(new Lesson(7, "J.Polski", "100", "DA", 1, lessonPlan1));
        lessonRepositiry.save(new Lesson(8, "Fizyka", "201", "MM", 1, lessonPlan1));
        lessonRepositiry.save(new Lesson(9, "Zzw", "03", "DA", 1, lessonPlan1));

        lessonRepositiry.save(new Lesson(3, "Matematyka", "202", "GD", 2, lessonPlan1));
        lessonRepositiry.save(new Lesson(4, "Matematyka", "202", "GD", 2, lessonPlan1));
        lessonRepositiry.save(new Lesson(5, "Historia", "101", "TW", 2, lessonPlan1));
        lessonRepositiry.save(new Lesson(6, "J.Ang / PRZERWA NA CKP", "33 / ckp", "PB / ckp", 2, lessonPlan1));
        lessonRepositiry.save(new Lesson(7, "Wf / Apk. Internetowe", "B / A_210 ckp", "MA / GT", 2, lessonPlan1));

        lessonRepositiry.save(new Lesson(1, "Wf (G_1)", "SGD1", "MA", 3, lessonPlan1));
        lessonRepositiry.save(new Lesson(2, "J.Polsko", "103", "DA", 3, lessonPlan1));
        lessonRepositiry.save(new Lesson(3, "J.Polsko", "103", "DA", 3, lessonPlan1));
        lessonRepositiry.save(new Lesson(4, "Matematyka", "104", "GD", 3, lessonPlan1));
        lessonRepositiry.save(new Lesson(5, "Bdai / Psk", "207 / 306", "GT / KU", 3, lessonPlan1));
        lessonRepositiry.save(new Lesson(6, "Bdai / Psk", "207 / 306", "GT / KU", 3, lessonPlan1));
        lessonRepositiry.save(new Lesson(7, "Psk / Wf", "306 / SGD2", "KU / PE", 3, lessonPlan1));
        lessonRepositiry.save(new Lesson(8, "Psk / Wf", "306 / SGD2", "KU / PE", 3, lessonPlan1));
        lessonRepositiry.save(new Lesson(9, "J.Niemiecki", "29 / 102", "SM / MI", 3, lessonPlan1));

        lessonRepositiry.save(new Lesson(1, "CKP", "", "", 4, lessonPlan1));
        lessonRepositiry.save(new Lesson(2, "CKP", "", "", 4, lessonPlan1));
        lessonRepositiry.save(new Lesson(3, "CKP", "", "", 4, lessonPlan1));
        lessonRepositiry.save(new Lesson(4, "CKP", "", "", 4, lessonPlan1));
        lessonRepositiry.save(new Lesson(5, "CKP", "", "", 4, lessonPlan1));
        lessonRepositiry.save(new Lesson(6, "CKP", "", "", 4, lessonPlan1));
        lessonRepositiry.save(new Lesson(7, "CKP", "", "", 4, lessonPlan1));
        lessonRepositiry.save(new Lesson(8, "CKP", "", "", 4, lessonPlan1));
        lessonRepositiry.save(new Lesson(9, "CKP", "", "", 4, lessonPlan1));
        lessonRepositiry.save(new Lesson(10, "CKP", "", "", 4, lessonPlan1));
        lessonRepositiry.save(new Lesson(11, "CKP", "", "", 4, lessonPlan1));

        lessonRepositiry.save(new Lesson(0, "Bdai (G_1)", "207", "GT", 5, lessonPlan1));
        lessonRepositiry.save(new Lesson(1, "Bdai (G_1)", "207", "GT", 5, lessonPlan1));
        lessonRepositiry.save(new Lesson(2, "Fizyka / Wf", "201 / SGD1", "MM / PE", 5, lessonPlan1));
        lessonRepositiry.save(new Lesson(3, "Religia / Etyka", "08 / 03", "OJ / JK", 5, lessonPlan1));
        lessonRepositiry.save(new Lesson(4, "J.Angielski", "33 / 202", "PB / KA", 5, lessonPlan1));
        lessonRepositiry.save(new Lesson(5, "Bdai (G_2)", "207", "GT", 5, lessonPlan1));
        lessonRepositiry.save(new Lesson(6, "Bdai (G_2)", "207", "GT", 5, lessonPlan1));
        lessonRepositiry.save(new Lesson(7, "Fizyka (G_2)", "201", "MM", 5, lessonPlan1));
        lessonRepositiry.save(new Lesson(8, "J.Angielski (G_2)", "34", "KA", 5, lessonPlan1));








    }

}


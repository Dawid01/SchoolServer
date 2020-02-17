package pl.szczepaniak.school.server.schoolserver;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import pl.szczepaniak.school.server.schoolserver.controller.UserController;
import pl.szczepaniak.school.server.schoolserver.files.FileController;
import pl.szczepaniak.school.server.schoolserver.files.FileStorageProperties;
import pl.szczepaniak.school.server.schoolserver.files.UploadFileResponse;
import pl.szczepaniak.school.server.schoolserver.lesson_plan.Card;
import pl.szczepaniak.school.server.schoolserver.lesson_plan.LessonPlanReader;
import pl.szczepaniak.school.server.schoolserver.lesson_plan.Replacement;
import pl.szczepaniak.school.server.schoolserver.lesson_plan.ReplacementRepository;
import pl.szczepaniak.school.server.schoolserver.model.*;
import pl.szczepaniak.school.server.schoolserver.repository.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;


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
    private ReplacementRepository replacementRepository;

    @Autowired
    private FileController fileController;

    @Autowired
    private UserController userController;


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
        user2.setPhoto("https://us.123rf.com/450wm/kritchanut/kritchanut1406/kritchanut140600112/29213222-stock-vector-male-silhouette-avatar-profile-picture.jpg?ver=6");
        User user3 = new User();
        user3.setEmail("user3@gmail.com");
        user3.setName("User");
        user3.setSurname("3");
        user3.setPassword("12345678");
        user3.setPhoto("https://www.w3schools.com/howto/img_avatar.png");


        user1 = userRepository.save(user1);
        Post post1 =  new Post();
        post1.setUser(user1);
        post1.setContent("Lorem Ipsum jest tekstem stosowanym jako przykładowy wypełniacz w przemyśle poligraficznym. Został po raz pierwszy użyty w XV w. przez nieznanego drukarza do wypełnienia tekstem próbnej książki. Pięć wieków później zaczął być używany przemyśle elektronicznym, pozostając praktycznie niezmienionym. Spopularyzował się w latach 60. XX w. wraz z publikacją arkuszy Letrasetu, zawierających fragmenty Lorem Ipsum, a ostatnio z zawierającym różne wersje Lorem Ipsum oprogramowaniem przeznaczonym do realizacji druków na komputerach osobistych, jak Aldus PageMaker. Ogólnie znana teza głosi, iż użytkownika może rozpraszać zrozumiała zawartość strony, kiedy ten chce zobaczyć sam jej wygląd. Jedną z mocnych stron używania Lorem Ipsum jest to, że ma wiele różnych „kombinacji” zdań, słów i akapitów, w przeciwieństwie do zwykłego: „tekst, tekst, tekst”, sprawiającego, że wygląda to „zbyt czytelnie” po polsku. Wielu webmasterów i designerów używa Lorem Ipsum jako domyślnego modelu tekstu i wpisanie w internetowej wyszukiwarce ‘lorem ipsum’ spowoduje znalezienie bardzo wielu stron, które wciąż są w budowie. Wiele wersji tekstu ewoluowało i zmieniało się przez lata, czasem przez przypadek, czasem specjalnie (humorystyczne wstawki itd).");
        post1.setPermission(0);
        post1.setDateTime("01.07.2019");
        post1.setUserID(user1.getId());
        String[] photos1 = new String[1];
        Random random = new Random();
        photos1[0] = "https://picsum.photos/id/" + random.nextInt(500)+ "/600/1200";
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
//        photos2[0] = "https://images.unsplash.com/photo-1531804055935-76f44d7c3621?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80";
//        photos2[1] = "https://s.yimg.com/ny/api/res/1.2/OL87IFKTs.1ThAybTzev_A--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyNDI7aD04MjkuMjY1NzU3MjkwNjg2Nw--/https://s.yimg.com/uu/api/res/1.2/etJ72CvNrQE8Jq9rqEJ1Fg--~B/aD0yODM5O3c9NDI1MjtzbT0xO2FwcGlkPXl0YWNoeW9u/https://media-mbst-pub-ue1.s3.amazonaws.com/creatr-uploaded-images/2019-08/ec7f4d90-ca6e-11e9-97e5-a960dba4e558";
//        photos2[2] = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dw-burnett-2020-gt500-1547418557.jpg?resize=768:*";
//        photos2[3] = "https://images.unsplash.com/photo-1534067783941-51c9c23ecefd?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80";
//        photos2[4] = "https://www.designboom.com/wp-content/uploads/2018/09/photos-burning-man-2018-designboom-1.jpg";
//        photos2[5] = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4R7pF91TTryDvBS00aHt9nYWQVybgKCDj-0rdky6buOpFufwbmQ";
//        photos2[6] = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dw-burnett-2020-gt500-1547418557.jpg?resize=768:*";
//        photos2[7] = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dw-burnett-2020-gt500-1547418557.jpg?resize=768:*";
//        photos2[8] = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dw-burnett-2020-gt500-1547418557.jpg?resize=768:*";
        photos2[0] = "https://picsum.photos/id/" + random.nextInt(500)+ "/600/400";
        photos2[1] = "https://picsum.photos/id/" + random.nextInt(500)+ "/600/400";
        photos2[2] = "https://picsum.photos/id/" + random.nextInt(500)+ "/600/400";
        photos2[3] = "https://picsum.photos/id/" + random.nextInt(500)+ "/600/400";
        photos2[4] = "https://picsum.photos/id/" + random.nextInt(500)+ "/600/400";
        photos2[5] = "https://picsum.photos/id/" + random.nextInt(500)+ "/600/400";
        photos2[6] = "https://picsum.photos/id/" + random.nextInt(500)+ "/600/400";
        photos2[7] = "https://picsum.photos/id/" + random.nextInt(500)+ "/600/400";
        photos2[8] = "https://picsum.photos/id/" + random.nextInt(500)+ "/600/400";

        post2.setPhotos(photos2);
        postRepositiry.save(post2);


        user3 = userRepository.save(user3);
        Post post3 =  new Post();
        post3.setUser(user3);
        post3.setContent("Lorem Ipsum jest tekstem stosowanym jako przykładowy wypełniacz w przemyśle poligraficznym. Został po raz pierwszy użyty w XV w. przez nieznanego drukarza do wypełnienia tekstem próbnej książki. Pięć wieków później zaczął być używany przemyśle elektronicznym, pozostając praktycznie niezmienionym. Spopularyzował się w latach 60. XX w. wraz z publikacją arkuszy Letrasetu, zawierających fragmenty Lorem Ipsum, a ostatnio z zawierającym różne wersje Lorem Ipsum oprogramowaniem przeznaczonym do realizacji druków na komputerach osobistych, jak Aldus PageMaker. Ogólnie znana teza głosi, iż użytkownika może rozpraszać zrozumiała zawartość strony, kiedy ten chce zobaczyć sam jej wygląd. Jedną z mocnych stron używania Lorem Ipsum jest to, że ma wiele różnych „kombinacji” zdań, słów i akapitów, w przeciwieństwie do zwykłego: „tekst, tekst, tekst”, sprawiającego, że wygląda to „zbyt czytelnie” po polsku. Wielu webmasterów i designerów używa Lorem Ipsum jako domyślnego modelu tekstu i wpisanie w internetowej wyszukiwarce ‘lorem ipsum’ spowoduje znalezienie bardzo wielu stron, które wciąż są w budowie. Wiele wersji tekstu ewoluowało i zmieniało się przez lata, czasem przez przypadek, czasem specjalnie (humorystyczne wstawki itd).");
        post3.setPermission(0);
        post3.setDateTime("01.07.2019");
        String[] photos3 = new String[4];
//        photos3[0] = "https://iso.500px.com/wp-content/uploads/2015/01/primeqa_cover.jpg";
//        photos3[1] = "https://cnet4.cbsistatic.com/img/QNyEq1zWTqUwGACe8fTKLv4K1us=/980x551/2019/01/13/53d3ba47-59df-4f54-bcc4-3af9f228f578/2020-ford-mustang-shelby-gt500-detroit-auto-show-46.jpg";
//        photos3[2] = "https://images.unsplash.com/photo-1531804055935-76f44d7c3621?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80";
//        photos3[3] = "https://cdn.pixabay.com/photo/2016/11/14/04/45/elephant-1822636_960_720.jpg";
        photos3[0] = "https://picsum.photos/id/" + random.nextInt(500)+ "/600/400";
        photos3[1] = "https://picsum.photos/id/" + random.nextInt(500)+ "/600/400";
        photos3[2] = "https://picsum.photos/id/" + random.nextInt(500)+ "/600/400";
        photos3[3] = "https://picsum.photos/id/" + random.nextInt(500)+ "/600/400";

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

        User user4 = new User();
        user4.setName("Big");
        user4.setSurname("Lion");
        user4.setPermissions(0);
        user4.setEmail("biglion@gmail.com");
        user4.setPassword("12345678");
        user4.setPhoto("https://preview.redd.it/sg5q1bijotn31.jpg?width=960&crop=smart&auto=webp&s=45b0267d1e4a090bbcd0406e0d3ccaabf0c7392b");
        userRepository.save(user4);


        Replacement replacement = new Replacement();
        replacement.setClassName("1 AG");
        replacement.setDay("10000");
        replacement.setLessonNumber(5);
        replacement.setWeek("09.12.2019 - 15.12.2019");
        replacement.setSubject("Matematyka");
        replacement.setRoom("203");
        replacement.setTeacher("test");
        replacement.setStartTime("11:40");
        replacement.setEndTime("12:25");
        replacement.setStatus("zastępstwo");
        replacement.setGroupName("Cala klasa");
        replacementRepository.save(replacement);

        Replacement replacement2 = new Replacement();
        replacement2.setClassName("1 AG");
        replacement2.setDay("10000");
        replacement2.setLessonNumber(0);
        replacement2.setWeek("09.12.2019 - 15.12.2019");
        replacement2.setSubject("");
        replacement2.setRoom("");
        replacement2.setTeacher("");
        replacement2.setStartTime("");
        replacement2.setEndTime("");
        replacement2.setStatus("odwołane");
        replacement2.setGroupName("G_E");
        replacementRepository.save(replacement2);

        Replacement replacement3 = new Replacement();
        replacement3.setClassName("1 AG");
        replacement3.setDay("10000");
        replacement3.setLessonNumber(1);
        replacement3.setWeek("09.12.2019 - 15.12.2019");
        replacement3.setSubject("Język niemiecki");
        replacement3.setRoom("32");
        replacement3.setTeacher("test");
        replacement3.setStartTime("8:00");
        replacement3.setEndTime("8:45");
        replacement3.setStatus("zastępstwo");
        replacement3.setGroupName("G_E");
        replacementRepository.save(replacement3);

      
//        try {
//            URL url = new URL("https://plan.ezn.edu.pl/data.json");
//            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
//            String str = "";
//            while (null != (str = br.readLine())) {
//                new LessonPlanReader(str,fileController);
//
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }


    }

}


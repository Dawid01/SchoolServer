package pl.sykisoft.flashcards.server.flashcardsserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.sykisoft.flashcards.server.flashcardsserver.model.Post;

public class PostRepositiry extends JpaRepository<Post,Long> {

    @Query(value = "SELECT f FROM Post f WHERE f.user.id =:userId",
        countQuery = "SELECT count(f) FROM Post f WHERE f.user.id =:userId",
        nativeQuery = false)
    Page<Post> findUsersFlashCards(@Param("userId") long userId, Pageable pageable);

}

package pl.sykisoft.flashcards.server.flashcardsserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.sykisoft.flashcards.server.flashcardsserver.model.User;

public interface UserRepository extends JpaRepository<User,Long> {


    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
    public User findByEmail(@Param("email") String email);
}

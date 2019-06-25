package pl.szczepaniak.school.server.schoolserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.szczepaniak.school.server.schoolserver.model.Post;

public interface  PostRepositiry extends JpaRepository<Post,Long> {

    @Query(value = "SELECT p FROM Post p WHERE p.user.id =:userId",
        countQuery = "SELECT count(p) FROM Post p WHERE p.user.id =:userId",
        nativeQuery = false)
    Page<Post> findUsersPost(@Param("userId") long userId, Pageable pageable);

}

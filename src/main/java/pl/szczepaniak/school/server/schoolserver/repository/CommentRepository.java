package pl.szczepaniak.school.server.schoolserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.szczepaniak.school.server.schoolserver.model.Comment;
import pl.szczepaniak.school.server.schoolserver.model.Post;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query(value = "SELECT c FROM Comment c WHERE c.post.id = id",
            countQuery = "SELECT count(c) FROM Comment c WHERE c.post.id = id",
            nativeQuery = false)
    Page<Comment> findByPostId(@Param("id") long id, Pageable pageable);
}

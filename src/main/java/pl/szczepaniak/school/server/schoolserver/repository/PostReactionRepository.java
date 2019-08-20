package pl.szczepaniak.school.server.schoolserver.repository;


import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.szczepaniak.school.server.schoolserver.model.PostReaction;

import java.util.List;

public interface PostReactionRepository extends JpaRepository<PostReaction,Long> {

//    @Query("SELECT r FROM PostReaction r WHERE LOWER(r.post.id) =:postId")
//    public List<PostReaction> findByPost(@Param("postId") Long post);

}

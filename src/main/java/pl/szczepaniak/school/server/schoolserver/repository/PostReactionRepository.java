package pl.szczepaniak.school.server.schoolserver.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.szczepaniak.school.server.schoolserver.model.PostReaction;


public interface PostReactionRepository extends JpaRepository<PostReaction,Long> {

//    @Query("SELECT r FROM PostReaction r WHERE LOWER(r.post.id) =:postId")
//    public List<PostReaction> findByPost(@Param("postId") Long post);

}

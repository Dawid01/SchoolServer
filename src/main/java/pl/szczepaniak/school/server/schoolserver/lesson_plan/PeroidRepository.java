package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.szczepaniak.school.server.schoolserver.model.Comment;

public interface PeroidRepository extends JpaRepository<Period,Long> {
}

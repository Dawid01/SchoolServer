package pl.szczepaniak.school.server.schoolserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.szczepaniak.school.server.schoolserver.model.Game;

public interface GameRepository extends JpaRepository<Game,Long> {


}

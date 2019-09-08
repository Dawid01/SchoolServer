package pl.szczepaniak.school.server.schoolserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.szczepaniak.school.server.schoolserver.model.GameScore;

public interface GameScoreRepository extends JpaRepository<GameScore,Long> {

}

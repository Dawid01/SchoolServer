package pl.szczepaniak.school.server.schoolserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.szczepaniak.school.server.schoolserver.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    @Query("SELECT t FROM ConfirmationToken t WHERE LOWER(t.confirmationToken) = LOWER(:confirmationToken)")
    ConfirmationToken findByConfirmationToken(@Param("confirmationToken")String confirmationToken);
}

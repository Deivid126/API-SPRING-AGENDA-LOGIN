package agenda.com.repository;

import agenda.com.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Optional<List<Task>> findByData(Date data);

    @Query("FROM Task WHERE data BETWEEN ?1 AND ?2")
    List<Task> findBetweenDate(Date stardate, Date enddate);
}

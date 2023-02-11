package agenda.com.repository;

import agenda.com.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {

    @Query("SELECT u FROM task u WHERE u.data = ?1")
    List<Task> findTaskByData(Date data);
}

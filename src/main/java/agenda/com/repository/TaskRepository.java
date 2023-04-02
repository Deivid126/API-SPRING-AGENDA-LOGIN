package agenda.com.repository;

import agenda.com.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByData(Date data);

    List<Task> findByDataBetween(Date startdate, Date enddate);

}

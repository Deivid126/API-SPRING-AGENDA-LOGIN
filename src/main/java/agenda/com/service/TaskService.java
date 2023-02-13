package agenda.com.service;

import agenda.com.model.Task;
import agenda.com.model.User;
import agenda.com.repository.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserService userService;


    public Task CreateTask(Task task, int id){
        User user = userService.findById(id);
        task.setUser(user);
        return taskRepository.save(task);
    }
    public Task uptadeTask(Task task, int id){
        Optional<Task> tasknew = taskRepository.findById(id);
        BeanUtils.copyProperties(task, tasknew, "user");
        return taskRepository.save(tasknew.get());
    }
    public List<Task> AllTask(){
        return taskRepository.findAll();
    }
    public List<Task> findByDate(Date data){
        return taskRepository.findByData(data).get();
    }

    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }
    public List<Task> findBeteweenDate(Date startdate, Date enddate){
        return taskRepository.findBetweenDate(startdate,enddate);
    }

}

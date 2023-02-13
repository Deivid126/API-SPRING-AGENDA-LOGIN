package agenda.com.controller;

import agenda.com.model.Task;
import agenda.com.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/{id}")
    public ResponseEntity<Task> saveTask(@PathVariable(value = "id") int id, @RequestBody Task task){
        Task tasknew = taskService.CreateTask(task,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(tasknew);
    }
    @GetMapping
    public ResponseEntity<List<Task>> getAllTask(){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.AllTask());
    }
    @GetMapping("/data/{data}")
    public ResponseEntity<List<Task>> getData(@PathVariable(value = "data")String data) throws ParseException {
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findByDate(date1));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> putTask(@PathVariable(value = "id") int id, @RequestBody Task task){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.uptadeTask(task,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable(value = "id") int id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Task Removida");
    }
    @GetMapping("/data-beteween/{stardate}/{enddate}")
    public ResponseEntity<List<Task>> findBeteween(@PathVariable(value = "stardate") String stardate, @PathVariable(value = "enddate") String enddate) throws ParseException {
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(stardate);
        Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(enddate);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findBeteweenDate(date1,date2));
    }
}

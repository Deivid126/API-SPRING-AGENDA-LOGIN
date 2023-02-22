package agenda.com;
import org.junit.Test;
import agenda.com.model.Task;
import agenda.com.model.User;
import agenda.com.repository.TaskRepository;
import agenda.com.service.TaskService;
import agenda.com.service.UserService;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testCreateTask() {
        Task task = new Task();
        User user = new User();
        user.setId(1);
        when(userService.findById(1)).thenReturn(Optional.of(user));
        when(taskRepository.save(task)).thenReturn(task);
        Task savedTask = taskService.CreateTask(task, 1);
        verify(taskRepository).save(task);
        assertEquals(user, savedTask.getUser());
    }

    @Test
    public void testUpdateTask() {
        Task oldTask = new Task();
        oldTask.setId(1);
        oldTask.setDescricao("old description");
        Task newTask = new Task();
        newTask.setId(1);
        newTask.setDescricao("new description");
        when(taskRepository.findById(1)).thenReturn(Optional.of(oldTask));
        when(taskRepository.save(oldTask)).thenReturn(newTask);
        Task updatedTask = taskService.uptadeTask(newTask, 1);
        verify(taskRepository).save(oldTask);
        assertEquals("new description", updatedTask.getDescricao());
    }

    @Test
    public void testAllTask() {
        Task task1 = new Task();
        Task task2 = new Task();
        List<Task> tasks = Arrays.asList(task1, task2);
        when(taskRepository.findAll()).thenReturn(tasks);
        List<Task> allTasks = taskService.AllTask();
        verify(taskRepository).findAll();
        assertEquals(2, allTasks.size());
    }

    @Test
    public void testFindByDate() {
        Task task = new Task();
        Date date = new Date();
        List<Task> tasks = Arrays.asList(task);
        when(taskRepository.findByData(date)).thenReturn(tasks);
        List<Task> foundTasks = taskService.findByDate(date);
        verify(taskRepository).findByData(date);
        assertEquals(tasks, foundTasks);
    }

    @Test
    public void testDeleteTask() {
        int id = 1;
        doNothing().when(taskRepository).deleteById(id);
        taskService.deleteTask(id);
        verify(taskRepository).deleteById(id);
    }

    @Test
    public void testFindBetweenDate() {
        Task task1 = new Task();
        Task task2 = new Task();
        Date startDate = new Date(2022, 1, 1);
        Date endDate = new Date(2022, 12, 31);
        List<Task> tasks = Arrays.asList(task1, task2);
        when(taskRepository.findBetweenDate(startDate, endDate)).thenReturn(tasks);
        List<Task> foundTasks = taskService.findBeteweenDate(startDate, endDate);
        verify(taskRepository).findBetweenDate(startDate, endDate);
        assertEquals(2, foundTasks.size());
    }

}






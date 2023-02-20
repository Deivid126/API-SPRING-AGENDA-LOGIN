package agenda.com.teste.service;

import agenda.com.model.Role;
import agenda.com.model.Task;
import agenda.com.model.User;
import agenda.com.repository.TaskRepository;
import agenda.com.repository.UserRepository;
import agenda.com.service.TaskService;
import agenda.com.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTeste {

    @Mock
    TaskRepository taskRepository;
    @InjectMocks
    TaskService taskService;
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    @Test
    public void saveTask() {

        Date data = new Date(01,10,15,01,01,01);
        User user = User.builder().id(1).email("dgoncalves@gmail.com").nome("Deivid").password("Deivid159").role(Role.USER).tasks(List.of()).build();
        Task task = Task.builder().id(1).data(data).hora("18:08").titulo("Prova").descricao("Amanha tem prova").user(user).build();



        Mockito.when(userService.findById(user.getId())).thenReturn(Optional.of(user));

        userService.findById(user.getId());
        taskRepository.findById(anyInt());
        task.setUser(user);

        Mockito.when(taskService.CreateTask(task, user.getId())).thenReturn(task);

        taskService.CreateTask(task, user.getId());


        Mockito.verify(taskService).CreateTask(task, user.getId());

    }
}

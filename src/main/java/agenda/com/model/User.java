package agenda.com.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(nullable = false,name = "email")
    private String email;
    @NotNull
    @Column(nullable = false,name = "nome")
    private String nome;
    @NotNull
    @Column(nullable = false,name = "senha")
    private String password;
    @NotNull
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Task> tasks = new ArrayList<Task>();

    public User(){}

    public User(int id, String email, String nome, String password, List<Task> tasks){
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.tasks = tasks;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}

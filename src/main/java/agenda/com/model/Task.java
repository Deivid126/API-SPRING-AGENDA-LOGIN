package agenda.com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @Column(nullable = false,name = "data")
    private Date data;
    @NotNull
    @Column(nullable = false,name = "hora")
    private String hora;
    @NotNull
    @Column(nullable = false,name = "titulo")
    private String titulo;
    @NotNull
    @Column(nullable = false,name = "descricao")
    private String descricao;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public Task(){}

    public Task(int id, Date data, String hora, String titulo, String descricao,User user){
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.titulo = titulo;
        this.descricao = descricao;
        this.user = user;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package agenda.com.model;

import agenda.com.enums.CategoryofTasks;
import agenda.com.enums.TypeTask;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task implements Serializable{

    @Id
    private UUID Id;
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
    @Column(nullable = false, name = "typetask")
    @Enumerated(EnumType.STRING)
    private TypeTask typeTask;
    @Column(nullable = false, name = "categoria")
    @Enumerated(EnumType.STRING)
    private CategoryofTasks category;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user")
    @JsonBackReference
    private User user;

}

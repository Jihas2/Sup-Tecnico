package suporte.sup.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Tecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String especialidade;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnicoResponsavel", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

}

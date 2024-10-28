package suporte.sup.Entities;

import lombok.Data;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "usuarioCriador", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}

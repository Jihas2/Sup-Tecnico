package suporte.sup.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import jakarta.persistence.*;
import suporte.sup.Enum.Categoria;
import suporte.sup.Enum.Prioridade;
import suporte.sup.Enum.StatusTicket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTicket status;

    private Boolean confirmacaoUsuario = false;
    private Boolean confirmacaoTecnico = false;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioCriador;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnicoResponsavel;

   /* @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resposta> respostas = new ArrayList<>();*/

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Resposta> respostas = new ArrayList<>();

    private LocalDateTime dataFechamento;

    private String resolucaoFinal = "Não resolvido";

    private boolean usuarioConfirmado;
    private boolean tecnicoConfirmado;
}

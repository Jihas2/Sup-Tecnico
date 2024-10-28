package suporte.sup.Service;

import suporte.sup.Entities.Ticket;
import suporte.sup.Entities.Usuario;
import suporte.sup.Entities.Tecnico;
import suporte.sup.Enum.StatusTicket;
import suporte.sup.Repositories.TicketRepository;
import suporte.sup.Repositories.UsuarioRepository;
import suporte.sup.Repositories.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;
    private final TecnicoRepository tecnicoRepository;

    public Ticket saveTicket(Ticket ticket, Long usuarioId, Long tecnicoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Tecnico tecnico = tecnicoRepository.findById(tecnicoId).orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
        ticket.setUsuarioCriador(usuario);
        ticket.setTecnicoResponsavel(tecnico);
        ticket.setDataCriacao(LocalDateTime.now());
        ticket.setStatus(StatusTicket.ABERTO);
        return ticketRepository.save(ticket);
    }

    public List<Ticket> findByStatus(StatusTicket status) {
        return ticketRepository.findByStatus(status);
    }

    public Ticket findById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket não encontrado"));
    }

    public Ticket closeTicket(Long id, String resolucaoFinal) {
        Ticket ticket = findById(id);
        ticket.setResolucaoFinal(resolucaoFinal);
        ticket.setDataFechamento(LocalDateTime.now());
        ticket.setStatus(StatusTicket.FECHADO);
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public void deleteAllTickets() {
        ticketRepository.deleteAll();
    }
}

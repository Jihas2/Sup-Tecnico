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
import java.time.Year;
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

    public Ticket confirmUsuario(Long ticketId) {
        Ticket ticket = findById(ticketId);
        ticket.setUsuarioConfirmado(true);
        return ticketRepository.save(ticket);
    }

    public Ticket confirmTecnico(Long ticketId) {
        Ticket ticket = findById(ticketId);
        ticket.setTecnicoConfirmado(true);
        return ticketRepository.save(ticket);
    }

    public Ticket closeTicket(Long id, String resolucaoFinal) {
        Ticket ticket = findById(id);
        if (ticket.isUsuarioConfirmado() && ticket.isTecnicoConfirmado()) {
            ticket.setResolucaoFinal(resolucaoFinal);
            ticket.setDataFechamento(LocalDateTime.now());
            ticket.setStatus(StatusTicket.FECHADO);
            return ticketRepository.save(ticket);
        } else {
            throw new RuntimeException("O ticket não pode ser fechado sem a confirmação de usuário e técnico.");
        }
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public void deleteAllTickets() {
        ticketRepository.deleteAll();
    }

    public void verificarConfirmacoesEFecharTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado"));

        if (ticket.getConfirmacaoUsuario() && ticket.getConfirmacaoTecnico()) {
            ticket.setStatus(StatusTicket.FECHADO);
            ticket.setDataFechamento(LocalDateTime.now());
            ticket.setResolucaoFinal("Resolvido");
            ticketRepository.save(ticket);
        }
    }

    public Ticket confirmarResolucaoUsuario(Long ticketId) {
        return atualizarConfirmacaoUsuario(ticketId, true);
    }

    public Ticket confirmarResolucaoTecnico(Long ticketId) {
        return atualizarConfirmacaoTecnico(ticketId, true);
    }



    private Ticket atualizarConfirmacaoUsuario(Long ticketId, boolean confirmacao) {
        Ticket ticket = findById(ticketId);
        ticket.setConfirmacaoUsuario(confirmacao);
        ticket = ticketRepository.save(ticket);
        verificarConfirmacoesEFecharTicket(ticketId);
        return ticket;
    }

    private Ticket atualizarConfirmacaoTecnico(Long ticketId, boolean confirmacao) {
        Ticket ticket = findById(ticketId);
        ticket.setConfirmacaoTecnico(confirmacao);
        ticket = ticketRepository.save(ticket);
        verificarConfirmacoesEFecharTicket(ticketId);
        return ticket;
    }

    public Long countTicketsThisYear() {
        LocalDateTime startDate = Year.now().atDay(1).atStartOfDay();
        LocalDateTime endDate = Year.now().atDay(365).atTime(23, 59, 59);
        return ticketRepository.countTicketsBetween(startDate, endDate);
    }

    public Long countOpenTicketsThisYear() {
        return ticketRepository.countOpenTicketsThisYear();
    }

    public Long countClosedTicketsThisYear() {
        return ticketRepository.countClosedTicketsThisYear();
    }

}

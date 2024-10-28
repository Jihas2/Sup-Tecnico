package suporte.sup.Service;

import suporte.sup.Entities.Resposta;
import suporte.sup.Entities.Ticket;
import suporte.sup.Repositories.RespostaRepository;
import suporte.sup.Repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RespostaService {

    private final RespostaRepository respostaRepository;
    private final TicketRepository ticketRepository;

    public Resposta saveResposta(Long ticketId, Resposta resposta) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado"));

        if (ticket.getRespostas() == null) {
            ticket.setRespostas(new ArrayList<>());
        }

        resposta.setTicket(ticket);

        ticket.getRespostas().add(resposta);

        respostaRepository.save(resposta);

        return resposta;
    }

    public List<Resposta> findRespostasByTicketId(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado"));
        return ticket.getRespostas();
    }
}


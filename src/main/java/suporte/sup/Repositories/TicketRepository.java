package suporte.sup.Repositories;

import suporte.sup.Entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import suporte.sup.Enum.StatusTicket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByStatus(StatusTicket status);
}

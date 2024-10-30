package suporte.sup.Repositories;

import suporte.sup.Entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import suporte.sup.Enum.StatusTicket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByStatus(StatusTicket status);

    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.dataCriacao BETWEEN :startDate AND :endDate")
    Long countTicketsBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(t) FROM Ticket t WHERE YEAR(t.dataCriacao) = YEAR(CURRENT_DATE)")
    Long countTicketsThisYear();

    @Query("SELECT COUNT(t) FROM Ticket t WHERE YEAR(t.dataCriacao) = YEAR(CURRENT_DATE) AND t.status = 'ABERTO'")
    Long countOpenTicketsThisYear();

    @Query("SELECT COUNT(t) FROM Ticket t WHERE YEAR(t.dataCriacao) = YEAR(CURRENT_DATE) AND t.status = 'FECHADO'")
    Long countClosedTicketsThisYear();
}

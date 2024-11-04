package suporte.sup.Controller;

import suporte.sup.Entities.Ticket;
import suporte.sup.Enum.StatusTicket;
import suporte.sup.Service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/salvar")
    public ResponseEntity<Ticket> criarTicket(@RequestBody Ticket ticket, @RequestParam Long usuarioId, @RequestParam Long tecnicoId) {
        return ResponseEntity.ok(ticketService.saveTicket(ticket, usuarioId, tecnicoId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Ticket>> listarTicketsPorStatus(@PathVariable StatusTicket status) {
        return ResponseEntity.ok(ticketService.findByStatus(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> buscarTicketPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.findById(id));
    }

    @PutMapping("/{id}/confirmar-usuario")
    public ResponseEntity<Ticket> confirmarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.confirmarResolucaoUsuario(id));
    }

    @PutMapping("/{id}/confirmar-tecnico")
    public ResponseEntity<Ticket> confirmarTecnico(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.confirmarResolucaoTecnico(id));
    }

    @PutMapping("/{id}/fechar")
    public ResponseEntity<Ticket> fecharTicket(@PathVariable Long id, @RequestParam String resolucaoFinal) {
        return ResponseEntity.ok(ticketService.closeTicket(id, resolucaoFinal));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletarTodos")
    public ResponseEntity<Void> deleteAllTickets() {
        ticketService.deleteAllTickets();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ano")
    public ResponseEntity<Long> countTicketsThisYear() {
        Long totalTickets = ticketService.countTicketsThisYear();
        return ResponseEntity.ok(totalTickets);
    }

    @GetMapping("/ano/abertos")
    public ResponseEntity<Long> countOpenTicketsThisYear() {
        Long openTickets = ticketService.countOpenTicketsThisYear();
        return ResponseEntity.ok(openTickets);
    }

    @GetMapping("/ano/fechados")
    public ResponseEntity<Long> countClosedTicketsThisYear() {
        Long closedTickets = ticketService.countClosedTicketsThisYear();
        return ResponseEntity.ok(closedTickets);
    }

}

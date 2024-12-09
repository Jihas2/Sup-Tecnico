package suporte.sup.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
    @PostMapping("/salvar")
    public ResponseEntity<Ticket> criarTicket(@RequestBody Ticket ticket, @RequestParam Long usuarioId, @RequestParam Long tecnicoId) {
        return ResponseEntity.ok(ticketService.saveTicket(ticket, usuarioId, tecnicoId));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Ticket>> listarTicketsPorStatus(@PathVariable StatusTicket status) {
        return ResponseEntity.ok(ticketService.findByStatus(status));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> buscarTicketPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.findById(id));
    }

    @PreAuthorize("hasRole ('USER')")
    @PutMapping("/{id}/confirmar-usuario")
    public ResponseEntity<Ticket> confirmarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.confirmarResolucaoUsuario(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/confirmar-tecnico")
    public ResponseEntity<Ticket> confirmarTecnico(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.confirmarResolucaoTecnico(id));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
    @PutMapping("/{id}/fechar")
    public ResponseEntity<Ticket> fecharTicket(@PathVariable Long id, @RequestParam String resolucaoFinal) {
        return ResponseEntity.ok(ticketService.closeTicket(id, resolucaoFinal));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deletarTodos")
    public ResponseEntity<Void> deleteAllTickets() {
        ticketService.deleteAllTickets();
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/ano")
    public ResponseEntity<Long> countTicketsThisYear() {
        Long totalTickets = ticketService.countTicketsThisYear();
        return ResponseEntity.ok(totalTickets);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/ano/abertos")
    public ResponseEntity<Long> countOpenTicketsThisYear() {
        Long openTickets = ticketService.countOpenTicketsThisYear();
        return ResponseEntity.ok(openTickets);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/ano/fechados")
    public ResponseEntity<Long> countClosedTicketsThisYear() {
        Long closedTickets = ticketService.countClosedTicketsThisYear();
        return ResponseEntity.ok(closedTickets);
    }

}

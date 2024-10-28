package suporte.sup.Controller;

import suporte.sup.Entities.Ticket;
import suporte.sup.Enum.StatusTicket;
import suporte.sup.Service.TicketService;
import suporte.sup.Service.UsuarioService;
import suporte.sup.Service.TecnicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final UsuarioService usuarioService;
    private final TecnicoService tecnicoService;

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
        Ticket ticket = ticketService.findById(id);
        return ResponseEntity.ok(ticketService.findById(id));
        //return ResponseEntity.ok(ticket);
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
}

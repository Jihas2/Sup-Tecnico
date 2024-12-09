package suporte.sup.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import suporte.sup.Entities.Ticket;
import suporte.sup.Enum.StatusTicket;
import suporte.sup.Service.TicketService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TicketControllerTest {

    @InjectMocks
    private TicketController ticketController;

    @Mock
    private TicketService ticketService;

    private Ticket sampleTicket;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleTicket = new Ticket();
        sampleTicket.setId(1L);
        sampleTicket.setTitulo("Problema no sistema");
        sampleTicket.setDescricao("Descrição detalhada");
        sampleTicket.setStatus(StatusTicket.ABERTO);
    }

    @Test
    void testCriarTicket() {
        when(ticketService.saveTicket(any(Ticket.class), eq(1L), eq(2L))).thenReturn(sampleTicket);

        ResponseEntity<Ticket> response = ticketController.criarTicket(sampleTicket, 1L, 2L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleTicket, response.getBody());
    }

    @Test
    void testListarTicketsPorStatus() {
        when(ticketService.findByStatus(StatusTicket.ABERTO)).thenReturn(Collections.singletonList(sampleTicket));

        ResponseEntity<List<Ticket>> response = ticketController.listarTicketsPorStatus(StatusTicket.ABERTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(sampleTicket, response.getBody().get(0));
    }

    @Test
    void testBuscarTicketPorId() {
        when(ticketService.findById(1L)).thenReturn(sampleTicket);

        ResponseEntity<Ticket> response = ticketController.buscarTicketPorId(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleTicket, response.getBody());
    }

    @Test
    void testConfirmarUsuario() {
        when(ticketService.confirmarResolucaoUsuario(1L)).thenReturn(sampleTicket);

        ResponseEntity<Ticket> response = ticketController.confirmarUsuario(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleTicket, response.getBody());
    }

    @Test
    void testConfirmarTecnico() {
        when(ticketService.confirmarResolucaoTecnico(1L)).thenReturn(sampleTicket);

        ResponseEntity<Ticket> response = ticketController.confirmarTecnico(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleTicket, response.getBody());
    }

    @Test
    void testFecharTicket() {
        when(ticketService.closeTicket(1L, "Resolvido")).thenReturn(sampleTicket);

        ResponseEntity<Ticket> response = ticketController.fecharTicket(1L, "Resolvido");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleTicket, response.getBody());
    }

    @Test
    void testDeletarTicket() {
        doNothing().when(ticketService).deleteTicket(1L);

        ResponseEntity<Void> response = ticketController.deleteTicket(1L);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testDeletarTodosTickets() {
        doNothing().when(ticketService).deleteAllTickets();

        ResponseEntity<Void> response = ticketController.deleteAllTickets();

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testCountTicketsThisYear() {
        when(ticketService.countTicketsThisYear()).thenReturn(10L);

        ResponseEntity<Long> response = ticketController.countTicketsThisYear();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(10L, response.getBody());
    }

    @Test
    void testCountOpenTicketsThisYear() {
        when(ticketService.countOpenTicketsThisYear()).thenReturn(5L);

        ResponseEntity<Long> response = ticketController.countOpenTicketsThisYear();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(5L, response.getBody());
    }

    @Test
    void testCountClosedTicketsThisYear() {
        when(ticketService.countClosedTicketsThisYear()).thenReturn(4L);

        ResponseEntity<Long> response = ticketController.countClosedTicketsThisYear();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(4L, response.getBody());
    }
}

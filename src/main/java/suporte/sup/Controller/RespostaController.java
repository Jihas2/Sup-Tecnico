package suporte.sup.Controller;

import suporte.sup.Entities.Resposta;
import suporte.sup.Service.RespostaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/respostas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RespostaController {

    private final RespostaService respostaService;

    @PostMapping("/salvar/{ticketId}")
    public ResponseEntity<Resposta> saveResposta(@PathVariable Long ticketId, @RequestBody Resposta resposta) {
        return ResponseEntity.ok(respostaService.saveResposta(ticketId, resposta));
    }

    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<List<Resposta>> getRespostasByTicketId(@PathVariable Long ticketId) {
        return ResponseEntity.ok(respostaService.findRespostasByTicketId(ticketId));
    }
}

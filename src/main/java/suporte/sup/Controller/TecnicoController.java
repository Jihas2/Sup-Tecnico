package suporte.sup.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import suporte.sup.Service.TecnicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suporte.sup.Entities.Tecnico;

import java.util.List;

@RestController
@RequestMapping("/tecnico")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TecnicoController {

    private final TecnicoService tecnicoService;

    @PostMapping("/salvar")
    public ResponseEntity<Tecnico> saveTecnico(@RequestBody Tecnico tecnico) {
        return ResponseEntity.ok(tecnicoService.savetecnico(tecnico));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Tecnico>> findAllTecnico() {
        return ResponseEntity.ok(tecnicoService.findAllTecnico());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tecnico> findByIdTecnico(@PathVariable Long id) {
        return ResponseEntity.ok(tecnicoService.findByIdTecnico(id));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Tecnico> updateTecnico(@PathVariable Long id, @RequestBody Tecnico Tecnico) {
        return ResponseEntity.ok(tecnicoService.updateTecnico(id, Tecnico));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deleteTecnico(@PathVariable Long id) {
        tecnicoService.deleteTecnico(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletarTodos")
    public ResponseEntity<Void> deleteAllTecnico() {
       tecnicoService.deleteAllTecnico();
        return ResponseEntity.noContent().build();
    }
}

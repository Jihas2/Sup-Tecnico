package suporte.sup.Controller;

import suporte.sup.Entities.Usuario;
import suporte.sup.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/salvar")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.saveUsuario(usuario));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Usuario>> findAllUsuarios() {
        return ResponseEntity.ok(usuarioService.findAllUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findByIdUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findByIdUsuario(id));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletarTodos")
    public ResponseEntity<Void> deleteAllUsuarios() {
        usuarioService.deleteAllUsuarios();
        return ResponseEntity.noContent().build();
    }
}

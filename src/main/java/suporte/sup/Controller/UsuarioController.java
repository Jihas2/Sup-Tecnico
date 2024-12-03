package suporte.sup.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import suporte.sup.Controller.usuario.UsuarioResponse;
import suporte.sup.Entities.Usuario;
import suporte.sup.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @PreAuthorize("hasRole('ADMIN') or ('USER')")
    @GetMapping("/teste")
    public List<UsuarioResponse> teste(){
        var data = usuarioService.findAllUsuarios();
        var result = new ArrayList<UsuarioResponse>();
        for (var customer: data){
            result.add(new UsuarioResponse(
                    customer.getId(),
                    customer.getNome(),
                    customer.getEmail(),
                    customer.getPassword(),
                    customer.getRole()
            ));
        }
        return result;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/todos")
    public ResponseEntity<List<Usuario>> findAllUsuarios() {
        return ResponseEntity.ok(usuarioService.findAllUsuarios());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findByIdUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findByIdUsuario(id));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deletarTodos")
    public ResponseEntity<Void> deleteAllUsuarios() {
        usuarioService.deleteAllUsuarios();
        return ResponseEntity.noContent().build();
    }
}

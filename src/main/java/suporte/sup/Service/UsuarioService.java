package suporte.sup.Service;

import suporte.sup.Entities.Usuario;
import suporte.sup.Repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario findByIdUsuario(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public void deleteAllUsuarios() {
        usuarioRepository.deleteAll();
    }
}

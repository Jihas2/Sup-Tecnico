package suporte.sup.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import suporte.sup.Controller.LoginRequest;
import suporte.sup.Entities.Usuario;
import suporte.sup.Repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private JwtUtils jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

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

    public String logar(LoginRequest login) {
        var data = usuarioRepository.findByNomr(login.login()).get();
        System.out.println(data.getUsername());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.login(),
                        login.senha()
                )
        );
//        Usuario user = usuarioRepository.findByNomr(login.login()).get();
        String jwtToken = jwtService.generateToken(data);

        return jwtToken;
    }
}

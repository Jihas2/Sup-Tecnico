package suporte.sup.Controller.usuario;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        String password,
        String role
) {
}

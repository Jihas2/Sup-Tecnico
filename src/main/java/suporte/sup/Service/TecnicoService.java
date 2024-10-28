package suporte.sup.Service;

import suporte.sup.Entities.Tecnico;
import suporte.sup.Repositories.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TecnicoService {
    private final TecnicoRepository tecnicoRepository;

    public Tecnico savetecnico(Tecnico tecnico) {
        return tecnicoRepository.save(tecnico);
    }

    public List<Tecnico> findAllTecnico() {
        return tecnicoRepository.findAll();
    }

    public Tecnico findByIdTecnico(Long id) {
        return tecnicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
    }

    public Tecnico updateTecnico(Long id, Tecnico tecnicoAtualizado) {
        Tecnico tecnico = tecnicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
        tecnico.setNome(tecnicoAtualizado.getNome());
        tecnico.setEspecialidade(tecnicoAtualizado.getEspecialidade());
        return tecnicoRepository.save(tecnico);
    }

    public void deleteTecnico(Long id) {
        tecnicoRepository.deleteById(id);
    }

    public void deleteAllTecnico() {
        tecnicoRepository.deleteAll();
    }
}

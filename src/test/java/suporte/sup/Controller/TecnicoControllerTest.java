package suporte.sup.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import suporte.sup.Entities.Tecnico;
import suporte.sup.Service.TecnicoService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TecnicoControllerTest {

    @InjectMocks
    private TecnicoController tecnicoController;

    @Mock
    private TecnicoService tecnicoService;

    private Tecnico sampleTecnico;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleTecnico = new Tecnico();
        sampleTecnico.setId(1L);
        sampleTecnico.setNome("João Silva");
        sampleTecnico.setEspecialidade("Manutenção de Sistemas");
    }

    @Test
    void testSaveTecnico() {
        when(tecnicoService.savetecnico(any(Tecnico.class))).thenReturn(sampleTecnico);

        ResponseEntity<Tecnico> response = tecnicoController.saveTecnico(sampleTecnico);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleTecnico, response.getBody());
    }

    @Test
    void testFindAllTecnico() {
        when(tecnicoService.findAllTecnico()).thenReturn(Collections.singletonList(sampleTecnico));

        ResponseEntity<List<Tecnico>> response = tecnicoController.findAllTecnico();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(sampleTecnico, response.getBody().get(0));
    }

    @Test
    void testFindByIdTecnico() {
        when(tecnicoService.findByIdTecnico(1L)).thenReturn(sampleTecnico);

        ResponseEntity<Tecnico> response = tecnicoController.findByIdTecnico(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleTecnico, response.getBody());
    }

    @Test
    void testUpdateTecnico() {
        when(tecnicoService.updateTecnico(eq(1L), any(Tecnico.class))).thenReturn(sampleTecnico);

        ResponseEntity<Tecnico> response = tecnicoController.updateTecnico(1L, sampleTecnico);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleTecnico, response.getBody());
    }

    @Test
    void testDeleteTecnico() {
        doNothing().when(tecnicoService).deleteTecnico(1L);

        ResponseEntity<Void> response = tecnicoController.deleteTecnico(1L);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testDeleteAllTecnico() {
        doNothing().when(tecnicoService).deleteAllTecnico();

        ResponseEntity<Void> response = tecnicoController.deleteAllTecnico();

        assertEquals(204, response.getStatusCodeValue());
    }
}

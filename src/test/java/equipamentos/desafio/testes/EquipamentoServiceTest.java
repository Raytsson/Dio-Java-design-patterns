package equipamentos.desafio.testes;

import equipamentos.desafio.exception.ResourceNotFoundException;
import equipamentos.desafio.model.Equipamentos;
import equipamentos.desafio.repository.EquipamentoRepository;
import equipamentos.desafio.service.EquipamentoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.testng.Assert;

import java.util.Optional;

@SpringBootTest
public class EquipamentoServiceTest {


    @Autowired
    private EquipamentoService equipamentoService;

    @MockBean
    private EquipamentoRepository equipamentoRepository;

    @Test
    public void testFindById_EquipamentoExistente() {
        Equipamentos equipamento = new Equipamentos(1L, "Impressora", "Eletrônico", 1500.00);
        Mockito.when(equipamentoRepository.findById(1L)).thenReturn(Optional.of(equipamento));

        Equipamentos resultado = equipamentoService.findById(1L);
        Assert.assertNotNull(resultado);
        Assert.assertEquals("Impressora", resultado.getNome());
    }

    @Test
    public void testFindById_EquipamentoNaoExistente() {
        Mockito.when(equipamentoRepository.findById(1L)).thenReturn(Optional.empty());

        Assert.assertThrows(ResourceNotFoundException.class, () -> {
            equipamentoService.findById(1L);
        });
    }


}

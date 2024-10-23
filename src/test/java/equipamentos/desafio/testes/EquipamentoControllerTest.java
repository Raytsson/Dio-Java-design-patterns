package equipamentos.desafio.testes;

import equipamentos.desafio.controller.EquipamentosController;
import equipamentos.desafio.exception.ResourceNotFoundException;
import equipamentos.desafio.model.Equipamentos;
import equipamentos.desafio.service.EquipamentoFacade;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(EquipamentosController.class)
public class EquipamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EquipamentoFacade equipamentoFacade;

    @Test
    public void testGetEquipamento_Sucesso() throws Exception {
        Equipamentos equipamento = new Equipamentos(1L, "Impressora", "Eletrônico", 1500.00);
        Mockito.when(equipamentoFacade.buscarEquipamento(1L)).thenReturn(equipamento);

        mockMvc.perform(get("/equipamentos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Impressora"));
    }

    @Test
    public void testGetEquipamento_NotFound() throws Exception {
        Mockito.when(equipamentoFacade.buscarEquipamento(1L)).thenThrow(new ResourceNotFoundException("Equipamento não encontrado com o ID: 1"));

        mockMvc.perform(get("/equipamentos/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCadastrarEquipamento_Sucesso() throws Exception {
        Equipamentos equipamento = new Equipamentos(null, "Impressora", "Eletrônico", 1500.00);
        Equipamentos createdEquipamento = new Equipamentos(1L, "Impressora", "Eletrônico", 1500.00);

        Mockito.when(equipamentoFacade.cadastrarEquipamento(Mockito.any(Equipamentos.class))).thenReturn(createdEquipamento);

        mockMvc.perform(post("/equipamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Impressora\",\"tipo\":\"Eletrônico\",\"valor\":1500.00}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Impressora"));
    }
}

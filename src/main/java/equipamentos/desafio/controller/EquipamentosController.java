package equipamentos.desafio.controller;

import equipamentos.desafio.model.Equipamentos;
import equipamentos.desafio.service.EquipamentoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentosController {

    @Autowired
    private EquipamentoFacade equipamentoFacade;

    @GetMapping
    public List<Equipamentos> getEquipamentos() {
        return equipamentoFacade.listarEquipamentos();
    }

    @PostMapping
    public ResponseEntity<Equipamentos> createEquipamento(@RequestBody Equipamentos equipamento) {
        Equipamentos createdEquipamento = equipamentoFacade.cadastrarEquipamento(equipamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEquipamento);
    }

    @GetMapping("/{id}")
    public Equipamentos getEquipamentoById(@PathVariable Long id) {
        return equipamentoFacade.buscarEquipamento(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipamento(@PathVariable Long id) {
        equipamentoFacade.removerEquipamento(id);
    }
}

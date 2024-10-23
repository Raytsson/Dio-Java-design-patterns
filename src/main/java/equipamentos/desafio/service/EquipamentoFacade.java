package equipamentos.desafio.service;

import equipamentos.desafio.model.Equipamentos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentoFacade {

    @Autowired
    private EquipamentoService equipamentoService;

    public List<Equipamentos> listarEquipamentos() {
        return equipamentoService.findAll();
    }

    public Equipamentos cadastrarEquipamento(Equipamentos equipamentos) {
        return equipamentoService.save(equipamentos);
    }

    public Equipamentos buscarEquipamento(Long id) {
        return equipamentoService.findById(id);
    }

    public void removerEquipamento(Long id) {
        equipamentoService.deleteById(id);
    }
}

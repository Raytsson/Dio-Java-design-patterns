package equipamentos.desafio.service;

import equipamentos.desafio.exception.ResourceNotFoundException;
import equipamentos.desafio.model.Equipamentos;
import equipamentos.desafio.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository repository;

    public List<Equipamentos> findAll() {
        return repository.findAll();
    }

    public Equipamentos save(Equipamentos equipamentos) {
        return repository.save(equipamentos);
    }

    public Equipamentos findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipamento não encontrado com o ID: " + id));
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Equipamento não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }

    // Strategy pattern para cálculo de depreciação
    public interface CalculoDepreciacaoStrategy {
        double calcularDepreciacao(double valorInicial, int anos);
    }

    public class DepreciacaoEletronicos implements CalculoDepreciacaoStrategy {
        @Override
        public double calcularDepreciacao(double valorInicial, int anos) {
            return valorInicial * Math.pow(0.8, anos);
        }
    }

    public class DepreciacaoMecanicos implements CalculoDepreciacaoStrategy {
        @Override
        public double calcularDepreciacao(double valorInicial, int anos) {
            return valorInicial * Math.pow(0.9, anos);
        }
    }

}

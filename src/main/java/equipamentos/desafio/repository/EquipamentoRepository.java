package equipamentos.desafio.repository;

import equipamentos.desafio.model.Equipamentos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<Equipamentos, Long> {
}

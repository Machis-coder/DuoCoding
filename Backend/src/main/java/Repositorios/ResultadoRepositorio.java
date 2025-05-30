package Repositorios;

import Entidades.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoRepositorio extends JpaRepository<Resultado, Long> {
}

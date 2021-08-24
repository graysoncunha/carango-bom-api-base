package br.com.caelum.carangobom.marca;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

    public Optional<Marca> findById(Long id);

    public List<Marca> findAllByOrderByNome();

    public Marca findByNome(String nome);

}

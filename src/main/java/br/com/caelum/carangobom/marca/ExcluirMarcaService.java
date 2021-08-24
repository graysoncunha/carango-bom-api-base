package br.com.caelum.carangobom.marca;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcluirMarcaService {

    private MarcaRepository repository;

    @Autowired
    public ExcluirMarcaService(MarcaRepository repository) {
        this.repository = repository;
    }

    public void excluir(Long id) throws Exception {
        Optional<Marca> marca = repository.findById(id);

        if (!marca.isPresent()) {
            throw new Exception("Exclusão inválida! Marca inexistente");
        }

        repository.deleteById(id);
    }
}

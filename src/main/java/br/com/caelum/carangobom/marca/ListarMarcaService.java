package br.com.caelum.carangobom.marca;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListarMarcaService {

    private MarcaRepository repository;

    @Autowired
    public ListarMarcaService(MarcaRepository repository) {
        this.repository = repository;
    }

    public List<Marca> todos() {
        return repository.findAllByOrderByNome();
    }

    public Optional<Marca> porId(Long id) throws Exception {
        Optional<Marca> marca = repository.findById(id);

        if (!marca.isPresent()) {
            throw new Exception("Marca não encontrada");
        }

        return marca;
    }



}

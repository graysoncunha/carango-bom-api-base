package br.com.caelum.carangobom.marca;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarcaFacade {

    private MarcaRepository repository;

    @Autowired
    public MarcaFacade(MarcaRepository repository) {
        this.repository = repository;
    }

    public List<Marca> listarOrdenadoPorNome() {
        return repository.findAllByOrderByNome();
    }

    public Optional<Marca> recuperar(Long id) throws Exception {
        Optional<Marca> marca = repository.findById(id);

        if (!marca.isPresent()) {
            throw new Exception("Marca não encontrada");
        }

        return marca;
    }


    public Marca cadastrar(Marca novaMarca) throws Exception {
        Marca marca = repository.findByNome(novaMarca.getNome());

        if (marca != null) {
            throw new Exception("Marca já cadastrada anteriormente");
        }

        return repository.save(novaMarca);
    }

    public Marca alterar(Long id, Marca dadosAlteracaoMarca) throws Exception {
        Optional<Marca> optionalMarca = repository.findById(id);

        if (!optionalMarca.isPresent()) {
            throw new Exception("Marca inexistente");
        }

        Marca marca = optionalMarca.get();

        marca.setNome(dadosAlteracaoMarca.getNome());

        return repository.save(marca);

    }

    public void excluir(Long id) throws Exception {
        Optional<Marca> marca = repository.findById(id);

        if (!marca.isPresent()) {
            throw new Exception("Exclusão inválida! Marca inexistente");
        }

        repository.deleteById(id);
    }


}

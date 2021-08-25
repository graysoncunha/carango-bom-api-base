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

    public Marca recuperar(Long id) throws MarcaNaoEncontradaException {
        Optional<Marca> marca = repository.findById(id);

        if (!marca.isPresent()) {
            throw new MarcaNaoEncontradaException();
        }

        return marca.get();
    }


    public Marca cadastrar(Marca novaMarca) throws MarcaCadastradaAnteriormenteException {
        var marca = repository.findByNome(novaMarca.getNome());

        if (marca != null) {
            throw new MarcaCadastradaAnteriormenteException();
        }

        return repository.save(novaMarca);
    }

    public Marca alterar(Long id, Marca dadosAlteracaoMarca) throws MarcaNaoEncontradaException {
        var marca = recuperar(id);

        marca.setNome(dadosAlteracaoMarca.getNome());

        return repository.save(marca);
    }

    public Marca deletar(Long id) throws MarcaNaoEncontradaException {
        Optional<Marca> marca = repository.findById(id);

        if (!marca.isPresent()) {
            throw new MarcaNaoEncontradaException();
        }

        repository.deleteById(id);

        return marca.get();
    }


}

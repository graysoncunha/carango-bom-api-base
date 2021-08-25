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

    public Optional<Marca> recuperar(Long id) throws MarcaNaoEncontradaException {
        Optional<Marca> marca = repository.findById(id);

        if (!marca.isPresent()) {
            throw new MarcaNaoEncontradaException();
        }

        return marca;
    }


    public Marca cadastrar(Marca novaMarca) throws MarcaCadastradaAnteriormenteException {
        Marca marca = repository.findByNome(novaMarca.getNome());

        if (marca != null) {
            throw new MarcaCadastradaAnteriormenteException();
        }

        return repository.save(novaMarca);
    }

    public Marca alterar(Long id, Marca dadosAlteracaoMarca) throws MarcaNaoEncontradaException {
        Optional<Marca> optionalMarca = recuperar(id);

        Marca marca = optionalMarca.get();

        marca.setNome(dadosAlteracaoMarca.getNome());

        return repository.save(marca);
    }

    public Optional<Marca> deletar(Long id) throws MarcaNaoEncontradaException {
        Optional<Marca> marca = repository.findById(id);

        if (!marca.isPresent()) {
            throw new MarcaNaoEncontradaException();
        }

        repository.deleteById(id);

        return marca;
    }


}

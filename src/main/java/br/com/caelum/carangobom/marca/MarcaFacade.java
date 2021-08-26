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
        return repository.findById(id);
    }

    public Marca cadastrar(Marca novaMarca) throws MarcaCadastradaAnteriormenteException {
        var marca = repository.findByNome(novaMarca.getNome());

        if (marca.isEmpty()) {
            throw new MarcaCadastradaAnteriormenteException();
        }

        return repository.save(novaMarca);
    }

    public Marca alterar(Long id, Marca dadosAlteracaoMarca) throws MarcaNaoEncontradaException {
        var marca = recuperar(id);

        if (marca.isEmpty()) {
            throw new MarcaCadastradaAnteriormenteException();
        }

        var marcaParaAlteracao = marca.get();

        marcaParaAlteracao.setNome(dadosAlteracaoMarca.getNome());

        return repository.save(marcaParaAlteracao);
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

package br.com.caelum.carangobom.veiculo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.caelum.carangobom.marca.MarcaRepository;

@Service
public class VeiculoFacade {
  private VeiculoRepository veiculoRepository;
  private MarcaRepository marcaRepository;

  @Autowired
  public VeiculoFacade(VeiculoRepository veiculoRepository, MarcaRepository marcaRepository) {
    this.veiculoRepository = veiculoRepository;
    this.marcaRepository = marcaRepository;
  }

  public List<VeiculoView> listar() {
    var veiculos = veiculoRepository.findAll();

    return veiculos.stream().map(VeiculoView::new).collect(Collectors.toList());
  }

  public Optional<VeiculoView> recuperar(Long id) {
    var veiculo = veiculoRepository.findById(id);

    if (veiculo.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(new VeiculoView(veiculo.get()));
  }

  public VeiculoView cadastrar(VeiculoForm form) {
    var marca = marcaRepository.findById(form.getMarcaId());

    if (marca.isEmpty()) {
      // TODO: Substituir por MarcaNaoEncontradaException após merge
      throw new RuntimeException("Marca não encontrada");
    }

    var veiculo = new Veiculo(marca.get(), form.getModelo(), form.getAno(), form.getValor());

    veiculoRepository.save(veiculo);

    return new VeiculoView(veiculo);
  }

  public VeiculoView alterar(Long id, VeiculoForm form) {
    var veiculoOpt = veiculoRepository.findById(id);

    if (veiculoOpt.isEmpty()) {
      throw new RuntimeException("Veículo não encontrado");
    }

    var marca = marcaRepository.findById(form.getMarcaId());

    if (marca.isEmpty()) {
      // TODO: Substituir por MarcaNaoEncontradaException após merge
      throw new RuntimeException("Marca não encontrada");
    }

    var veiculo = veiculoOpt.get();
    veiculo.setMarca(marca.get());
    veiculo.setModelo(form.getModelo());
    veiculo.setAno(form.getAno());
    veiculo.setValor(form.getValor());

    return new VeiculoView(veiculo);
  }

  public void deletar(Long id) {
    veiculoRepository.deleteById(id);
  }
}

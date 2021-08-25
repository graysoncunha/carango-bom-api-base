package br.com.caelum.carangobom.veiculo;

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
}

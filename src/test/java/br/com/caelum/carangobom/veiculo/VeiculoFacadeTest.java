package br.com.caelum.carangobom.veiculo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import br.com.caelum.carangobom.marca.Marca;
import br.com.caelum.carangobom.marca.MarcaRepository;

class VeiculoFacadeTest {

  private VeiculoFacade veiculoFacade;

  @Mock
  private VeiculoRepository veiculoRepository;

  @Mock
  private MarcaRepository marcaRepository;

  private List<Veiculo> veiculos;

  @BeforeEach
  public void configuraMock() {
    openMocks(this);

    veiculoFacade = new VeiculoFacade(veiculoRepository, marcaRepository);
    veiculos = obterVeiculos();
  }

  @ParameterizedTest
  @ValueSource(longs = {1L, 2L})
  void deveRetornarVeiculoPeloId(Long id) {
    var veiculoASerRecuperado = veiculos.stream().filter(x -> x.getId().equals(id)).findFirst();

    when(veiculoRepository.findById(id)).thenReturn(veiculoASerRecuperado);

    var veiculo = veiculoASerRecuperado.get();
    var veiculoView = veiculoFacade.recuperar(id).get();

    assertEquals(veiculo.getId(), veiculoView.getId());
    assertEquals(veiculo.getModelo(), veiculoView.getModelo());
    assertEquals(veiculo.getValor(), veiculoView.getValor());

    var marca = veiculo.getMarca();
    var marcaView = veiculoView.getMarca();

    assertEquals(marca.getId(), marcaView.getId());
    assertEquals(marca.getNome(), marcaView.getNome());

    verify(veiculoRepository).findById(id);
  }

  @Test
  void deveCadastrarVeiculo() {
    var marca = new Marca("Fiat");
    var veiculo = new Veiculo("Gol", "2021", marca, new BigDecimal("70000"));

    when(marcaRepository.findById(any())).thenReturn(Optional.of(marca));
    when(veiculoRepository.save(veiculo)).thenReturn(veiculo);

    var form = new VeiculoForm();
    form.setMarcaId(marca.getId());
    form.setModelo(veiculo.getModelo());
    form.setAno(veiculo.getAno());
    form.setValor(veiculo.getValor());

    var veiculoView = veiculoFacade.cadastrar(form);

    assertEquals(veiculo.getModelo(), veiculoView.getModelo());
    assertEquals(veiculo.getValor(), veiculoView.getValor());

    verify(veiculoRepository).save(veiculo);
  }

  private List<Veiculo> obterVeiculos() {
    var veiculos = new ArrayList<Veiculo>();

    var marca = new Marca("Audi");
    var veiculo0 = new Veiculo(1L, "A4", "2000", marca, new BigDecimal("20000"));
    var veiculo1 = new Veiculo(2L, "A6", "2020", marca, new BigDecimal("22000"));

    veiculos.add(veiculo0);
    veiculos.add(veiculo1);

    return veiculos;
  }

}

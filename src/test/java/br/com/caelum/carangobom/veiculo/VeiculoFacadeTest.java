package br.com.caelum.carangobom.veiculo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
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

    var veiculoView = new VeiculoView(veiculoASerRecuperado.get());

    var viewRecuperada = veiculoFacade.recuperar(id).get();

    assertEquals(veiculoView.getId(), viewRecuperada.getId());
    assertEquals(veiculoView.getModelo(), viewRecuperada.getModelo());
    assertEquals(veiculoView.getValor(), viewRecuperada.getValor());
    // TODO: assegurar igualdade de MarcaView

    verify(veiculoRepository).findById(id);
  }

  private List<Veiculo> obterVeiculos() {
    var veiculos = new ArrayList<Veiculo>();

    var marca = new Marca("Audi");
    var veiculo0 = new Veiculo(1L, marca, "A4", "2000", new BigDecimal("20000"));
    var veiculo1 = new Veiculo(2L, marca, "A6", "2020", new BigDecimal("22000"));

    veiculos.add(veiculo0);
    veiculos.add(veiculo1);

    return veiculos;
  }

}

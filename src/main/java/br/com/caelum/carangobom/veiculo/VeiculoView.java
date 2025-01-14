package br.com.caelum.carangobom.veiculo;

import java.math.BigDecimal;
import br.com.caelum.carangobom.marca.MarcaView;

public class VeiculoView {
  private Long id;
  private String modelo;
  private String ano;
  private BigDecimal valor;
  private MarcaView marca;

  public VeiculoView(Veiculo veiculo) {
    this.id = veiculo.getId();
    this.modelo = veiculo.getModelo();
    this.ano = veiculo.getAno();
    this.valor = veiculo.getValor();
    this.marca = new MarcaView(veiculo.getMarca());
  }

  public Long getId() {
    return id;
  }

  public String getModelo() {
    return modelo;
  }

  public String getAno() {
    return ano;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public MarcaView getMarca() {
    return marca;
  }
}

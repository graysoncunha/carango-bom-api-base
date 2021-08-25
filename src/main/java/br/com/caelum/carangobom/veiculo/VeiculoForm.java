package br.com.caelum.carangobom.veiculo;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class VeiculoForm {
  private Long MarcaId;

  @NotBlank
  @Size(min = 2, message = "Deve ter {min} ou mais caracteres.")
  private String modelo;

  @NotBlank
  @Size(min = 4, max = 4, message = "Deve ter {min} ou mais caracteres.")
  private String ano;

  private BigDecimal valor;

  public Long getMarcaId() {
    return MarcaId;
  }

  public void setMarcaId(Long marcaId) {
    MarcaId = marcaId;
  }

  public String getModelo() {
    return modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public String getAno() {
    return ano;
  }

  public void setAno(String ano) {
    this.ano = ano;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }
}

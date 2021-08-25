package br.com.caelum.carangobom.veiculo;

import static javax.persistence.GenerationType.IDENTITY;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import br.com.caelum.carangobom.marca.Marca;

@Entity
public class Veiculo {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @NotBlank
  @Size(min = 2, message = "Deve ter {min} ou mais caracteres.")
  private String modelo;

  @NotBlank
  @Size(min = 4, max = 4, message = "Deve ter {min} ou mais caracteres.")
  private String ano;

  private BigDecimal valor;

  @ManyToOne
  private Marca marca;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Marca getMarca() {
    return marca;
  }

  public void setMarca(Marca marca) {
    this.marca = marca;
  }

}

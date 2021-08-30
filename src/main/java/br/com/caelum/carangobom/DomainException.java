package br.com.caelum.carangobom;

public class DomainException extends RuntimeException {
  private static final long serialVersionUID = 1050812768976980295L;

  public DomainException(String message) {
    super(message);
  }
}

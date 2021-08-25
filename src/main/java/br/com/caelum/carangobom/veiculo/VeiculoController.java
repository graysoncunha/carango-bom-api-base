package br.com.caelum.carangobom.veiculo;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
  private VeiculoFacade veiculoFacade;

  @Autowired
  public VeiculoController(VeiculoFacade veiculoFacade) {
    this.veiculoFacade = veiculoFacade;
  }

  @PostMapping
  @Transactional
  public ResponseEntity<VeiculoView> cadastrar(@RequestBody @Valid VeiculoForm form,
      UriComponentsBuilder uriComponentsBuilder) {
    var view = veiculoFacade.cadastrar(form);

    var uri = uriComponentsBuilder.path("/veiculos/{id}").buildAndExpand(view.getId()).toUri();

    return ResponseEntity.created(uri).body(view);
  }
}

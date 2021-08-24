package br.com.caelum.carangobom.marca;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

	private MarcaRepository repository;

	@Autowired
	public MarcaController(MarcaRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	@Transactional
	public List<Marca> lista() {
		return repository.findAllByOrderByNome();
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<Marca> id(@PathVariable Long id) {
		Optional<Marca> m1 = repository.findById(id);
		if (m1.isPresent()) {
			return ResponseEntity.ok(m1.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Marca> cadastra(@Valid @RequestBody Marca m1, UriComponentsBuilder uriBuilder) {
		Marca m2 = repository.save(m1);
		URI h = uriBuilder.path("/marcas/{id}").buildAndExpand(m1.getId()).toUri();
		return ResponseEntity.created(h).body(m2);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Marca> altera(@PathVariable Long id, @Valid @RequestBody Marca m1) {
		Optional<Marca> m2 = repository.findById(id);
		if (m2.isPresent()) {
			Marca m3 = m2.get();
			m3.setNome(m1.getNome());
			return ResponseEntity.ok(m3);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Marca> deleta(@PathVariable Long id) {
		Optional<Marca> m1 = repository.findById(id);
		if (m1.isPresent()) {
			Marca m2 = m1.get();
			repository.delete(m2);
			return ResponseEntity.ok(m2);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
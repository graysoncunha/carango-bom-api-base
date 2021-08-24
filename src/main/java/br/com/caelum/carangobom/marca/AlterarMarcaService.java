package br.com.caelum.carangobom.marca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarMarcaService {

	private MarcaRepository repository;

	@Autowired
	public AlterarMarcaService(MarcaRepository repository) {
		this.repository = repository;
	}
	
	public Marca alterar(Long id, Marca dadosAlteracaoMarca) throws Exception {
		Optional<Marca> optionalMarca = repository.findById(id);
		
		if(!optionalMarca.isPresent()) {
			throw new Exception("Marca inexistente");
		}
		
		Marca marca = optionalMarca.get();
		
		marca.setNome(dadosAlteracaoMarca.getNome());
		
		return repository.save(marca);
		
	}
	
	
}

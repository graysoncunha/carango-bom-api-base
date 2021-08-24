package br.com.caelum.carangobom.marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

	
@Service
public class CadastrarMarcaService {

		private MarcaRepository repository;
		
		@Autowired
		public CadastrarMarcaService(MarcaRepository repository) {
			this.repository = repository;
		}
		
		public Marca alterar(Marca novaMarca) throws Exception {
			Marca marca = repository.findByNome(novaMarca.getNome());
			
			if(marca != null) {
				throw new Exception("Marca já cadastrada anteriormente");
			}
			
			return repository.save(novaMarca);
		}
}

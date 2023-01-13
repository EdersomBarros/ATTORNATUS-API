package br.com.desafiojava.desafiojava.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiojava.desafiojava.model.Endereco;
import br.com.desafiojava.desafiojava.model.Pessoa;
import br.com.desafiojava.desafiojava.repository.EnderecoRepository;
import br.com.desafiojava.desafiojava.repository.PessoaRepository;

@RestController
@RequestMapping(path = "/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@PostMapping
	public ResponseEntity<Endereco> post(@Valid @RequestBody Endereco endereco){
		
		String nomePessoa = endereco.getPessoa().getNome();
		Optional<Pessoa> pessoa = pessoaRepository.findByNome(nomePessoa);
		
		if (pessoa.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		endereco.setPessoa(pessoa.get());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoRepository.save(endereco));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> buscarPorId(@Valid @PathVariable Long id){
		return enderecoRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping
	public ResponseEntity<List<Endereco>> buscarTodos(){
		return ResponseEntity.ok(enderecoRepository.findAll());
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Endereco> atualizar(@Valid @RequestBody Endereco endereco){
		return enderecoRepository.findById(endereco.getId_endereco())
				.map(resp -> ResponseEntity.ok()
				.body(enderecoRepository.save(endereco)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	 @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        enderecoRepository.deleteById(id);
	    }
	

}

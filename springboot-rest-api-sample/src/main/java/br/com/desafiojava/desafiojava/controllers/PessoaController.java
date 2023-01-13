package br.com.desafiojava.desafiojava.controllers;

import java.util.List;

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

import br.com.desafiojava.desafiojava.model.Pessoa;
import br.com.desafiojava.desafiojava.repository.PessoaRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
@RequestMapping(path = "/pessoa")
public class PessoaController {

	@Autowired
	private PessoaRepository repository;

	@PostMapping
	public ResponseEntity<Pessoa> salvarPessoa(@Valid @RequestBody Pessoa pessoa) {

		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(pessoa));

	}
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> buscarTodos(){
		return  ResponseEntity.ok(repository.findAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());

	}
	
	@GetMapping("/buscarnome/{nome}")
	public ResponseEntity<List<Pessoa>> buscarPorNome(@PathVariable String nome) {
		
		return  ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));

	}
	@PutMapping("/atualizar")
	public ResponseEntity<Pessoa> atualizarPessoa(@Valid @RequestBody Pessoa pessoa) {
		
		return repository.findById(pessoa.getId_pessoa())
				.map(resp -> ResponseEntity.ok().body(repository.save(pessoa)))
				.orElse(ResponseEntity.notFound().build());

	}
	
	@DeleteMapping("/{id}")
	 public void delete(@PathVariable Long id) {
		 
		 repository.deleteById(id);
		 
	 }
	

}

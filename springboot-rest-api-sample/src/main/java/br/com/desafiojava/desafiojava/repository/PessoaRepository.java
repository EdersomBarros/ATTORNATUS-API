package br.com.desafiojava.desafiojava.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafiojava.desafiojava.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {


	public Optional<Pessoa> findByNome(String nome);
	public List<Pessoa> findAllByNomeContainingIgnoreCase(String nome);

	

}

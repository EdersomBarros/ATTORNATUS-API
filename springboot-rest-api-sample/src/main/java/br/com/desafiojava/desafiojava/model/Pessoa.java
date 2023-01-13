package br.com.desafiojava.desafiojava.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Pessoa")
public class Pessoa  {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa")
	private Long id_pessoa;

	private String nome;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("pessoa")
	private List<Endereco> enderecos;

	public Pessoa() {
	}

	public Pessoa(Long id_pessoa, String nome, LocalDate dataNascimento) {

		this.id_pessoa = id_pessoa;
		this.nome = nome;
		this.dataNascimento = dataNascimento;

	}

	public Long getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(Long id_pessoa) {
		this.id_pessoa = id_pessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Endereco> getEndereco() {
		return enderecos;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.enderecos = endereco;
	}

}

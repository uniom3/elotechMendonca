package com.mendonca.elotech.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;

import com.mendonca.elotech.domain.ListaContato;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.mendonca.elotech.domain.Pessoa;

public class PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@CPF(message = "CPF inválido")
	private String cpf;

	@NotEmpty(message = "Preenchimento obrigatório")
	@PastOrPresent
	@Column(columnDefinition = "DATE")
	private LocalDate dataNascimento;

	private List<ListaContato> listacontato ;

	public PessoaDTO(Pessoa obj) {
		id = obj.getId();
		nome = obj.getNome();
		cpf = obj.getCpf();
		dataNascimento = obj.getDataNascimento();
		listacontato = obj.getListaContato();
	}

	public PessoaDTO(Long id,
			@NotEmpty(message = "Preenchimento obrigatório") @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres") String nome,
			@NotEmpty(message = "Preenchimento obrigatório") @CPF(message = "CPF inválido") String cpf,
			@NotEmpty(message = "Preenchimento obrigatório") @PastOrPresent LocalDate dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<ListaContato> getListacontato() {
		return listacontato;
	}

	public void setListacontato(List<ListaContato> listacontato) {
		this.listacontato = listacontato;
	}
}

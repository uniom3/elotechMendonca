package com.mendonca.elotech.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.mendonca.elotech.domain.ListaContato;
import com.mendonca.elotech.domain.Pessoa;

public class ListaContatoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 3, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String name;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String telefone;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "E-mail inválido")
	private String email;

	private Pessoa Pessoa ;

	public ListaContatoDTO(ListaContato obj) {
		id = obj.getId();
		name = obj.getName();
		telefone = obj.getTelefone();
		email = obj.getEmail();
		Pessoa = obj.getPessoa();
	}

	public ListaContatoDTO(Long id, String name, String telefone, String email, com.mendonca.elotech.domain.Pessoa pessoa) {
		this.id = id;
		this.name = name;
		this.telefone = telefone;
		this.email = email;
		Pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public com.mendonca.elotech.domain.Pessoa getPessoa() {
		return Pessoa;
	}

	public void setPessoa(com.mendonca.elotech.domain.Pessoa pessoa) {
		Pessoa = pessoa;
	}
}

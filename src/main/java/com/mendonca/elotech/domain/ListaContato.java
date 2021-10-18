package com.mendonca.elotech.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;



@Entity
public class ListaContato implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3)
	private String name;
	
	@NotNull
	private String telefone;
	
	@Column(unique=true)
	@Email
	private String email;
	
	
	@ManyToOne
	@JoinColumn(name="pessoa_id")
	@JsonBackReference
	private Pessoa pessoa;
	
	public ListaContato() {
		
	}

	public ListaContato(Long id, String name, String telefone, String email, Pessoa pessoa) {
		super();
		this.id = id;
		this.name = name;
		this.telefone = telefone;
		this.email = email;
		this.pessoa = pessoa;
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
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListaContato other = (ListaContato) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(telefone, other.telefone);
	}

	@Override
	public String toString() {
		return "ListaContato [id=" + id + ", name=" + name + ", telefone=" + telefone + ", email=" + email + ", pessoa="
				+ pessoa + "]";
	}

	


}

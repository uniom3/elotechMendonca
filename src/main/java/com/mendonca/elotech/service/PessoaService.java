package com.mendonca.elotech.service;

import java.util.List;
import java.util.Optional;

import com.mendonca.elotech.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mendonca.elotech.domain.ListaContato;
import com.mendonca.elotech.domain.Pessoa;
import com.mendonca.elotech.dto.PessoaNewDTO;
import com.mendonca.elotech.repositories.ListaContatoRepository;
import com.mendonca.elotech.repositories.PessoaRepository;


import javax.transaction.Transactional;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ListaContatoRepository listaContatoRepository;

	@Transactional
	public Pessoa save(Pessoa pessoa) {
		pessoa.setId(null);
		pessoa = pessoaRepository.save(pessoa);
		listaContatoRepository.saveAll(pessoa.getListaContato());
		return pessoa;
	}


	public Pessoa update(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	public Pessoa findById(Long id) {
		try {
			Optional<Pessoa> obj = pessoaRepository.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Pessoa não encontrado! Id: " + id + Pessoa.class.getName()));
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Pessoa> find(){
		return pessoaRepository.findAll();
	}
	
	public Page<Pessoa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return pessoaRepository.findAll(pageRequest);
	}


	public void delete(Long id) {
		findById(id);
		pessoaRepository.deleteById(id);
	}
	

	public Pessoa fromDTO(PessoaNewDTO objDto) {
		Pessoa pessoa = new Pessoa(null, objDto.getNome(), objDto.getCpf(), objDto.getDataNascimento());
		ListaContato lista = new ListaContato(null, objDto.getName(), objDto.getTelefone(), objDto.getEmail(), pessoa);
		pessoa.getListaContato().add(lista);
		return pessoa;
	}


	public Pessoa fromDTO(Pessoa objDto) {		
		return new Pessoa(objDto.getId(), objDto.getNome(),objDto.getCpf(), objDto.getDataNascimento()) ;
	}

}

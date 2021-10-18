package com.mendonca.elotech.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mendonca.elotech.domain.ListaContato;
import com.mendonca.elotech.domain.Pessoa;
import com.mendonca.elotech.dto.ListaContatoNewDTO;
import com.mendonca.elotech.repositories.ListaContatoRepository;
import com.mendonca.elotech.repositories.PessoaRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ListaContatoService {

	@Autowired
	private ListaContatoRepository ListaContatoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Transactional
	public ListaContato save(ListaContato ListaContato) {
		ListaContato.setId(null);
		ListaContato = ListaContatoRepository.save(ListaContato);
		pessoaRepository.save(ListaContato.getPessoa());
		return ListaContato;
	}


	public ListaContato update(ListaContato ListaContato) {
		return ListaContatoRepository.save(ListaContato);
	}

	public ListaContato findById(Long id) {
		try {
			Optional<ListaContato> obj = ListaContatoRepository.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"ListaContato não encontrado! Id: " + id + ListaContato.class.getName()));
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<ListaContato> find(){
		return ListaContatoRepository.findAll();
	}

	public Page<ListaContato> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return ListaContatoRepository.findAll(pageRequest);
	}


	public void delete(Long id) {
		findById(id);
		ListaContatoRepository.deleteById(id);
	}


	public ListaContato fromDTO(ListaContatoNewDTO objDto) {
		Pessoa pessoa = new Pessoa(null, objDto.getNome(), objDto.getCpf(), objDto.getDataNascimento());
		ListaContato lista = new ListaContato(null, objDto.getName(), objDto.getTelefone(), objDto.getEmail(), pessoa);
		pessoa.getListaContato().add(lista);
		return lista;
	}


	public ListaContato fromDTO(ListaContato objDto) {
		return new ListaContato(objDto.getId(), objDto.getName(),objDto.getTelefone(), objDto.getEmail(), objDto.getPessoa()) ;
	}

}

package com.mendonca.elotech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mendonca.elotech.domain.ListaContato;

@Repository
public interface ListaContatoRepository extends JpaRepository<ListaContato, Long>{

}

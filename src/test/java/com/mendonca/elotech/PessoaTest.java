package com.mendonca.elotech;

import com.mendonca.elotech.domain.ListaContato;
import com.mendonca.elotech.domain.Pessoa;
import com.mendonca.elotech.service.PessoaService;
import org.hibernate.Transaction;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PessoaTest {

    @Autowired
    private PessoaService pessoaService;

     Pessoa pessoa = new Pessoa();
     ListaContato lista = new ListaContato();


    @Test
    public void Save(){
        saved("39711216833","ui4@gmail.com");
        assertEquals(7l, pessoa.getId());
    }
    @Test
    public void findById(){
        //saved("23067301803","uniom3@gmail.com");
        pessoa = pessoaService.findById(4l);
        assertEquals(4l, pessoa.getId());
    }

    @Test
    public void findByAll(){
        //saved("28450222478","uni@gmail.com");
        List<Pessoa> pessoa = pessoaService.find();
        try {
            assertEquals(3l, pessoa.getClass().getField("id"));
        } catch (NoSuchFieldException e) {
            e.getMessage();
        }
    }

    @Test
    public void findByPage(){
       // saved("42892632595","unim@gmail.com");
        Page<Pessoa> pessoa = pessoaService.findPage(1,10,"id","ASC");
        try {
            assertEquals(4l, pessoa.getClass().getField("id"));
        } catch (NoSuchFieldException e) {
            e.getMessage();
        }
    }

    @Test
    public void delete(){
     //   saved("46036602278", "iu@gmail.com");
        pessoaService.delete(2l);
        assertNotEquals("2l", pessoa.getId());
    }

    @Test
    @Rollback(false)
    public void update(){
        //saved("65740292875", "uni1@gmail.com");
        pessoa =  pessoaService.findById(6l);
        pessoa.setId(6l);
        pessoa.setNome("rau");
        pessoaService.update(pessoa);

        assertEquals("rau", pessoa.getNome());
    }




    public void saved(String cpf, String email){
        pessoa.setNome("Deivid");
        pessoa.setCpf(cpf);
        pessoa.setDataNascimento(LocalDate.parse("1992-06-14"));
        lista.setEmail(email);
        lista.setName("deivid");
        lista.setTelefone("16993718019");
        lista.setPessoa(pessoa);
        pessoa.setListaContato(Collections.singletonList(lista));
        pessoaService.save(pessoa);
    }
}

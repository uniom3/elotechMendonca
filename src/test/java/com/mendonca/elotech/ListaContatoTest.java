package com.mendonca.elotech;

import com.mendonca.elotech.domain.ListaContato;
import com.mendonca.elotech.domain.Pessoa;
import com.mendonca.elotech.service.ListaContatoService;
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

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ListaContatoTest {

    @Autowired
    private ListaContatoService listaContatoService;

     Pessoa pessoa = new Pessoa();
     ListaContato lista = new ListaContato();


    @Test
    public void Save(){
        saved("058.621.593-04","ui@gmail.com");
        assertEquals(1l, lista.getId());
    }
    @Test
    public void findById(){
        saved("23067301803","uniom3@gmail.com");
        lista = listaContatoService.findById(4l);
        assertEquals(4l, lista.getId());
    }

    @Test
    public void findByAll(){
        saved("28450222478","uni@gmail.com");
        List<ListaContato> lista = listaContatoService.find();
        try {
            assertEquals(3l, pessoa.getClass().getField("id"));
        } catch (NoSuchFieldException e) {
            e.getMessage();
        }
    }

    @Test
    public void findByPage(){
        saved("42892632595","unim@gmail.com");
        Page<ListaContato> lista = listaContatoService.findPage(1,10,"id","ASC");
        try {
            assertEquals(4l, lista.getClass().getField("id"));
        } catch (NoSuchFieldException e) {
            e.getMessage();
        }
    }

    @Test
    public void delete(){
        saved("46036602278", "iu@gmail.com");
        listaContatoService.delete(2l);
        assertNotEquals("2l", pessoa.getId());
    }

    @Test
    @Rollback(false)
    public void update(){
        saved("65740292875", "uni1@gmail.com");
        lista=  listaContatoService.findById(6l);
        lista.setId(6l);
        lista.setName("raul");
        listaContatoService.update(lista);

        assertEquals("raul", lista.getName());
    }




    public void saved(String cpf, String email){
        pessoa.setNome("Deivid");
        pessoa.setCpf(cpf);
        pessoa.setDataNascimento(LocalDate.parse("1992-06-14"));
        lista.setEmail(email);
        lista.setName("deivid");
        lista.setTelefone("16993718019");
        lista.setPessoa(pessoa);
        listaContatoService.save(lista);
    }
}

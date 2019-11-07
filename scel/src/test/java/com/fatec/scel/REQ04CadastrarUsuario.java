package com.fatec.scel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fatec.scel.model.Usuario;
import com.fatec.scel.model.UsuarioRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
public class REQ04CadastrarUsuario {
	@Autowired
	UsuarioRepository repository;
	private Validator validator;
	private ValidatorFactory validatorFactory;
	/**
	 * Verificar o comportamento da classe UsuarioRepository
	 */
	@Test
	public void CT01CadastrarLivroComSucesso() {
		// dado que o isbn nao esta cadastrado
		repository.deleteAll();
		// quando o usurio inclui as informacoes obrigatorias e confirma a operacao
		Usuario usuario= new Usuario("1111","Jojo", "1jojo.pokemon1@gmail.com","03276110","aaaaaaaaa");
		repository.save(usuario);
		// entao
		assertEquals(1, repository.count());
	}
}

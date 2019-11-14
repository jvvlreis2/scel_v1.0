package com.fatec.scel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fatec.scel.model.Livro;
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
	public void CT01CadastrarUsuarioComSucesso() {
		// dado que o isbn nao esta cadastrado
		repository.deleteAll();
		// quando o usurio inclui as informacoes obrigatorias e confirma a operacao
		Usuario usuario= new Usuario("1111","Jojo", "1jojo.pokemon1@gmail.com","03276110","aaaaaaaaa");
		repository.save(usuario);
		// entao
		assertEquals(1, repository.count());
	}


	@Test
	public void CT02CadastrarLivroComSucesso_dados_validos() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// given:
		Usuario usuario= new Usuario("1111","Jojo", "1jojo.pokemon1@gmail.com","03276110","aaaaaaaaa");
		// when:
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		// then:
		assertTrue(violations.isEmpty());
	}

	@Test
	public void CT03DeveDetectarRaInvalido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o titulo do livro esta invalido
		Usuario usuario= new Usuario("","Jojo", "1jojo.pokemon1@gmail.com","03276110","aaaaaaaaa");
		// when:
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		// then:
		assertEquals(violations.size(), 1);
		assertEquals("O ra deve ser preenchido", violations.iterator().next().getMessage());
	}
	
	@Test
	public void CT04DeveDetectarNomeInvalido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o titulo do livro esta invalido
		Usuario usuario= new Usuario("1111","", "1jojo.pokemon1@gmail.com","03276110","aaaaaaaaa");
		// when:
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		// then:
		assertEquals(violations.size(), 1);
		assertEquals("O nome deve ser preenchido", violations.iterator().next().getMessage());
	}
	
	@Test
	public void CT05DeveDetectarEmailInvalido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o titulo do livro esta invalido
		Usuario usuario= new Usuario("1111","jojo", "","03276110","aaaaaaaaa");
		// when:
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		// then:
		assertEquals(violations.size(), 1);
		assertEquals("O e-mail deve ser preenchido", violations.iterator().next().getMessage());
	}

}

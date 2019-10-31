package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.fatec.scel.model.LivroRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class REQ02ConsultarLivro {
	@Autowired
	LivroRepository repository;
	static Livro livro;
	private Validator validator;
	private ValidatorFactory validatorFactory;

	@Test
	public void CT01ConsultaLivro_com_sucesso() {
		// dado que o livro esta cadastrado
		livro = new Livro("3333", "Teste de Software", "Delamaro");
		repository.save(livro);
		// quando o usurio consulta o livro
		Livro ro = repository.findByIsbn("3333");
		// entao 
		assertThat(ro.getTitulo()).isEqualTo(livro.getTitulo());
		equals (ro.equals(livro));
	}
	
	@Test
	public void CT02DeveDetectarLivroInvalido() {
		// dado que o livro esta cadastrado
		livro = new Livro("4444", "Teste de Software", "Delamaro");
		repository.save(livro);
		// quando o usurio consulta o livro
		Livro ro =  repository.findByIsbn("5555");
		// entao 
		assertThat(ro).isNull();
	}
}
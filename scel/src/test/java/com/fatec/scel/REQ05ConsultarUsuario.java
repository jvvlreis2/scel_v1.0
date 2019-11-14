package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;
import com.fatec.scel.model.Usuario;
import com.fatec.scel.model.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class REQ05ConsultarUsuario {

	@Autowired
	UsuarioRepository repository;
	static Usuario usuario;
	private Validator validator;
	private ValidatorFactory validatorFactory;

	@Test
	public void CT01ConsultaUsuarioCom_sucesso() {
		// dado que o livro esta cadastrado
		usuario= new Usuario("1111","Jojo", "1jojo.pokemon1@gmail.com","03276110","aaaaaaaaa");
		repository.save(usuario);
		// quando o usurio consulta o livro
		Usuario ro = repository.findByRa("1111");
		// entao
		assertThat(ro.getNome()).isEqualTo(usuario.getNome());
		equals(ro.equals(usuario));
	}

	@Test
	public void CT02DeveDetectarUsuarioInvalido() {
		usuario = new Usuario("1111", "Jojo", "1jojo.pokemon1@gmail.com", "03276110", "aaaaaaaaa");
		repository.save(usuario);
		// quando o usurio consulta o livro
		Usuario ro = repository.findByRa("5555");
		// entao
		assertThat(ro).isNull();
	}
	
}

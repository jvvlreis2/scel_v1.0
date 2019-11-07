package com.fatec.scel.model;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Servico {
	public String obtemEndereco(String cep) {
		RestTemplate template = new RestTemplate();
		String url = "https://viacep.com.br/ws/{cep}/json/";
		Endereco endereco = template.getForObject(url, Endereco.class, cep);
		return endereco.getLogradouro();
	}
}

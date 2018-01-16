package br.com.juliano.springcloud.model;

import lombok.Data;

@Data
public class Contato {
	private Long id;
	private String nome;
	private String email;
	private String tipoContato;
	private String telefone;
}
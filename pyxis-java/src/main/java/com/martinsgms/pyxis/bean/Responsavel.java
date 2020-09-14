package com.martinsgms.pyxis.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PYX_RESPONSAVEL")
public class Responsavel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Acolito> dependente;
	
	@Column(name = "DS_NOME")
	private String nome;
	
	@Column(name = "DS_SOBRENOME")
	private String sobrenome;
	
	@Column(name = "DS_EMAIL")
	private String email;
	
	@Column(name = "DS_TELEFONE")
	private Long telefone;
}

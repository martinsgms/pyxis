package com.martinsgms.pyxis.bean;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.martinsgms.pyxis.enums.SimNaoEnum;

import lombok.Data;

@Data
@Entity
@Table(name = "PYX_ACOLITO")
public class Acolito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DS_NOME")
	private String nome;
	
	@Column(name = "DS_SOBRENOME")
	private String sobrenome;
	
	@Column(name = "DS_USUARIO")
	private String usuario;
	
	@Column(name = "DS_SENHA")
	private String senha;
	
	@Column(name = "DS_EMAIL")
	private String email;
	
	@Column(name = "DS_TELEFONE")
	private Long telefone;
	
	@Column(name = "DT_NASCIMENTO")
	private LocalDate dtNascimento;
	
	@Column(name = "DT_NOMEACAO")
	private LocalDate dtNomeacao;
	
	@Column(name = "SN_CERIMONIARIO")
	@Enumerated(EnumType.STRING)
	private SimNaoEnum cerimoniario;
}

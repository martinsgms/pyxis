package com.martinsgms.pyxis.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PYX_FUNCAO")
public class Funcao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DS_NOME")
	private String funcao;
	
	@Column(name = "DS_SIGLA")
	private String sigla; 
}

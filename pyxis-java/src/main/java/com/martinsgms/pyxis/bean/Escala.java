package com.martinsgms.pyxis.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PYX_ESCALA")
public class Escala {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Acolito acolito;
	
	@ManyToOne
	private Funcao funcao;

	@ManyToOne
	private Missa missa;
}

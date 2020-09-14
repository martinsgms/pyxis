package com.martinsgms.pyxis.bean;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.martinsgms.pyxis.enums.SimNaoEnum;

import lombok.Data;

@Data
@Entity
@Table(name = "PYX_DISPONIBILIDADE")
public class Disponibilidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Acolito acolito;
	
	@Column(name = "DH_ESCALA")
	private LocalDateTime dhEscala;
	
	@Column(name = "SN_QUARTA_FEIRA")
	private SimNaoEnum podeAsQuartas;
	
}

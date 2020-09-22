package com.martinsgms.pyxis.bean;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PYX_MISSA")
@Data @NoArgsConstructor
public class Missa {

	public Missa(ComunidadesParoquiaisEnum local, LocalDateTime data) {
		this.local = local;
		this.data = data;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private ComunidadesParoquiaisEnum local;
	
	@Column(name = "DH_HORARIO")
	private LocalDateTime data;
	
}

package com.martinsgms.pyxis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.martinsgms.pyxis.utils.ScheduleBrain;

@Controller
@RequestMapping("/escala/disponibilidade")
public class DisponibilidadeController {

	@Autowired
	private ScheduleBrain scheduleBrain;
	
	@GetMapping
	public String acessarPagina() {

		scheduleBrain.generateShceduleOfNextMonth();

		return "disponibilidade";
	}
}

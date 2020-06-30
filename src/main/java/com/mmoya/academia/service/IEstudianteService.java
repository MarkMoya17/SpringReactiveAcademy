package com.mmoya.academia.service;

import com.mmoya.academia.document.Estudiante;

import reactor.core.publisher.Flux;

public interface IEstudianteService extends ICRUD<Estudiante, String>{
	
	Flux<Estudiante> findAllOrderByEdadDesc();
	
}

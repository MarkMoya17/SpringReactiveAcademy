package com.mmoya.academia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;

import com.mmoya.academia.document.Estudiante;
import com.mmoya.academia.repo.IEstudianteRepo;
import com.mmoya.academia.repo.IGenericRepo;
import com.mmoya.academia.service.IEstudianteService;

import reactor.core.publisher.Flux;

@Service
public class EstudianteServiceImpl extends ICRUDImp<Estudiante, String> implements IEstudianteService{

	@Autowired
	private IEstudianteRepo repo;
	
	@Override
	protected IGenericRepo<Estudiante, String> getRepo(){
		return repo;
	}

	@Override
	public Flux<Estudiante> findAllOrderByEdadDesc() {
		return repo.findAll(Sort.by(new Order(Direction.DESC, "edad")));
	}


	
	
}

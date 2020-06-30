package com.mmoya.academia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmoya.academia.document.Matricula;
import com.mmoya.academia.repo.IGenericRepo;
import com.mmoya.academia.repo.IMatriculaRepo;
import com.mmoya.academia.service.IMatriculaService;

@Service
public class MatriculaServiceImp extends ICRUDImp<Matricula, String> implements IMatriculaService{

	@Autowired
	private IMatriculaRepo repo;
	
	@Override
	protected IGenericRepo<Matricula, String> getRepo(){
		return repo;
	}
	
}

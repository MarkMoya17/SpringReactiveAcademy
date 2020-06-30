package com.mmoya.academia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmoya.academia.document.Curso;
import com.mmoya.academia.repo.ICursoRepo;
import com.mmoya.academia.repo.IGenericRepo;
import com.mmoya.academia.service.ICursoService;

@Service
public class CursoServiceImpl extends ICRUDImp<Curso, String> implements ICursoService{
	
	@Autowired
	private ICursoRepo repo;
	
	@Override
	protected IGenericRepo<Curso, String> getRepo(){
		return repo;
	}
	
	
}

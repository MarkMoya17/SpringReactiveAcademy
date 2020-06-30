package com.mmoya.academia.service.impl;

import com.mmoya.academia.repo.IGenericRepo;
import com.mmoya.academia.service.ICRUD;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class ICRUDImp<T, ID> implements ICRUD<T, ID> {

	protected abstract IGenericRepo<T, ID> getRepo();

	@Override
	public Mono<T> registrar(T t) {
		return getRepo().save(t);
	}

	@Override
	public Mono<T> modificar(T t) {
		return getRepo().save(t);
	}

	@Override
	public Flux<T> listar() {
		return getRepo().findAll();
	}

	@Override
	public Mono<T> listarPorId(ID v) {
		return getRepo().findById(v);
	}

	@Override
	public Mono<Void> eliminar(ID v) {
		return getRepo().deleteById(v);
	}
	
}

package com.mmoya.academia.repo;

import com.mmoya.academia.document.Usuario;

import reactor.core.publisher.Mono;

public interface IUsuarioRepo extends IGenericRepo<Usuario, String> {
	
	 Mono<Usuario> findOneByUsuario(String usuario);
}

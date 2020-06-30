package com.mmoya.academia.service;

import com.mmoya.academia.document.Usuario;
import com.mmoya.academia.security.User;

import reactor.core.publisher.Mono;

public interface IUsuarioService extends ICRUD<Usuario, String>{
	
	Mono<User> buscarPorUsuario(String usuario);
}

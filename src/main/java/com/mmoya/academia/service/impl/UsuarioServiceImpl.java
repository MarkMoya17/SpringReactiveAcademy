package com.mmoya.academia.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mmoya.academia.document.Rol;
import com.mmoya.academia.document.Usuario;
import com.mmoya.academia.repo.IUsuarioRepo;
import com.mmoya.academia.security.User;
import com.mmoya.academia.service.IUsuarioService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioRepo repo;
	
	@Override
	public Mono<Usuario> registrar(Usuario t) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		t.setClave(bcrypt.encode(t.getClave()));
		return repo.save(t);
	}

	@Override
	public Mono<Usuario> modificar(Usuario t) {		
		return repo.save(t);
	}

	@Override
	public Flux<Usuario> listar() {
		return repo.findAll();
	}
	
	@Override
	public Mono<Usuario> listarPorId(String v) {
		return repo.findById(v);
	}

	@Override
	public Mono<Void> eliminar(String v) {
		return repo.deleteById(v);
	}
	
	@Override
	public Mono<User> buscarPorUsuario(String usuario) {
		Mono<Usuario> monoUsuario = repo.findOneByUsuario(usuario);
		List<String> roles = new ArrayList<String>();
		return monoUsuario.doOnNext(u -> {
			for (Rol role : u.getRoles()) {
				roles.add(role.getNombre());
			}
		}).flatMap(u -> {
			return Mono.just(new User(u.getUsuario(), u.getClave(), u.getEstado(), roles));
		});
	}

}

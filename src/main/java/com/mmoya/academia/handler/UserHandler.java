package com.mmoya.academia.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.mmoya.academia.document.Usuario;
import com.mmoya.academia.service.IUsuarioService;
import com.mmoya.academia.validators.RequestValidator;

import reactor.core.publisher.Mono;

@Component
public class UserHandler{

	@Autowired
	private IUsuarioService service;
	
	@Autowired
	private RequestValidator validadorGeneral;
	
	public Mono<ServerResponse> registrar(ServerRequest request) {
		Mono<Usuario> usuarioMono = request.bodyToMono(Usuario.class);
		return usuarioMono
				.flatMap(this.validadorGeneral::validate)
				.flatMap(service::registrar)
				.flatMap(e -> {
					return ServerResponse.created(URI.create(request.uri().toString().concat("/").concat(e.getId())))
					.contentType(MediaType.APPLICATION_JSON)
					.body(fromValue(e));
				});
	}

	public Mono<ServerResponse> listar(ServerRequest request) {
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.listar(), Usuario.class);
	}

	

}

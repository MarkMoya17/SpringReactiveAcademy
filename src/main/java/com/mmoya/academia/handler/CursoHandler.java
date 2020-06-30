package com.mmoya.academia.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.mmoya.academia.document.Curso;
import com.mmoya.academia.service.ICursoService;
import com.mmoya.academia.validators.RequestValidator;

import reactor.core.publisher.Mono;

@Component
public class CursoHandler implements IHandler{

	@Autowired
	private ICursoService service;
	
	@Autowired
	private RequestValidator validadorGeneral;
	
	@Override
	public Mono<ServerResponse> listar(ServerRequest request) {
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.listar(), Curso.class);
	}

	@Override
	public Mono<ServerResponse> listarPorId(ServerRequest request) {
		String id = request.pathVariable("id");
		return service.listarPorId(id)
				.flatMap(e -> ServerResponse.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(fromValue(e))
						)
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	@Override
	public Mono<ServerResponse> registrar(ServerRequest request) {
		Mono<Curso> cursoMono = request.bodyToMono(Curso.class);
		return cursoMono
				.flatMap(this.validadorGeneral::validate)
				.flatMap(service::registrar)
				.flatMap(e -> ServerResponse.created(URI.create(request.uri().toString().concat("/").concat(e.getId())))
						.contentType(MediaType.APPLICATION_JSON)
						.body(fromValue(e))
						);
	}

	@Override
	public Mono<ServerResponse> modificar(ServerRequest request) {
		Mono<Curso> cursoMono = request.bodyToMono(Curso.class);
		return cursoMono
				.flatMap(this.validadorGeneral::validate)
				.flatMap(service::modificar)
				.flatMap(e -> ServerResponse.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(fromValue(e))
						)
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	@Override
	public Mono<ServerResponse> eliminar(ServerRequest request) {
		String id = request.pathVariable("id");
		return service.listarPorId(id)
				.flatMap(p -> service.eliminar(p.getId())
						.then(ServerResponse.noContent().build())
						)
				.switchIfEmpty(ServerResponse.notFound().build());
	}

}

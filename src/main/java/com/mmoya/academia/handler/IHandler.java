package com.mmoya.academia.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

public interface IHandler {
	Mono<ServerResponse> listar(ServerRequest request);
	Mono<ServerResponse> listarPorId(ServerRequest request);
	Mono<ServerResponse> registrar(ServerRequest request);
	Mono<ServerResponse> modificar(ServerRequest request);
	Mono<ServerResponse> eliminar(ServerRequest request);
}

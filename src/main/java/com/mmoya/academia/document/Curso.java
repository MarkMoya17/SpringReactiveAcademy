package com.mmoya.academia.document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cursos")
public class Curso {
	
	@Id
	private String id;
	
	@NotEmpty
	@Size(min = 3)
	private String nombres;
	
	@NotEmpty
	@Size(min = 3)
	private String siglas;
	
	@NotNull
	private boolean estado;
	
	public Curso() {
		super();
	}
	
	public Curso(String id, String nombres, String siglas, boolean estado) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.siglas = siglas;
		this.estado = estado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getSiglas() {
		return siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombres=" + nombres + ", siglas=" + siglas + ", estado=" + estado + "]";
	}
	
	
	
}

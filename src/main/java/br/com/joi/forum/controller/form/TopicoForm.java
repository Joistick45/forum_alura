package br.com.joi.forum.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.joi.forum.model.Curso;
import br.com.joi.forum.model.Topico;
import br.com.joi.forum.repository.CursoRepository;


public class TopicoForm {
	
	@NotNull @NotBlank @NotEmpty @Size(min = 5)
	private String titulo;
		
	@NotNull @NotBlank @NotEmpty @Size(min = 10)
	private String mensagem;
	
	@NotNull @NotBlank @NotEmpty
	private String nomeCurso;
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public Topico converter(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}
	
	

}

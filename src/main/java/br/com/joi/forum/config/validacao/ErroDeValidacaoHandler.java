package br.com.joi.forum.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	@Autowired
	private MessageSource messageSource; //injection ajuda a pegar mensagens de erro de acordo com o idioma do client
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST) //devolve 400 no post
	@ExceptionHandler(MethodArgumentNotValidException.class) //exception handler chama metodo quando alguma exception ocorre esta exceção de parametro no controller
	public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception) {
		
		List<ErroDeFormularioDto> dto = new ArrayList<>(); //faz uma lista com dois atributos, erro e mensagem apenas
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();//transforma em atributo, cada campo da exception lançada no badrequest
		
		
		fieldErrors.forEach(e ->{
			
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			
			ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem);
			
			dto.add(erro);
		});
		
		
		
		
		return dto;
	}
	
	

}

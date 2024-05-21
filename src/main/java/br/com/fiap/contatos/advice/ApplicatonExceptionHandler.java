package br.com.fiap.contatos.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicatonExceptionHandler{


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> manusearAgumentosInvalidos(MethodArgumentNotValidException erro){
        Map<String,String> mapErro = new HashMap<>();
        List<FieldError> campos = erro.getBindingResult().getFieldErrors();
        for (FieldError campo : campos){
            mapErro.put(campo.getField(), campo.getDefaultMessage());
        }
        return mapErro;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String,String> manusearIntegridadeDosDados(DataIntegrityViolationException erro){
        Map<String,String> mapErro = new HashMap<>();
        mapErro.put("erro","Usuário Já cadastrado");
        return mapErro;
    }


}

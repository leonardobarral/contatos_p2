package br.com.fiap.contatos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuariosNaoEncontradosException extends RuntimeException {

    public UsuariosNaoEncontradosException(String mensagem) {
        super(mensagem);
    }

}

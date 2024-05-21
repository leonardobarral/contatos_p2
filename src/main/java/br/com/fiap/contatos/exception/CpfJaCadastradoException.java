package br.com.fiap.contatos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CpfJaCadastradoException extends RuntimeException {

    public CpfJaCadastradoException(String mensagem) {
        super(mensagem);
    }

}

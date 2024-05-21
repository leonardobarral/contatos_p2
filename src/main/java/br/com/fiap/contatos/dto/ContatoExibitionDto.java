package br.com.fiap.contatos.dto;

import br.com.fiap.contatos.model.Contato;

import java.time.LocalDate;

public record ContatoExibitionDto(
        Long id,
        String nome,
        String email,
        LocalDate dataNascimento,
        String cpf
) {

    public ContatoExibitionDto(Contato contato) {
        this(
                contato.getId(),
                contato.getNome(),
                contato.getEmail(),
                contato.getDataNascimento(),
                contato.getCpf()
        );
    }
}

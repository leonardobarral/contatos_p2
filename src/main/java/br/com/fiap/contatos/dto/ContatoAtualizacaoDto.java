package br.com.fiap.contatos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record ContatoAtualizacaoDto(

        @NotNull(message = "O ID é obrigatório!")
        Long id,

        @NotBlank(message = "O Nome do contato é obrigatório!")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório!")
        @Email(message = "O e-amil está com formato inválido!")
        String email,

        @NotBlank(message = "A senha é obrigatória!")
        @Size(min = 6,max = 10, message = "A senha deve conter entre 6 e 10 caracteres!")
        String senha,

        @NotNull(message = "A data de nascimento é obrigatória!")
        LocalDate dataNascimento,

        @NotBlank(message = "O CPF é obrigatóri0!")
        @CPF(message = "O CPF é em um formato inválido!")
        String cpf
) {
}

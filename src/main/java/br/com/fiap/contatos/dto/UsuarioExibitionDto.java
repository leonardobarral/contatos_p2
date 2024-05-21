package br.com.fiap.contatos.dto;

import br.com.fiap.contatos.model.Usuario;
import br.com.fiap.contatos.model.UsuarioRole;

public record UsuarioExibitionDto(
        Long id,
        String nome,
        String email,
        UsuarioRole role
) {
    public UsuarioExibitionDto(Usuario usuario){
        this(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getRole()
        );
    }
}

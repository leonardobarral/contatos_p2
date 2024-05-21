package br.com.fiap.contatos.service;


import br.com.fiap.contatos.dto.UsuarioAtualizacaoDto;
import br.com.fiap.contatos.dto.UsuarioCadastroDto;
import br.com.fiap.contatos.dto.UsuarioExibitionDto;
import br.com.fiap.contatos.exception.UsuarioNaoEncontradoException;
import br.com.fiap.contatos.exception.UsuariosNaoEncontradosException;
import br.com.fiap.contatos.model.Usuario;
import br.com.fiap.contatos.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;


    @Transactional
    public UsuarioExibitionDto gravar(UsuarioCadastroDto usuarioCadastroDto) {

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioCadastroDto.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDto, usuario);

        usuario.setSenha(senhaCriptografada);

        return new UsuarioExibitionDto(repository.save(usuario));
    }


    @Transactional(readOnly = true)
    public UsuarioExibitionDto buscarPorId(Long id) {
        Optional<Usuario> usuarioOption = repository.findById(id);
        if (usuarioOption.isPresent()) {
            return new UsuarioExibitionDto(usuarioOption.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado");
        }
    }


    @Transactional(readOnly = true)
    public Page<UsuarioExibitionDto> listarTodosOsUsuarios(Pageable paginacao) {
        Page<UsuarioExibitionDto> usuarios = repository
                .findAll(paginacao)
                .map(UsuarioExibitionDto::new);
        if (!usuarios.isEmpty()) {
            return usuarios;
        } else {
            throw new UsuariosNaoEncontradosException("Usuarios não encontrados");
        }
    }


    @Transactional(readOnly = true)
    public Page<UsuarioExibitionDto> buscarPorNome(String nome, Pageable paginacao) {
        Page<UsuarioExibitionDto> usuarios = repository.findByNomeContainingIgnoreCase(nome, paginacao).map(UsuarioExibitionDto::new);
        if (!usuarios.isEmpty()) {
            return usuarios;
        } else {
            throw new UsuariosNaoEncontradosException("Usuarios não encontrados");
        }
    }


    @Transactional
    public UsuarioExibitionDto Atualizar(UsuarioAtualizacaoDto usuarioAtualizacaoDto) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioAtualizacaoDto, usuario);
        Optional<Usuario> usuarioOptional = repository.findById(usuario.getId());
        if (usuarioOptional.isPresent()) {
            return new UsuarioExibitionDto(repository.save(usuario));
        } else {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado");
        }
    }


    @Transactional
    public void excluir(Long id) {
        Optional<Usuario> usuarioOptional = repository.findById(id);
        if (usuarioOptional.isPresent()) {
            repository.delete(usuarioOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado");
        }
    }
}

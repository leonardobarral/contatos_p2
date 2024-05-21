package br.com.fiap.contatos.controller;


import br.com.fiap.contatos.dto.UsuarioAtualizacaoDto;
import br.com.fiap.contatos.dto.UsuarioCadastroDto;
import br.com.fiap.contatos.dto.UsuarioExibitionDto;
import br.com.fiap.contatos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

//    @PostMapping("/usuarios")
//    @ResponseStatus(HttpStatus.CREATED)
//    public UsuarioExibitionDto gravar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto) {
//        return service.gravar(usuarioCadastroDto);
//    }

    @GetMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibitionDto usuarioPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioExibitionDto> buscarTodosOsContatos(Pageable paginacao) {
        return service.listarTodosOsUsuarios(paginacao);
    }

    @GetMapping(value = "/usuarios", params = "nome")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioExibitionDto> buscarPorNome(@RequestParam String nome, Pageable paginacao) {
        return service.buscarPorNome(nome, paginacao);
    }


    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibitionDto atualizar(@RequestBody @Valid UsuarioAtualizacaoDto usuarioAtualizacaoDto) {
        return service.Atualizar(usuarioAtualizacaoDto);
    }


    @DeleteMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }


}

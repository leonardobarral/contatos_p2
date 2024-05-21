package br.com.fiap.contatos.controller;


import br.com.fiap.contatos.dto.ContatoAtualizacaoDto;
import br.com.fiap.contatos.dto.ContatoCadastroDto;
import br.com.fiap.contatos.dto.ContatoExibitionDto;
import br.com.fiap.contatos.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @PostMapping("/contatos")
    @ResponseStatus(HttpStatus.CREATED)
    public ContatoExibitionDto gravar(@RequestBody @Valid ContatoCadastroDto contatoCadastroDto) {
        return service.gravar(contatoCadastroDto);
    }

    @GetMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibitionDto buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping(value = "/contatos", params = "cpf")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibitionDto buscarPorCpf(@RequestParam String cpf) {
        return service.buscarPorCpf(cpf);
    }

    @GetMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public Page<ContatoExibitionDto> buscarTodosOsContatos(Pageable paginacao) {
        return service.listarTodosOsContatos(paginacao);
    }


    @GetMapping("/contatos/{dataInicial}/{dataFinal}")
    @ResponseStatus(HttpStatus.OK)
    public Page<ContatoExibitionDto> uscarEmUmPerido(@PathVariable LocalDate dataInicial, @PathVariable LocalDate dataFinal, Pageable paginacao) {
        return service.buscarEmUmPerido(dataInicial, dataFinal, paginacao);
    }

    @GetMapping(value = "/contatos", params = "nome")
    @ResponseStatus(HttpStatus.OK)
    public Page<ContatoExibitionDto> buscarPorNome(@RequestParam String nome, Pageable paginacao) {
        return service.buscarPorNome(nome, paginacao);
    }


    @PutMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibitionDto atualizar(@RequestBody @Valid ContatoAtualizacaoDto contatoAtualizacaoDto) {
        return service.Atualizar(contatoAtualizacaoDto);
    }


    @DeleteMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

}

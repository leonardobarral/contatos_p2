package br.com.fiap.contatos.service;


import br.com.fiap.contatos.dto.ContatoAtualizacaoDto;
import br.com.fiap.contatos.dto.ContatoCadastroDto;
import br.com.fiap.contatos.dto.ContatoExibitionDto;
import br.com.fiap.contatos.exception.CpfJaCadastradoException;
import br.com.fiap.contatos.exception.UsuarioNaoEncontradoException;
import br.com.fiap.contatos.exception.UsuariosNaoEncontradosException;
import br.com.fiap.contatos.model.Contato;
import br.com.fiap.contatos.repository.ContatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Transactional
    public ContatoExibitionDto gravar(ContatoCadastroDto contatoCadastroDto) {

        Optional<Contato> contatoOption = contatoRepository.findByCpf(contatoCadastroDto.cpf());

        if (contatoOption.isEmpty()) {

            Contato contato = new Contato();
            BeanUtils.copyProperties(contatoCadastroDto, contato);

            return new ContatoExibitionDto(contatoRepository.save(contato));

        } else {
            throw new CpfJaCadastradoException("CPF já cadastrado no banco de dados");
        }

    }

    @Transactional(readOnly = true)
    public ContatoExibitionDto buscarPorId(Long id) {

        Optional<Contato> contatoOption = contatoRepository.findById(id);

        if (contatoOption.isPresent()) {
            return new ContatoExibitionDto(contatoOption.get());
        } else {
            throw new UsuarioNaoEncontradoException("Contato não encontrado");
        }
    }

    @Transactional(readOnly = true)
    public ContatoExibitionDto buscarPorCpf(String cpf) {

        Optional<Contato> contatoOption = contatoRepository.findByCpf(cpf);

        if (contatoOption.isPresent()) {
            return new ContatoExibitionDto(contatoOption.get());
        } else {
            throw new UsuarioNaoEncontradoException("Contato não encontrado");
        }
    }

    @Transactional(readOnly = true)
    public Page<ContatoExibitionDto> listarTodosOsContatos(Pageable paginacao) {

        Page<ContatoExibitionDto> contatos = contatoRepository
                .findAll(paginacao)
                .map(ContatoExibitionDto::new);
        if (!contatos.isEmpty()) {
            return contatos;
        } else {
            throw new UsuariosNaoEncontradosException("Contatos não encontrados");
        }
    }

    @Transactional(readOnly = true)
    public Page<ContatoExibitionDto> buscarEmUmPerido(LocalDate dataInicial, LocalDate dataFinal, Pageable paginacao) {

        Page<ContatoExibitionDto> contatos = contatoRepository
                .findByDataNascimentoBetween(dataInicial, dataFinal, paginacao)
                .map(ContatoExibitionDto::new);
        if (!contatos.isEmpty()) {
            return contatos;
        } else {
            throw new UsuariosNaoEncontradosException("Contatos não encontrados");
        }
    }

    @Transactional(readOnly = true)
    public Page<ContatoExibitionDto> buscarPorNome(String nome, Pageable paginacao) {

        Page<ContatoExibitionDto> contatos = contatoRepository.findByNomeContainingIgnoreCase(nome, paginacao).map(ContatoExibitionDto::new);

        if (!contatos.isEmpty()) {
            return contatos;
        } else {
            throw new UsuariosNaoEncontradosException("Contatos não encontrados");
        }
    }

    @Transactional
    public ContatoExibitionDto Atualizar(ContatoAtualizacaoDto contatoAtualizacaoDto) {

        Contato contato = new Contato();
        BeanUtils.copyProperties(contatoAtualizacaoDto, contato);

        Optional<Contato> contatoOptional = contatoRepository.findById(contato.getId());

        if (contatoOptional.isPresent()) {

            Optional<Contato> contatoOptional2 = contatoRepository.findByCpf(contatoAtualizacaoDto.cpf());

            if (contatoOptional2.isEmpty() || contatoOptional == contatoOptional2) {
                return new ContatoExibitionDto(contatoRepository.save(contato));

            } else {
                throw new CpfJaCadastradoException("CPF já cadastrado no banco de dados");
            }

        } else {
            throw new UsuarioNaoEncontradoException("Contato não encontrado");
        }
    }

    @Transactional
    public void excluir(Long id) {

        Optional<Contato> contatoOptional = contatoRepository.findById(id);


        if (contatoOptional.isPresent()) {
            contatoRepository.delete(contatoOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Contato não encontrado");
        }

    }


}

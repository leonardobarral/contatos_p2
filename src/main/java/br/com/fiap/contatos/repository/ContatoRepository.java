package br.com.fiap.contatos.repository;


import br.com.fiap.contatos.model.Contato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    public Page<Contato> findByNomeContainingIgnoreCase(String nome, Pageable paginacao);


    @Query("SELECT c FROM Contato c WHERE c.cpf = :cpf")
    public Optional<Contato> findByCpf(@Param("cpf") String cpf);

    public Page<Contato> findByDataNascimentoBetween(LocalDate dataInicial, LocalDate dataFinal, Pageable paginacao);

}

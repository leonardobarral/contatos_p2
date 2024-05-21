package br.com.fiap.contatos.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_contato")
public class Contato {


    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TB_CONTATO_SEQ"
    )

    @SequenceGenerator(
            name = "TB_CONTATO_SEQ",
            sequenceName = "TB_CONTATO_SEQ",
            allocationSize = 1
    )
    private long id;
    private String nome;
    private String email;
    private String senha;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private String cpf;


//    @ManyToOne
//    @JoinColumn(name = "tipoContato_id")
//    private TipoContato tipoContato;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Contato contato = (Contato) object;
        return id == contato.id && Objects.equals(nome, contato.nome) && Objects.equals(email, contato.email) && Objects.equals(senha, contato.senha) && Objects.equals(dataNascimento, contato.dataNascimento) && Objects.equals(cpf, contato.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, senha, dataNascimento, cpf);
    }
}

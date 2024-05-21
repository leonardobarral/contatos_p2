package br.com.fiap.contatos.model;//package br.com.fiap.contato.model;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//import java.util.Objects;
//
//@Entity
//@Table(name = "tb_tipo_contato")
//public class TipoContato {
//
//    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "TB_TIPO_CONTATO_SEQ"
//    )
//    @SequenceGenerator(
//            name = "TB_TIPO_CONTATO_SEQ",
//            sequenceName = "TB_TIPO_CONTATO_SEQ",
//            allocationSize = 1
//    )
//    private long id;
//    private String tipo;
//
//    @OneToMany(mappedBy = "tipoContato")
//    private List<Contato> contatos;
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getTipo() {
//        return tipo;
//    }
//
//    public void setTipo(String tipo) {
//        this.tipo = tipo;
//    }
//
//    public List<Contato> getContatos() {
//        return contatos;
//    }
//
//    public void setContatos(List<Contato> contatos) {
//        this.contatos = contatos;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        if (this == object) return true;
//        if (object == null || getClass() != object.getClass()) return false;
//        TipoContato that = (TipoContato) object;
//        return id == that.id && Objects.equals(tipo, that.tipo) && Objects.equals(contatos, that.contatos);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, tipo, contatos);
//    }
//}

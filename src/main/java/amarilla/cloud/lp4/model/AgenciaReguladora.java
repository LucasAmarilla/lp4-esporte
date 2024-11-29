package amarilla.cloud.lp4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

@Entity
public class AgenciaReguladora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nome;

    @OneToMany(mappedBy = "agenciaReguladora")
    private List<Esporte> esportes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AgenciaReguladora that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(esportes, that.esportes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, esportes);
    }

    @Override
    public String toString() {
        return "AgenciaReguladora{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", esportes=" + esportes +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Esporte> getEsportes() {
        return esportes;
    }

    public void setEsportes(List<Esporte> esportes) {
        this.esportes = esportes;
    }
}

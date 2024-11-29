package amarilla.cloud.lp4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
public class Esporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode ser vazio")
    @Size(min = 3, message = "O nome deve ter mais que 3 caracteres")
    private String nome;

    @Min(1)
    private int qntdJogaores;

    @ManyToOne
    private AgenciaReguladora agenciaReguladora;

    @Override
    public String toString() {
        return "Esporte{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", qntdJogaores=" + qntdJogaores +
                ", agenciaReguladora=" + agenciaReguladora +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O nome não pode ser vazio") @Size(min = 3, message = "O nome deve ter mais que 3 caracteres") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome não pode ser vazio") @Size(min = 3, message = "O nome deve ter mais que 3 caracteres") String nome) {
        this.nome = nome;
    }

    @Min(1)
    public int getQntdJogaores() {
        return qntdJogaores;
    }

    public void setQntdJogaores(@Min(1) int qntdJogaores) {
        this.qntdJogaores = qntdJogaores;
    }

    public AgenciaReguladora getAgenciaReguladora() {
        return agenciaReguladora;
    }

    public void setAgenciaReguladora(AgenciaReguladora agenciaReguladora) {
        this.agenciaReguladora = agenciaReguladora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Esporte esporte)) return false;
        return qntdJogaores == esporte.qntdJogaores && Objects.equals(id, esporte.id) && Objects.equals(nome, esporte.nome) && Objects.equals(agenciaReguladora, esporte.agenciaReguladora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, qntdJogaores, agenciaReguladora);
    }

    public Esporte(Long id, String nome, int qntdJogaores, AgenciaReguladora agenciaReguladora) {
        this.id = id;
        this.nome = nome;
        this.qntdJogaores = qntdJogaores;
        this.agenciaReguladora = agenciaReguladora;
    }

    public Esporte() {
    }
}

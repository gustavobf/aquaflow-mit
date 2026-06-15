package com.infnet.aquaflow.infraestrutura.persistencia.entidade;

import com.infnet.aquaflow.dominio.enumeracao.StatusMatricula;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "matriculas")
public class MatriculaJpa {
    @Id
    private UUID id;
    @Column(nullable = false)
    private UUID alunoId;
    @Column(nullable = false)
    private UUID turmaId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusMatricula status;
    @Column(nullable = false)
    private BigDecimal valorMensalidade;

    protected MatriculaJpa () {
    }

    public MatriculaJpa (UUID id, UUID alunoId, UUID turmaId, StatusMatricula status, BigDecimal valorMensalidade) {
        this.id = id;
        this.alunoId = alunoId;
        this.turmaId = turmaId;
        this.status = status;
        this.valorMensalidade = valorMensalidade;
    }

    public UUID getId () {
        return id;
    }

    public UUID getAlunoId () {
        return alunoId;
    }

    public UUID getTurmaId () {
        return turmaId;
    }

    public StatusMatricula getStatus () {
        return status;
    }

    public BigDecimal getValorMensalidade () {
        return valorMensalidade;
    }
}

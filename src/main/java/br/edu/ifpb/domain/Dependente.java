package br.edu.ifpb.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Dependente {

    private String uuid;
    private String nome;
    private LocalDate dataDeNascimento;

    public Dependente() {
        this("");
    }

    public Dependente(String nome) {
        this(
            UUID.randomUUID().toString(),
            nome,
            LocalDate.now()
        );
    }
    public Dependente(String uuid,String nome) {
        this(
            uuid,nome,LocalDate.now()
        );
    }

    public Dependente(String uuid,String nome,LocalDate dataDeNascimento) {
        this.uuid = uuid;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
    }

    public static Dependente fake() {
        return new Dependente("-1","-1");
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public boolean naoValido() {
        return nomeVazio() || nascimentoAnterior();
    }

    public boolean nomeVazio() {
        return this.nome == null || this.nome.trim().equals("");
    }

    public boolean nascimentoAnterior() {
        return this.dataDeNascimento == null
            || this.dataDeNascimento.isEqual(LocalDate.now())
            || this.dataDeNascimento.isAfter(LocalDate.now());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.uuid);
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.dataDeNascimento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dependente other = (Dependente) obj;
        if (!Objects.equals(this.uuid,other.uuid)) {
            return false;
        }
        if (!Objects.equals(this.nome,other.nome)) {
            return false;
        }
        if (!Objects.equals(this.dataDeNascimento,other.dataDeNascimento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dependente{" +
                "uuid='" + uuid + '\'' +
                ", nome='" + nome + '\'' +
                ", dataDeNascimento=" + dataDeNascimento +
                '}';
    }
}

package br.edu.ifpb.domain;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 01/02/2021, 09:43:10
 */
public class Pessoa {

    private String nome;
    private long id;
    private CPF cpf;
    private Dependente dependente;

    public Pessoa() {
    }

    public Pessoa(String nome) {
        this(nome, "11111111111");
    }
    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.id = System.currentTimeMillis();
        this.cpf = new CPF(cpf);
    }
    public void alterarNome() {
        this.nome = this.nome.toUpperCase();
    }
    public void alterarNomeMinusculo() {
        this.nome = this.nome.toLowerCase();
    }

    public static Pessoa fake(){
        return new Pessoa("fake");
    }

    public Dependente getDependente() {
        return dependente;
    }
    public void setDependente(Dependente dependente) {
        this.dependente = dependente;
    }
    public CPF getCpf() {
        return cpf;
    }
    public void setCpf(CPF cpf) {
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public long getId() {
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                ", cpf=" + cpf +
                ", dependente=" + dependente +
                '}';
    }
}

package br.edu.ifpb.domain;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 08/02/2021, 07:38:47
 */
public interface PessoasJDBC extends Serializable {

    public void nova(Pessoa pessoa) throws SQLException, ClassNotFoundException;

    public List<Pessoa> todas() throws SQLException, ClassNotFoundException;

    public void excluir(Pessoa pessoa) throws SQLException, ClassNotFoundException;

    public void atualizar(Pessoa pessoa) throws SQLException, ClassNotFoundException;

    public Pessoa localizarPessoaComId(long id) throws SQLException, ClassNotFoundException;

    public List<Dependente> todosOsDepentendes() throws SQLException, ClassNotFoundException;

    public Dependente localizarDependenteComId(long uuid) throws SQLException, ClassNotFoundException;

    public void novo(Dependente dependente) throws SQLException, ClassNotFoundException;

    public Pessoa localizarPorCPF(CPF cpf) throws SQLException, ClassNotFoundException;
}

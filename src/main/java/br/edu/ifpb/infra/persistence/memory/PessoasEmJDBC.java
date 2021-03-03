package br.edu.ifpb.infra.persistence.memory;

import br.edu.ifpb.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PessoasEmJDBC implements PessoasJDBC {

    public PessoasEmJDBC() {

    }

    @Override
    public void nova(Pessoa pessoa) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "insert into Pessoa(nome, cpf, idDependente) values (?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,pessoa.getNome());
        stmt.setString(2,pessoa.getCpf().getNumero());
        stmt.setString(3,pessoa.getDependente().getUuid());

        stmt.executeUpdate();
        conn.close();
    }

    @Override
    public List<Pessoa> todas() throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionFactory.getConnection();
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "select * from Pessoa";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(rs.getString("nome"));
            pessoa.setCpf(new CPF(rs.getString("cpf")));
            pessoa.setDependente(localizarDependenteComId(rs.getInt("idDependente")));
            pessoas.add(pessoa);
        }
        conn.close();
        return pessoas;
    }

    @Override
    public void excluir(Pessoa pessoa) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "delete from Pessoa where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,(int) pessoa.getId());

        stmt.executeUpdate();
        conn.close();
    }

    @Override
    public void atualizar(Pessoa pessoa) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "update Pessoa set nome = ?,cpf = ?,idDependente = ? " +
                "where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,pessoa.getNome());
        stmt.setString(2,pessoa.getCpf().getNumero());
        stmt.setString(3,pessoa.getDependente().getUuid());
        stmt.setInt(4,(int) pessoa.getId());

        stmt.executeUpdate();
        conn.close();
    }

    @Override
    public Pessoa localizarPessoaComId(long id) throws SQLException, ClassNotFoundException {
        Pessoa pessoa = new Pessoa();
        Connection conn = ConnectionFactory.getConnection();
        String sql = "select * from Pessoa where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,(int) id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            pessoa.setId(rs.getInt("id"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setCpf(new CPF(rs.getString("cpf")));
            pessoa.setDependente(localizarDependenteComId(rs.getInt("idDependente")));
            conn.close();
            return pessoa;
        }else{
            conn.close();
            return null;
        }
    }

    @Override
    public List<Dependente> todosOsDepentendes() throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionFactory.getConnection();
        List<Dependente> dependentes = new ArrayList<>();
        String sql = "select * from Dependente";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Dependente dependente = new Dependente();
            dependente.setNome(rs.getString("nome"));
            dependente.setUuid(String.valueOf(rs.getInt("id")));
            dependente.setDataDeNascimento((rs.getDate("dataNascimento")).toLocalDate());
            dependentes.add(dependente);
        }
        conn.close();
        return dependentes;
    }

    @Override
    public Dependente localizarDependenteComId(long uuid) throws SQLException, ClassNotFoundException {
        Dependente dependente = new Dependente();
        Connection conn = ConnectionFactory.getConnection();
        String sql = "select * from Dependente where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,(int) uuid);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            dependente.setNome(rs.getString("nome"));
            dependente.setUuid(String.valueOf(rs.getInt("id")));
            dependente.setDataDeNascimento((rs.getDate("dataNascimento")).toLocalDate());
            conn.close();
            return dependente;
        }else{
            conn.close();
            return null;
        }
    }

    @Override
    public void novo(Dependente dependente) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "insert into Dependente(nome, dataNascimento) values (?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,dependente.getNome());
        stmt.setDate(2,Date.valueOf(dependente.getDataDeNascimento()));

        stmt.executeUpdate();
        conn.close();
    }

    @Override
    public Pessoa localizarPorCPF(CPF cpf) throws SQLException, ClassNotFoundException {
        Pessoa pessoa = new Pessoa();
        Connection conn = ConnectionFactory.getConnection();
        String sql = "select * from Pessoa where cpf = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,cpf.getNumero());
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            pessoa.setId(rs.getInt("id"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setCpf(new CPF(rs.getString("cpf")));
            pessoa.setDependente(localizarDependenteComId(rs.getInt("idDependente")));
            conn.close();
            return pessoa;
        }else{
            conn.close();
            return null;
        }
    }
}

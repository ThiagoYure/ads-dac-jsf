package br.edu.ifpb.infra.persistence.memory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://localhost:5432/dac-ifpb-jsf";
        String usuario = "postgres";
        String senha = "senha";

        return DriverManager.getConnection(url, usuario, senha);

    }
}

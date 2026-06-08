package br.com.fiap.conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    public Connection conexao() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.OracleDriver");

        String url = buscarVariavelObrigatoria("DB_URL");
        String usuario = buscarVariavelObrigatoria("DB_USERNAME");
        String senha = buscarVariavelObrigatoria("DB_PASSWORD");

        Connection conn = DriverManager.getConnection(url, usuario, senha);
        conn.setAutoCommit(false);

        return conn;
    }

    private String buscarVariavelObrigatoria(String nome) {
        String valor = System.getenv(nome);

        if (valor == null || valor.trim().isEmpty()) {
            valor = System.getProperty(nome);
        }

        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalStateException(
                    "Variável de ambiente obrigatória não configurada: " + nome
            );
        }

        return valor;
    }
}
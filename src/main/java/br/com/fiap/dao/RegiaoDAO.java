package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.Regiao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegiaoDAO {

    // INSERT - O Oracle gera o id_regiao automaticamente via IDENTITY
    public void inserir(Regiao regiao) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO TB_REGIAO (nm_regiao, ds_oceano) VALUES (?, ?)";

        try {
            conn = new ConexaoFactory().conexao();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, regiao.getNomeRegiao());
            stmt.setString(2, regiao.getOceano());

            stmt.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    // SELECT ALL - Busca os dados com os nomes corretos das colunas
    public List<Regiao> listarTodos() throws SQLException, ClassNotFoundException {
        List<Regiao> regioes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id_regiao, nm_regiao, ds_oceano FROM TB_REGIAO ORDER BY id_regiao";

        try {
            conn = new ConexaoFactory().conexao();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Regiao regiao = new Regiao();
                regiao.setIdRegiao(rs.getLong("id_regiao"));
                regiao.setNomeRegiao(rs.getString("nm_regiao"));
                regiao.setOceano(rs.getString("ds_oceano"));

                regioes.add(regiao);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return regioes;
    }

    // SELECT BY ID - Busca por um ID específico
    public Regiao buscarPorId(Long id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id_regiao, nm_regiao, ds_oceano FROM TB_REGIAO WHERE id_regiao = ?";

        try {
            conn = new ConexaoFactory().conexao();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return new Regiao(
                        rs.getLong("id_regiao"),
                        rs.getString("nm_regiao"),
                        rs.getString("ds_oceano")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return null;
    }

    // UPDATE - Atualiza com as colunas corretas
    public void atualizar(Regiao regiao) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "UPDATE TB_REGIAO SET nm_regiao = ?, ds_oceano = ? WHERE id_regiao = ?";

        try {
            conn = new ConexaoFactory().conexao();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, regiao.getNomeRegiao());
            stmt.setString(2, regiao.getOceano());
            stmt.setLong(3, regiao.getIdRegiao());

            stmt.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    // DELETE
    public void excluir(Long id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "DELETE FROM TB_REGIAO WHERE id_regiao = ?";

        try {
            conn = new ConexaoFactory().conexao();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);

            stmt.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}
package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.PrevisaoIA;
import jakarta.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PrevisaoIADAO {

    public void inserir(PrevisaoIA p) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null; // Adicionado para capturar a chave

        String sql = "INSERT INTO TB_PREVISAO_IA (id_regiao, vl_prob_expansao, vl_area_prevista, tp_risco) VALUES (?, ?, ?, ?)";

        try {
            conn = new ConexaoFactory().conexao();

            // O truque no Oracle: declarar qual coluna ele deve retornar após o INSERT
            stmt = conn.prepareStatement(sql, new String[] {"ID_PREVISAO"});

            stmt.setLong(1, p.getIdRegiao());
            stmt.setDouble(2, p.getProbExpansao());
            stmt.setDouble(3, p.getAreaPrevista());
            stmt.setString(4, p.getTipoRisco());

            stmt.executeUpdate();

            // Capturando o ID gerado pelo banco e colocando no objeto
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                p.setIdPrevisao(rs.getLong(1));
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (rs != null) rs.close(); // Fechando o ResultSet
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    public List<PrevisaoIA> listarTodas() throws SQLException, ClassNotFoundException {
        List<PrevisaoIA> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id_previsao, id_regiao, vl_prob_expansao, vl_area_prevista, tp_risco, dt_previsao FROM TB_PREVISAO_IA ORDER BY id_previsao";
        try {
            conn = new ConexaoFactory().conexao();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new PrevisaoIA(
                        rs.getLong("id_previsao"),
                        rs.getLong("id_regiao"),
                        rs.getDouble("vl_prob_expansao"),
                        rs.getDouble("vl_area_prevista"),
                        rs.getString("tp_risco"),
                        rs.getTimestamp("dt_previsao").toLocalDateTime()
                ));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return lista;
    }

    public PrevisaoIA buscarPorId(Long id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id_previsao, id_regiao, vl_prob_expansao, vl_area_prevista, tp_risco, dt_previsao FROM TB_PREVISAO_IA WHERE id_previsao = ?";
        try {
            conn = new ConexaoFactory().conexao();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new PrevisaoIA(
                        rs.getLong("id_previsao"),
                        rs.getLong("id_regiao"),
                        rs.getDouble("vl_prob_expansao"),
                        rs.getDouble("vl_area_prevista"),
                        rs.getString("tp_risco"),
                        rs.getTimestamp("dt_previsao").toLocalDateTime()
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return null;
    }

    public void atualizar(PrevisaoIA p) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "UPDATE TB_PREVISAO_IA SET id_regiao = ?, vl_prob_expansao = ?, vl_area_prevista = ?, tp_risco = ? WHERE id_previsao = ?";
        try {
            conn = new ConexaoFactory().conexao();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, p.getIdRegiao());
            stmt.setDouble(2, p.getProbExpansao());
            stmt.setDouble(3, p.getAreaPrevista());
            stmt.setString(4, p.getTipoRisco());
            stmt.setLong(5, p.getIdPrevisao());
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

    public void excluir(Long id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "DELETE FROM TB_PREVISAO_IA WHERE id_previsao = ?";
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
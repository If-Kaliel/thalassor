package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.Embarcacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmbarcacaoDAO {

    public void inserir(Embarcacao emb) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO TB_EMBARCACAO (nm_embarcacao, vl_capacidade_t, st_embarcacao) VALUES (?, ?, ?)";

        try {
            conn = new ConexaoFactory().conexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, emb.getNomeEmbarcacao());
            stmt.setDouble(2, emb.getCapacidade());
            stmt.setString(3, emb.getStatus());

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

    public List<Embarcacao> listarTodos() throws SQLException, ClassNotFoundException {
        List<Embarcacao> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id_embarcacao, nm_embarcacao, vl_capacidade_t, st_embarcacao FROM TB_EMBARCACAO ORDER BY id_embarcacao";

        try {
            conn = new ConexaoFactory().conexao();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new Embarcacao(
                        rs.getLong("id_embarcacao"),
                        rs.getString("nm_embarcacao"),
                        rs.getDouble("vl_capacidade_t"),
                        rs.getString("st_embarcacao")
                ));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return lista;
    }

    public Embarcacao buscarPorId(Long id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id_embarcacao, nm_embarcacao, vl_capacidade_t, st_embarcacao FROM TB_EMBARCACAO WHERE id_embarcacao = ?";

        try {
            conn = new ConexaoFactory().conexao();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return new Embarcacao(
                        rs.getLong("id_embarcacao"),
                        rs.getString("nm_embarcacao"),
                        rs.getDouble("vl_capacidade_t"),
                        rs.getString("st_embarcacao")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return null;
    }

    public void atualizar(Embarcacao emb) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "UPDATE TB_EMBARCACAO SET nm_embarcacao = ?, vl_capacidade_t = ?, st_embarcacao = ? WHERE id_embarcacao = ?";

        try {
            conn = new ConexaoFactory().conexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, emb.getNomeEmbarcacao());
            stmt.setDouble(2, emb.getCapacidade());
            stmt.setString(3, emb.getStatus());
            stmt.setLong(4, emb.getIdEmbarcacao());

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
        String sql = "DELETE FROM TB_EMBARCACAO WHERE id_embarcacao = ?";

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
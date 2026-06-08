package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.OrdemColeta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdemColetaDAO {

    public void inserir(OrdemColeta ordem) throws SQLException, ClassNotFoundException {

        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = """
                INSERT INTO TB_ORDEM_COLETA
                (id_foco, id_embarcacao, id_usuario,
                 st_ordem, tx_observacoes)
                VALUES (?, ?, ?, ?, ?)
                """;

        try {

            conn = new ConexaoFactory().conexao();

            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, ordem.getIdFoco());
            stmt.setLong(2, ordem.getIdEmbarcacao());
            stmt.setLong(3, ordem.getIdUsuario());
            stmt.setString(4, ordem.getStatusOrdem());
            stmt.setString(5, ordem.getObservacoes());

            stmt.executeUpdate();

            conn.commit();

        } catch (SQLException e) {

            if (conn != null)
                conn.rollback();

            throw e;

        } finally {

            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    public List<OrdemColeta> listarTodos() throws SQLException, ClassNotFoundException {

        List<OrdemColeta> lista = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM TB_ORDEM_COLETA ORDER BY id_ordem";

        try {

            conn = new ConexaoFactory().conexao();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                OrdemColeta ordem = new OrdemColeta();

                ordem.setIdOrdem(rs.getLong("id_ordem"));
                ordem.setIdFoco(rs.getLong("id_foco"));
                ordem.setIdEmbarcacao(rs.getLong("id_embarcacao"));
                ordem.setIdUsuario(rs.getLong("id_usuario"));
                ordem.setDataAbertura(rs.getTimestamp("dt_abertura"));
                ordem.setDataConclusao(rs.getTimestamp("dt_conclusao"));
                ordem.setStatusOrdem(rs.getString("st_ordem"));
                ordem.setObservacoes(rs.getString("tx_observacoes"));

                lista.add(ordem);
            }

        } finally {

            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return lista;
    }

    public OrdemColeta buscarPorId(Long id) throws SQLException, ClassNotFoundException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM TB_ORDEM_COLETA WHERE id_ordem = ?";

        try {

            conn = new ConexaoFactory().conexao();

            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {

                return new OrdemColeta(
                        rs.getLong("id_ordem"),
                        rs.getLong("id_foco"),
                        rs.getLong("id_embarcacao"),
                        rs.getLong("id_usuario"),
                        rs.getTimestamp("dt_abertura"),
                        rs.getTimestamp("dt_conclusao"),
                        rs.getString("st_ordem"),
                        rs.getString("tx_observacoes")
                );
            }

        } finally {

            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return null;
    }

    public void atualizar(OrdemColeta ordem) throws SQLException, ClassNotFoundException {

        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = """
                UPDATE TB_ORDEM_COLETA
                SET id_foco = ?,
                    id_embarcacao = ?,
                    id_usuario = ?,
                    dt_conclusao = ?,
                    st_ordem = ?,
                    tx_observacoes = ?
                WHERE id_ordem = ?
                """;

        try {

            conn = new ConexaoFactory().conexao();

            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, ordem.getIdFoco());
            stmt.setLong(2, ordem.getIdEmbarcacao());
            stmt.setLong(3, ordem.getIdUsuario());
            stmt.setTimestamp(4, ordem.getDataConclusao());
            stmt.setString(5, ordem.getStatusOrdem());
            stmt.setString(6, ordem.getObservacoes());
            stmt.setLong(7, ordem.getIdOrdem());

            stmt.executeUpdate();

            conn.commit();

        } catch (SQLException e) {

            if (conn != null)
                conn.rollback();

            throw e;

        } finally {

            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    public void excluir(Long id) throws SQLException, ClassNotFoundException {

        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM TB_ORDEM_COLETA WHERE id_ordem = ?";

        try {

            conn = new ConexaoFactory().conexao();

            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            stmt.executeUpdate();

            conn.commit();

        } catch (SQLException e) {

            if (conn != null)
                conn.rollback();

            throw e;

        } finally {

            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}
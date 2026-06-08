package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void inserir(Usuario usuario) throws SQLException, ClassNotFoundException {

        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = """
                INSERT INTO TB_USUARIO
                (nm_usuario, ds_email, ds_senha, tp_perfil)
                VALUES (?, ?, ?, ?)
                """;

        try {
            conn = new ConexaoFactory().conexao();

            stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getNomeUsuario());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getPerfil());

            stmt.executeUpdate();

            conn.commit();

        } catch (SQLException e) {

            if (conn != null)
                conn.rollback();

            throw e;

        } finally {

            if (stmt != null)
                stmt.close();

            if (conn != null)
                conn.close();
        }
    }

    public List<Usuario> listarTodos() throws SQLException, ClassNotFoundException {

        List<Usuario> usuarios = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = """
                SELECT id_usuario,
                       nm_usuario,
                       ds_email,
                       ds_senha,
                       tp_perfil,
                       dt_cadastro
                FROM TB_USUARIO
                ORDER BY id_usuario
                """;

        try {

            conn = new ConexaoFactory().conexao();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setIdUsuario(rs.getLong("id_usuario"));
                usuario.setNomeUsuario(rs.getString("nm_usuario"));
                usuario.setEmail(rs.getString("ds_email"));
                usuario.setSenha(rs.getString("ds_senha"));
                usuario.setPerfil(rs.getString("tp_perfil"));
                usuario.setDataCadastro(rs.getTimestamp("dt_cadastro"));

                usuarios.add(usuario);
            }

        } finally {

            if (rs != null)
                rs.close();

            if (stmt != null)
                stmt.close();

            if (conn != null)
                conn.close();
        }

        return usuarios;
    }

    public Usuario buscarPorId(Long id) throws SQLException, ClassNotFoundException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = """
                SELECT *
                FROM TB_USUARIO
                WHERE id_usuario = ?
                """;

        try {

            conn = new ConexaoFactory().conexao();

            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {

                return new Usuario(
                        rs.getLong("id_usuario"),
                        rs.getString("nm_usuario"),
                        rs.getString("ds_email"),
                        rs.getString("ds_senha"),
                        rs.getString("tp_perfil"),
                        rs.getTimestamp("dt_cadastro")
                );
            }

        } finally {

            if (rs != null)
                rs.close();

            if (stmt != null)
                stmt.close();

            if (conn != null)
                conn.close();
        }

        return null;
    }

    public void atualizar(Usuario usuario) throws SQLException, ClassNotFoundException {

        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = """
                UPDATE TB_USUARIO
                SET nm_usuario = ?,
                    ds_email = ?,
                    ds_senha = ?,
                    tp_perfil = ?
                WHERE id_usuario = ?
                """;

        try {

            conn = new ConexaoFactory().conexao();

            stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getNomeUsuario());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getPerfil());
            stmt.setLong(5, usuario.getIdUsuario());

            stmt.executeUpdate();

            conn.commit();

        } catch (SQLException e) {

            if (conn != null)
                conn.rollback();

            throw e;

        } finally {

            if (stmt != null)
                stmt.close();

            if (conn != null)
                conn.close();
        }
    }

    public void excluir(Long id) throws SQLException, ClassNotFoundException {

        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM TB_USUARIO WHERE id_usuario = ?";

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

            if (stmt != null)
                stmt.close();

            if (conn != null)
                conn.close();
        }
    }
}
package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.FocoPoluicao;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FocoPoluicaoDAO {

    private final ConexaoFactory factory = new ConexaoFactory();

    public List<FocoPoluicao> listarTodos() throws Exception {
        List<FocoPoluicao> lista = new ArrayList<>();

        String sql = """
                SELECT id_foco,
                       id_regiao,
                       vl_latitude,
                       vl_longitude,
                       vl_extensao_km2,
                       vl_indice_fdi,
                       url_imagem,
                       st_foco,
                       dt_deteccao,
                       ds_nivel_risco
                FROM TB_FOCO_POLUICAO
                ORDER BY id_foco
                """;

        try (Connection con = factory.conexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(montarFoco(rs));
            }
        }

        return lista;
    }

    public FocoPoluicao buscarPorId(Long id) throws Exception {
        String sql = """
                SELECT id_foco,
                       id_regiao,
                       vl_latitude,
                       vl_longitude,
                       vl_extensao_km2,
                       vl_indice_fdi,
                       url_imagem,
                       st_foco,
                       dt_deteccao,
                       ds_nivel_risco
                FROM TB_FOCO_POLUICAO
                WHERE id_foco = ?
                """;

        try (Connection con = factory.conexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return montarFoco(rs);
                }
            }
        }

        return null;
    }

    public void inserir(FocoPoluicao foco) throws Exception {
        String sql = """
                INSERT INTO TB_FOCO_POLUICAO
                (id_regiao,
                 vl_latitude,
                 vl_longitude,
                 vl_extensao_km2,
                 vl_indice_fdi,
                 url_imagem,
                 st_foco,
                 ds_nivel_risco)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = factory.conexao()) {
            try (PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setLong(1, foco.getIdRegiao());
                stmt.setDouble(2, foco.getLatitude());
                stmt.setDouble(3, foco.getLongitude());
                stmt.setDouble(4, foco.getExtensaoKm2());

                if (foco.getIndiceFdi() != null) {
                    stmt.setDouble(5, foco.getIndiceFdi());
                } else {
                    stmt.setNull(5, Types.DOUBLE);
                }

                stmt.setString(6, foco.getUrlImagem());
                stmt.setString(7, foco.getStatusFoco());
                stmt.setString(8, foco.getNivelRisco());

                stmt.executeUpdate();
                con.commit();

            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        }
    }

    public void atualizar(FocoPoluicao foco) throws Exception {
        String sql = """
                UPDATE TB_FOCO_POLUICAO
                SET id_regiao = ?,
                    vl_latitude = ?,
                    vl_longitude = ?,
                    vl_extensao_km2 = ?,
                    vl_indice_fdi = ?,
                    url_imagem = ?,
                    st_foco = ?,
                    ds_nivel_risco = ?
                WHERE id_foco = ?
                """;

        try (Connection con = factory.conexao()) {
            try (PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setLong(1, foco.getIdRegiao());
                stmt.setDouble(2, foco.getLatitude());
                stmt.setDouble(3, foco.getLongitude());
                stmt.setDouble(4, foco.getExtensaoKm2());

                if (foco.getIndiceFdi() != null) {
                    stmt.setDouble(5, foco.getIndiceFdi());
                } else {
                    stmt.setNull(5, Types.DOUBLE);
                }

                stmt.setString(6, foco.getUrlImagem());
                stmt.setString(7, foco.getStatusFoco());
                stmt.setString(8, foco.getNivelRisco());
                stmt.setLong(9, foco.getIdFoco());

                stmt.executeUpdate();
                con.commit();

            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        }
    }

    public void excluir(Long id) throws Exception {
        String sql = "DELETE FROM TB_FOCO_POLUICAO WHERE id_foco = ?";

        try (Connection con = factory.conexao()) {
            try (PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setLong(1, id);
                stmt.executeUpdate();
                con.commit();

            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        }
    }

    private FocoPoluicao montarFoco(ResultSet rs) throws SQLException {
        Double indiceFdi = null;

        double valorIndice = rs.getDouble("vl_indice_fdi");
        if (!rs.wasNull()) {
            indiceFdi = valorIndice;
        }

        return new FocoPoluicao(
                rs.getLong("id_foco"),
                rs.getLong("id_regiao"),
                rs.getDouble("vl_latitude"),
                rs.getDouble("vl_longitude"),
                rs.getDouble("vl_extensao_km2"),
                indiceFdi,
                rs.getString("url_imagem"),
                rs.getString("st_foco"),
                rs.getTimestamp("dt_deteccao"),
                rs.getString("ds_nivel_risco")
        );
    }
}
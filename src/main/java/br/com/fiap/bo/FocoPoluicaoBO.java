package br.com.fiap.bo;

import br.com.fiap.dao.FocoPoluicaoDAO;
import br.com.fiap.entities.FocoPoluicao;
import br.com.fiap.services.NasaSatelliteService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class FocoPoluicaoBO {

    @Inject
    FocoPoluicaoDAO focoDAO;

    @Inject
    NasaSatelliteService nasaSatelliteService;

    public List<FocoPoluicao> listar() throws Exception {
        return focoDAO.listarTodos();
    }

    public FocoPoluicao buscar(Long id) throws Exception {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do foco inválido.");
        }

        FocoPoluicao foco = focoDAO.buscarPorId(id);

        if (foco == null) {
            throw new IllegalArgumentException("Foco de poluição com ID " + id + " não encontrado.");
        }

        return foco;
    }

    public void cadastrar(FocoPoluicao foco) throws Exception {
        validarFoco(foco);

        if (foco.getStatusFoco() == null || foco.getStatusFoco().trim().isEmpty()) {
            foco.setStatusFoco("DETECTADO");
        }

        foco.setStatusFoco(foco.getStatusFoco().trim().toUpperCase());
        foco.setNivelRisco(calcularRisco(foco));

        if (foco.getUrlImagem() == null || foco.getUrlImagem().trim().isEmpty()) {
            foco.setUrlImagem(
                    nasaSatelliteService.gerarUrlImagem(
                            foco.getLatitude(),
                            foco.getLongitude()
                    )
            );
        }

        focoDAO.inserir(foco);
    }

    public void atualizar(Long id, FocoPoluicao foco) throws Exception {
        buscar(id);
        validarFoco(foco);

        if (foco.getStatusFoco() == null || foco.getStatusFoco().trim().isEmpty()) {
            foco.setStatusFoco("DETECTADO");
        }

        foco.setIdFoco(id);
        foco.setStatusFoco(foco.getStatusFoco().trim().toUpperCase());
        foco.setNivelRisco(calcularRisco(foco));

        if (foco.getUrlImagem() == null || foco.getUrlImagem().trim().isEmpty()) {
            foco.setUrlImagem(
                    nasaSatelliteService.gerarUrlImagem(
                            foco.getLatitude(),
                            foco.getLongitude()
                    )
            );
        }

        focoDAO.atualizar(foco);
    }

    public void deletar(Long id) throws Exception {
        buscar(id);
        focoDAO.excluir(id);
    }

    private void validarFoco(FocoPoluicao foco) {
        if (foco == null) {
            throw new IllegalArgumentException("Os dados do foco de poluição são obrigatórios.");
        }

        if (foco.getIdRegiao() == null || foco.getIdRegiao() <= 0) {
            throw new IllegalArgumentException("A região é obrigatória.");
        }

        if (foco.getLatitude() == null) {
            throw new IllegalArgumentException("A latitude é obrigatória.");
        }

        if (foco.getLatitude() < -90 || foco.getLatitude() > 90) {
            throw new IllegalArgumentException("A latitude deve estar entre -90 e 90.");
        }

        if (foco.getLongitude() == null) {
            throw new IllegalArgumentException("A longitude é obrigatória.");
        }

        if (foco.getLongitude() < -180 || foco.getLongitude() > 180) {
            throw new IllegalArgumentException("A longitude deve estar entre -180 e 180.");
        }

        if (foco.getExtensaoKm2() == null) {
            throw new IllegalArgumentException("A extensão do foco é obrigatória.");
        }

        if (foco.getExtensaoKm2() <= 0) {
            throw new IllegalArgumentException("A extensão do foco deve ser maior que zero.");
        }

        if (foco.getIndiceFdi() != null && (foco.getIndiceFdi() < 0 || foco.getIndiceFdi() > 1)) {
            throw new IllegalArgumentException("O índice FDI deve estar entre 0 e 1.");
        }

        if (foco.getUrlImagem() != null && foco.getUrlImagem().length() > 500) {
            throw new IllegalArgumentException("A URL da imagem deve ter no máximo 500 caracteres.");
        }

        if (foco.getStatusFoco() != null && !foco.getStatusFoco().trim().isEmpty()) {
            String status = foco.getStatusFoco().trim().toUpperCase();

            if (!status.equals("DETECTADO") &&
                    !status.equals("EM_RECOLHIMENTO") &&
                    !status.equals("LIMPO")) {
                throw new IllegalArgumentException(
                        "Status inválido. Use DETECTADO, EM_RECOLHIMENTO ou LIMPO."
                );
            }
        }
    }

    private String calcularRisco(FocoPoluicao foco) {
        double extensao = foco.getExtensaoKm2();
        double indiceFdi = foco.getIndiceFdi() != null ? foco.getIndiceFdi() : 0;

        if (extensao >= 100 || indiceFdi >= 0.85) {
            return "CRITICO";
        }

        if (extensao >= 50 || indiceFdi >= 0.65) {
            return "ALTO";
        }

        if (extensao >= 10 || indiceFdi >= 0.35) {
            return "MEDIO";
        }

        return "BAIXO";
    }
}
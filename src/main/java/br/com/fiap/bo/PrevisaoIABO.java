package br.com.fiap.bo;

import br.com.fiap.dao.PrevisaoIADAO;
import br.com.fiap.entities.PrevisaoIA;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class PrevisaoIABO {

    @Inject
    PrevisaoIADAO dao;

    public List<PrevisaoIA> listar() throws Exception {
        return dao.listarTodas();
    }

    public PrevisaoIA buscar(Long id) throws Exception {
        PrevisaoIA p = dao.buscarPorId(id);
        if (p == null) {
            throw new IllegalArgumentException("Previsão não encontrada.");
        }
        return p;
    }

    public void cadastrar(PrevisaoIA p) throws Exception {
        validarPrevisao(p);
        dao.inserir(p);
    }

    public void atualizar(Long id, PrevisaoIA p) throws Exception {
        buscar(id); // Garante que existe antes de atualizar
        validarPrevisao(p);
        p.setIdPrevisao(id);
        dao.atualizar(p);
    }

    public void deletar(Long id) throws Exception {
        buscar(id); // Garante que existe antes de deletar
        dao.excluir(id);
    }

    private void validarPrevisao(PrevisaoIA p) {
        if (p.getIdRegiao() == null || p.getIdRegiao() <= 0) {
            throw new IllegalArgumentException("ID da região vinculada inválido.");
        }
        if (p.getProbExpansao() == null || p.getProbExpansao() < 0 || p.getProbExpansao() > 100) {
            throw new IllegalArgumentException("A probabilidade de expansão deve ser entre 0 e 100%.");
        }
        if (p.getAreaPrevista() == null || p.getAreaPrevista() < 0) {
            throw new IllegalArgumentException("A área prevista não pode ser negativa.");
        }

        // Validação da CONSTRAINT CHECK do banco
        if (p.getTipoRisco() == null) {
            throw new IllegalArgumentException("O tipo de risco é obrigatório.");
        }
        List<String> riscosValidos = Arrays.asList("BAIXO", "MEDIO", "ALTO", "CRITICO");
        if (!riscosValidos.contains(p.getTipoRisco().toUpperCase())) {
            throw new IllegalArgumentException("Tipo de risco inválido. Valores permitidos: BAIXO, MEDIO, ALTO ou CRITICO.");
        }
        p.setTipoRisco(p.getTipoRisco().toUpperCase());
    }
}
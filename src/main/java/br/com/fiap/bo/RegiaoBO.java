package br.com.fiap.bo;

import br.com.fiap.dao.RegiaoDAO;
import br.com.fiap.entities.Regiao;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped // Permite que o Quarkus injete esta classe automaticamente onde for necessário
public class RegiaoBO {

    private final RegiaoDAO regiaoDAO = new RegiaoDAO();

    public List<Regiao> listar() throws Exception {
        return regiaoDAO.listarTodos();
    }

    public Regiao buscar(Long id) throws Exception {
        Regiao regiao = regiaoDAO.buscarPorId(id);
        if (regiao == null) {
            throw new IllegalArgumentException("Região com ID " + id + " não encontrada.");
        }
        return regiao;
    }

    public void cadastrar(Regiao regiao) throws Exception {
        // Validações de regra de negócio obrigatórias
        if (regiao.getNomeRegiao() == null || regiao.getNomeRegiao().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da região é obrigatório.");
        }
        if (regiao.getOceano() == null || regiao.getOceano().trim().isEmpty()) {
            throw new IllegalArgumentException("O oceano correspondente é obrigatório.");
        }
        regiaoDAO.inserir(regiao);
    }

    public void atualizar(Long id, Regiao regiao) throws Exception {
        // Garante que a região existe antes de tentar atualizar
        buscar(id);

        if (regiao.getNomeRegiao() == null || regiao.getNomeRegiao().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da região não pode ficar vazio.");
        }

        regiao.setIdRegiao(id);
        regiaoDAO.atualizar(regiao);
    }

    public void deletar(Long id) throws Exception {
        buscar(id); // Garante que existe antes de deletar
        regiaoDAO.excluir(id);
    }
}
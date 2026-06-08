package br.com.fiap.bo;

import br.com.fiap.dao.EmbarcacaoDAO;
import br.com.fiap.entities.Embarcacao;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class EmbarcacaoBO {

    private final EmbarcacaoDAO dao = new EmbarcacaoDAO();

    public List<Embarcacao> listar() throws Exception { return dao.listarTodos(); }

    public Embarcacao buscar(Long id) throws Exception {
        Embarcacao emb = dao.buscarPorId(id);
        if (emb == null) throw new IllegalArgumentException("Embarcação não encontrada.");
        return emb;
    }

    public void cadastrar(Embarcacao emb) throws Exception {
        if (emb.getNomeEmbarcacao() == null || emb.getNomeEmbarcacao().trim().isEmpty())
            throw new IllegalArgumentException("Nome da embarcação é obrigatório.");
        dao.inserir(emb);
    }

    public void atualizar(Long id, Embarcacao emb) throws Exception {
        buscar(id);
        emb.setIdEmbarcacao(id);
        dao.atualizar(emb);
    }

    public void deletar(Long id) throws Exception {
        buscar(id);
        dao.excluir(id);
    }
}
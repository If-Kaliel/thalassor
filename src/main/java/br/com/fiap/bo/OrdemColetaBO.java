package br.com.fiap.bo;

import br.com.fiap.dao.OrdemColetaDAO;
import br.com.fiap.entities.OrdemColeta;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class OrdemColetaBO {

    private final OrdemColetaDAO ordemDAO = new OrdemColetaDAO();

    public List<OrdemColeta> listar() throws Exception {
        return ordemDAO.listarTodos();
    }

    public OrdemColeta buscar(Long id) throws Exception {

        OrdemColeta ordem = ordemDAO.buscarPorId(id);

        if (ordem == null) {
            throw new IllegalArgumentException(
                    "Ordem de coleta com ID " + id + " não encontrada."
            );
        }

        return ordem;
    }

    public void cadastrar(OrdemColeta ordem) throws Exception {

        if (ordem.getIdFoco() == null)
            throw new IllegalArgumentException("O foco é obrigatório.");

        if (ordem.getIdEmbarcacao() == null)
            throw new IllegalArgumentException("A embarcação é obrigatória.");

        if (ordem.getIdUsuario() == null)
            throw new IllegalArgumentException("O usuário é obrigatório.");

        ordemDAO.inserir(ordem);
    }

    public void atualizar(Long id, OrdemColeta ordem) throws Exception {

        buscar(id);

        ordem.setIdOrdem(id);

        ordemDAO.atualizar(ordem);
    }

    public void deletar(Long id) throws Exception {

        buscar(id);

        ordemDAO.excluir(id);
    }
}
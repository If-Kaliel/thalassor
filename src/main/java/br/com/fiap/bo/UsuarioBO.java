package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.entities.Usuario;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UsuarioBO {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public List<Usuario> listar() throws Exception {
        return usuarioDAO.listarTodos();
    }

    public Usuario buscar(Long id) throws Exception {

        Usuario usuario = usuarioDAO.buscarPorId(id);

        if (usuario == null) {
            throw new IllegalArgumentException(
                    "Usuário com ID " + id + " não encontrado."
            );
        }

        return usuario;
    }

    public void cadastrar(Usuario usuario) throws Exception {

        if (usuario.getNomeUsuario() == null ||
                usuario.getNomeUsuario().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "O nome do usuário é obrigatório."
            );
        }

        if (usuario.getEmail() == null ||
                usuario.getEmail().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "O e-mail é obrigatório."
            );
        }

        if (usuario.getSenha() == null ||
                usuario.getSenha().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "A senha é obrigatória."
            );
        }

        if (usuario.getPerfil() == null ||
                usuario.getPerfil().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "O perfil é obrigatório."
            );
        }

        usuarioDAO.inserir(usuario);
    }

    public void atualizar(Long id, Usuario usuario) throws Exception {

        buscar(id);

        usuario.setIdUsuario(id);

        usuarioDAO.atualizar(usuario);
    }

    public void deletar(Long id) throws Exception {

        buscar(id);

        usuarioDAO.excluir(id);
    }
}
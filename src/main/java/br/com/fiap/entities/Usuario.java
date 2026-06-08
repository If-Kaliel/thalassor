package br.com.fiap.entities;

import java.sql.Timestamp;

/**
 * POJO que representa a tabela TB_USUARIO.
 */
public class Usuario {

    private Long idUsuario;
    private String nomeUsuario;
    private String email;
    private String senha;
    private String perfil;
    private Timestamp dataCadastro;

    public Usuario() {
    }

    public Usuario(Long idUsuario, String nomeUsuario, String email,
                   String senha, String perfil, Timestamp dataCadastro) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
        this.dataCadastro = dataCadastro;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", email='" + email + '\'' +
                ", perfil='" + perfil + '\'' +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}
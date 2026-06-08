package br.com.fiap.entities;

import java.sql.Timestamp;

/**
 * POJO que representa a tabela TB_ORDEM_COLETA.
 */
public class OrdemColeta {

    private Long idOrdem;
    private Long idFoco;
    private Long idEmbarcacao;
    private Long idUsuario;
    private Timestamp dataAbertura;
    private Timestamp dataConclusao;
    private String statusOrdem;
    private String observacoes;

    public OrdemColeta() {
    }

    public OrdemColeta(Long idOrdem, Long idFoco, Long idEmbarcacao,
                       Long idUsuario, Timestamp dataAbertura,
                       Timestamp dataConclusao, String statusOrdem,
                       String observacoes) {
        this.idOrdem = idOrdem;
        this.idFoco = idFoco;
        this.idEmbarcacao = idEmbarcacao;
        this.idUsuario = idUsuario;
        this.dataAbertura = dataAbertura;
        this.dataConclusao = dataConclusao;
        this.statusOrdem = statusOrdem;
        this.observacoes = observacoes;
    }

    public Long getIdOrdem() {
        return idOrdem;
    }

    public void setIdOrdem(Long idOrdem) {
        this.idOrdem = idOrdem;
    }

    public Long getIdFoco() {
        return idFoco;
    }

    public void setIdFoco(Long idFoco) {
        this.idFoco = idFoco;
    }

    public Long getIdEmbarcacao() {
        return idEmbarcacao;
    }

    public void setIdEmbarcacao(Long idEmbarcacao) {
        this.idEmbarcacao = idEmbarcacao;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Timestamp getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Timestamp dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Timestamp getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Timestamp dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public String getStatusOrdem() {
        return statusOrdem;
    }

    public void setStatusOrdem(String statusOrdem) {
        this.statusOrdem = statusOrdem;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return "OrdemColeta{" +
                "idOrdem=" + idOrdem +
                ", idFoco=" + idFoco +
                ", idEmbarcacao=" + idEmbarcacao +
                ", idUsuario=" + idUsuario +
                ", dataAbertura=" + dataAbertura +
                ", dataConclusao=" + dataConclusao +
                ", statusOrdem='" + statusOrdem + '\'' +
                ", observacoes='" + observacoes + '\'' +
                '}';
    }
}
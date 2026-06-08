package br.com.fiap.entities;

import java.sql.Timestamp;

/**
 * POJO que representa a tabela TB_FOCO_POLUICAO.
 */
public class FocoPoluicao {

    private Long idFoco;
    private Long idRegiao;
    private Double latitude;
    private Double longitude;
    private Double extensaoKm2;
    private Double indiceFdi;
    private String urlImagem;
    private String statusFoco;
    private Timestamp dataDeteccao;
    private String nivelRisco;

    public FocoPoluicao() {
    }

    public FocoPoluicao(Long idFoco, Long idRegiao, Double latitude,
                        Double longitude, Double extensaoKm2,
                        Double indiceFdi, String urlImagem,
                        String statusFoco, Timestamp dataDeteccao,
                        String nivelRisco) {
        this.idFoco = idFoco;
        this.idRegiao = idRegiao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.extensaoKm2 = extensaoKm2;
        this.indiceFdi = indiceFdi;
        this.urlImagem = urlImagem;
        this.statusFoco = statusFoco;
        this.dataDeteccao = dataDeteccao;
        this.nivelRisco = nivelRisco;
    }

    public Long getIdFoco() {
        return idFoco;
    }

    public void setIdFoco(Long idFoco) {
        this.idFoco = idFoco;
    }

    public Long getIdRegiao() {
        return idRegiao;
    }

    public void setIdRegiao(Long idRegiao) {
        this.idRegiao = idRegiao;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getExtensaoKm2() {
        return extensaoKm2;
    }

    public void setExtensaoKm2(Double extensaoKm2) {
        this.extensaoKm2 = extensaoKm2;
    }

    public Double getIndiceFdi() {
        return indiceFdi;
    }

    public void setIndiceFdi(Double indiceFdi) {
        this.indiceFdi = indiceFdi;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getStatusFoco() {
        return statusFoco;
    }

    public void setStatusFoco(String statusFoco) {
        this.statusFoco = statusFoco;
    }

    public Timestamp getDataDeteccao() {
        return dataDeteccao;
    }

    public void setDataDeteccao(Timestamp dataDeteccao) {
        this.dataDeteccao = dataDeteccao;
    }

    public String getNivelRisco() {
        return nivelRisco;
    }

    public void setNivelRisco(String nivelRisco) {
        this.nivelRisco = nivelRisco;
    }

    @Override
    public String toString() {
        return "FocoPoluicao{" +
                "idFoco=" + idFoco +
                ", idRegiao=" + idRegiao +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", extensaoKm2=" + extensaoKm2 +
                ", indiceFdi=" + indiceFdi +
                ", urlImagem='" + urlImagem + '\'' +
                ", statusFoco='" + statusFoco + '\'' +
                ", dataDeteccao=" + dataDeteccao +
                ", nivelRisco='" + nivelRisco + '\'' +
                '}';
    }
}
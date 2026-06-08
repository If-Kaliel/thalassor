package br.com.fiap.entities;

import java.time.LocalDateTime;

public class PrevisaoIA {

    private Long idPrevisao;
    private Long idRegiao;
    private Double probExpansao;
    private Double areaPrevista;
    private String tipoRisco;
    private LocalDateTime dataPrevisao;

    // Construtor padrão (obrigatório para frameworks e inicializações limpas)
    public PrevisaoIA() {
    }

    // Construtor completo (utilizado principalmente pelo DAO ao recuperar do banco)
    public PrevisaoIA(Long idPrevisao, Long idRegiao, Double probExpansao, Double areaPrevista, String tipoRisco, LocalDateTime dataPrevisao) {
        this.idPrevisao = idPrevisao;
        this.idRegiao = idRegiao;
        this.probExpansao = probExpansao;
        this.areaPrevista = areaPrevista;
        this.tipoRisco = tipoRisco;
        this.dataPrevisao = dataPrevisao;
    }

    // Getters e Setters
    public Long getIdPrevisao() {
        return idPrevisao;
    }

    public void setIdPrevisao(Long idPrevisao) {
        this.idPrevisao = idPrevisao;
    }

    public Long getIdRegiao() {
        return idRegiao;
    }

    public void setIdRegiao(Long idRegiao) {
        this.idRegiao = idRegiao;
    }

    public Double getProbExpansao() {
        return probExpansao;
    }

    public void setProbExpansao(Double probExpansao) {
        this.probExpansao = probExpansao;
    }

    public Double getAreaPrevista() {
        return areaPrevista;
    }

    public void setAreaPrevista(Double areaPrevista) {
        this.areaPrevista = areaPrevista;
    }

    public String getTipoRisco() {
        return tipoRisco;
    }

    public void setTipoRisco(String tipoRisco) {
        this.tipoRisco = tipoRisco;
    }

    public LocalDateTime getDataPrevisao() {
        return dataPrevisao;
    }

    public void setDataPrevisao(LocalDateTime dataPrevisao) {
        this.dataPrevisao = dataPrevisao;
    }
}
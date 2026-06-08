package br.com.fiap.entities;

public class Embarcacao {

    private Long idEmbarcacao;
    private String nomeEmbarcacao;
    private Double capacidade; // Mapeia vl_capacidade_t
    private String status;     // Mapeia st_embarcacao

    // Construtor padrão obrigatório para o Jackson (JSON)
    public Embarcacao() {
    }

    public Embarcacao(Long idEmbarcacao, String nomeEmbarcacao, Double capacidade, String status) {
        this.idEmbarcacao = idEmbarcacao;
        this.nomeEmbarcacao = nomeEmbarcacao;
        this.capacidade = capacidade;
        this.status = status;
    }

    // Getters e Setters completos
    public Long getIdEmbarcacao() {
        return idEmbarcacao;
    }

    public void setIdEmbarcacao(Long idEmbarcacao) {
        this.idEmbarcacao = idEmbarcacao;
    }

    public String getNomeEmbarcacao() {
        return nomeEmbarcacao;
    }

    public void setNomeEmbarcacao(String nomeEmbarcacao) {
        this.nomeEmbarcacao = nomeEmbarcacao;
    }

    public Double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Double capacidade) {
        this.capacidade = capacidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Embarcacao{" +
                "idEmbarcacao=" + idEmbarcacao +
                ", nomeEmbarcacao='" + nomeEmbarcacao + '\'' +
                ", capacidade=" + capacidade +
                ", status='" + status + '\'' +
                '}';
    }
}
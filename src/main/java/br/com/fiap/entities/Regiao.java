package br.com.fiap.entities;

/**
 * POJO que representa a tabela TB_REGIAO do banco de dados.
 */
public class Regiao {

    private Long idRegiao;
    private String nomeRegiao;
    private String oceano;

    // Construtor padrão obrigatório para o Jackson (JSON)
    public Regiao() {
    }

    public Regiao(Long idRegiao, String nomeRegiao, String oceano) {
        this.idRegiao = idRegiao;
        this.nomeRegiao = nomeRegiao;
        this.oceano = oceano;
    }

    public Long getIdRegiao() {
        return idRegiao;
    }

    public void setIdRegiao(Long idRegiao) {
        this.idRegiao = idRegiao;
    }

    public String getNomeRegiao() {
        return nomeRegiao;
    }

    public void setNomeRegiao(String nomeRegiao) {
        this.nomeRegiao = nomeRegiao;
    }

    public String getOceano() {
        return oceano;
    }

    public void setOceano(String oceano) {
        this.oceano = oceano;
    }

    @Override
    public String toString() {
        return "Regiao{" +
                "idRegiao=" + idRegiao +
                ", nomeRegiao='" + nomeRegiao + '\'' +
                ", oceano='" + oceano + '\'' +
                '}';
    }
}
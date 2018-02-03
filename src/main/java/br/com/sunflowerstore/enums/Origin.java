package br.com.sunflowerstore.enums;

/**
 * Created by VictorJr on 08/04/2017.
 */
public enum Origin {

    NACIONAL("Nacional"),
    IMPORTADO("Importado");

    private String descricao;

    Origin(String descricao) {
        this.descricao=descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

package br.com.sunflowerstore.enums;

/**
 * Created by VictorJr on 08/04/2017.
 */
public enum Origin {

    NACIONAL("Nacional"),
    IMPORTADO("Importado");

    private String description;

    Origin(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

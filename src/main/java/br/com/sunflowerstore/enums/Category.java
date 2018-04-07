package br.com.sunflowerstore.enums;

/**
 * Created by VictorJr on 04/04/2017.
 */
public enum Category {

    BRINQUEDO("Brinquedo"),
    BIJUTERIA("Bijuteria"),
    FERRAMENTA("Ferramenta"),
    KERB("Kerb"),
    NATAL("Natal"),
    PASCOA("Páscoa"),
    CHAPELARIA("Chapelaria"),
    DECORAÇÃO("Decoração"),
    COZINHA("Cozinha"),
    BANHEIRO("Banheiro"),
    ELETRÔNICO("Eletrônico");

    private String description;

    Category(String description) {
        this.description = description;

    }

    public String getDescription() {
        return description;
    }

}

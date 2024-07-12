package br.com.alura.literalura10.model;

public enum Idioma {

    PT("pt", "Português"),
    EN("en", "Inglês"),
    ES("es", "Espanhol"),
    FR("fr", "Francês"),
    DE("de", "Alemão"),
    IT("it", "Italiano");


    private String idioma;

    private String idiomaPortugues;

    Idioma(String idioma, String idiomaPortugues) {
        this.idioma = idioma;
        this.idiomaPortugues = idiomaPortugues;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getIdiomaPortugues() {
        return idiomaPortugues;
    }

    public void setIdiomaPortugues(String idiomaPortugues) {
        this.idiomaPortugues = idiomaPortugues;
    }

    public static Idioma fromString(String text) {
        for (Idioma idiomas : Idioma.values()) {
            if (idiomas.idioma.equalsIgnoreCase(text)) {
                return idiomas;
            }
        }

        throw new IllegalArgumentException("Nenhum idioma encontrado para string fornecida " + text);
    }

    public static Idioma fromPortugues(String text) {
        for (Idioma idiomas : Idioma.values()) {
            if (idiomas.idiomaPortugues.equalsIgnoreCase(text)) {
                return idiomas;
            }
        }

        throw new IllegalArgumentException("Nenhum idioma encontrado para string fornecida " + text);
    }
}
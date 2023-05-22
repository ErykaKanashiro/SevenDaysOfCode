package model;

public class Filme {

    private final String titulo;
    private final String urlImage;
    private final String ano;
    private final String nota;

    public Filme(String titulo, String urlImage, String ano, String nota){
        this.titulo = titulo;
        this.urlImage = urlImage;
        this.ano = ano;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "titulo='" + titulo + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", ano='" + ano + '\'' +
                ", nota='" + nota + '\'' +
                '}';
    }
}

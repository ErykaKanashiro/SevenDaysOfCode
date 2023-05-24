import model.Filme;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DOMParserJson {

    static String[] parseJsonMovies(String responseJson){

        String[] movies = responseJson.split("\\},\\{");
        movies[0] = movies[0].substring(10);
        movies[movies.length - 1] = movies[movies.length - 1].split("\\}")[0];
        return movies;
    }

    static List<String> parseJsonAttributes(String[] movies, int pos){

        return Stream.of(movies)
                .map(e -> e.split("\",\"")[pos])
                .map(e -> e.split(":\"")[1])
                .map(e -> e.replaceAll("\"", ""))
                .collect(Collectors.toList());
    }

    private static List<String> parseTitulo(String[] movies) {
        return parseJsonAttributes(movies, 3);
    }

    private static List<String> parseUrlImages(String[] movies) {
        return parseJsonAttributes(movies, 5);
    }

    private static List<String> parseAno(String[] movies) {
        return parseJsonAttributes(movies, 4);
    }

    private static List<String> parseNota(String[] movies) {
        return parseJsonAttributes(movies, 7);
    }

    static List<Filme> parseFilmes(String responseJson){

        String[] movies = parseJsonMovies(responseJson);

        List<String> titulos = parseTitulo(movies);
        List<String> urlImages = parseUrlImages(movies);
        List<String> anos = parseAno(movies);
        List<String> notas = parseNota(movies);

        List<Filme> filmes = new ArrayList<>();

        for(int i = 0; i < movies.length; i++){
            filmes.add(new Filme(titulos.get(i), urlImages.get(i), anos.get(i), notas.get(i)));
        }
        return filmes;
    }
}

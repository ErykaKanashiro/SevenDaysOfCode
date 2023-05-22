import model.Filme;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws URISyntaxException {

        System.out.println("Requisição TOP 250 Filmes do IMDB");

        String apiKey = "XXXXXXXXXX";
        String uri = "https://imdb-api.com/en/API/Top250Movies/" + apiKey;

        String responseJson = "";

        String[] movies;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET()
                .build();

        try {

            HttpResponse<String> response = client.send
                    (httpRequest, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            String responseBody = response.body();

            System.out.println("Status: " + statusCode);
            System.out.println("Body: " + responseBody + "\n");

            responseJson = response.body();

        } catch (Exception e) {
            System.out.println("Erro ao fazer a requisição: " + e.getMessage());
        }

        movies = parseJsonMovies(responseJson);

        List<String> titulos = parseTitulo(movies);
        List<String> urlImages = parseUrlImages(movies);
        List<String> anos = parseAno(movies);
        List<String> notas = parseNota(movies);

        List<Filme> filmes = new ArrayList<>();

        for(int i = 0; i < movies.length; i++){
            filmes.add(new Filme(titulos.get(i), urlImages.get(i), anos.get(i), notas.get(i)));
        }

        filmes.forEach(System.out::println);
    }

    static String[] parseJsonMovies(String responseJson){

        String[] movies = responseJson.split("\\},\\{");
        movies[0] = movies[0].substring(10);
        movies[movies.length - 1] = movies[movies.length - 1].split("\\}")[0];
        return movies;
    }

    static List<String> parseJsonAtributos(String[] movies, int pos){

        return Stream.of(movies)
                .map(e -> e.split("\",\"")[pos])
                .map(e -> e.split(":\"")[1])
                .map(e -> e.replaceAll("\"", ""))
                .collect(Collectors.toList());
    }

    private static List<String> parseTitulo(String[] movies) {
        return parseJsonAtributos(movies, 3);
    }

    private static List<String> parseUrlImages(String[] movies) {
        return parseJsonAtributos(movies, 5);
    }

    private static List<String> parseAno(String[] movies) {
        return parseJsonAtributos(movies, 4);
    }

    private static List<String> parseNota(String[] movies) {
        return parseJsonAtributos(movies, 7);
    }
}
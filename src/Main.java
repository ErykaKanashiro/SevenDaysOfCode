import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws URISyntaxException {

        System.out.println("Requisição TOP 250 Filmes do IMDB");

        String apiKey = "XXXXXXXX";

        String responseJson = "";

        String[] movies;
        String[] atributos;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI("https://imdb-api.com/en/API/Top250Movies/" + apiKey))
                .GET()
                .build();

        try {

            HttpResponse<String> response = client.send
                    (httpRequest, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            String responseBody = response.body();

            System.out.println("Status: " + statusCode);
            System.out.println("Body: " + responseBody);

            responseJson = response.body();

        } catch (Exception e) {
            System.out.println("Erro ao fazer a requisição: " + e.getMessage());
        }

        movies = parseJsonMovies(responseJson);

        atributos = parseJsonAtributos(movies);

        List<String> titulos = parseTitulos(atributos);
        List<String> urlsImagens = parseUrlsImagens(atributos);

        System.out.println("Quantidade de títulos:" + titulos.size());
        System.out.println("Quantidade de urls:" + titulos.size());

        titulos.forEach(System.out::println);
        urlsImagens.forEach(System.out::println);
    }

    private static List<String> parseTitulos(String[] atributos) {
        List<String> titulos = new ArrayList<>();

        for(int i = 0; i < atributos.length; i++){
            String[] atributo = atributos[i].split(",");
            String titulo = atributo[2].substring(9);
            titulos.add(titulo);
        }

      return titulos;
    }

    private static List<String> parseUrlsImagens(String[] atributos) {

        List<String> urls = new ArrayList<>();

        for(int i = 0; i < atributos.length; i++){
            String[] atributo = atributos[i].split(",");
            String url = atributo[5].substring(9);
            urls.add(url);
        }

        return urls;
    }

    static String[] parseJsonMovies(String responseJson){

        String[] movies = responseJson.split("},");
        movies[0] = movies[0].substring(10);

        return movies;
    }

    static String[] parseJsonAtributos(String[] movies){

        String[] atributos = new String[250];

        for (int i = 0; i < movies.length; i++) {
            atributos[i] = Arrays.toString((movies[i].split(",")));
        }
         return atributos;
    }
}
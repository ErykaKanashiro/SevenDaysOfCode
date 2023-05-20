import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws URISyntaxException {

        System.out.println("Requisição TOP 250 Filmes do IMDB");

        String apiKey = "XXXXXXXX";

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

        } catch (Exception e) {

            System.out.println("Erro ao fazer a requisição: " + e.getMessage());

        }

    }
}
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class ClientIMDB {
    Properties properties = new Properties();
    FileInputStream file;

    {
        try {
            file = new FileInputStream("config.properties");
            properties.load(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    String uri = "https://imdb-api.com/en/API/Top250Movies/" + properties.getProperty("apiKey");

    HttpClient client = HttpClient.newHttpClient();

    public ClientIMDB() throws IOException {
    }

    public String getJson() throws URISyntaxException {

        String json = "";

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

            json = response.body();

        } catch (Exception e) {
            System.out.println("Erro ao fazer a requisição: " + e.getMessage());
        }

        return json;
    }
}

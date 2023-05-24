import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        ClientIMDB clientIMDB = new ClientIMDB();

        PrintWriter out = new PrintWriter("index.html");

        HTMLGenerator htmlGenerator = new HTMLGenerator(out);
        htmlGenerator.generateHTML(DOMParserJson.parseFilmes(clientIMDB.getJson()));

        out.close();
    }
}
import model.Filme;

import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator {

    PrintWriter out;

    public HTMLGenerator(PrintWriter out) {
        this.out = out;
    }
    String head =
            """
            <head>
                <meta charset="utf-8">
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"\s
                    + "integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">				\t
            </head>
            """;
    String divTemplate =
            """
            <div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
                <h4 class="card-header">%s</h4>
                <div class="card-body">
                    <img class="card-img" src="%s" alt="%s">
                    <p class="card-text mt-2">Nota: %s - Ano: %s</p>
                </div>
            </div>
            """;

    public void generateHTML(List<Filme> filmes) {

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"pt-br\">");
        out.println(head);
        out.println("<body>");
        out.println("<div class=\"container\">");
        out.println("<div class=\"row\">");

        for (Filme filme : filmes) {
            out.println(String.format(divTemplate, filme.getTitulo(), filme.getUrlImage(), filme.getTitulo(), filme.getNota(), filme.getAno()));
        }
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}


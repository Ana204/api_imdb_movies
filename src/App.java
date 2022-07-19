import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
    
        //fazer uma conexão http - buscar os top 250 filmes
        String url = "https://alura-imdb-api.herokuapp.com/movies";

        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //pegar somente os dados que nos interresa - Title, image, classificação
        var jsonParser = new JsonParser();

        List<Map<String, String>> listMovie = jsonParser.parse(body);

        //extrair e manipular os dados
        for (Map<String, String> filmes : listMovie){
            System.out.println("Titulo: " + filmes.get("title"));
            System.out.println("Poster: " + filmes.get("image"));
            System.out.println("\u001b[37m \u001b[44m Classificação: \u001b[m"  + filmes.get("imDbRating"));
            System.out.println("⭐⭐⭐⭐⭐⭐");

        }

    }
}

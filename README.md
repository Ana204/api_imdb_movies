<h1 align="center">
    <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.net.http/java/net/http/HttpRequest.html">🔗 HttpRequest</a>
</h1>

<h1 align="center">
     <img src="https://img.shields.io/static/v1?label=JAVA&message=imdb-API&color=7159c1&style=for-the-badge&logo=ghost"/>
  </h1>
  
<p align="center"> ☕	An HTTP request</p>


### Prerequisites

Before starting, you will need to have the following tools installed on your machine:
[JVM](https://www.oracle.com/java/technologies/downloads/).
Besides, it's good to have an editor to work with the code like [VSCode](https://code.visualstudio.com/)


```bash
# clone this right
$ git clone <https://github.com/Ana204/api_imdb_movies.git>

# run the application
$ In option 'Run Java'
```

### 🛠 Technology

The following tool was used in building the project:

- [JAVA](https://www.java.com/pt-BR/)


```bash
# if you don't want to clone
 //connection https -  fetch the top 250 movies
        String url = "https://alura-imdb-api.herokuapp.com/movies";

        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //get only the data we are interested in - Title, image, classification
        var jsonParser = new JsonParser();

        List<Map<String, String>> listMovie = jsonParser.parse(body);

        //extract and manipulate the data
        for (Map<String, String> filmes : listMovie){
            System.out.println("Titulo: " + filmes.get("title"));
            System.out.println("Poster: " + filmes.get("image"));
            System.out.println("\u001b[37m \u001b[44m Classificação: \u001b[m"  + filmes.get("imDbRating"));
            System.out.println("⭐⭐⭐⭐⭐⭐");

        }

    }

# you will also need to create a class called jsonParser

    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");
    
    public List<Map<String, String>> parse(String json){
        
        Matcher matcher = REGEX_ITEMS.matcher(json);
        if (!matcher.find()) {

            throw new IllegalArgumentException("Não encontrou items.");
        }

        String[] items = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> dados = new ArrayList<>();

        for (String item : items) {

            Map<String, String> atributosItem = new HashMap<>();

            Matcher matcherAtributosJson = REGEX_ATRIBUTOS_JSON.matcher(item);
            while (matcherAtributosJson.find()) {
                String atributo = matcherAtributosJson.group(1);
                String valor = matcherAtributosJson.group(2);
                atributosItem.put(atributo, valor);
            }

            dados.add(atributosItem);
        }

        return dados;

    }
```

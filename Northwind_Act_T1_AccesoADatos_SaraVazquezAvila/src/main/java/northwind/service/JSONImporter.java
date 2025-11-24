package northwind.service;

import northwind.model.Product;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class JSONImporter {

    public static List<Product> fetchProducts() {
        List<Product> list = new ArrayList<>();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(new URI("https://dummyjson.com/products"))
                    .GET()
                    .build();

            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            JsonObject root = gson.fromJson(res.body(), JsonObject.class);
            JsonArray products = root.getAsJsonArray("products");

            for (int i = 0; i < products.size(); i++) {
                JsonObject p = products.get(i).getAsJsonObject();

                Product prod = new Product();
                prod.setId(p.get("id").getAsInt());
                prod.setTitle(p.get("title").getAsString());
                prod.setDescription(p.get("description").getAsString());
                prod.setStock(p.get("stock").getAsInt());
                prod.setPrice(p.get("price").getAsDouble());

                list.add(prod);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}

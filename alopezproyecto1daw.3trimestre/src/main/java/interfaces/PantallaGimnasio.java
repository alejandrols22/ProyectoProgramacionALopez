package interfaces;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/gimnasios")
public class PantallaGimnasio {

    @GET
    @Path("/cercanos")
    @Produces(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response obtenerGimnasiosCercanos() {
        try {
            // Llamar al servicio de Nominatim para obtener los gimnasios cercanos
            List<String> gimnasiosCercanos = obtenerGimnasiosCercanosDesdeNominatim("Málaga", "Spain");

            // Devolver la lista de gimnasios cercanos como respuesta JSON
            return javax.ws.rs.core.Response.ok(gimnasiosCercanos).build();
        } catch (Exception e) {
            return javax.ws.rs.core.Response.serverError().build();
        }
    }

    private List<String> obtenerGimnasiosCercanosDesdeNominatim(String ciudad, String pais) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://nominatim.openstreetmap.org/search?city=" + ciudad + "&country=" + pais + "&format=json&limit=50&q=gym")
                .build();

        try (Response response = client.newCall(request).execute()) {
            // Aquí puedes parsear la respuesta JSON para obtener los nombres de los gimnasios
            String jsonData = response.body().string();
            return parseGimnasios(jsonData);
        }
    }

    private List<String> parseGimnasios(String jsonData) {
        List<String> gimnasios = new ArrayList<>();

        JsonArray jsonArray = JsonParser.parseString(jsonData).getAsJsonArray();

        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String nombre = jsonObject.get("display_name").getAsString();
            gimnasios.add(nombre);
        }

        return gimnasios;
    }
}
package interfaces;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/gimnasios")
public class PantallaGimnasio {

    @GET
    @Path("/cercanos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerGimnasiosCercanos() {
        try {
            // Obtener la ubicación actual del usuario
            String latitud = "37.7749"; // Latitud de ejemplo
            String longitud = "-122.4194"; // Longitud de ejemplo

            // Llamar al servicio de Google Places para obtener los gimnasios cercanos
            List<String> gimnasiosCercanos = obtenerGimnasiosCercanosDesdeGooglePlaces(latitud, longitud);

            // Devolver la lista de gimnasios cercanos como respuesta JSON
            return Response.ok(gimnasiosCercanos).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    private List<String> obtenerGimnasiosCercanosDesdeGooglePlaces(String latitud, String longitud) throws IOException {
        // Aquí se realizaría la llamada a la API de Google Places para obtener los gimnasios cercanos
        // Utiliza las coordenadas de latitud y longitud para filtrar los resultados
        // Aquí se devuelve una lista de nombres de gimnasios como ejemplo
        List<String> gimnasiosCercanos = new ArrayList<>();
        gimnasiosCercanos.add("Gimnasio 1");
        gimnasiosCercanos.add("Gimnasio 2");
        gimnasiosCercanos.add("Gimnasio 3");
        return gimnasiosCercanos;
    }
}
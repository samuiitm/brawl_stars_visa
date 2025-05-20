package Controlador;

import com.google.gson.*;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class ExistenBrawlers {
    static final String API_URL = "https://api.brawlstars.com/v1/brawlers";
    static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjI1ZTMxMzAxLWZlNjAtNGZiMy1iNzM1LTU1Yjk3YjhlMjgyYSIsImlhdCI6MTc0NzY0NzYwNSwic3ViIjoiZGV2ZWxvcGVyL2QwYTE0NWI4LTZlOTUtMDBlMS05ZmYyLTFkZDM3OTU5ZTQ5MyIsInNjb3BlcyI6WyJicmF3bHN0YXJzIl0sImxpbWl0cyI6W3sidGllciI6ImRldmVsb3Blci9zaWx2ZXIiLCJ0eXBlIjoidGhyb3R0bGluZyJ9LHsiY2lkcnMiOlsiODUuNTAuMTYwLjQzIl0sInR5cGUiOiJjbGllbnQifV19.8UkJRM2dtbKVhwPpk3gv2kgm0_j-UhAqqK5E5T4wGQT5BP8J5mIc9qarZUbasEJ7C5z41n1FGSpAtz_W1jooNA";

    public static void existenBrawlers() {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = Model.ConnexioBD.getConnexio()) {

            // 1. Obtener lista de brawlers desde la API oficial con token
            HttpURLConnection con = (HttpURLConnection) new URL(API_URL).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + TOKEN);

            JsonArray brawlers = JsonParser.parseReader(new InputStreamReader(con.getInputStream()))
                    .getAsJsonObject().getAsJsonArray("items");

            // 2. Mostrar los primeros 15
            List<Integer> idsMostrados = new ArrayList<>();
            System.out.println("Lista de Brawlers:");
            for (int i = 0; i < 15 && i < brawlers.size(); i++) {
                JsonObject brawler = brawlers.get(i).getAsJsonObject();
                int id = brawler.get("id").getAsInt();
                String name = brawler.get("name").getAsString();
                System.out.printf("[%d] %s\n", id, name);
                idsMostrados.add(id);
            }

            // 3. Preguntar al usuario
            System.out.print("¿Qué ID quieres comprobar si existe? ");
            int idInput = sc.nextInt();

            // 4. Consultar en la base de datos
            String sql = "SELECT COUNT(*) FROM brawlers WHERE id_brawler = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, idInput);
                ResultSet rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("El ID " + idInput + " SÍ existe en la base de datos.");
                } else {
                    System.out.println("El ID " + idInput + " NO existe en la base de datos.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
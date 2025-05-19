package Controlador;

import com.google.gson.*;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class ExistenBrawlers {
    static final String API_URL = "https://api.brawlify.com/v1/brawlers";

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);
             Connection conn = Model.ConnexioBD.getConnexio()) {

            // 1. Obtener lista de brawlers
            HttpURLConnection con = (HttpURLConnection) new URL(API_URL).openConnection();
            con.setRequestMethod("GET");
            JsonArray brawlers = JsonParser.parseReader(new InputStreamReader(con.getInputStream()))
                    .getAsJsonObject().getAsJsonArray("list");

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
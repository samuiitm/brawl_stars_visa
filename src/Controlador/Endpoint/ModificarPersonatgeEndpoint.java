package Controlador.Endpoint;

import Model.ConnexioBD;
import com.google.gson.*;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.Scanner;

public class ModificarPersonatgeEndpoint {
    public static void modificarPersonatge() {
        try (Connection conn = ConnexioBD.getConnexio();
             Scanner sc = new Scanner(System.in)) {

            while (true) {
                System.out.println("====== LLISTA DE BRAWLERS ======");
                String query = "SELECT id_brawler, nom FROM brawlers ORDER BY id";
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(query)) {

                    while (rs.next()) {
                        System.out.printf("[%d] %s\n", rs.getInt("id"), rs.getString("name"));
                    }
                }

                System.out.print("\nIntrodueix l'ID del Brawler per veure info des del endpoint (0 per sortir): ");
                int idInput = sc.nextInt();

                if (idInput == 0) {
                    System.out.println("Sortint...");
                    break;
                }

                mostrarBrawlerDelEndpoint(idInput);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mostrarBrawlerDelEndpoint(int brawlerId) {
        try {
            String urlStr = "https://api.brawlify.com/v1/brawlers";
            HttpURLConnection con = (HttpURLConnection) new URL(urlStr).openConnection();
            con.setRequestMethod("GET");

            JsonArray brawlersArray = JsonParser.parseReader(new InputStreamReader(con.getInputStream()))
                    .getAsJsonObject().getAsJsonArray("list");

            for (JsonElement el : brawlersArray) {
                JsonObject brawler = el.getAsJsonObject();
                int id = brawler.get("id").getAsInt();

                if (id == brawlerId) {
                    System.out.println("\n===== DADES DEL BRAWLER =====");
                    System.out.println("Nom: " + brawler.get("name").getAsString());
                    System.out.println("ID: " + id);
                    System.out.println("Raretat: " + brawler.get("rarity").getAsString());
                    System.out.println("Descripció: " + brawler.get("description").getAsString());
                    return;
                }
            }

            System.out.println("⚠️ No s'ha trobat cap brawler amb l'ID " + brawlerId + " a l'endpoint.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


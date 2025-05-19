package Controlador.Endpoint;

import Model.ConnexioBD;
import Vista.Vista;
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
                        Vista.mostrarMissatgeFormat("[%d] %s\n", rs.getInt("id"), rs.getString("name"));
                    }
                }

                Vista.mostrarMissatge("\nIntrodueix l'ID del Brawler per veure info des del endpoint (0 per sortir): ");
                int idInput = sc.nextInt();

                if (idInput == 0) {
                    Vista.mostrarMissatge("Sortint...");
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
                    Vista.mostrarMissatge("\n===== DADES DEL BRAWLER =====");
                    Vista.mostrarMissatge("Nom: " + brawler.get("name").getAsString());
                    Vista.mostrarMissatge("ID: " + id);
                    Vista.mostrarMissatge("Raretat: " + brawler.get("rarity").getAsString());
                    Vista.mostrarMissatge("Descripció: " + brawler.get("description").getAsString());
                    return;
                }
            }

            Vista.mostrarMissatge("No s'ha trobat cap brawler amb l'ID " + brawlerId + " a l'endpoint.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


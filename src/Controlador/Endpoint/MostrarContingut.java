package Controlador.Endpoint;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MostrarContingut {

    private static final String API_URL = "https://api.brawlapi.com/v1/brawlers";

    public static void main(String[] args) {
        try {
            // Conexión HTTP GET
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Leer respuesta
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseContent = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                responseContent.append(line);
            }
            in.close();

            // Parsear JSON con Gson
            Gson gson = new Gson();
            JsonObject root = JsonParser.parseString(responseContent.toString()).getAsJsonObject();
            JsonArray brawlers = root.getAsJsonArray("list");

            // Iterar sobre cada Brawler
            for (JsonElement element : brawlers) {
                JsonObject brawler = element.getAsJsonObject();

                String name = brawler.get("name").getAsString();
                String rarity = brawler.getAsJsonObject("rarity").get("name").getAsString();
                String className = brawler.getAsJsonObject("class").get("name").getAsString();

                System.out.println("\uD83D\uDE80\u200B Brawler: " + name);
                System.out.println("    \uD83C\uDFB2\u200B Rareza: " + rarity);
                System.out.println("    \uD83C\uDFAD\u200B Clase: " + className);

                // Gadgets
                System.out.println("  🛠️ Gadgets:");
                JsonArray gadgets = brawler.getAsJsonArray("gadgets");
                if (gadgets.isEmpty()) {
                    System.out.println("    (No tiene gadgets)");
                } else {
                    for (JsonElement g : gadgets) {
                        JsonObject gadget = g.getAsJsonObject();
                        System.out.println("        - " + gadget.get("name").getAsString());
                    }
                }

                // Star Powers
                System.out.println("  🌟 Star Powers:");
                JsonArray starPowers = brawler.getAsJsonArray("starPowers");
                if (starPowers.isEmpty()) {
                    System.out.println("    (No tiene star powers)");
                } else {
                    for (JsonElement s : starPowers) {
                        JsonObject starPower = s.getAsJsonObject();
                        System.out.println("        - " + starPower.get("name").getAsString());
                    }
                }

                System.out.println(); // Espaciado entre brawlers
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

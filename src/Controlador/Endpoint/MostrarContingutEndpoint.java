package Controlador.Endpoint;

import Vista.Vista;
import com.google.gson.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MostrarContingutEndpoint {
    final static String API_URL = "https://api.brawlify.com/v1/brawlers";

    public static void mostrarContingut() {
        try {
            // Connexió HTTP GET
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Llegir resposta
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseContent = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                responseContent.append(line);
            }
            in.close();

            // Parsejar JSON amb Gson
            Gson gson = new Gson();
            JsonObject root = JsonParser.parseString(responseContent.toString()).getAsJsonObject();
            JsonArray brawlers = root.getAsJsonArray("list");

            // Iterar sobre cada Brawler
            for (JsonElement element : brawlers) {
                JsonObject brawler = element.getAsJsonObject();

                String name = brawler.get("name").getAsString();
                String rarity = brawler.getAsJsonObject("rarity").get("name").getAsString();
                String className = brawler.getAsJsonObject("class").get("name").getAsString();

                Vista.mostrarMissatge("\uD83D\uDE80\u200B Brawler: " + name);
                Vista.mostrarMissatge("    \uD83C\uDFB2\u200B Rareza: " + rarity);
                Vista.mostrarMissatge("    \uD83C\uDFAD\u200B Clase: " + className);

                // Gadgets
                Vista.mostrarMissatge("  🛠️ Gadgets:");
                JsonArray gadgets = brawler.getAsJsonArray("gadgets");
                if (gadgets.isEmpty()) {
                    Vista.mostrarMissatge("    (No tiene gadgets)");
                } else {
                    for (JsonElement g : gadgets) {
                        JsonObject gadget = g.getAsJsonObject();
                        Vista.mostrarMissatge("        - " + gadget.get("name").getAsString());
                    }
                }

                // Star Powers
                Vista.mostrarMissatge("  🌟 Star Powers:");
                JsonArray starPowers = brawler.getAsJsonArray("starPowers");
                if (starPowers.isEmpty()) {
                    Vista.mostrarMissatge("    (No tiene star powers)");
                } else {
                    for (JsonElement s : starPowers) {
                        JsonObject starPower = s.getAsJsonObject();
                        Vista.mostrarMissatge("        - " + starPower.get("name").getAsString());
                    }
                }

                Vista.mostrarMissatge(""); // Espaciado entre brawlers
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
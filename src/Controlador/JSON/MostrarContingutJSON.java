package Controlador.JSON;

import Vista.Vista;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;

public class MostrarContingutJSON {
    public static void mostrarContingut() {
        try {
            FileReader reader = new FileReader("src/resources/brawler.json");
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray brawlers = root.getAsJsonArray("list");

            for (JsonElement element : brawlers) {
                JsonObject brawler = element.getAsJsonObject();

                String name = brawler.get("name").getAsString();
                String rarity = brawler.getAsJsonObject("rarity").get("name").getAsString();
                String className = brawler.getAsJsonObject("class").get("name").getAsString();

                Vista.mostrarMissatge("🚀​ Brawler: " + name);
                Vista.mostrarMissatge("    🎲​ Rareza: " + rarity);
                Vista.mostrarMissatge("    🎭​ Clase: " + className);

                Vista.mostrarMissatge("  🛠️ Gadgets:");
                JsonArray gadgets = brawler.getAsJsonArray("gadgets");
                if (gadgets == null || gadgets.isEmpty()) {
                    Vista.mostrarMissatge("    (No tiene gadgets)");
                } else {
                    for (JsonElement g : gadgets) {
                        JsonObject gadget = g.getAsJsonObject();
                        Vista.mostrarMissatge("        - " + gadget.get("name").getAsString());
                    }
                }

                Vista.mostrarMissatge("  🌟 Star Powers:");
                JsonArray starPowers = brawler.getAsJsonArray("starPowers");
                if (starPowers == null || starPowers.isEmpty()) {
                    Vista.mostrarMissatge("    (No tiene star powers)");
                } else {
                    for (JsonElement s : starPowers) {
                        JsonObject starPower = s.getAsJsonObject();
                        Vista.mostrarMissatge("        - " + starPower.get("name").getAsString());
                    }
                }

                Vista.mostrarMissatge("");  // Espacio entre brawlers
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
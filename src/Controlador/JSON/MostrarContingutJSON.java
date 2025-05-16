package Controlador.JSON;

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

                System.out.println("🚀​ Brawler: " + name);
                System.out.println("    🎲​ Rareza: " + rarity);
                System.out.println("    🎭​ Clase: " + className);

                System.out.println("  🛠️ Gadgets:");
                JsonArray gadgets = brawler.getAsJsonArray("gadgets");
                if (gadgets == null || gadgets.isEmpty()) {
                    System.out.println("    (No tiene gadgets)");
                } else {
                    for (JsonElement g : gadgets) {
                        JsonObject gadget = g.getAsJsonObject();
                        System.out.println("        - " + gadget.get("name").getAsString());
                    }
                }

                System.out.println("  🌟 Star Powers:");
                JsonArray starPowers = brawler.getAsJsonArray("starPowers");
                if (starPowers == null || starPowers.isEmpty()) {
                    System.out.println("    (No tiene star powers)");
                } else {
                    for (JsonElement s : starPowers) {
                        JsonObject starPower = s.getAsJsonObject();
                        System.out.println("        - " + starPower.get("name").getAsString());
                    }
                }

                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
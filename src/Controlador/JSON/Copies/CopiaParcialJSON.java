package Controlador.JSON.Copies;

import Model.Constructors.*;
import Model.Constructors.Class;
import Model.DAO.*;
import Vista.Vista;

import com.google.gson.*;
import java.io.*;
import java.io.InputStreamReader;
import java.util.*;

public class CopiaParcialJSON {
    public static void copiaParcial() {
        try {
            BrawlerDAO brawlerDAO = new BrawlerDAO();
            ClassDAO classDAO = new ClassDAO();
            GadgetDAO gadgetDAO = new GadgetDAO();
            RarityDAO rarityDAO = new RarityDAO();
            StarpowerDAO starpowerDAO = new StarpowerDAO();

            List<Brawler> brawlersExistents = brawlerDAO.llistar();
            Set<Integer> idExistents = new HashSet<>();
            for (Brawler b : brawlersExistents) {
                idExistents.add(b.getId());
            }

            InputStream is = CopiaParcialJSON.class.getResourceAsStream("/resources/brawler.json");
            if (is == null) throw new FileNotFoundException("Fitxer JSON no trobat.");
            InputStreamReader reader = new InputStreamReader(is);
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray brawlers = root.getAsJsonArray("list");

            int nousAfegits = 0;

            for (JsonElement element : brawlers) {
                JsonObject brawlerJson = element.getAsJsonObject();
                int brawlerId = brawlerJson.get("id").getAsInt();

                if (idExistents.contains(brawlerId)) continue;

                String name = brawlerJson.get("name").getAsString();
                String description = brawlerJson.get("description").getAsString();

                JsonObject classJson = brawlerJson.getAsJsonObject("class");
                int classId = classJson.get("id").getAsInt();
                String className = classJson.get("name").getAsString();

                JsonObject rarityJson = brawlerJson.getAsJsonObject("rarity");
                int rarityId = rarityJson.get("id").getAsInt();
                String rarityName = rarityJson.get("name").getAsString();

                try { classDAO.inserir(new Class(classId, className)); }
                catch (Exception e) { System.err.println("⚠️ Error classe: " + e.getMessage()); }

                try { rarityDAO.inserir(new Rarity(rarityId, rarityName)); }
                catch (Exception e) { System.err.println("⚠️ Error raretat: " + e.getMessage()); }

                brawlerDAO.inserir(new Brawler(brawlerId, name, description, rarityId, classId));

                JsonArray gadgets = brawlerJson.getAsJsonArray("gadgets");
                for (JsonElement g : gadgets) {
                    JsonObject gadgetJson = g.getAsJsonObject();
                    int gadgetId = gadgetJson.get("id").getAsInt();
                    String gadgetName = gadgetJson.get("name").getAsString();
                    String gadgetDesc = gadgetJson.get("description").getAsString();
                    gadgetDAO.inserir(new Gadget(gadgetId, gadgetName, gadgetDesc, brawlerId));
                }

                JsonArray starPowers = brawlerJson.getAsJsonArray("starPowers");
                for (JsonElement s : starPowers) {
                    JsonObject starJson = s.getAsJsonObject();
                    int starId = starJson.get("id").getAsInt();
                    String starName = starJson.get("name").getAsString();
                    String starDesc = starJson.get("description").getAsString();
                    starpowerDAO.inserir(new Starpower(starId, starName, starDesc, brawlerId));
                }

                nousAfegits++;
            }

            Vista.mostrarMissatge("✅ Inserció parcial completada. Nous brawlers afegits: " + nousAfegits);
        } catch (Exception e) {
            e.printStackTrace();
            Vista.mostrarMissatge("❌ Error durant la inserció parcial: " + e.getMessage());
        }
    }
}

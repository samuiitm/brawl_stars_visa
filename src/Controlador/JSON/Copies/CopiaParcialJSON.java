package Controlador.JSON.Copies;

import Model.Constructors.*;
import Model.Constructors.Class;
import Model.DAO.*;
import Vista.Vista;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CopiaParcialJSON {
    public static void copiaParcial() {
        try {
            // DAOs inicialitzats
            BrawlerDAO brawlerDAO = new BrawlerDAO();
            ClassDAO classDAO = new ClassDAO();
            GadgetDAO gadgetDAO = new GadgetDAO();
            RarityDAO rarityDAO = new RarityDAO();
            StarpowerDAO starpowerDAO = new StarpowerDAO();

            // Llistar IDs existents
            List<Brawler> brawlersExistents = brawlerDAO.llistar();
            Set<Integer> idExistents = new HashSet<>();

            for (Brawler b : brawlersExistents) {
                idExistents.add(b.getId());
            }

            // Llegir JSON
            FileReader reader = new FileReader("src/resources/brawler.json");
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray brawlers = root.getAsJsonArray("list");

            int nousAfegits = 0;

            for (JsonElement element : brawlers) {
                JsonObject brawlerJson = element.getAsJsonObject();

                int brawlerId = brawlerJson.get("id").getAsInt();
                if (idExistents.contains(brawlerId)) {
                    continue;
                }

                String name = brawlerJson.get("name").getAsString();
                String description = brawlerJson.get("description").getAsString();

                JsonObject classJson = brawlerJson.getAsJsonObject("class");
                int classId = classJson.get("id").getAsInt();
                String className = classJson.get("name").getAsString();

                JsonObject rarityJson = brawlerJson.getAsJsonObject("rarity");
                int rarityId = rarityJson.get("id").getAsInt();
                String rarityName = rarityJson.get("name").getAsString();

                // Inserir classe i raretat
                try { classDAO.inserir(new Class(classId, className)); } catch (Exception ignored) {}
                try { rarityDAO.inserir(new Rarity(rarityId, rarityName)); } catch (Exception ignored) {}

                // Inserir brawler
                brawlerDAO.inserir(new Brawler(brawlerId, name, description, rarityId, classId));

                // Inserir gadgets
                JsonArray gadgets = brawlerJson.getAsJsonArray("gadgets");
                for (JsonElement g : gadgets) {
                    JsonObject gadgetJson = g.getAsJsonObject();
                    int gadgetId = gadgetJson.get("id").getAsInt();
                    String gadgetName = gadgetJson.get("name").getAsString();
                    String gadgetDesc = gadgetJson.get("description").getAsString();

                    gadgetDAO.inserir(new Gadget(gadgetId, gadgetName, gadgetDesc, brawlerId));
                }

                // Inserir starpowers
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
            Vista.mostrarMissatge("❌ Error durant la inserció parcial.");
        }
    }
}

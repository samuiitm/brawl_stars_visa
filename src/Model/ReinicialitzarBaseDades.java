package Model;

import Model.Constructors.*;
import Model.Constructors.Class;
import Model.DAO.*;
import com.google.gson.*;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class ReinicialitzarBaseDades {
    public static void reinicialitzarBaseDades() {
        try {
            FileReader reader = new FileReader("src/resources/brawler.json");
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray brawlers = root.getAsJsonArray("list");

            BrawlerDAO brawlerDAO = new BrawlerDAO();
            ClassDAO classeDAO = new ClassDAO();
            RarityDAO rarityDAO = new RarityDAO();
            GadgetDAO gadgetDAO = new GadgetDAO();
            StarpowerDAO starPowerDAO = new StarpowerDAO();

            Connection conn = ConnexioBD.getConnexio();
            Statement stmt = conn.createStatement();

            stmt.execute("SET FOREIGN_KEY_CHECKS = 0");

            stmt.executeUpdate("DELETE FROM gadgets");
            stmt.executeUpdate("DELETE FROM starpowers");
            stmt.executeUpdate("DELETE FROM brawlers");
            stmt.executeUpdate("DELETE FROM classes");
            stmt.executeUpdate("DELETE FROM rarities");

            stmt.execute("SET FOREIGN_KEY_CHECKS = 1");

            Set<Integer> insertedClasses = new HashSet<>();
            Set<Integer> insertedRarities = new HashSet<>();

            System.out.println("Reinicialitzant la base de dades...");
            for (JsonElement element : brawlers) {
                JsonObject brawlerJson = element.getAsJsonObject();

                int brawlerId = brawlerJson.get("id").getAsInt();
                String name = brawlerJson.get("name").getAsString();
                String description = brawlerJson.get("description").getAsString();

                JsonObject classJson = brawlerJson.getAsJsonObject("class");
                int classId = classJson.get("id").getAsInt();
                String className = classJson.get("name").getAsString();

                JsonObject rarityJson = brawlerJson.getAsJsonObject("rarity");
                int rarityId = rarityJson.get("id").getAsInt();
                String rarityName = rarityJson.get("name").getAsString();

                if (!insertedClasses.contains(classId)) {
                    classeDAO.inserir(new Class(classId, className));
                    insertedClasses.add(classId);
                }

                if (!insertedRarities.contains(rarityId)) {
                    rarityDAO.inserir(new Rarity(rarityId, rarityName));
                    insertedRarities.add(rarityId);
                }

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

                    starPowerDAO.inserir(new Starpower(starId, starName, starDesc, brawlerId));
                }
            }

            System.out.println("Reinicialització completada.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
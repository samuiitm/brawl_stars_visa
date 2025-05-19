package Controlador.JSON.Copies;

import Model.Constructors.*;
import Model.Constructors.Class;
import Model.DAO.*;
import Model.ConnexioBD;
import Vista.Vista;
import com.google.gson.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

public class CopiaTotalJSON {
    public static void copiaTotal() {
        try {
            // DAOs inicialitzats
            BrawlerDAO brawlerDAO = new BrawlerDAO();
            ClassDAO classDAO = new ClassDAO();
            RarityDAO rarityDAO = new RarityDAO();
            GadgetDAO gadgetDAO = new GadgetDAO();
            StarpowerDAO starPowerDAO = new StarpowerDAO();

            // Connexió amb la base de dades
            Connection conn = ConnexioBD.getConnexio();
            Statement stmt = conn.createStatement();

            // Desactivar foreign keys y netejar la base de dades
            stmt.execute("SET FOREIGN_KEY_CHECKS = 0");
            stmt.executeUpdate("DELETE FROM gadgets");
            stmt.executeUpdate("DELETE FROM starpowers");
            stmt.executeUpdate("DELETE FROM brawlers");
            stmt.executeUpdate("DELETE FROM classes");
            stmt.executeUpdate("DELETE FROM rarities");
            stmt.execute("SET FOREIGN_KEY_CHECKS = 1");

            // Llegir el JSON
            FileReader reader = new FileReader("src/resources/brawler.json");
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray brawlers = root.getAsJsonArray("list");

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

                // Inseir classe, raretat y brawler
                classDAO.inserir(new Class(classId, className));
                rarityDAO.inserir(new Rarity(rarityId, rarityName));
                brawlerDAO.inserir(new Brawler(brawlerId, name, description, rarityId, classId));

                // Inserir gadget
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

                    starPowerDAO.inserir(new Starpower(starId, starName, starDesc, brawlerId));
                }
            }

            Vista.mostrarMissatge("✅ Còpia total completada correctament. Totes les dades han estat reinicialitzades.");
        } catch (Exception e) {
            e.printStackTrace();
            Vista.mostrarMissatge("❌ Error durant la còpia total.");
        }
    }
}
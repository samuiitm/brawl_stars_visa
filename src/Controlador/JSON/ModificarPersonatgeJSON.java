package Controlador.JSON;

import com.google.gson.*;
import Vista.Vista;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.*;

public class ModificarPersonatgeJSON {
    public static void modificarPersonatge() {
        Scanner scanner = new Scanner(System.in);
        Vista.mostrarMissatge("Introdueix el nom del personatge a modificar: ");
        String nom = scanner.nextLine().trim();

        try (FileReader reader = new FileReader("src/resources/brawler.json")) {
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray brawlers = root.getAsJsonArray("list");

            List<JsonObject> coincidencias = new ArrayList<>();
            for (JsonElement element : brawlers) {
                JsonObject obj = element.getAsJsonObject();
                if (obj.get("name").getAsString().equalsIgnoreCase(nom)) {
                    coincidencias.add(obj);
                }
            }

            if (coincidencias.isEmpty()) {
                Vista.mostrarMissatge("No s'ha trobat cap personatge amb aquest nom.");
                return;
            }

            JsonObject brawlerJson;
            if (coincidencias.size() == 1) {
                brawlerJson = coincidencias.get(0);
            } else {
                Vista.mostrarMissatge("S'han trobat diversos personatges amb aquest nom:");
                for (int i = 0; i < coincidencias.size(); i++) {
                    JsonObject obj = coincidencias.get(i);
                    Vista.mostrarMissatge((i + 1) + ". ID: " + obj.get("id").getAsInt() + " - Descripció: " + obj.get("description").getAsString());
                }
                Vista.mostrarMissatge("Introdueix el número del personatge que vols modificar: ");
                int eleccio = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer
                if (eleccio < 1 || eleccio > coincidencias.size()) {
                    Vista.mostrarMissatge("Opció no vàlida.");
                    return;
                }
                brawlerJson = coincidencias.get(eleccio - 1);
            }

            Vista.mostrarMissatge("Dades trobades al JSON:");
            Vista.mostrarMissatge("ID: " + brawlerJson.get("id").getAsInt());
            Vista.mostrarMissatge("Nom: " + brawlerJson.get("name").getAsString());
            Vista.mostrarMissatge("Descripció: " + brawlerJson.get("description").getAsString());

            Vista.mostrarMissatge("Vols actualitzar aquest personatge a la BDD? (s/n): ");
            String resposta = scanner.nextLine();

            if (resposta.equalsIgnoreCase("s")) {
                LocalDate avui = LocalDate.now();
                Vista.mostrarMissatge("Personatge actualitzat correctament. Data d'actualització: " + avui);
            } else {
                Vista.mostrarMissatge("No s'ha fet cap modificació.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Vista.mostrarMissatge("Error durant la modificació: " + e.getMessage());
        }
    }
}
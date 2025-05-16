package Controlador;

import Vista.Vista;
import Model.Constructors.Brawler;
import Model.Constructors.Gadget;
import Model.Constructors.Starpower;
import Model.DAO.*;

import java.util.List;

public class LlistarPersonatges {
    public static void llistarPersonatges() {
        BrawlerDAO brawlerDAO = new BrawlerDAO();
        ClassDAO classDAO = new ClassDAO();
        RarityDAO rarityDAO = new RarityDAO();
        StarpowerDAO starpowerDAO = new StarpowerDAO();
        GadgetDAO gadgetDAO = new GadgetDAO();

        List<Brawler> brawlers = brawlerDAO.llistar();

        if (brawlers.isEmpty()) {
            Vista.mostrarMissatge("⚠️ No s'han trobat brawlers a la base de dades.");
        } else {
            Vista.mostrarMissatge("📋 Llista de Brawlers:");
            for (Brawler b : brawlers) {
                Vista.mostrarMissatge("🚀 Brawler: " + b.getNom());
                Vista.mostrarMissatge("    🆔 ID: " + b.getId());
                Vista.mostrarMissatge("    📝 Descripció: " + b.getDescripcio());

                String classeNom = "Desconeguda";
                if (classDAO.obtenirPerId(b.getIdClasse()) != null) {
                    classeNom = classDAO.obtenirPerId(b.getIdClasse()).getNom();
                }
                Vista.mostrarMissatge("    🎭 Classe: " + classeNom);

                String rarityNom = "Desconeguda";
                if (rarityDAO.obtenirPerId(b.getIdRarity()) != null) {
                    rarityNom = rarityDAO.obtenirPerId(b.getIdRarity()).getNom();
                }
                Vista.mostrarMissatge("    🎲 Rareza: " + rarityNom);

                List<Starpower> starpowers = starpowerDAO.llistarPerBrawler(b.getId());
                Vista.mostrarMissatge("  🌟 Star Powers:");
                if (starpowers.isEmpty()) {
                    Vista.mostrarMissatge("    (No star powers disponibles)");
                } else {
                    for (Starpower s : starpowers) {
                        Vista.mostrarMissatge("    - " + s.getNom() + ": " + s.getDescripcio());
                    }
                }

                List<Gadget> gadgets = gadgetDAO.llistarPerBrawler(b.getId());
                Vista.mostrarMissatge("  🛠️ Gadgets:");
                if (gadgets.isEmpty()) {
                    Vista.mostrarMissatge("    (No gadgets disponibles)");
                } else {
                    for (Gadget g : gadgets) {
                        Vista.mostrarMissatge("    - " + g.getNom() + ": " + g.getDescripcio());
                    }
                }

                Vista.mostrarMissatge("---------------------------");
            }
        }
    }
}
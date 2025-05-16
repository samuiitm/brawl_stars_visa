package Controlador;

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
            System.out.println("No s'han trobat brawlers a la base de dades.");
        } else {
            System.out.println("Llista de Brawlers:");
            for (Brawler b : brawlers) {
                System.out.println("ID: " + b.getId());
                System.out.println("Nom: " + b.getNom());
                System.out.println("Descripció: " + b.getDescripcio());
                System.out.println("Classe: " + (classDAO.obtenirPerId(b.getIdClasse()) != null ? classDAO.obtenirPerId(b.getIdClasse()).getNom() : "Desconeguda"));
                System.out.println("Rareza: " + (rarityDAO.obtenirPerId(b.getIdRarity()) != null ? rarityDAO.obtenirPerId(b.getIdRarity()).getNom() : "Desconeguda"));

                List<Starpower> starpowers = starpowerDAO.llistarPerBrawler(b.getId());
                System.out.println("Starpowers:");
                if (starpowers.isEmpty()) {
                    System.out.println("  - Cap starpower disponible.");
                } else {
                    for (Starpower s : starpowers) {
                        System.out.println("  - " + s.getNom() + ": " + s.getDescripcio());
                    }
                }

                List<Gadget> gadgets = gadgetDAO.llistarPerBrawler(b.getId());
                System.out.println("Gadgets:");
                if (gadgets.isEmpty()) {
                    System.out.println("  - Cap gadget disponible.");
                } else {
                    for (Gadget g : gadgets) {
                        System.out.println("  - " + g.getNom() + ": " + g.getDescripcio());
                    }
                }

                System.out.println("---------------------------");
            }
        }
    }
}
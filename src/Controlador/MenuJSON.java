package Controlador;

import Controlador.JSON.JsonDownloader;
import Controlador.JSON.ModificarPersonatgeJSON;
import Controlador.JSON.MostrarContingutJSON;
import Vista.Vista;
import java.util.Scanner;

public class MenuJSON {
    public static void menuJSON() {
        JsonDownloader.descarregarJson("https://api.brawlify.com/v1/brawlers", "src/resources/brawler.json");

        Vista.mostrarMenuJSON();
        Vista.mostrarMissatge("Escolliu una opcio: ");

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        switch (n) {
            //***Modificar personatge segons el JSON***
            //L’usuari escollirà quin personatge tractar mitjançant el ID que tenim a la BDD i cercarem el ID al JSON.
            //Amb les dades resultants obtingudes del JSON, preguntarem si vol actualitzar-lo o no.
            //Disposarem d’un camp de tipus date que contindrà la data de la darrera actualització que s’ha fet.
            case 1:
                // Llista dels Brawlers i el seu ID per saber quin buscar.
                MostrarContingutJSON.mostrarContingut();
                break;
            case 2:
                // Funcio per buscar aquest personatge al JSON i després preguntar si es vol actualitzar
                // Afegir el  cap data via BBDD amb CURDATE al UPDATE
                ModificarPersonatgeJSON.modificarPersonatge();
                break;
            case 0:
                // Tornar
                System.out.println("Tornant al menú principal...");
                break;
            default:
                System.out.println("Opció no valida");
        }
    }
}
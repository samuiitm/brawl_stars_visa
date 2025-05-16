package Controlador;

import Controlador.JSON.JsonDownloader;

import java.util.Scanner;

public class MenuJSON {
    public static void menuJSON() {
        JsonDownloader.descarregarJson("https://api.brawlify.com/v1/brawlers", "brawlers.json");

        System.out.println("1. Mostrar llista de Brawlers amb ID segons el JSON");
        System.out.println("2. Modificar personatge segons l'endpoint amb ID");
        System.out.println("3. Sortir");
        System.out.print("Escolliu una opcio: ");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        switch (n) {
            //***Modificar personatge segons el JSON***
            //L’usuari escollirà quin personatge tractar mitjançant el ID que tenim a la BDD i cercarem el ID al JSON.
            //Amb les dades resultants obtingudes del JSON, preguntarem si vol actualitzar-lo o no.
            //Disposarem d’un camp de tipus date que contindrà la data de la darrera actualització que s’ha fet.
            case 1:
                // Llista dels Brawlers i el seu ID per saber quin buscar.

                break;
            case 2:
                // Funcio per buscar aquest personatge al JSON i després preguntar si es vol actualitzar
                // Afegir el  cap data via BBDD amb CURDATE al UPDATE

                break;
            case 0:
                // Tornar
                System.out.println("Tornant al menú principal...");
                break;
            default:
                System.out.println("Opcio no valida");
        }
    }
}
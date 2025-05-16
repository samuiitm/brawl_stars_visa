package Controlador;

import Controlador.JSON.MostrarContingut;

import java.util.Scanner;

public class MenuEndpoint {
    public static void menuEndPoint() {
        System.out.println("1. Mostrar llista de Brawlers amb ID segons l'endpoint");
        System.out.println("2. Modificar personatge segons el l'endpoint amb ID");
        System.out.println("3. Sortir");
        System.out.print("Escolliu una opcio: ");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        switch (n) {
            //***Modificar personatge segons l’endpoint***
            //L’usuari escollirà quin personatge tractar mitjançant el ID que tenim a la BDD i executarem l’endpoint corresponent per aquest ID (https://api.brawlstars.com/v1/brawlers/[id]).
            //Amb les dades resultants de l’endpoint preguntarem si vol actualitzar-lo o no.
            //Disposarem d’un camp de tipus date que contindrà la data de la darrera actualització que s’ha fet.
            case 1:
                // Mostrar llista de cada nom i ID dels Brawlers a l'endpoint.
                MostrarContingut.mostrarContingut();
                break;
            case 2:
                // Escollir l'ID per agafar les dades, mostrar-les i preguntar si les volem actualitzar.
                // Afegir el cap data via BBDD amb CURDATE al UPDATE

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

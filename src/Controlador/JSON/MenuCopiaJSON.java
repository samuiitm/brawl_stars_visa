package Controlador.JSON;

import Controlador.JSON.Copies.CopiaParcialJSON;
import Controlador.JSON.Copies.CopiaTotalJSON;
import Vista.Vista;

import java.util.Scanner;

public class MenuCopiaJSON {
    public static void menuCopia() {
        Vista.mostrarMenuJSON();
        Vista.mostrarMissatge("Escolliu una opcio: ");

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        switch (n) {
            case 1:
                // Funció per fer la copia parcial de les dades del JSON
                CopiaParcialJSON.copiaParcial();
                break;
            case 2:
                // Funcio per fer la còpia total de les dades del JSON
                CopiaTotalJSON.copiaTotal();
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

package Controlador.Endpoint;

import Controlador.Endpoint.Copies.CopiaParcialEndpoint;
import Controlador.Endpoint.Copies.CopiaTotalEndpoint;
import Controlador.MenuEndpoint;
import Vista.Vista;

import java.util.Scanner;

public class MenuCopiaEndpoint {
    public static void menuCopia() {
        Vista.mostrarMenuCopiaEndpoint();
        Vista.mostrarMissatge("Escolliu una opcio: ");

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        switch (n) {
            case 1:
                // Funció per fer la copia parcial de les dades del Endpoint
                CopiaParcialEndpoint.copiaParcial();
                break;
            case 2:
                // Funcio per fer la còpia total de les dades del Endpoint
                CopiaTotalEndpoint.copiaTotal();
                break;
            case 0:
                // Tornar
                MenuEndpoint.menuEndPoint();
                break;
            default:
                System.out.println("Opció no valida");
        }
    }
}
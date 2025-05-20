import Controlador.ExistenBrawlers;
import Controlador.LlistarPersonatges;
import Controlador.MenuEndpoint;
import Controlador.MenuJSON;
import Vista.Vista;

import java.util.Scanner;

public class
Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcio = -1;

        do {
            Vista.mostrarMenuPrincipal();

            String input = scanner.nextLine();

            try {
                opcio = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                Vista.mostrarMissatge("L'opció introduida no es vàlida. Si us plau, introdueix una opció del menú");
                continue;
            }

            switch (opcio) {
                // CAS 1: LLISTAR PERSONATGES
                case 1:
                    LlistarPersonatges.llistarPersonatges();
                    break;

                // CAS 2: MENÚ ADMINISTRAR SEGONS ENDPOINT
                case 2:
                    MenuEndpoint.menuEndPoint();
                    break;

                // CAS 3: MENÚ ADMINISTRAR SEGONS JSON
                case 3:
                    MenuJSON.menuJSON();
                    break;

                // CAS 4: COMPROVACIÓ BRAWLER
                case 4:
                    // Aquí es pot afegir la funcionalitat de comprovació de brawlers
                    ExistenBrawlers.existenBrawlers();
                    break;

                // SORTIR DEL PROGRAMA
                case 0:
                    Vista.mostrarMissatge("Sortint del programa...");
                    break;

                // CAS DEFAULT
                default:
                    Vista.mostrarMissatge("L'opció introduida no es vàlida. Si us plau, introdueix una opció del menú");
                    break;
            }
        } while (opcio != 0);
    }
}
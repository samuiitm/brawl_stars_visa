package Vista;

public class Vista {
    public static void mostrarMissatge(String missatge) {
        System.out.println(missatge);
    }
    public static void mostrarMissatgeFormat(String format, Object... args) {
        System.out.printf(format, args);
    }
    
    public static void mostrarMenuPrincipal() {
        System.out.println("--------------------------------\n" +
                           "------- BRAWL STARS VISA -------\n" +
                           "--------------------------------\n" +
                           "1. Llistar personatges\n" +
                           "2. Administrar segons endpoint\n" +
                           "3. Administrar segons JSON\n" +
                           "4. Reinicialitzar base de dades\n" +
                           "0. Sortir");
    }

    public static void mostrarMenuEndpoint() {
        System.out.println("--------------------------------\n" +
                           "---- GESTIÓ SEGONS ENDPOINT ----\n" +
                           "--------------------------------\n" +
                           "1. Mostrar contingut\n" +
                           "2. Modificar personatge\n" +
                           "3. Còpies\n" +
                           "0. Sortir");
    }

    public static void mostrarMenuJSON() {
        System.out.println("--------------------------------\n" +
                           "------ GESTIÓ SEGONS JSON ------\n" +
                           "--------------------------------\n" +
                           "1. Mostrar contingut\n" +
                           "2. Modificar personatge\n" +
                           "3. Còpies\n" +
                           "0. Sortir");
    }
}

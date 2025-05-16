package Controlador.JSON;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonDownloader {
    public static void descarregarJson(String endpoint, String nomFitxer) {
        try {
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linia;

            while ((linia = in.readLine()) != null) {
                resposta.append(linia);
            }

            in.close();
            conn.disconnect();

            try (FileWriter fitxer = new FileWriter(nomFitxer)) {
                fitxer.write(resposta.toString());
            }

            System.out.println("JSON desat a: " + nomFitxer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
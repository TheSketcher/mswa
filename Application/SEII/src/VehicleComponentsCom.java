
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

    public class VehicleComponentsCom {
        private static final String API_URL = "/vehicles/components";
        private static final String API_BASE_URL = "https://vehicle-data.dh.dtr0cks.de/api/v1";

        public static void sendPostRequest() throws Exception {
            // Request-Body als String definieren
            String requestBody = "{\"model\": \"983.493\", \"codes\": \"A1A, B2B, C3C\", \"buildDate\": \"2023-03-24T14:13:27.741Z\"}";


            // Verbindung zur API-URL herstellen
            URL url = new URL(API_BASE_URL + API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Request-Body an API-URL senden
            OutputStream os = conn.getOutputStream();
            os.write(requestBody.getBytes());
            os.flush();

            // Antwort von API-URL lesen
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            // Verbindung schließen
            conn.disconnect();
        }
    }


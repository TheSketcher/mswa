import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class LogisticCom {
    public static final String API_DIMENSIONS_URL = "/vehicle/dimensions";
    public static final String API_WEIGHTS_URL = "/vehicle/weights";
    public static final String API_BASE_URL = "https://logistics.dh.dtr0cks.de/api/v1";

    public static void sendPostRequest(String apiUrl) throws Exception {

        // Create a JSON object with Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode json = objectMapper.createObjectNode();
        json.put("model", "983.493");
        json.put("codes", "A1A, B2B, C3C");
        json.put("buildDate", "2023-03-24T15:30:24.656Z");

        // Create the URL object and open the connection
        URL url = new URL(API_BASE_URL + apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Send the JSON object to the API
        OutputStream os = conn.getOutputStream();
        byte[] jsonBytes = json.toString().getBytes("utf-8");
        os.write(jsonBytes, 0, jsonBytes.length);
        os.flush();

        // Read the response from the API
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        String output;
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        // Close the connection
        conn.disconnect();
    }
}
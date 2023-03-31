package main.java.vehicle;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import main.java.print.VehicleConfigGenerator;
import main.java.print.VehicleConfiguration;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.List;

public class VehicleDataClient {

    public static void main (String[] args) {
        VehicleConfigGenerator gen = new VehicleConfigGenerator();
        VehicleConfiguration vehicle = gen.generateVehicle(false).getConfig();
        System.out.println(new VehicleDataClient().getVehicleComponents(vehicle));
    }

    public List<VehicleComponent> getVehicleComponents(final VehicleConfiguration vehicle){
        // Anfrage serialisieren
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        PipedOutputStream out = new PipedOutputStream();
        PipedInputStream in;
        try {
            in = new PipedInputStream(out);
            objectMapper.writeValue(out, vehicle);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Request failed - failed to create request!");
        }

        // Request vorbereiten
        Request request = Request.post("https://vehicle-data.dh.dtr0cks.de/api/v1/vehicles/components")
                .bodyStream(in)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json");

        // Request senden
        InputStream body;
        try {
            Response response = request.execute();
            body = response.returnContent().asStream();
        } catch (IOException e) {
            throw new RuntimeException("Request failed: " + e.getMessage(), e);
        }

        // Response deserialisieren
        if (null == body) {
            throw new RuntimeException("Request failed - missing response!");
        }
        try {
            return objectMapper.readValue(body, new TypeReference<List<VehicleComponent>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Request failed - cannot parse response!");
        }
    }
}

package de.dhbw.ka.se2.plugin.logistics;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import de.dhbw.ka.se2.application.print.VehicleConfigGenerator;
import de.dhbw.ka.se2.domain.logistics.VehicleWeights;
import de.dhbw.ka.se2.domain.print.VehicleConfiguration;

public class LogisticsClient {
	public static void main(String[] args) {
		VehicleConfigGenerator gen = new VehicleConfigGenerator();
		VehicleConfiguration vehicle = gen.generateVehicle(false);
		System.out.println(new LogisticsClient().getWeights(vehicle));
	}

	public VehicleWeights getWeights(final VehicleConfiguration vehicle) {
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
		Request request = Request.post("https://logistics.dh.dtr0cks.de/api/v1/vehicle/weights")
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
			return objectMapper.readValue(body, VehicleWeights.class);
		} catch (IOException e) {
			throw new RuntimeException("Request failed - cannot parse response!");
		}
	}
}
